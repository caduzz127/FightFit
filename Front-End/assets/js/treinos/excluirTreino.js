let idSelecionadoExcluir = null;

document.addEventListener('click', function (e) {
    console.log('Clique detectado:', e.target);
    const btn = e.target.closest('.abrirModalTreino');

    if (btn) {
        const card = btn.closest('.card-treino');

        if (!card) {
            console.error('Card não encontrado');
            return;
        }

        idSelecionadoExcluir = card.dataset.id;

        console.log('ID selecionado:', idSelecionadoExcluir);
    }
});


document.addEventListener('click', function (e) {
    const btnConfirmarExclusao = e.target.closest('.confirmarExclusaoTreino');
     console.log('Clique detectado no btn de excluir', btnConfirmarExclusao);
    if (idSelecionadoExcluir) {


        if (btnConfirmarExclusao) {
            console.log('Clique detectado no btn de excluir');
           
            excluirTreino(idSelecionadoExcluir);
        }
    }
});

async function excluirTreino(id) {
    console.log('Iniciando exclusão do treino com ID:', id);
    try {
        const response = await fetch(`http://localhost:8080/fightfit/treinos/deletarTreino/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao excluir treino');
        }

        console.log('Treino excluído com sucesso');
        window.location.href = 'telaIndividualTreinos.html';
    } catch (error) {
        console.error('Erro ao excluir treino:', error);
    }
}