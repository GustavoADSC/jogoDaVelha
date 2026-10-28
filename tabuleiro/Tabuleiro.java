package tabuleiro;

public class Tabuleiro implements Cloneable
{
    // as 8 trincas que dao vitoria, cada uma como {lin, col, lin, col, lin, col}
    public static final int[][] LINHAS_VENCEDORAS = {
            {0, 0, 0, 1, 0, 2}, // linha 0
            {1, 0, 1, 1, 1, 2}, // linha 1
            {2, 0, 2, 1, 2, 2}, // linha 2
            {0, 0, 1, 0, 2, 0}, // coluna 0
            {0, 1, 1, 1, 2, 1}, // coluna 1
            {0, 2, 1, 2, 2, 2}, // coluna 2
            {0, 0, 1, 1, 2, 2}, // diagonal principal
            {0, 2, 1, 1, 2, 0} // diagonal secundaria
        };

    // a principio, toda igual a null
    private Marca matriz [][] = new Marca [3][3];
    
    public Tabuleiro() {}

    public boolean haMarcaNaPosicao (int lin, int col) throws Exception
    {
        if(lin < 0 || col < 0 || lin >= 3 || col >= 3) throw new Exception("Linhas e colunas precisam ser numeros inteiros positivos e menor que 3.");
        return matriz[lin][col] != null;
    }

    public Marca getMarcaNaPosicao (int lin, int col) throws Exception
    {
        if(lin < 0 || col < 0 || lin >= 3 || col >= 3) throw new Exception("Linhas e colunas precisam ser numeros inteiros positivos e menor que 3.");
        return matriz[lin][col];
    }

    public void setMarcaNaPosicao (Marca mrc, int lin, int col) throws Exception
    {
        if(lin < 0 || col < 0 || lin >= 3 || col >= 3) throw new Exception("Linhas e colunas precisam ser numeros inteiros positivos e menor que 3.");
        if(mrc == null) throw new Exception("A marca não pode ser nulo.");
        matriz[lin][col] = mrc;
    }

    @Override
    public String toString ()
    {
        String retorno = "";

        for (int lin = 0; lin < 3; lin++)
        {
            for (int col = 0; col < 3; col++)
            {
                if (matriz[lin][col] == null)
                    retorno += "   ";
                else
                    retorno += " " + matriz[lin][col] + " "; // usa o toString da Marca

                if (col < 2) retorno += "|";
            }

            retorno += "\n";
            if (lin < 2) retorno += "---+---+---\n";
        }

        return retorno;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!this.getClass().equals(obj.getClass())) return false;
        Tabuleiro outro = (Tabuleiro)obj;

        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
        
                if (this.matriz[lin][col] == null) {
                    if (outro.matriz[lin][col] != null) return false;
                }
                else if (!this.matriz[lin][col].equals(outro.matriz[lin][col]))
                    return false;
            }
        }
        return true;
    }

        @Override
    public int hashCode ()
    {
        int ret = 1; // qualquer número positivo

        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                ret = 7 * ret; // primo qualquer

                if (matriz[lin][col] != null)
                    ret = ret + matriz[lin][col].hashCode();
            }
        }
        if (ret < 0) ret = -ret;

        return ret;
    }

    // construtor de copia
    public Tabuleiro (Tabuleiro modelo) throws Exception
    {
        if (modelo == null) throw new Exception("O modelo não pode ser nulo.");

        this.matriz = new Marca[3][3];

        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                this.matriz[lin][col] = modelo.matriz[lin][col];
            }
        }
    }

    @Override
    public Object clone ()
    {
        // copia direto, sem passar pelo construtor de copia, que lanca Exception
        Tabuleiro ret = new Tabuleiro();

        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                ret.matriz[lin][col] = this.matriz[lin][col];
            }
        }

        return ret;
    }
}