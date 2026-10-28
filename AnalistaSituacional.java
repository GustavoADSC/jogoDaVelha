
import tabuleiro.Marca;
import tabuleiro.Tabuleiro;

public class AnalistaSituacional {

    private Tabuleiro tabDoJogo;

    public AnalistaSituacional(Tabuleiro tab) throws Exception {
        if (tab == null) {
            throw new Exception("Tabuleiro inválido!");
        }

        this.tabDoJogo = tab;
    }

    public Marca getMarcaDoVencedor() {
        try {
            for (int[] l : Tabuleiro.LINHAS_VENCEDORAS) {
                Marca a = tabDoJogo.getMarcaNaPosicao(l[0], l[1]);
                Marca b = tabDoJogo.getMarcaNaPosicao(l[2], l[3]);
                Marca c = tabDoJogo.getMarcaNaPosicao(l[4], l[5]);

                if (a != null && a.equals(b) && a.equals(c)) {
                    return a;
                }
            }
        } catch (Exception e) {
            // nao ocorre, pois as posicoes acima sao sempre validas
        }

        return null;
    }

    private boolean tabuleiroCheio() {
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!tabDoJogo.haMarcaNaPosicao(i, j)) {
                        return false;
                    }
                }
            }
        } catch (Exception err) {
            // nao ocorre, pois as posicoes acima sao sempre validas
            return false;
        }

        return true;
    }

    public boolean fimDeJogo() {
        return this.getMarcaDoVencedor() != null || this.tabuleiroCheio();
    }

    @Override
    public String toString() {
        Marca vencedor = this.getMarcaDoVencedor();

        if (vencedor != null) {
            return "Vencedor: " + vencedor;
        }
        if (this.tabuleiroCheio()) {
            return "Empate";
        }

        return "Jogo em andamento";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        AnalistaSituacional outro = (AnalistaSituacional) obj;
        return this.tabDoJogo.equals(outro.tabDoJogo);
    }

    @Override
    public int hashCode() {
        return this.tabDoJogo.hashCode();
    }
}
