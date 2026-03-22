document.addEventListener("DOMContentLoaded", () => {
    exibirTreinos();
});

async function exibirTreinos() {

    const usuarioLogado = JSON.parse(sessionStorage.getItem("usuarioLogado"));

    try {

        const response = await fetch(`http://localhost:8080/fightfit/usuario/buscarUsuariosComTreinosExercicios/${usuarioLogado.nome}`);

        if (!response.ok) {
            throw new Error("Erro ao encontrar os treinos");
        }

        const dados = await response.json();

        const listaTreinos = document.getElementById("listaTreinos");

        listaTreinos.innerHTML = "";

        //caso não tenha treinos
        if (dados.listaDeTreinos.length === 0) {

            listaTreinos.innerHTML = `
                <div class="card-treino">
                    <h2 class="nome-treino">Você ainda não tem treinos</h2>
                    <div class="exercicios">
                        <p>Crie seu primeiro treino para começar.</p>
                    </div>
                    <button class="btn-adicionar-treino" onclick="abrirModal()">Adicionar Treino</button>
                </div>
            `;

            return;
        }

        // se tiver treinos
        dados.listaDeTreinos.forEach(treino => {

            let card = `
            <div class="card-treino" data-id="${treino.id}">
                    <div class="card-treino-header">
                        <h2 class="nome-treino">${treino.nome}</h2>
                        <button class="abrirModalTreino" ><img
                                src="assets/imgs/menu.svg" alt="Fechar"
                                width="30px">
                        </button>
                    </div>
                    <div class="exercicios-conteiner">
                        <div class="exercicios-conteiner-header">
                            <h2>Exercícios</h2>
                        </div>
            `;

            if (treino.listaDeExercicios.length === 0) {

                card += `
                <div class="exercicios">
                    <p>Esse treino ainda não possui exercícios.</p>
                </div>
                `;

            } else {

                treino.listaDeExercicios.forEach(exercicio => {

                    card += `
                    <div class="exercicios">
                        <p>Nome do exercicio: ${exercicio.nome}</p>
                        <p>Carga: ${exercicio.carga}</p>
                        <p>Repetições: ${exercicio.repeticoes}</p>
                        <p>Series: ${exercicio.serie}</p>
                    </div>
                    `;

                });

            }

            card += `</div>
                    <div class="div-adicionar-exercicios">
                        <button class="adicionarExercicios" data-id="${treino.id}">
                        Adicionar Exercício
                        </button>
                    </div>
                </div>
                
            `;

            listaTreinos.innerHTML += card;
            console.log(`Treino adicionado ao HTML: ${treino.id} - ${treino.nome}`);

        });

    } catch (error) {

        console.error(error);
        alert("Erro ao conectar com a API");

    }
}


