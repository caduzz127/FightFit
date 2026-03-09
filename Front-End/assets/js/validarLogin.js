async function fazerLogin(event) {

  event.preventDefault(); // impede o form de recarregar a página

  const nome = document.getElementById("nome").value;
  const senha = document.getElementById("senha").value;

  try {
    const response = await fetch("http://localhost:8080/fightfit/usuario/login", {
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
      alert("Usuário ou senha inválidos");
      return;
    }

    if (!response.ok) {
      throw new Error("Erro no servidor");
    }

    const data = await response.json();

    console.log("Usuário logado:", data);

    window.location.href = "telaPrincipal.html";

  } catch (error) {
    console.error(error);
    alert("Erro ao conectar com a API");
  }
}