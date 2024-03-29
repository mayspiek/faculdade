import json
from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def index():
    with open('books.json', 'r') as f:
        books = json.load(f)
        return books

@app.get("/book/{id}")
def busca_id(id: int):
    with open('books.json', 'r') as f:
        books = json.load(f)
        for book in books:
            if book['id'] == id:
                return book
        return {"erro": "livro não encontrado"}
    
# 2. Busca por ano
@app.get("/book/ano/{ano}")
def busca_ano(ano: int):
    livros = []
    with open('books.json', 'r') as f:
        books = json.load(f)
        for book in books:
            if book['year'] == ano:
                livros.append(book)
    if livros == [] or livros == None:
        return {"erro": "livro não encontrado"}
    else:
        return livros
    
# 3. Busca por título
@app.get("/book/titulo/{titulo}")
def busca_titulo(titulo: str):
    with open('books.json', 'r') as f:
        books = json.load(f)
        livros = []
        for book in books:
            if titulo.lower() in book['title'].lower():
                print("TITULO: " + titulo)
                livros.append(book)
    if livros == [] or livros == None:
        return {"erro": "livro não encontrado"}
    else:
        return livros

# 4. Busca por authors
@app.get("/book/autor/{autor}")
def busca_autor(autor: str):
    with open('books.json') as f:
        books = json.load(f)
        livros = []
        for book in books:
            for autores in book['authors']:
                if autor.lower() in autores.lower():
                    livros.append(book)
        if livros == []:
            return {"erro": "livro não encontrado"}
        else:
            return livros