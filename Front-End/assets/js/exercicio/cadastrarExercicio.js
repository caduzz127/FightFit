const modalExercicios = document.getElementById("modalOverlayExercicios");
const fecharModalExercicios = document.getElementById("fecharModalExercicios");
console.log("Modal Exercícios:", modalExercicios, fecharModalExercicios);

fecharModalExercicios.addEventListener("click", () => {
    modalExercicios.classList.remove("ativo");
});
modalExercicios.addEventListener("click", (event) => {

    if (event.target === modalExercicios) {
        modalExercicios.classList.remove("ativo");
    }

});
let treinoSelecionadoId = null;

document.addEventListener("click", function(event){

    if(event.target.classList.contains("adicionarExercicios")){

        treinoSelecionadoId = event.target.dataset.id;

        console.log("Treino selecionado:", treinoSelecionadoId);

        modalExercicios.classList.add("ativo");

    }

});