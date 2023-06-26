import random
from bson import ObjectId
from flask import (
    Flask, render_template, request, redirect, url_for, flash
)
from pymongo import MongoClient
from dotenv import load_dotenv
import os
load_dotenv()

app = Flask(__name__)
app.config['SECRET_KEY'] = 'asdf0001'

cluster_url = os.getenv("CLUSTER_URL")
cluster = MongoClient(cluster_url)


def get_collection():
    db = cluster["db_questoes"]
    col = db["concurso"]
    return col


# ==================================
# ROTAS
# ==================================


@app.route("/")
def index():
    return render_template('index.html')


@app.route("/questoes/index", methods=["GET"])
def questoes_index():
    col = get_collection()
    res = col.find().sort('numQuest')
    questoes = list(res)

    return render_template("questoes_list.html", questoes=questoes)


# ROTA DO FORM
@app.route("/questoes/novo", methods=["GET"])
def questoes_novo():
    return render_template("questoes.html", action='inserir', questao=None)

@app.route("/questoes/editar/<id>", methods=["GET"])
def questoes_editar(id):
    collection = get_collection()
    objId = ObjectId(id)
    questoes = list(collection.find({"_id": objId}))


    for questao in questoes:
        ### ALTERNATIVAS ###
        # Obtenha a lista de alternativas da questão
        alternativas = questao['alternativas']

        # Extraia apenas os valores 'alt' das alternativas em uma lista separada
        alternativas_vals = [alt['op'] for alt in alternativas]

        # Junte os valores das alternativas em uma única string, separados por vírgula
        alternativas_str = ', '.join(alternativas_vals)

        # Adicione a string das alternativas de volta à questão
        questao['alternativas'] = alternativas_str

        ### TOPICOS ###

        # Obtenha a lista de tópicos da questão
        topicos = questao['topico']

        # Junte os tópicos em uma única string, separados por vírgula
        topicos_str = ', '.join(topicos)

        # Adicione a string dos tópicos de volta à questão
        questao['topico'] = topicos_str

        
    return render_template("questoes.html", action='edit', questao=questao)

# INSERT = insertOne
@app.route("/questoes/inserir", methods=["POST"])
def inserir_questoes():
    col = get_collection()
    # PEGA O VALOR INSERIDO NO FORM
    numQuestao = request.form.get('numQuest')
    enunciado = request.form.get('enunciado')
    topicos_str = request.form.get('topico')
    alternativas_str = request.form.get('alternativas')

    # CONFERE SE FOI INSERIDO ALGO E SÓ AI FAZ O SPLIT
    if topicos_str and alternativas_str is not None: 
        topicos_list = topicos_str.split(',')
        alternativas_list = alternativas_str.split(',')
    else:
        alternativas_list = []
        topicos_list = []

    resposta = int(request.form.get('resposta'))

    # FAZ UM ARRAY DE ALTERNATIVAS POIS SÃO VÁRIAS ALTERNATIVAS
    alt = ['a', 'b', 'c', 'd', 'e']

    alternativas = []
    for i, op in enumerate(alternativas_list):
        alternativa = {"alt": alt[i], "op": op}
        alternativas.append(alternativa)


    questao = {
        "numQuest": numQuestao,
        "enunciado": enunciado,
        "topico": topicos_list,
        "alternativas": alternativas,
        "resposta": resposta
    }

    result = col.insert_one(questao)

    if result.acknowledged:
        flash(f'Questão inserida com sucesso!', 'success')
    else: 
        flash(f'Erro ao inserir questão!', 'danger')
    return redirect(url_for('questoes_index'))

# REMOVE = delete
@app.route("/questoes/deletar/<id>", methods=["GET"])
def questoes_delete(id):
    col = get_collection()
    objId = ObjectId(id)
    result = col.delete_one({"_id": objId})

    if result.deleted_count > 0:
        flash(f'Questão excluída com sucesso!', 'success')
    else:
        flash(f'Não foi possível excluir a questão', 'warning')
    return redirect(url_for('questoes_index'))

# UPDATE = updateOne
@app.route("/questoes/edit", methods=["POST"])
def questoes_edit():
    collection = get_collection()
    
    id = request.form.get('id')
    objId = ObjectId(id)

    numQuest = request.form.get('numQuest')
    enunciado = request.form.get('enunciado')
    topicos_str = request.form.get('topico')
    alternativas_str = request.form.get('alternativas')
    resposta = request.form.get('resposta')

    alternativas = []
    if topicos_str and alternativas_str is not None and alternativas_str and topicos_str != "": 
        topicos = topicos_str.split(',')
        alternativas_list = alternativas_str.split(',')

    alt = ['a', 'b', 'c', 'd', 'e']

     # Itera sobre a lista alternativas_str e criar as alternativas
    for i, alt_str in enumerate(alternativas_list):
        alternativa = {"alt": alt[i], "op": alt_str.strip()}
        alternativas.append(alternativa)


    questao = {
        '$set': {
            "numQuest": numQuest,
            "enunciado": enunciado,
            "topico": topicos,
            "alternativas": alternativas,
            "resposta": int(resposta)
        }
    }

    result = collection.update_one({"_id": objId}, questao)
    
    print(result)
    if result.modified_count > 0:
        print(f'Questão alterada com sucesso!', 'success')
    else:
        print(f"Erro ao alterar questão.", 'danger')


    return redirect(url_for('questoes_index'))



if __name__ == "__main__":
    app.run(debug=True)
