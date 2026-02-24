// Array para armazenar os treinos
let trainings = JSON.parse(localStorage.getItem('trainings')) || [];

// Elementos do DOM
const trainingForm = document.getElementById('trainingForm');
const trainingsList = document.getElementById('trainingsList');
const hamburgerBtn = document.getElementById('hamburgerBtn');
const menuDropdown = document.getElementById('menuDropdown');

// Event Listener para o menu hamburguer
if (hamburgerBtn) {
    hamburgerBtn.addEventListener('click', function() {
        hamburgerBtn.classList.toggle('active');
        menuDropdown.classList.toggle('active');
    });
}

// Fechar menu ao clicar em um link
if (menuDropdown) {
    const menuLinks = menuDropdown.querySelectorAll('a');
    menuLinks.forEach(link => {
        link.addEventListener('click', function() {
            hamburgerBtn.classList.remove('active');
            menuDropdown.classList.remove('active');
        });
    });
}

// Fechar menu ao clicar fora
document.addEventListener('click', function(event) {
    if (!event.target.closest('.navbar')) {
        if (hamburgerBtn) hamburgerBtn.classList.remove('active');
        if (menuDropdown) menuDropdown.classList.remove('active');
    }
});

// --- Groups feature ---
let groups = JSON.parse(localStorage.getItem('groups')) || [];
const navGrupos = document.getElementById('navGrupos');
const groupsSection = document.getElementById('groupsSection');
const formSection = document.querySelector('.form-section');
const trainingsSection = document.querySelector('.trainings-section');
const showCreateGroupBtn = document.getElementById('showCreateGroupBtn');
const createGroupForm = document.getElementById('createGroupForm');
const createGroupBtn = document.getElementById('createGroupBtn');
const myGroupsList = document.getElementById('myGroupsList');
const allGroupsList = document.getElementById('allGroupsList');

// Navigation: show/hide sections
const aboutSection = document.getElementById('aboutSection');

function hideAllSections() {
    const secs = [groupsSection, formSection, trainingsSection, aboutSection];
    secs.forEach(s => { if (s) s.style.display = 'none'; });
}

function showSections(ids) {
    hideAllSections();
    ids.forEach(id => {
        const el = typeof id === 'string' ? document.getElementById(id) : id;
        if (el) el.style.display = '';
    });
}

// Bind main nav links
document.querySelectorAll('.nav-menu a').forEach(link => {
    link.addEventListener('click', function(e) {
        e.preventDefault();
        const href = (link.getAttribute('href') || '').replace('#','');
        // Map href to sections
        if (href === 'grupos') {
            showSections(['groupsSection']);
            renderGroups();
        } else if (href === 'minhaArea' || href === 'minhaConta') {
            showSections(['formSection','trainingsSection']);
        } else if (href === 'sobre' || href === 'tour') {
            showSections(['aboutSection']);
        } else {
            // default: show form + trainings
            showSections(['formSection','trainingsSection']);
        }
        // close mobile menu if open
        if (hamburgerBtn) hamburgerBtn.classList.remove('active');
        if (menuDropdown) menuDropdown.classList.remove('active');
    });
});

// Bind dropdown links (mobile menu)
if (menuDropdown) {
    menuDropdown.querySelectorAll('a').forEach(link => {
        link.addEventListener('click', function(e){
            e.preventDefault();
            const href = (link.getAttribute('href') || '').replace('#','');
            if (href === 'grupos') {
                showSections(['groupsSection']);
                renderGroups();
            } else if (href === 'minhaArea' || href === 'minhaConta') {
                showSections(['formSection','trainingsSection']);
            } else if (href === 'sobre' || href === 'tour') {
                showSections(['aboutSection']);
            }
            if (hamburgerBtn) hamburgerBtn.classList.remove('active');
            menuDropdown.classList.remove('active');
        });
    });
}

// Mostrar/ocultar formulário de criação
if (showCreateGroupBtn) {
    showCreateGroupBtn.addEventListener('click', function() {
        createGroupForm.style.display = createGroupForm.style.display === 'none' ? 'block' : 'none';
    });
}

// Criar novo grupo
if (createGroupBtn) {
    createGroupBtn.addEventListener('click', function() {
        const nameInput = document.getElementById('groupName');
        const descInput = document.getElementById('groupDesc');
        const name = nameInput.value.trim();
        const desc = descInput.value.trim();
        if (!name) {
            alert('Informe um nome para o grupo.');
            return;
        }
        const group = {
            id: Date.now(),
            name,
            desc,
            members: ['Você']
        };
        groups.push(group);
        localStorage.setItem('groups', JSON.stringify(groups));
        nameInput.value = '';
        descInput.value = '';
        createGroupForm.style.display = 'none';
        renderGroups();
    });
}

function renderGroups() {
    // all groups
    allGroupsList.innerHTML = '';
    if (!groups || groups.length === 0) {
        allGroupsList.innerHTML = '<p class="empty-message">Nenhum grupo criado ainda.</p>';
    } else {
        groups.forEach(g => {
            const card = document.createElement('div');
            card.className = 'group-card';
            card.innerHTML = `
                <h4>${g.name}</h4>
                <p>${g.desc || ''}</p>
                <p><strong>Membros:</strong> ${g.members.length}</p>
                <div style="margin-top:8px;"><button class="btn-primary" onclick="joinGroup(${g.id})">Participar</button></div>
            `;
            allGroupsList.appendChild(card);
        });
    }

    // my groups (where 'Você' is a member)
    myGroupsList.innerHTML = '';
    const my = groups.filter(g => g.members && g.members.includes('Você'));
    if (!my || my.length === 0) {
        myGroupsList.innerHTML = '<p class="empty-message">Você não participa de nenhum grupo ainda.</p>';
    } else {
        my.forEach(g => {
            const card = document.createElement('div');
            card.className = 'group-card';
            card.innerHTML = `
                <h4>${g.name}</h4>
                <p>${g.desc || ''}</p>
                <p><strong>Membros:</strong> ${g.members.length}</p>
                <div style="margin-top:8px;"><button class="btn-primary" onclick="leaveGroup(${g.id})">Sair</button></div>
            `;
            myGroupsList.appendChild(card);
        });
    }
}

// Funções globais para join/leave (necessário tornar acessível)
window.joinGroup = function(id) {
    const g = groups.find(x => x.id === id);
    if (!g) return;
    if (!g.members) g.members = [];
    if (!g.members.includes('Você')) {
        g.members.push('Você');
        localStorage.setItem('groups', JSON.stringify(groups));
        renderGroups();
    }
}

window.leaveGroup = function(id) {
    const g = groups.find(x => x.id === id);
    if (!g || !g.members) return;
    g.members = g.members.filter(m => m !== 'Você');
    localStorage.setItem('groups', JSON.stringify(groups));
    renderGroups();
}


// Event Listener para o formulário
trainingForm.addEventListener('submit', function(event) {
    event.preventDefault();
    
    // Capturar valores do formulário
    const trainingName = document.getElementById('trainingName').value;
    const exercise = document.getElementById('exercise').value;
    const series = document.getElementById('series').value;
    const repetitions = document.getElementById('repetitions').value;
    const weight = document.getElementById('weight').value || 'Não informado';
    const notes = document.getElementById('notes').value;
    
    // Criar objeto do treino
    const training = {
        id: Date.now(),
        trainingName,
        exercise,
        series,
        repetitions,
        weight,
        notes,
        date: new Date().toLocaleDateString('pt-BR')
    };
    
    // Adicionar ao array
    trainings.push(training);
    
    // Salvar no localStorage
    localStorage.setItem('trainings', JSON.stringify(trainings));
    
    // Limpar formulário
    trainingForm.reset();
    
    // Atualizar lista
    renderTrainings();
});

// Função para renderizar os treinos
function renderTrainings() {
    // Limpar lista
    trainingsList.innerHTML = '';
    
    // Se não há treinos, mostrar mensagem vazia
    if (trainings.length === 0) {
        trainingsList.innerHTML = '<p class="empty-message">Nenhum treino adicionado ainda. Crie seu primeiro treino!</p>';
        return;
    }
    
    // Criar cards para cada treino
    trainings.forEach(training => {
        const card = document.createElement('div');
        card.className = 'training-card';
        card.innerHTML = `
            <h3>${training.trainingName}</h3>
            <p><strong>Data:</strong> ${training.date}</p>
            <div class="exercise-name">${training.exercise}</div>
            <div class="details">
                <div class="detail-item">
                    <strong>${training.series}</strong>
                    <span>Séries</span>
                </div>
                <div class="detail-item">
                    <strong>${training.repetitions}</strong>
                    <span>Repetições</span>
                </div>
                <div class="detail-item">
                    <strong>${training.weight}</strong>
                    <span>Peso (kg)</span>
                </div>
            </div>
            ${training.notes ? `<div class="notes">${training.notes}</div>` : ''}
            <div class="card-actions">
                <button class="btn-delete" onclick="deleteTraining(${training.id})">🗑️ Deletar</button>
            </div>
        `;
        trainingsList.appendChild(card);
    });
}

// Função para deletar um treino
function deleteTraining(id) {
    if (confirm('Tem certeza que deseja deletar este treino?')) {
        trainings = trainings.filter(training => training.id !== id);
        localStorage.setItem('trainings', JSON.stringify(trainings));
        renderTrainings();
    }
}

// Renderizar treinos ao carregar a página
renderTrainings();
