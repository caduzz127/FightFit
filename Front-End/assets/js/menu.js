// Seleciona elementos do DOM
const hamburgerBtn = document.getElementById('hamburguer');
const menuDropdown = document.getElementById('menuDropdown');
const nav = document.getElementById('nav');

// Estado do menu
let menuOpen = false;

// Função para abrir o menu
function openMenu() {
    menuOpen = true;
    hamburgerBtn.classList.add('active');
    menuDropdown.classList.add('active');
    nav.classList.add('active');
    hamburgerBtn.setAttribute('aria-expanded', 'true');
}

// Função para fechar o menu
function closeMenu() {
    menuOpen = false;
    hamburgerBtn.classList.remove('active');
    menuDropdown.classList.remove('active');
    nav.classList.remove('active');
    hamburgerBtn.setAttribute('aria-expanded', 'false');
}

// Função para alternar (toggle) o menu
function toggleMenu() {
    if (menuOpen) {
        closeMenu();
    } else {
        openMenu();
    }
}

// Evento de clique no hamburguer
hamburgerBtn.addEventListener('click', (e) => {
    e.stopPropagation();
    toggleMenu();
});

// Fechar menu ao clicar fora
document.addEventListener('click', (e) => {
    if (!hamburgerBtn.contains(e.target) && !menuDropdown.contains(e.target)) {
        closeMenu();
    }
});

// Fechar menu ao pressionar ESC
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && menuOpen) {
        closeMenu();
    }
});

// Fechar menu ao redimensionar para tamanho desktop
window.addEventListener('resize', () => {
    if (window.innerWidth > 900) {
        closeMenu();
    }
});

// Inicializar: garante que o menu começa fechado
window.addEventListener('load', () => {
    closeMenu();
});
