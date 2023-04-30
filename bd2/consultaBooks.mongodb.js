use("db_books");

let books = db.getCollection("books");

// let res = books.countDocuments();

// res = books.find(
//     { "pageCount" : { $gt : 1092 } }
// )
// console.log(JSON.stringify(res.toArray(), null, 2));

// res = books.distinct("categories");

// console.log(JSON.stringify(res, null, 2));

// res = books.distinct("status");
// console.log(JSON.stringify(res, null, 2));

let res = books.find(
    {},
    { "title": 1, "pageCount": 1, "_id": 0 })
    .sort({ "pageCount": -1 })
    .limit(5);
console.log(JSON.stringify(res.toArray(), null, 2));

res = books.find({ "longDescription": { $regex : 'COBOL' } },
    { "title": 1, "pageCount": 1, "_id": 0, "longDescription": 1 })
    .sort({ "pageCount": -1 });
console.log(JSON.stringify(res.toArray(), null, 2));
res = books.find({ "longDescription": { $regex : 'Debugger' } },
    { "title": 1, "pageCount": 1, "_id": 0, "longDescription": 1 })
    .sort({ "pageCount": -1 });
console.log(JSON.stringify(res.toArray(), null, 2));