use('db_books');

let books =  db.getCollection('books');

const fs = require('fs');
let rawdata = fs.readFileSync('books3.json');

let data = JSON.parse(rawdata);
console.log('Total de livros carregados do arquivo: ' + data.length);

console.log('Inserindo Documentos no cloud atlas...');
books.insertMany(data);
console.log('Total Documentos inseridos: ' + books.countDocuments() + ' livros.');