const usuario = JSON.parse(sessionStorage.getItem("usuarioLogado"));

document.getElementById("nomeUsuario").textContent = usuario ? usuario.nome : "";