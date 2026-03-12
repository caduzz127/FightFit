async function exibirTreinos() {

    const usuarioLogado = JSON.parse(sessionStorage.getItem("usuarioLogado"))

    try{

        const response = await fetch(`http://localhost:8080/fightfit/usuario/buscarUsuariosComTreinosExercicios/${usuarioLogado.nome}`,{
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            throw new Error("Erro ao encontrar os treinos");
        }

        const dados = await response.json();

        console.log(dados);

    }catch(error){
        console.error(error);
        alert("Erro ao conectar com a API");
    }
}