const hamburger = document.getElementById('hamburger');
const dropdown = document.getElementById('menuDropdown');

// Safety checks
if (hamburger && dropdown) {
    hamburger.setAttribute('aria-expanded', 'false');
    dropdown.style.display = 'none';

    function openMenu() {
        hamburger.classList.add('active');
        dropdown.classList.add('active');
        dropdown.style.display = 'block';
        hamburger.setAttribute('aria-expanded', 'true');
    }
    function closeMenu() {
        hamburger.classList.remove('active');
        dropdown.classList.remove('active');
        dropdown.style.display = 'none';
        hamburger.setAttribute('aria-expanded', 'false');
    }

    hamburger.addEventListener('click', (e) => {
        e.stopPropagation();
        if (hamburger.classList.contains('active')) closeMenu();
        else openMenu();
    });

    // Fecha ao clicar fora
    document.addEventListener('click', (e) => {
        if (!hamburger.contains(e.target) && !dropdown.contains(e.target)) {
            closeMenu();
        }
    });

    // Fecha ao pressionar Esc
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') closeMenu();
    });

    // Fecha ao redimensionar para desktop
    window.addEventListener('resize', () => {
        if (window.innerWidth > 900) closeMenu();
    });
}