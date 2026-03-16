async function fazerCadastro(event) {

  event.preventDefault(); // impede o form de recarregar a página

  const nome = document.getElementById("nomeCadastro").value;
  const senha = document.getElementById("senhaCadastro").value;

  try {
    const response = await fetch("http://localhost:8080/fightfit/usuario/cadastrarUsuario", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        nome: nome,
        senha: senha
      })
    });

    if (response.status === 401) {
      alert("Não foi possível cadastrar o usuário");
      return;
    }

    if (!response.ok) {
      throw new Error("Erro no servidor");
    }

    window.location.href = "index.html";

  } catch (error) {
    console.error(error);
    alert("Erro ao conectar com a API");
  }
}