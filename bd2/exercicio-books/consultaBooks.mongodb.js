use("db_books");

let books = db.getCollection("books");

let res = books.countDocuments();
//1) Livros com mais de 1092 (paginas)
res = books.find(
    { "pageCount": { $gt: 1092 } }
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

//5) Encontrar livros sobre COBOL
res = books.find({ "longDescription": { $regex: 'COBOL' } },
    { "title": 1, "pageCount": 1, "_id": 0, "longDescription": 1 })
    .sort({ "pageCount": -1 });
console.log(JSON.stringify(res.toArray(), null, 2));

// 6) Livros que contém no título a palavra "Development" com data de publicação a partir de 2000:
res = book.find(
    {
        $and: [
            { "title": { $regex: /development/i } },
            {
                "publishedDate.$date":
                    { $gt: '2000-01-01T00:00:00Z' }
            }
        ]
    },
    { "title": 1, "_id": 0, "publishedDate": 1 },
)
console.log('Livros que contém no título a palavra "Development" e foram publicados depois de 2000: ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 7) Livros publicados anteriores a 1995
res = book.find(
    {
        "publishedDate.$date":
            { $lt: '1995-01-01T00:00:00Z' }
    },
    { "title": 1, "_id": 0, "publishedDate": 1 },
);
console.log('Livros que foram publicados antes de 1995: ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 8) Total de livros publicados no ano 2000
res = book.find({
    "publishedDate.$date": {
        $gt: '2000-01-01T00:00:00Z',
        $lt: '2001-01-01T00:00:00Z'
    }
}, { "title": 1, "_id": 0 }
).count();
console.log('Quantidade de livros que foram publicados no ano 2000: ' + JSON.stringify(res, null, 2) + '\n');

// 9) Qual o livro publicado mais recentemente?
res = book.find(
    {},
    { "title": 1, "_id": 0, "publishedDate": 1 }
).sort(
    // sort ordena a consulta de acordo com o valor passado no parâmetro
    { "publishedDate": -1 }
    // o "-1" quer dizer que é na ordem decrescente
).limit(1);
// a função limit limita a quantidade de resultados da consulta
console.log('O livro publicado mais recente é o: ' + JSON.stringify(res.toArray(), null, 2) + '\n');


//10) Contém a palavra "Debugger" na descrição
res = books.find({ "longDescription": { $regex: 'Debugger' } },
    { "title": 1, "pageCount": 1, "_id": 0, "longDescription": 1 })
    .sort({ "pageCount": -1 });
console.log(JSON.stringify(res.toArray(), null, 2));

// 11) Qual o livro com o menor número de páginas?
res = book.find(
    {},
    { "title": 1, "_id": 0, "pageCount": 1 }
).sort(
    { "pageCount": 1 }
    // ordem crescente é "1"
).limit(1);
console.log('Livro que contém menos páginas: ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 12) Qual o livro publicado mais antigo?
res = book.find(
    { "publishedDate": { "$exists": true } },
    // filtro para que mostre só os resultados que tem esse atributo
    { "title": 1, "_id": 0, "publishedDate": 1 }
).sort(
    { "publishedDate": 1 }
).limit(1);
console.log('Livro mais antigo: ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 13) Livros com 3 ou mais autores
res = book.find(
    {
        $expr: {
            // o operador "$expr" permite combinar outros operadores para executar consultas mais complexas.
            $gt: [{ $size: "$authors" }, 3]
        }
    },
    { "title": 1, "_id": 0, "authors": 1 }
);
console.log('Livros com mais de 3 autores: ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 14) Contem o termo "Java" e foi publicado a partir de 2013.
res = book.find(
    {
        $and: [
            {
                $or: [
                    { "title": { $regex: /java/i } },
                    { "longDescription": { $regex: /java/i } },
                    { "shortDescription": { $regex: /java/i } },
                    { "categories": { $regex: /java/i } }
                ]
            },
            {
                "publishedDate.$date": { $gt: '2013-01-01T00:00:00Z' }
            }
        ]
    },
    { "title": 1, "_id": 0, "publishedDate": 1 }
);
console.log('Livros que contêm o termo "Java" e foram publicados a partir de 2013: ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 15) Livros que contém a categoria "Networking"
res = book.find(
    { "categories": { $regex: /networking/i } },
    { "title": 1, "_id": 0, "categories": 1 }
);
console.log('Livros que contêm o termo "Networking": ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 16) Livros que contém a categoria "Networking" publicados depois do ano 2000:
res = book.find(
    {
        $and: [
            { "publishedDate.$date": { $gt: '2000-01-01T00:00:00Z' } },
            { "categories": { $regex: /networking/i } },
        ]
    },
    { "title": 1, "_id": 0, "publishedDate": 1, "categories": 1 }
);
console.log('Livros que contêm o termo "Networking": ' + JSON.stringify(res.toArray(), null, 2) + '\n');

// 17) Total de livros na categoria networking
res = book.find(
    { "categories": { $regex: /networking/i } },
    { "title": 1, "_id": 0 }
).count();
console.log('Quantidade total de livros na categoria networking: ' + JSON.stringify(res, null, 2) + '\n');

// 18) Sumarizar o total de livros por categoria
res = book.aggregate([
    { $unwind: "$categories" },
    // Remove valores duplicados em um array.
    {
        $group: {
            // agrupa as categorias
            _id: "$categories",
            //  indica que os documentos serão agrupados por categoria.
            count: { $sum: 1 }
            // esse comando vai contar o númento de documentos criado em cada '_id'
        }
    },
    {
        $project: {
            // o project é usada para especificar quais campos devem ser selecionados e retornados na consulta, permitindo também a criação de novos campos calculados ou renomeação de campos existentes.
            _id: 0,
            category: "$_id",
            count: 1
        }
    }
]);
console.log('Quantidade total de livros na categoria networking: ' + JSON.stringify(res.toArray(), null, 2) + '\n');
