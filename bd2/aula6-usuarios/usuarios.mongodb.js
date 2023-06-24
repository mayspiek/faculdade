use('db_usuarios');  // (1)

let users = db.getCollection('users'); // (2)

const fs = require('fs');
let rawdata = fs.readFileSync('usuarios.json'); // (3)
let data = JSON.parse(rawdata);
console.log('Total docs carregados do arquivo: ' + data.length);

console.log('Inserindo Documentos no cloud atlas...');
users.insertMany(data);
console.log('Total Documentos inseridos: ' + users.countDocuments());
