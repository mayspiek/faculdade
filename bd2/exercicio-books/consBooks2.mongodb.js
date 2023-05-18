use('geo');

let colMuni = db.getCollection('municipios');

// consulta 1: todos os municipios do estado de Roraima e ordena por nome
let docs = colMuni.find(
  { Uf: 'RO' }
).sort( { Nome: 1 } );

// docs => é um array de documentos pois usamos o find()

// para imprimir o conteudo do array (documentos):
docs.forEach(d => {
  console.log(`${d.Nome} - ${d.Uf}`);
});

console.log('# Total: ' + docs.count() + ' municipios.'); // 52 municipios

// Equivale ao Where - Match
// Select * from municipios Where UF = 'RO'

// let municipiosRO = colMuni.aggregate([
//     {
//         "match" : {
//         "Uf": "RO" 
//         }
//     } ]
// )
// console.log(municipiosRO.toArray().lenght)


let municipiosRO = colMuni.aggregate([
    {
        $match : {
        "Uf": "SP",
        "Id": { $gt: 3500}, 
        }
    },
    {
        $project: {
          "Nome" : 1, "Uf" : 1
        }
    }
 ]
)
console.log(municipiosRO.toArray().lenght)