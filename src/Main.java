import Helpers.Validacao;
import Models.Questao;
import Models.Quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        inicializaQuiz();
    }

    public static void inicializaQuiz() {
        int numTurma;
        String descricao;

        Quiz quiz = new Quiz();

        System.out.println("Olá professor! Insira a turma e a descrição do seu quiz.");

        Scanner addNumTurma = new Scanner(System.in);

        do {
            while (!addNumTurma.hasNextInt()) {
                System.out.println("Ops! O parâmetro 'nº turma' não é numérico, insira-o novamente.");
                addNumTurma.next();
            }

            numTurma = addNumTurma.nextInt();
        } while (numTurma <= 0);

        quiz.setNumTurma(numTurma);

        Scanner addDescricao = new Scanner(System.in);
        descricao = addDescricao.next();

        if (Validacao.stringCorreta(descricao)) {
            quiz.setConteudoDescricao(descricao);
        }

        System.out.println("Você criou o quiz para a turma: " + quiz.getNumTurma() + ", com a descrição: " + quiz.getConteudoDescricao());

        situacaoQuiz();
    }

    public static void situacaoQuiz() {
        boolean finalizaSwitch = false;
        int statusQuiz = 0;

        do {
            System.out.println("Selecione o status que o quiz irá possuir: (R) Rascunho    (P) Pronto    (I) Inativo");

            Scanner addTipoSituacao = new Scanner(System.in);
            char opcao = addTipoSituacao.next().toUpperCase().charAt(0);

            switch (opcao) {
                case 'R':
                    statusQuiz = 1;
                    System.out.println("O quiz será armazenado como: 'rascunho'");
                    finalizaSwitch = true;

                    break;
                case 'P':
                    statusQuiz = 2;
                    System.out.println("O quiz será armazenado como: 'pronto'");
                    finalizaSwitch = true;

                    break;

                case 'I':
                    statusQuiz = 3;
                    System.out.println("O quiz será armazenado como: 'inativo'");
                    finalizaSwitch = true;

                    break;
                default:
                    System.out.println("Você deve selecionar apenas: (R) Rascunho    (P) Pronto    (I) Inativo");
                    break;
            }
        } while (!finalizaSwitch);

        System.out.println("O status do seu quiz é: " + statusQuiz);

        inserePerguntas();
    }

    public static void inserePerguntas() {
        int qntPerguntas;
        String pergunta;
        String respostaCorreta;

        Scanner scanQntPerguntas = new Scanner("Agora que você criou o quiz, insira a quantidade de perguntas:");
        System.out.println(scanQntPerguntas.nextLine());

        Scanner addQntPerguntas = new Scanner(System.in);

        do {
            while (!addQntPerguntas.hasNextInt()) {
                System.out.println("Ops! O parâmetro 'nº turma' não é numérico, insira-o novamente.");
                addQntPerguntas.next();
            }

            qntPerguntas = addQntPerguntas.nextInt();
        } while (qntPerguntas <= 0);

        Scanner umaPergunta = new Scanner("Certo! O quiz terá: " + qntPerguntas + " pergunta. Agora insira as perguntas:");
        Scanner maisDeUmaPergunta = new Scanner("Certo! O quiz terá: " + qntPerguntas + " perguntas. Agora insira as perguntas:");

        Scanner scanPerguntas = qntPerguntas >= 2 ? maisDeUmaPergunta : umaPergunta;
        System.out.println(scanPerguntas.nextLine());

        ArrayList<Questao> questoes = new ArrayList<Questao>();

        for (int i = 1; i <= qntPerguntas; i++) {
            Scanner addPergunta = new Scanner(System.in);
            pergunta = addPergunta.next();

            Scanner addRespostaCorreta = new Scanner(System.in);
            respostaCorreta = addRespostaCorreta.next();

            Questao questao = new Questao();
            questao.setPergunta(pergunta);
            questao.setRespostaCorreta(respostaCorreta);

            questoes.add(questao);
        }

        System.out.println("Você inseriu as perguntas: " + questoes);
    }
}
