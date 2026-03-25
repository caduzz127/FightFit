//PEGAR O ID DO CARD
let idSelecionadoAlterar = null;

document.addEventListener('click', function (e) {
    const btn = e.target.closest('.abrirModalTreino');

    if (btn) {
        const card = btn.closest('.card-treino');

        if (!card) {
            console.error('Card não encontrado');
            return;
        }

        idSelecionadoAlterar = card.dataset.id;

        console.log('ID selecionado:', idSelecionadoAlterar);
    }
});


document.addEventListener('click', function (e) {
    const btnAlterarNome = e.target.closest('.salvarAlteracaoTreino');

    if (btnAlterarNome) {
        const novoNomeTreino = document.getElementById('novoNomeTreino').value.trim();
        if(novoNomeTreino === "") {
            alert("O nome do treino não pode ser vazio.");
            return;
        }
        alterarTreino(idSelecionadoAlterar, novoNomeTreino);
        window.location.href = "telaIndividualTreinos.html";
    }

});



async function alterarTreino(id, nomeAtualizado) {
    // Validação básica
    if (!id || !nomeAtualizado?.trim()) {
        console.warn('ID ou nome inválido');
        return false;
    }

    try {
        const response = await fetch('http://localhost:8080/fightfit/treinos/atualizarTreino', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id,
                nome: nomeAtualizado.trim()
            })
        });

        // Verifica se a resposta foi OK
        if (!response.ok) {
            const erroTexto = await response.text();
            throw new Error(`Erro na requisição: ${response.status} - ${erroTexto}`);
        }

        // Se a API retornar JSON
        let data = null;
        try {
            data = await response.json();
        } catch {
            // caso não tenha body
        }

        console.log('Treino atualizado com sucesso!', data);
        return true;

    } catch (error) {
        console.error('Erro ao alterar treino:', error.message);
        return false;
    }
}