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


@app.route("/books", methods=["GET"])
def books_index():
    col = get_collection()
    # res = col.find({"title": "iPhone in Action"})
    res = col.find(
        {"title": {"$regex": "iPhone"}}
    )
    books = list(res)
    return render_template("books_list.html", books=books)

@app.route("/questoes/index", methods=["GET"])
def questoes_index():
    col = get_collection()
    res = col.find()
    questoes = list(res)
    return render_template("questoes_list.html", questoes=questoes)


# ROTA DO FORM
@app.route("/questoes/novo", methods=["GET"])
def questoes_novo():
    return render_template("questoes.html", action='inserir', questoes=None)

# INSERT = insertOne
@app.route("/questoes/inserir", methods=["POST"])
def inserir_questoes():
    col = get_collection()

    alt = ['a', 'b', 'c', 'd', 'e']

    numQuestao = request.form.get('numQuest')
    enunciado = request.form.get('enunciado')
    topicos_str = request.form.get('topico')
    alternativas_str = request.form.get('alternativas')

    # CONFERE SE FOI INSERIDO DADOS E FAZ O SPLIT
    if topicos_str and alternativas_str is not None: 
        topicos_list = topicos_str.split(',')
        alternativas_list = alternativas_str.split(',')
    else:
        alternativas_list = []
        topicos_list = []

    resposta = request.form.get('resposta')

    alternativas = []
    for i, alt in enumerate(alternativas_list):
        alternativa = {"alt": alt[i], "op": alternativas}
        alternativas.append(alternativa)


    questao = {
        "numQuest": numQuestao,
        "enunciado": enunciado,
        "topicos": topicos_list,
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
@app.route("/questoes/atualizar", methods=["POST"])
def questoes_atualizar():
    collection = get_collection()


if __name__ == "__main__":
    app.run(debug=True)
