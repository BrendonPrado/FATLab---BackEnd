**Endpoints**:
//Usuario
- post -> /usuarios ### cria novo usuario
- get -> /usuario/{id} ### volta usuario 

//Materia
- post -> /materias ### cria uma nova materia no sistema
- get -> /materias  ### retorna todas as materias no sistema  obs: não feito
- get -> /materias/{id} ### retorna uma materia pelo id

//aluno e professor -> Materia
- post -> /materias/alunos ### matricula um aluno a uma materia (passar usuario_id e materia_id)
- post -> /materias/professor ### matricula um professor a uma materia (passar usuario_id e materia_id)
- get -> /materias/{id}/alunos ### retorna um array de alunos de uma materia


//reservas
- post -> /reservas ### cria nova reserva (passar  diaMes num_aula turno lab_id materia_id) obs: não feito.