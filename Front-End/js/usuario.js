
const user = document.getElementById("cadastro-users")

user.addEventListener('submit', event => { 
    event.preventDefault();

    const formData = new FormData(user);  
    const data = Object.fromEntries(formData);

    fetch("http://localhost:8080/pessoas/cadastro", {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
});

function msg() {
    alert("Salvo com sucesso!");
    window.location.reload(true);
  }
