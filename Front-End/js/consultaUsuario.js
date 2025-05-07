const form = document.getElementById("table-consulta")

fetch("http://localhost:8080/pessoas").then((data)=>{
    return data.json();
  }).then((todosUsuarios) =>{
      let data1 = "";
      todosUsuarios.map((values) =>{
          data1 += `
          <tbody>
    
              <tr align="center">
                  <th scope="row">${values.id} </td>
                  <td>${values.nome}</td> 
                  <td>${values.email}</td> 
                  <td>${values.telefone} </td>
                  <td>${values.pontos}</td> 
              </tr>
          </tbody>
          `
      })
      document.getElementById("usuarioDados").innerHTML = data1;
      console.log(todosUsuarios);
  })