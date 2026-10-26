public class Tabuleiro implements Cloneable
{
    // a principio, toda igual a null
    private Marca matriz [][] = new Marca [3][3];

    public boolean haMarcaNaPosicao (int lin, int col) throws Exception
    {
        if(lin < 0 || col < 0 || lin > 3 || col > 3) throw new Exception("Linhas e colunas precisam ser numeros inteiros positivos e menor ou igual a 3.");
        return matriz[lin][col] == null;
    }

    public Marca getMarcaNaPosicao (int lin, int col) throws Exception
    {
        if(lin < 0 || col < 0 || lin > 3 || col > 3) throw new Exception("Linhas e colunas precisam ser numeros inteiros positivos e menor ou igual a 3.");
        return matriz[lin][col];
    }

    public void setMarcaNaPosicao (Marca mrc, int lin, int col) throws Exception
    {
        if(lin < 0 || col < 0 || lin > 3 || col > 3) throw new Exception("Linhas e colunas precisam ser numeros inteiros positivos e menor ou igual a 3.");
        if(mrc == null) throw new Exception("A marca não pode ser nulo.");
        matriz[lin][col] = mrc;
    }

    @Override
    public String toString ()
    {
        // retornar em um String o tabuleiro
        // formatado para ser exibido na tela
    }

    @Override
    public boolean equals (Object obj)
    {
        // retornar true se this for igual a obj ou false caso contrario
    }

    @Override
    public int hashCode ()
    {
        // faça
    }

    // construtor de copia
    public Tabuleiro (Tabuleiro modelo) throws Exception
    {
        // faça
    }

    @Override
    public Object clone ()
    {
        // faça
    }
}