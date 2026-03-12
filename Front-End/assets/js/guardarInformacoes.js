const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));

document.getElementById("nomeUsuario").textContent = usuario ? usuario.nome : "";