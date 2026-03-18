const listaTreinos = document.getElementById('listaTreinos');
const modal = document.getElementById('alterarTreinoModalOverlay');
const btnFechar = document.getElementById('fecharAlterarTreinoModal');
const fecharModalDeAlterar = document.getElementById('cancelarExclusaoTreino');

if (listaTreinos && modal && btnFechar) {
    listaTreinos.addEventListener('click', (event) => {
        const btn = event.target.closest('.abrirModalTreino');

        if (!btn) return;

        const card = btn.closest('.card-treino');
        if (!card) return;

        const nomeTreinoEl = card.querySelector('.nome-treino');
        const nomeTreino = nomeTreinoEl ? nomeTreinoEl.textContent : '';

        console.log('Treino clicado:', nomeTreino);

        modal.style.display = 'flex';

        const tituloModal = document.getElementById('tituloModalTreino');
        if (tituloModal) {
            tituloModal.textContent = nomeTreino;
        }
    });

    // FECHAR BOTÃO
    btnFechar.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    // FECHAR CLICANDO FORA
    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    fecharModalDeAlterar.addEventListener('click', () => {
        modal.style.display = 'none';
    });
}



//logica para alterar para exclusão e alteração do treino
const btnAlterarTreino = document.getElementById('abrirAlterarTreinoModal');
const btnExcluirTreino = document.getElementById('excluirTreinoModal');

const modalAlterarTreino = document.getElementById('modalAlterarTreino');
const modalExcluirTreino = document.getElementById('modalExcluirTreino');


btnAlterarTreino.addEventListener('click', () => {
    btnAlterarTreino.classList.add('ativo');
    btnAlterarTreino.classList.remove('desligado');

    btnExcluirTreino.classList.remove('ativo');
    btnExcluirTreino.classList.add('desligado');

    //modal
    modalAlterarTreino.classList.remove('desligado');
    modalExcluirTreino.classList.remove('ativo');
});

btnExcluirTreino.addEventListener('click', () => {
    btnExcluirTreino.classList.add('ativo');
    btnExcluirTreino.classList.remove('desligado');

    btnAlterarTreino.classList.remove('ativo');
    btnAlterarTreino.classList.add('desligado');
    //modal
    modalExcluirTreino.classList.add('ativo');
    modalAlterarTreino.classList.add('desligado');
});