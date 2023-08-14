use("brasil");

let estados = db.getCollection("estados");

estados.insertOne(
    {"nome" : "Minas Gerais", "sigla" : "SC", "regiao" : "Sul"}
)

estados.insertMany([
    { "nome" : "Minas 1", "sigla": "ES", idh : 80},
    { "nome" : "Minas 2", "sigla": "MS", idh : 81},
    { "nome" : "Rio de Janeiro", "sigla": "RJ", idh : 71},
])

estados.insertOne(
    { "nome" : "Rio de Janeiro", "sigla": "RJ", idh : 99},
)

//SEleciona o primeiro estado com a sigla RJ
let res = estados.findOne(
    {"sigla":"ES"}
);

console.log(res.nome);

//Seleciona os estados com idh superior a 80
let idhRes = estados.find(
    {"idh": {"$gt": 80} }
)

//console.log(JSON.stringify(idhRes, null, 2))

//Imprime o resultado do find usando filtro por idh
idhRes.forEach(e => {console.log(e.nome)});
