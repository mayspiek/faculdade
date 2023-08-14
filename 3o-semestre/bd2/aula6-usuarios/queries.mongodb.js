use("db_books");

let book = db.getCollection("books");

// 1) Livros com mais de 1092 (paginas)
let res1 = book.find(
    //filtros - condições
    { "pageCount" : { $gt : 1092 } },
    { "title" : 1, "pageCount" : 1, "_id" : 0 },
)

// transforma num vetor
console.log('Os livros que possuem mais de 1092 páginas são: ' + JSON.stringify(res1.toArray(), null, 2) + '\n');

// 2) Listar todas as categorias existentes
let res2 = book.distinct("categories");
console.log('As categorias de livros encontradas são: ' + JSON.stringify(res2, null, 2) + '\n');
// esse não precisa de toArray porque o distinct ja retorna um array

// 3) Listar os tipos de status
let res3 = book.distinct("status");
console.log('Os tipos de status são: ' + JSON.stringify(res3, null, 2) + '\n');

// 4) Listar os 5 livros com o maior número de páginas:
let res4 = book.find(
    {}, // o primeiro objeto é o filtro
    { "title" : 1, "_id" : 0 }, // o segundo é o que quero que mostre
).sort(
    { "pageCount" : -1 },
).limit(5);
console.log('Os 5 livros com maior número de páginas são:' + JSON.stringify(res4.toArray(), null, 2) + '\n');

// 5) Encontrar livros sobre COBOL
let res5 = book.find(
    { "longDescription" : { $regex : /cobol/i } },
    { "title" : 1, "_id" : 0 },
);
console.log('Livros sobre COBOL:' + JSON.stringify(res5.toArray(), null, 2) + '\n');

// 6) Livros que contém no título a palavra "Development" com data de publicação a partir de 2000:
let res6 = book.find(
    { $and : [
        { "title" : { $regex : /development/i } }, 
        { "publishedDate.$date" : 
            { $gt:'2000-01-01T00:00:00Z' } 
        }
    ] },
    { "title" : 1, "_id" : 0, "publishedDate" : 1 },
)
console.log('Livros que contém no título a palavra "Development" e foram publicados depois de 2000: ' + JSON.stringify(res6.toArray(), null, 2) + '\n');

// 7) Livros publicados anteriores a 1995
let res7 = book.find(
    { "publishedDate.$date" : 
        { $lt:'1995-01-01T00:00:00Z' } 
    },
    { "title" : 1, "_id" : 0, "publishedDate" : 1 },
);
console.log('Livros que foram publicados antes de 1995: ' + JSON.stringify(res7.toArray(), null, 2) + '\n');

// 8) Total de livros publicados no ano 2000
let res8 = book.find( {
    "publishedDate.$date" : {
            $gt : '2000-01-01T00:00:00Z',
            $lt : '2001-01-01T00:00:00Z'
        } 
    }, { "title" : 1, "_id" : 0 } 
).count();
console.log('Quantidade de livros que foram publicados no ano 2000: ' + JSON.stringify(res8, null, 2) + '\n');

// 9) Qual o livro publicado mais recentemente?
let res9 = book.find(
    { },
    { "title" : 1, "_id" : 0, "publishedDate" : 1}
).sort(
    // sort ordena a consulta de acordo com o valor passado no parâmetro
    { "publishedDate" : -1 }
    // o "-1" quer dizer que é na ordem decrescente
).limit(1);
// a função limit limita a quantidade de resultados da consulta
console.log('O livro publicado mais recente é o: ' + JSON.stringify(res9.toArray(), null, 2 ) + '\n' );

// 10) Contém a palavra "Debugger" na descrição
let res10 = book.find(
    { "longDescription" : { $regex : /debugger/i } },
    { "title" : 1, "_id" : 0 }
);
console.log('Livros que contém a palavra "Debugger" na descrição: ' + JSON.stringify(res10.toArray(), null, 2) + '\n' );

// 11) Qual o livro com o menor número de páginas?
let res11 = book.find(
    {},
    { "title" : 1, "_id" : 0, "pageCount" : 1 }
).sort(
    { "pageCount" : 1 }
    // ordem crescente é "1"
).limit(1);
console.log('Livro que contém menos páginas: ' + JSON.stringify(res11.toArray(), null, 2) + '\n' );

// 12) Qual o livro publicado mais antigo?
let res12 = book.find(
    { "publishedDate": { "$exists": true } },
    // filtro para que mostre só os resultados que tem esse atributo
    { "title" : 1, "_id" : 0, "publishedDate" : 1 }
).sort(
    { "publishedDate" : 1 }
).limit(1);
console.log('Livro mais antigo: ' + JSON.stringify(res12.toArray(), null, 2) + '\n' );

// 13) Livros com 3 ou mais autores
let res13 = book.find(
    {  $expr : {
        // o operador "$expr" permite combinar outros operadores para executar consultas mais complexas.
        $gt : [ { $size : "$authors" } , 3 ] } 
    },     
    { "title" : 1, "_id" : 0, "authors" : 1}
);
console.log('Livros com mais de 3 autores: ' + JSON.stringify(res13.toArray(), null, 2) + '\n');

// 14) Contem o termo "Java" e foi publicado a partir de 2013.
let res14 = book.find(
    { $and : [
        { $or : [
            { "title" : { $regex : /java/i } },
            { "longDescription" : { $regex : /java/i } },
            { "shortDescription" : { $regex : /java/i } },
            { "categories" : { $regex : /java/i } }
        ] },
        { "publishedDate.$date" : { $gt : '2013-01-01T00:00:00Z' } 
        }
    ] },
    { "title" : 1, "_id" : 0, "publishedDate" : 1 }
);
console.log('Livros que contêm o termo "Java" e foram publicados a partir de 2013: ' + JSON.stringify(res14.toArray(), null, 2) + '\n');

// 15) Livros que contém a categoria "Networking"
let res15 = book.find(
    { "categories" : { $regex : /networking/i } },
    { "title" : 1, "_id" : 0, "categories" : 1 }
);
console.log('Livros que contêm o termo "Networking": ' + JSON.stringify(res15.toArray(), null, 2) + '\n');

// 16) Livros que contém a categoria "Networking" publicados depois do ano 2000:
let res16 = book.find(
    { $and : [
        { "publishedDate.$date" : { $gt : '2000-01-01T00:00:00Z' } },
        { "categories" : { $regex : /networking/i } }, 
    ] },
    { "title" : 1, "_id" : 0, "publishedDate" : 1, "categories" : 1 }
);
console.log('Livros que contêm o termo "Networking": ' + JSON.stringify(res16.toArray(), null, 2) + '\n');

// 17) Total de livros na categoria networking?
let res17 = book.find(
    { "categories" : { $regex : /networking/i } }, 
    { "title" : 1, "_id" : 0 }
).count();
console.log('Quantidade total de livros na categoria networking: ' + JSON.stringify(res17, null, 2) + '\n');

// 18) Sumarizar o total de livros por categoria
let res18 = book.aggregate( [
    { $unwind : "$categories" },
    // Remove valores duplicados em um array.
    { $group: {
    // agrupa as categorias
        _id: "$categories",
        //  indica que os documentos serão agrupados por categoria.
        count: { $sum: 1 }
        // essa linha vai contar o númento de documentos criado em cada '_id'
    }   },
    { $project: {
        // o project é usada para especificar quais campos devem ser selecionados e retornados na consulta, permitindo também a criação de novos campos calculados ou renomeação de campos existentes.
          _id: 0,
          category: "$_id",
          count: 1
        }
    }
] );
console.log('Quantidade total de livros na categoria networking: ' + JSON.stringify(res18.toArray(), null, 2) + '\n');

