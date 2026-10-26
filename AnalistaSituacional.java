
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

        int[][] linhasVencedoras = {
            {0, 0, 0, 1, 0, 2}, // linha 0
            {1, 0, 1, 1, 1, 2}, // linha 1
            {2, 0, 2, 1, 2, 2}, // linha 2
            {0, 0, 1, 0, 2, 0}, // coluna 0
            {0, 1, 1, 1, 2, 1}, // coluna 1
            {0, 2, 1, 2, 2, 2}, // coluna 2
            {0, 0, 1, 1, 2, 2}, // diagonal principal
            {0, 2, 1, 1, 2, 0} // diagonal secundaria
        };

        try {
            for (int[] l : linhasVencedoras) {
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
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
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
        return this.getMarcaDoVencedor() == null || this.tabuleiroCheio();
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

        if (obj instanceof AnalistaSituacional other) {
            return this.tabDoJogo.equals(other.tabDoJogo);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.tabDoJogo.hashCode();
    }
}
