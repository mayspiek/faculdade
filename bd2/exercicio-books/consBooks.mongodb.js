use('db_books')
let books = db.getCollection('books');

const Queries = {
    fields: { "title": 1, 'pageCount': 1, _id: 0 },

    q1: () => {
        res = books.find(
            { "pageCount": { $gt: 1092 } },
            Queries.fields
        )

        console.log(JSON.stringify(res.toArray(), null, 2));
        //console.log('Resultados encontrados: ' + res.countDocuments());
    },
    run: () => {
        Queries.q1();
    }

}

Queries.run();