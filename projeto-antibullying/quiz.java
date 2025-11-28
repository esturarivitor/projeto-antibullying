// Banco de dados das perguntas (mantido como no Python)
const perguntas = [
    {
        "pergunta": "O que define o bullying, diferenciando-o de uma briga comum?",
        "opcoes": {
            "A": "Acontece apenas uma vez e logo se resolve.",
            "B": "É uma agressão intencional, repetitiva e ocorre num desequilíbrio de poder.",
            "C": "É quando dois amigos discutem por futebol."
        },
        "correta": "B",
        "explicacao": "O bullying exige repetição, intenção de ferir e desigualdade de força/poder."
    },
    {
        "pergunta": "Como chamamos as agressões que acontecem através da internet?",
        "opcoes": {
            "A": "Social Bullying.",
            "B": "Fake News.",
            "C": "Cyberbullying."
        },
        "correta": "C",
        "explicacao": "Cyberbullying é a prática de bullying no ambiente virtual."
    },
    {
        "pergunta": "João viu um colega sofrendo bullying, mas só olhou e não fez nada. Qual o papel dele?",
        "opcoes": {
            "A": "Espectador (Testemunha).",
            "B": "Agressor indireto.",
            "C": "Vítima."
        },
        "correta": "A",
        "explicacao": "Quem observa sem participar é o espectador. O silêncio muitas vezes valida a agressão."
    },
    {
        "pergunta": "Excluir alguém propositalmente de grupos ou espalhar boatos é:",
        "opcoes": {
            "A": "Bullying Físico.",
            "B": "Bullying Social (Psicológico).",
            "C": "Bullying Verbal."
        },
        "correta": "B",
        "explicacao": "Isolar e destruir a reputação de alguém é uma forma de violência psicológica e social."
    },
    {
        "pergunta": "Mito ou Verdade: 'O bullying fortalece o caráter da vítima para a vida adulta'.",
        "opcoes": {
            "A": "Verdade.",
            "B": "Depende da idade.",
            "C": "Mito (Falso)."
        },
        "correta": "C",
        "explicacao": "Isso é um mito! O bullying deixa cicatrizes emocionais e pode gerar ansiedade e depressão."
    },
    {
        "pergunta": "Qual a atitude correta ao presenciar ou sofrer bullying?",
        "opcoes": {
            "A": "Tentar se vingar.",
            "B": "Guardar segredo.",
            "C": "Procurar um adulto de confiança e relatar."
        },
        "correta": "C",
        "explicacao": "A melhor forma é buscar alguém com autoridade para interromper o ciclo de agressões."
    },
    {
        "pergunta": "Qual destas NÃO é uma consequência comum para quem sofre bullying?",
        "opcoes": {
            "A": "Aumento da autoestima.",
            "B": "Isolamento social.",
            "C": "Queda no rendimento escolar."
        },
        "correta": "A",
        "explicacao": "O bullying destrói a autoestima, nunca a aumenta."
    }
];

let pontos = 0;
let indicePerguntaAtual = 0;
const quizArea = document.getElementById('quiz-area');
const resultadoArea = document.getElementById('resultado-area');

// Função para exibir a pergunta atual
function exibirPergunta() {
    // Esconde o resultado e garante que a área do quiz esteja visível
    resultadoArea.style.display = 'none';
    quizArea.style.display = 'block';

    if (indicePerguntaAtual >= perguntas.length) {
        // Todas as perguntas foram respondidas
        exibirResultadoFinal();
        return;
    }

    const item = perguntas[indicePerguntaAtual];
    let htmlPergunta = `
        <div class="pergunta-box" id="pergunta-${indicePerguntaAtual}">
            <div class="pergunta-texto">PERGUNTA ${indicePerguntaAtual + 1}: ${item.pergunta}</div>
    `;
    
    // Adiciona as opções
    for (const letra in item.opcoes) {
        htmlPergunta += `
            <div class="opcao" data-resposta="${letra}">
                [${letra}] ${item.opcoes[letra]}
            </div>
        `;
    }

    // Adiciona o botão de Próxima Pergunta
    htmlPergunta += `
            <div id="feedback-${indicePerguntaAtual}" style="display: none;"></div>
            <button id="btn-proxima-${indicePerguntaAtual}" disabled>Próxima Pergunta »</button>
        </div>
    `;

    // Limpa e insere o HTML da pergunta atual
    quizArea.innerHTML = htmlPergunta;
    
    // Adiciona o evento de clique a cada opção
    document.querySelectorAll('.opcao').forEach(opcao => {
        opcao.addEventListener('click', responder);
    });
}

// Função para processar a resposta do usuário
function responder(event) {
    const respostaSelecionada = event.target.getAttribute('data-resposta');
    const item = perguntas[indicePerguntaAtual];
    const feedbackDiv = document.getElementById(`feedback-${indicePerguntaAtual}`);
    const proximaBtn = document.getElementById(`btn-proxima-${indicePerguntaAtual}`);
    
    // Desabilita o clique em todas as opções após a resposta
    document.querySelectorAll('.opcao').forEach(opcao => {
        opcao.removeEventListener('click', responder);
        // Garante que o hover pare de funcionar visualmente
        opcao.style.cursor = 'default';
    });

    // 1. Aplica o feedback de cor
    event.target.classList.add(respostaSelecionada === item.correta ? 'correta' : 'errada');
    
    // 2. Verifica e contabiliza a pontuação
    if (respostaSelecionada === item.correta) {
        pontos++;
        feedbackDiv.innerHTML = `
            <div class="correta">✅ Correto!</div>
        `;
    } else {
        // Marca a correta caso a resposta tenha sido errada para ajudar a visualização
        document.querySelector(`.opcao[data-resposta="${item.correta}"]`).classList.add('correta');

        feedbackDiv.innerHTML = `
            <div class="errada">❌ Errado! A resposta certa era ${item.correta}.</div>
        `;
    }

    // 3. Exibe a explicação
    feedbackDiv.innerHTML += `
        <div class="explicacao">📝 Explicação: ${item.explicacao}</div>
    `;
    feedbackDiv.style.display = 'block';
    
    // 4. Habilita o botão para a próxima pergunta
    proximaBtn.disabled = false;
    proximaBtn.addEventListener('click', () => {
        indicePerguntaAtual++;
        exibirPergunta(); // Chama a próxima pergunta (ou resultado)
    });
}

// Função para exibir o resultado final
function exibirResultadoFinal() {
    quizArea.style.display = 'none'; // Esconde a área do quiz
    resultadoArea.style.display = 'block'; // Mostra a área do resultado

    let classificacao = '';
    let mensagem = '';
    let classeCor = '';
    const total = perguntas.length;

    if (pontos <= 3) {
        classificacao = "APRENDIZ";
        mensagem = "Você precisa se informar mais sobre o assunto. O bullying é sério!";
        classeCor = 'aprendiz';
    } else if (pontos <= 5) {
        classificacao = "ALIADO CONSCIENTE";
        mensagem = "Bom trabalho! Você entende o básico, continue estudando para ajudar os outros.";
        classeCor = 'aliado';
    } else {
        classificacao = "🏆 GUARDIÃO DO RESPEITO 🏆";
        mensagem = "Parabéns! Você domina o assunto e pode ajudar a transformar sua escola.";
        classeCor = 'guardiao';
    }

    resultadoArea.innerHTML = `
        <h2>🏁 RESULTADO FINAL</h2>
        <p>Você acertou <strong>${pontos}</strong> de <strong>${total}</strong> perguntas.</p>
        <p class="${classeCor}">Classificação: ${classificacao}</p>
        <p>${mensagem}</p>
        <hr>
        <p>Obrigado por jogar!</p>
        <button onclick="location.reload()">Jogar Novamente</button>
    `;
}

// Inicia o quiz ao carregar a página
document.addEventListener('DOMContentLoaded', exibirPergunta);