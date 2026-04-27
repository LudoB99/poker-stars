async function startGame() {
    setButtons(false);
    const response = await fetch('/api/game/start', { method: 'POST' });
    if (!response.ok) return showError();
    const state = await response.json();
    document.getElementById('welcome').classList.add('d-none');
    document.getElementById('game-ui').classList.remove('d-none');
    document.getElementById('result-overlay').classList.add('d-none');
    updateUI(state);
}

async function performAction(action) {
    setButtons(false);
    const response = await fetch('/api/game/action', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ action })
    });
    if (!response.ok) return showError();
    const state = await response.json();
    updateUI(state);
}

function playAgain() {
    startGame();
}

function updateUI(state) {
    document.getElementById('phase-label').textContent = state.phaseDisplay;
    document.getElementById('human-chips').textContent = state.humanChips + ' jetons';
    document.getElementById('bot-chips').textContent = state.botChips + ' jetons';
    document.getElementById('pot-amount').textContent = 'Pot : ' + state.pot + ' jetons';

    renderCards('human-cards', state.humanCards);
    renderCards('bot-cards', state.botCards);
    renderCommunity('community-cards', state.communityCards);

    if (state.gameOver) {
        showResult(state);
    } else {
        setButtons(true);
    }
}

function renderCards(containerId, cards) {
    document.getElementById(containerId).innerHTML = cards.map(cardHtml).join('');
}

function renderCommunity(containerId, cards) {
    const slots = [];
    for (let i = 0; i < 5; i++) {
        slots.push(i < cards.length ? cardHtml(cards[i]) : '<div class="card-placeholder"></div>');
    }
    document.getElementById(containerId).innerHTML = slots.join('');
}

function cardHtml(card) {
    if (card.hidden) {
        return '<div class="card card-back"></div>';
    }
    const highlight = card.highlight ? ' card--highlight' : '';
    return `<div class="card ${card.color}${highlight}">
        <div class="top">${card.rank}<br>${card.suit}</div>
        <div class="center">${card.suit}</div>
        <div class="bottom">${card.rank}<br>${card.suit}</div>
    </div>`;
}

function showResult(state) {
    const titles = { WIN: ['Vous gagnez !', 'result-win'], LOSE: ['Vous perdez !', 'result-lose'], TIE: ['Égalité !', 'result-tie'] };
    const [text, cls] = titles[state.result] || ['?', ''];
    const titleEl = document.getElementById('result-title');
    titleEl.textContent = state.result === 'LOSE' && state.phase !== 'SHOWDOWN' ? 'Vous vous couchez !' : text;
    titleEl.className = 'result-title ' + cls;

    const handsEl = document.getElementById('result-hands');
    if (state.humanHandName && state.botHandName) {
        handsEl.innerHTML = `
            <div class="result-hand"><span>Vous</span><strong>${state.humanHandName}</strong></div>
            <div class="result-hand"><span>Bot</span><strong>${state.botHandName}</strong></div>`;
        handsEl.classList.remove('d-none');
    } else {
        handsEl.classList.add('d-none');
    }

    document.getElementById('chip-summary').textContent =
        `Vous : ${state.humanChips} jetons · Bot : ${state.botChips} jetons`;

    document.getElementById('result-overlay').classList.remove('d-none');
}

function setButtons(enabled) {
    ['btn-fold', 'btn-check', 'btn-raise'].forEach(id => {
        const btn = document.getElementById(id);
        if (btn) btn.disabled = !enabled;
    });
}

function showError() {
    alert('Une erreur est survenue. Veuillez réessayer.');
    setButtons(true);
}
