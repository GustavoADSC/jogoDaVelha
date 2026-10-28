package jogador;

import tabuleiro.Marca;
import tabuleiro.Tabuleiro;

public class JogadorAutomatico implements Jogador
{
    private Marca marca = null;
    private Tabuleiro tabDoJogo = null;

    // Construtor que decide a marca do JogadorAutomatico aleatoriamente.
    public JogadorAutomatico (Tabuleiro tab) throws Exception
    {
        if (tab == null) throw new Exception("Tabuleiro inexistente.");

        this.tabDoJogo = tab;

        if ((int)(Math.random() * 2) == 0){
            this.marca = new Marca('x');
        }
        else {
            this.marca = new Marca('o');
        }
    }

    // Construtor que recebe a marca que sobrou, quando o JogadorHumano começa e escolhe primeiro.
    public JogadorAutomatico (Tabuleiro tab, Marca mrc) throws Exception
    {
        if (tab == null || mrc == null) throw new Exception("Tabuleiro e/ou marca faltantes.");

        this.tabDoJogo = tab;
        this.marca = mrc;
    }

    public Marca getMarca ()
    {
        return this.marca;
    }

    // nao trata as excecoes aqui: se algo der errado, quem chamou fica sabendo
    // em vez do computador "passar a vez" sem jogar
    public void facaSuaJogada () throws Exception
    {
        // 1) tentando ganhar (2 marcas minhas e 1 casa vazia)
        for (int[] l : Tabuleiro.LINHAS_VENCEDORAS)
        {
            // as 3 casas dessa linha/coluna/diagonal
            Marca a = this.tabDoJogo.getMarcaNaPosicao(l[0], l[1]);
            Marca b = this.tabDoJogo.getMarcaNaPosicao(l[2], l[3]);
            Marca c = this.tabDoJogo.getMarcaNaPosicao(l[4], l[5]);

            if (this.marca.equals(a) && this.marca.equals(b) && c == null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[4], l[5]); // falta a casa c
                return;
            }
            else if (this.marca.equals(a) && this.marca.equals(c) && b == null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[2], l[3]); // falta a casa b
                return;
            }
            else if (this.marca.equals(b) && this.marca.equals(c) && a == null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[0], l[1]); // falta a casa a
                return;
            }
        }

        // 2) tentando atrapalhar vitoria (2 marcas do adversario e 1 casa vazia)
        Marca mrcAdv = new Marca(this.marca.getSimbolo() == 'x' ? 'o' : 'x');

        for (int[] l : Tabuleiro.LINHAS_VENCEDORAS)
        {
            Marca a = this.tabDoJogo.getMarcaNaPosicao(l[0], l[1]);
            Marca b = this.tabDoJogo.getMarcaNaPosicao(l[2], l[3]);
            Marca c = this.tabDoJogo.getMarcaNaPosicao(l[4], l[5]);

            if (mrcAdv.equals(a) && mrcAdv.equals(b) && c == null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[4], l[5]); // atrapalha na casa c
                return;
            }
            else if (mrcAdv.equals(a) && mrcAdv.equals(c) && b == null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[2], l[3]); // atrapalha na casa b
                return;
            }
            else if (mrcAdv.equals(b) && mrcAdv.equals(c) && a == null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[0], l[1]); // atrapalha na casa a
                return;
            }
        }

        // 3) tentando jogada de futuro (1 marca minha e as outras 2 casas vazias)
        for (int[] l : Tabuleiro.LINHAS_VENCEDORAS)
        {
            Marca a = this.tabDoJogo.getMarcaNaPosicao(l[0], l[1]);
            Marca b = this.tabDoJogo.getMarcaNaPosicao(l[2], l[3]);
            Marca c = this.tabDoJogo.getMarcaNaPosicao(l[4], l[5]);

            if (this.marca.equals(a) && b == null && c == null)
            {
                int i = Math.random() < 0.5 ? 2 : 4;   // sorteia b ou c
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[i], l[i+1]);
                return;
            }
            else if (this.marca.equals(b) && a == null && c == null)
            {
                int i = Math.random() < 0.5 ? 0 : 4;   // sorteia a ou c
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[i], l[i+1]);
                return;
            }
            else if (this.marca.equals(c) && a == null && b == null)
            {
                int i = Math.random() < 0.5 ? 0 : 2;   // sorteia a ou b
                this.tabDoJogo.setMarcaNaPosicao(this.marca, l[i], l[i+1]);
                return;
            }
        }

        // 4) termine jogando num lugar qualquer que esteja livre (sorteado)
        //    so chega aqui com o jogo em andamento, entao sempre existe casa livre
        for (;;)
        {
            int lin = (int)(Math.random() * 3);   // 0, 1 ou 2
            int col = (int)(Math.random() * 3);

            if (!this.tabDoJogo.haMarcaNaPosicao(lin, col))
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, lin, col);
                return;
            }
        }
    }
}
