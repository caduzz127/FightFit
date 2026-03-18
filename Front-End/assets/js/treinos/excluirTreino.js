let idSelecionado = null;

document.addEventListener('click', function (e) {
    const btn = e.target.closest('.abrirModalTreino');

    if (btn) {
        const card = btn.closest('.card-treino');

        if (!card) {
            console.error('Card não encontrado');
            return;
        }

        idSelecionado = card.dataset.id;

        console.log('ID selecionado:', idSelecionado);
    }
});


document.addEventListener('click', function (e) {
    const btnConfirmarExclusao = e.target.closest('.confirmarExclusaoTreino');
    if (idSelecionado) {


        if (btnConfirmarExclusao) {
            console.log('Botão de confirmação de exclusão clicado. ID do treino a excluir:', idSelecionado);
            excluirTreino(idSelecionado);
        }
    }
});

async function excluirTreino(id) {
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