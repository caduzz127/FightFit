const API_URL = 'http://localhost:8080/fightFit'; 

document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form');
	if (!form) return;

	form.addEventListener('submit', async (e) => {
		e.preventDefault();

		const nome = (form.querySelector('input[name="nome"]') || {}).value || '';
		const email = (form.querySelector('input[name="email"]') || {}).value || '';
		const senha = (form.querySelector('input[name="senha"]') || {}).value || '';

		if (!nome.trim() || !email.trim() || !senha) {
			alert('Por favor preencha todos os campos.');
			return;
		}

		try {
			const res = await fetch(API_URL, {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ nome: nome.trim(), email: email.trim(), senha })
			});

			if (res.ok) {
				const data = await res.json().catch(() => null);
				alert('Cadastro realizado com sucesso!');
				window.location.href = 'index.html';
			} else {
				const text = await res.text();
				alert('Erro ao cadastrar: ' + (text || res.statusText));
			}
		} catch (err) {
			alert('Erro de conexão: ' + err.message);
		}
	});
});

