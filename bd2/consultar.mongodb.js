use('geo');

let municipios = db.getCollection('municipios');

console.log(municipios.countDocuments());

// primeiro registro de uma coleçao
res = municipios.findOne();


//printa o atributo nome do res
console.log(res.Nome);

console.log(JSON.stringify(res, null, 2));

res = municipios.find({'Uf': "RO"});
console.log(res);

res.forEach(m =>{
    console.log(m.Nome, m.Uf);
});