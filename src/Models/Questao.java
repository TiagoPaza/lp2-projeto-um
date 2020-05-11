package Models;

public class Questao {
    private String pergunta;
    private String respostaCorreta;
    private boolean pontuacao;

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public boolean getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(boolean pontuacao) {
        this.pontuacao = pontuacao;
    }
}
