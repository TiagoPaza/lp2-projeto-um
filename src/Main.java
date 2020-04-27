import Helpers.Validacao;
import Models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Funcionario funcionario;
    private static Professor professor;

    public static void main(String[] args) {
        System.out.println("Olá usuário, antes de criar um novo quiz, identifique-se!");

        idetificaUsuario();
    }

    public static void idetificaUsuario() {
        String nomeCompleto;
        String cpf;
        int codUsuario;

        System.out.println("Informe abaixo seu nome completo:");

        Scanner addNomeCompleto = new Scanner(System.in);
        nomeCompleto = addNomeCompleto.next();

        funcionario = new Funcionario();

        if (Validacao.stringCorreta(nomeCompleto)) {
            funcionario.setNomeCompleto(nomeCompleto);
        }

        System.out.println("Agora, o seu CPF:");

        Scanner addCpf = new Scanner(System.in);
        cpf = addCpf.next();

        if (Validacao.stringCorreta(cpf)) {
            funcionario.setCpf(cpf);
        }

        System.out.println("Informe o código do seu usuário:");

        Scanner addCodUsuario = new Scanner(System.in);
        do {
            while (!addCodUsuario.hasNextInt()) {
                System.out.println("Ops! O parâmetro 'cód. de usuário' não é numérico, insira-o novamente.");
                addCodUsuario.next();
            }

            codUsuario = addCodUsuario.nextInt();
        } while (codUsuario <= 0);

        funcionario.setCodUsuario(codUsuario);

        boolean finalizaSwitch = false;

        do {
            System.out.println("Por fim, selecione o seu cargo:  (1) Professor");

            Scanner addCargo = new Scanner(System.in);
            char opcao = addCargo.next().charAt(0);

            switch (opcao) {
                case '1':
                    funcionario.setCargo(Character.getNumericValue(opcao));

                    System.out.println("Certo, você é um 'professor'!");
                    finalizaSwitch = true;

                    informacoesProfessor();
                    break;
                default:
                    System.out.println("Você deve selecionar apenas:  (1) Professor");
                    break;
            }
        } while (!finalizaSwitch);
    }

    public static void informacoesProfessor() {
        String areaConhecimento;

        professor = new Professor();

        System.out.println(funcionario.getNomeCompleto() + " informe a área de conhecimento que você atua:");

        Scanner addAreaConhecimento = new Scanner(System.in);
        areaConhecimento = addAreaConhecimento.next();

        if (Validacao.stringCorreta(areaConhecimento)) {
            professor.setAreaConhecimento(areaConhecimento);
        }

        int qntDisciplinas;
        String nomeDisciplina;

        System.out.println("Ok, agora nos informe a quantida de disciplinas que você possui:");

        Scanner addQntDisciplinas = new Scanner(System.in);
        qntDisciplinas = addQntDisciplinas.nextInt();

        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

        for (int i = 1; i <= qntDisciplinas; i++) {
            System.out.println("Informe a disciplina [" + i + "]");

            Scanner addNomeDisciplina = new Scanner(System.in);
            nomeDisciplina = addNomeDisciplina.next();

            Disciplina disciplina = new Disciplina();
            disciplina.setNome(nomeDisciplina);

            disciplinas.add(disciplina);
        }

        professor.setDisciplinas(disciplinas);

        System.out.println("Você definiu a área de conhecimento: " + professor.getAreaConhecimento());
        System.out.println("E as disciplinas: " + professor.getDisciplinas());

        defineQuiz();
    }

    public static void defineQuiz() {
        int numTurma;
        String descricao;

        System.out.println("Vamos definir o quiz, informe o nº da turma");

        Quiz quiz = new Quiz();

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
            System.out.println("Informe a pergunta [" + i + "]");

            Scanner addPergunta = new Scanner(System.in);
            pergunta = addPergunta.next();

            System.out.println("Informe a resposta correta para a pergunta [" + i + "]");

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
