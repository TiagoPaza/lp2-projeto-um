import Helpers.Validacao;
import Models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Questao> questoes = new ArrayList<Questao>();
    private static int statusQuiz = 0;

    private static Funcionario funcionario;
    private static Professor professor;
    private static int respostasCorretas = 0;

    public static void main(String[] args) {
        System.out.println("Olá usuário, antes de criar um novo quiz, identifique-se!");

        idetificaUsuario();
    }

    public static void idetificaUsuario() {
        String nomeCompleto;
        String cpf;
        int codUsuario;

        System.out.println("Informe abaixo seu nome completo:");

        Scanner addNomeCompleto = new Scanner(System.in).useDelimiter("\n");
        nomeCompleto = addNomeCompleto.next();

        funcionario = new Funcionario();

        if (Validacao.stringCorreta(nomeCompleto)) {
            funcionario.setNomeCompleto(nomeCompleto);
        }

        System.out.println("Agora, o seu CPF:");

        Scanner addCpf = new Scanner(System.in).useDelimiter("\n");
        cpf = addCpf.next();

        if (Validacao.stringCorreta(cpf)) {
            funcionario.setCpf(cpf);
        }

        System.out.println("Informe o código do seu usuário:");

        Scanner addCodUsuario = new Scanner(System.in).useDelimiter("\n");
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

            Scanner addCargo = new Scanner(System.in).useDelimiter("\n");
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

        Scanner addAreaConhecimento = new Scanner(System.in).useDelimiter("\n");
        areaConhecimento = addAreaConhecimento.next();

        if (Validacao.stringCorreta(areaConhecimento)) {
            professor.setAreaConhecimento(areaConhecimento);
        }

        int qntDisciplinas;
        String nomeDisciplina;

        System.out.println("Ok, agora nos informe a quantida de disciplinas que você possui:");

        Scanner addQntDisciplinas = new Scanner(System.in).useDelimiter("\n");
        qntDisciplinas = addQntDisciplinas.nextInt();

        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

        for (int i = 1; i <= qntDisciplinas; i++) {
            System.out.println("Informe a disciplina [" + i + "]");

            Scanner addNomeDisciplina = new Scanner(System.in).useDelimiter("\n");
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

        Scanner addNumTurma = new Scanner(System.in).useDelimiter("\n");

        do {
            while (!addNumTurma.hasNextInt()) {
                System.out.println("Ops! O parâmetro 'nº turma' não é numérico, insira-o novamente.");
                addNumTurma.next();
            }

            numTurma = addNumTurma.nextInt();
        } while (numTurma <= 0);

        quiz.setNumTurma(numTurma);

        System.out.println("Com a turma informata, precisamos de uma descrição");

        Scanner addDescricao = new Scanner(System.in).useDelimiter("\n");
        descricao = addDescricao.next();

        if (Validacao.stringCorreta(descricao)) {
            quiz.setConteudoDescricao(descricao);
        }

        System.out.println("Você criou o quiz para a turma: " + quiz.getNumTurma() + ", com a descrição: " + quiz.getConteudoDescricao());

        situacaoQuiz();
    }

    public static void situacaoQuiz() {
        boolean finalizaSwitch = false;

        do {
            System.out.println("Selecione o status que o quiz irá possuir: (R) Rascunho    (P) Pronto    (I) Inativo");

            Scanner addTipoSituacao = new Scanner(System.in).useDelimiter("\n");
            char opcaoTipoSituacao = addTipoSituacao.next().toUpperCase().charAt(0);

            switch (opcaoTipoSituacao) {
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

        if (questoes != null && !questoes.isEmpty()) {
            System.out.println("Este questionário já possui questões, deseja adicionar outras? (S) Sim    (N) Não");

            Scanner alteraPerguntas = new Scanner(System.in).useDelimiter("\n");
            char opcao = alteraPerguntas.next().toUpperCase().charAt(0);

            if (opcao == 'S') {
                inserePerguntas();
            } else {
                inicializaQuestionario();
            }
        }

        inserePerguntas();
    }

    public static void inserePerguntas() {
        int qntPerguntas;
        String pergunta;
        String respostaCorreta;

        System.out.println("Agora que você criou o quiz, insira a quantidade de perguntas:");

        Scanner addQntPerguntas = new Scanner(System.in).useDelimiter("\n");

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

        for (int i = 1; i <= qntPerguntas; i++) {
            System.out.println("Informe a pergunta [" + i + "]");

            Scanner addPergunta = new Scanner(System.in).useDelimiter("\n");
            pergunta = addPergunta.next();

            System.out.println("Ok, a pergunta [" + i + "] será: (D) Descritiva    (O) Objetiva");

            Scanner addTipoQuestao = new Scanner(System.in).useDelimiter("\n");
            char opcaoTipoQuestao = addTipoQuestao.next().toUpperCase().charAt(0);

            if (opcaoTipoQuestao == 'D') {
                System.out.println("Beleza, então será descritiva!");
            } else {
                ArrayList<String> options = new ArrayList<String>();

                System.out.println("Beleza, então sera objetiva!");
                int contadosOpcoes = 0;

                String opcaoPergunta;
                Scanner addOpcaoPergunta = new Scanner(System.in).useDelimiter("\n");

                while (contadosOpcoes < 4) {
                    int opcao = contadosOpcoes + 1;

                    System.out.print("Infome a opção " + opcao + ") ");
                    opcaoPergunta = addOpcaoPergunta.nextLine();

                    options.add(contadosOpcoes, opcaoPergunta);

                    contadosOpcoes++;
                }
            }

            System.out.println("Informe a resposta correta para a pergunta [" + i + "]");

            Scanner addRespostaCorreta = new Scanner(System.in).useDelimiter("\n");
            respostaCorreta = addRespostaCorreta.next();

            Questao questao = new Questao();
            questao.setPergunta(pergunta);
            questao.setRespostaCorreta(respostaCorreta);

            System.out.println("Essa questão acumulará pontos? (S) Sim    (N) Não");

            Scanner addAcumuloPontos = new Scanner(System.in).useDelimiter("\n");
            char opcaoAcumularPontos = addAcumuloPontos.next().toUpperCase().charAt(0);

            questao.setPontuacao(opcaoAcumularPontos == 'S');

            questoes.add(questao);
        }

        System.out.println("Você inseriu as perguntas: " + questoes);

        if (statusQuiz == 2) {
            inicializaQuestionario();
        } else {
            System.out.println("Este questionário não pode ser inicializado pois não possui o status 'pronto'");
            System.out.println("Deseja alterar o status? (S) Sim    (N) Não");

            Scanner alteraTipoSituacao = new Scanner(System.in).useDelimiter("\n");
            char opcaoTipoSituacao = alteraTipoSituacao.next().toUpperCase().charAt(0);

            if (opcaoTipoSituacao == 'S') {
                situacaoQuiz();
            }
        }
    }

    public static void inicializaQuestionario() {
        int totalQuestoes = questoes.size();

        System.out.println("O questionário será inicializado com: " + totalQuestoes + " perguntas.");

        for (Questao questao : questoes) {
            String respostaJogador;

            System.out.println(questao.getPergunta());

            System.out.println("Insira a sua resposta: ");

            Scanner addRespostaJogador = new Scanner(System.in).useDelimiter("\n");
            respostaJogador = addRespostaJogador.next();

            if (respostaJogador.equals(questao.getRespostaCorreta())) {

                if (questao.getPontuacao()) {
                    respostasCorretas += 1;

                    System.out.println("Booa! Você acertou e somou 1 ponto. TOTAL: " + respostasCorretas);
                } else {
                    System.out.println("Booa! Você acertou mas não somou nenhum ponto pois a questão não valia pontos. TOTAL: " + respostasCorretas);
                }
            } else {
                System.out.println("Ops! Você errou. TOTAL: " + respostasCorretas);
            }
        }

        System.out.println("Obrigado por participar! Até logo.");
    }
}
