use("db_books");

let books = db.getCollection("books");

let res = books.countDocuments();
//1) Livros com mais de 1092 (paginas)
res = books.find(
    { "pageCount" : { $gt : 1092 } }
)
console.log(JSON.stringify(res.toArray(), null, 2));

//2) Listar todas as categorias existentes
res = books.distinct("categories");
console.log(JSON.stringify(res, null, 2));

//3) Listar os tipos de status
res = books.distinct("status");
console.log(JSON.stringify(res, null, 2));

//4) Listar os 5 livros com o maior número de páginas:
res = books.find(
    {},
    { "title": 1, "pageCount": 1, "_id": 0 }) //atributos com 1 sao mostrado/buscados e com 0, não
    .sort({ "pageCount": -1 }) // mostra em ordem decrescente
    .limit(5); // limita a busca a 5 obj
console.log(JSON.stringify(res.toArray(), null, 2));

res = books.find({ "longDescription": { $regex : 'COBOL' } },
    { "title": 1, "pageCount": 1, "_id": 0, "longDescription": 1 })
    .sort({ "pageCount": -1 });
console.log(JSON.stringify(res.toArray(), null, 2));
res = books.find({ "longDescription": { $regex : 'Debugger' } },
    { "title": 1, "pageCount": 1, "_id": 0, "longDescription": 1 })
    .sort({ "pageCount": -1 });
console.log(JSON.stringify(res.toArray(), null, 2));