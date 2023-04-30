use('geo');
let municipios = db.getCollection('municipios');
console.log(municipios);

const fs = require('fs');

let rawdata = fs.readFileSync('municipios.json');
let data = JSON.parse(rawdata);


municipios.insertMany(
    //ler o arq mini.json e adicionar os municipios no banco
    data
)

municipios.count();