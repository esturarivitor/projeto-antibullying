import time
import os

# Função para limpar a tela (funciona em Windows e Linux/Mac)
def limpar_tela():
    os.system('cls' if os.name == 'nt' else 'clear')

# Códigos de cores para deixar o terminal bonito
class Cores:
    VERDE = '\033[92m'
    VERMELHO = '\033[91m'
    AMARELO = '\033[93m'
    AZUL = '\033[94m'
    RESET = '\033[0m'
    NEGRITO = '\033[1m'

# Banco de dados das perguntas
perguntas = [
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
]

def rodar_quiz():
    pontos = 0
    limpar_tela()
    
    print(f"{Cores.AZUL}{'='*40}")
    print(f"🛡️  SUPER QUIZ: JUNTOS CONTRA O BULLYING")
    print(f"{'='*40}{Cores.RESET}")
    print("Responda com A, B ou C.\n")
    time.sleep(2)

    for i, item in enumerate(perguntas):
        print(f"\n{Cores.NEGRITO}PERGUNTA {i+1}:{Cores.RESET} {item['pergunta']}")
        
        for letra, texto in item['opcoes'].items():
            print(f" [{letra}] {texto}")
        
        # Loop para validar a resposta do usuário
        while True:
            resposta = input(f"\n{Cores.AMARELO}Sua resposta: {Cores.RESET}").upper().strip()
            if resposta in ['A', 'B', 'C']:
                break
            print("Opção inválida! Digite A, B ou C.")

        # Verificação
        if resposta == item['correta']:
            print(f"{Cores.VERDE}✅ Correto!{Cores.RESET}")
            pontos += 1
        else:
            print(f"{Cores.VERMELHO}❌ Errado! A resposta certa era {item['correta']}.{Cores.RESET}")
        
        print(f"{Cores.AZUL}📝 Explicação:{Cores.RESET} {item['explicacao']}")
        print("-" * 40)
        time.sleep(2.5) # Pausa para ler a explicação

    # Resultado Final
    limpar_tela()
    print(f"{Cores.AZUL}{'='*40}")
    print(f"🏁 RESULTADO FINAL")
    print(f"{'='*40}{Cores.RESET}")
    print(f"Você acertou {Cores.NEGRITO}{pontos} de {len(perguntas)}{Cores.RESET} perguntas.\n")

    if pontos <= 3:
        print(f"{Cores.VERMELHO}Classificação: APRENDIZ{Cores.RESET}")
        print("Você precisa se informar mais sobre o assunto. O bullying é sério!")
    elif pontos <= 5:
        print(f"{Cores.AMARELO}Classificação: ALIADO CONSCIENTE{Cores.RESET}")
        print("Bom trabalho! Você entende o básico, continue estudando para ajudar os outros.")
    else:
        print(f"{Cores.VERDE}🏆 Classificação: GUARDIÃO DO RESPEITO 🏆{Cores.RESET}")
        print("Parabéns! Você domina o assunto e pode ajudar a transformar sua escola.")

    print("\nObrigado por jogar!")

if __name__ == "__main__":
    rodar_quiz()