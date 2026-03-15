async function cadastrarTreino() {
    try {
        const usuario = JSON.parse(sessionStorage.getItem("usuarioLogado"));
        const nomeTreino = document.getElementById("nomeTreino");
        const cadastrarTreino = await fetch(`http://localhost:8080/fightfit/treino/cadastrarTreino`,{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                nome: nomeTreino.value,
                idUsuario: usuario.id
        })
    });


    }
    catch (error) {
        console.error("Erro ao exibir treinos:", error);
        alert("Ocorreu um erro ao exibir os treinos. Por favor, tente novamente mais tarde.");
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


