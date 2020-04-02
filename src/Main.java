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

        String titulo = "Olá professor! Insira a turma e a descrição do seu quiz.";

        Scanner scan = new Scanner(titulo);
        System.out.println(scan.nextLine());

        Scanner addNumTurma = new Scanner(System.in);
        numTurma = Integer.parseInt(addNumTurma.next());

        Scanner addDescricao = new Scanner(System.in);
        descricao = addDescricao.next();

        Quiz quiz = new Quiz();
        quiz.setNumTurma(numTurma);
        quiz.setConteudoDescricao(descricao);

        System.out.println("Você criou o quiz para a turma: " + quiz.getNumTurma() + ", com a descrição: " + quiz.getConteudoDescricao());

        inserePerguntas();
    }

    public static void inserePerguntas() {
        int qntPerguntas;
        String pergunta;
        String respostaCorreta;

        Scanner scanQntPerguntas = new Scanner("Agora que você criou o quiz, insira a quantidade de perguntas:");
        System.out.println(scanQntPerguntas.nextLine());

        Scanner addQntPerguntas = new Scanner(System.in);
        qntPerguntas = Integer.parseInt(addQntPerguntas.next());

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
