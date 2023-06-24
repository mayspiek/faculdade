use('db_usuarios');
let users = db.getCollection('users');

// users.find( {"tarefas.status": { $regex : /pendente/ } } );

// users.find( { $and: [
//     {"pais" : { $regex : /Brasil/i } }, 
//     { "acessos" : { $gt : 0 } }  ] 
//   });

// users.find ( { "tarefas.tags": { $in : [/ifpr/i } });

users.find( { $and : [
  { "pais" : { $regex : /Brasil/i } },
  { "email" : 1, "pais" : 1 }
 ] } ).sort( { "idioma" : 1 } )
