// queries.mongodb.js
use("db_books");
let books = db.getCollection("books");
const Queries = {
    // define uma propriedade objeto Queries
    // campos usados para selecionar o resultado da query
    fields: {
        "title": 1, "publishedDate": 1, 
        "pageCount": 1, "_id": 0
    },
    q1: () => {
        // 1)Livros com mais de 1092 pagecount (paginas)
        let res = books.find(
            { "pageCount" : { $gt :1092 } },
            Queries.fields
        )
        Queries.printResult(res);
    },
    q1Agg: () => {
        // USANDO AGGREGATE
        // 1)Livros com mais de 1092 pagecount (paginas)
        let res = books.aggregate([
            {
                $match: {
                    "pageCount" : { $gt :1092 }
                }
            },
            {
                $project: {
                    "title": 1, "publishedDate": 1, 
                    "pageCount": 1, "_id": 0
                }
            }
        ])
        Queries.printResult(res);
    },
    q7: () => {
        // 7) Livros anteriores a 1995
        let res = books.find({
            "publishedDate.$date" : {
                $lt: "1995-01-01"
            }
        }, Queries.fields)
        Queries.printResult(res)
    },
    printResult: (res) => {
        let data = res.toArray();
        console.log(JSON.stringify(data, null, 2));
        console.log(
            "Resultados encontrados: " + data.length
        )
    },
    q13: () => {
        // 13) Livros com 3 ou mais autores
        let res = db.books.find(
            {
                $expr:{
                    $gte:[{ 
                        $size:"$authors"
                    }, 3]
                }
            }
        );
        Queries.printResult(res) // 83
    },
    run: () => {
        // Queries.q1();
        // Queries.q7();
        // Queries.q13();
        Queries.q1Agg();
    }
}

Queries.run();




