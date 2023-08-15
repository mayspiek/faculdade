from datetime import datetime
import locale
from fastapi import FastAPI
import pytz, json

app = FastAPI()

@app.get("/hora")
def horaCerta():
    return {
        "hora" : datetime.now(),
        "local" : "Brasil"
        }

@app.get("/upper/{nome}")
def upper(nome):
    return {
        "nome" : nome,
        "upper" : nome.upper()
        }

@app.get("/numero/{numero}")
def parOuImpar(numero):
    if(int(numero) % 2 == 0):
        return {
            "numero" : numero,
            "parOuImpar" : "par"
        }

    else:
        return {
            "numero" : numero,
            "parOuimpar" : "impar"
        }
@app.get("/horario/{cidade}")
def horario(cidade):
    if(cidade == "SaoPaulo"):
        return {
            "cidade" : cidade,
            "horario" : datetime.now().strftime("%H:%M:%S")
        }
    elif(cidade == "Londres"):
        tz_London = pytz.timezone('Europe/London')
        return {
            "cidade" : cidade,
            "horario" : datetime.now(tz_London).strftime("%H:%M:%S")
        }
    elif(cidade == "Tokyo"):
        tz_Tokyo = pytz.timezone('Asia/Tokyo')
        return {
            "cidade" : cidade,
            "horario" : datetime.now(tz_Tokyo).strftime("%H:%M:%S")
        }
    else:
        return {
            "cidade" : cidade,
            "horario" : "Cidade não cadastrada"
        }
    
@app.get("/diaSemana/{date}")
def pegaDiaSemana(date: str):
        locale.setlocale(locale.LC_TIME, 'pt_BR.utf8')
        # Convertendo a string da data para um objeto datetime
        date_obj = datetime.strptime(date, '%d-%m-%Y')

        # Obtendo o nome do dia da semana
        diaSemana = date_obj.strftime('%A')

        return {"dia-da-semana": diaSemana}


#iniciar o wervidor web
#uvicorn main:app --reload
# print(horaCerta())