async function cadastrarTreino() {
    console.log("Função cadastrarTreino chamada");

    try {

        const usuario = JSON.parse(sessionStorage.getItem("usuarioLogado"));
        const nomeTreinoInput = document.getElementById("nomeTreino");

        // verificar usuário
        if (!usuario) {
            alert("Usuário não está logado.");
            return;
        }

        // verificar nome do treino
        if (!nomeTreinoInput.value.trim()) {
            alert("Digite o nome do treino.");
            return;
        }

        const response = await fetch("http://localhost:8080/fightfit/treinos/cadastrarTreino", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nome: nomeTreinoInput.value.trim(),
                idUsuario: usuario.id
            })
        });

        console.log("Status:", response.status);

        if (!response.ok) {
            const erro = await response.text();
            throw new Error(erro || "Erro ao cadastrar treino");
        }

        console.log("Treino cadastrado com sucesso");

        alert("Treino cadastrado com sucesso!");

        
        nomeTreinoInput.value = "";

       window.Location.href = "telaIndividualTreinos.html";

    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao cadastrar treino.");
    }
}




const abrirTreino = document.getElementById("adicionarTreino");
const modalTreino = document.getElementById("modalTreinoOverlay");
const fecharModal = document.getElementById("fecharModalTreino");

abrirTreino.addEventListener("click", () => {
    modalTreino.classList.add("ativo");
});

fecharModal.addEventListener("click", () => {
   modalTreino.classList.remove("ativo");
});

window.addEventListener('click', (event) => {
    if (event.target === modalTreino) {
        modalTreino.classList.remove('ativo');
    }
});


