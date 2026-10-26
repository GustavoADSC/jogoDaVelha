package jogador;

import tabuleiro.*;

import java.io.*;
import java.util.Scanner;

public class JogadorHumano implements Jogador
{

    private Scanner sc = new Scanner(System.in);
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
//    private BufferedReader teclado   = new BufferedReader
//                                        (new InputStreamReader
//                                        (System.in));

    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        if (tab == null) throw new Exception("Tabuleiro não deve ser nulo");

        this.tabDoJogo = tab;
    }

    public JogadorHumano (Tabuleiro tab, Marca mrc) throws Exception
    {
        // atribuir tab para tabDoJogo
        // e mrc para marca
        if (tab == null || mrc == null) throw new Exception("Tabuleiro e Marca devem conter valores diferentes de null");

        this.tabDoJogo = tab;
        this.marca = mrc;
    }

    public Marca getMarca ()
    {
        return this.marca;
    }

    public void facaSuaJogada () throws Exception {
        // pergunta para o jogador onde ele quer
        // jogar, permitindo digitacao e chamando
        // os métodos apropriados de tabDoJogo

        System.out.println("Onde você quer jogar? digite no formato 'linha-coluna', separado por traço");
        String onde = sc.nextLine().strip().replace("-", "");
        int linha = Integer.valueOf(onde.charAt(0));
        int coluna = Integer.valueOf(onde.charAt(0));

        boolean temErro = false;
        if (linha < 1 || linha > 3) {
            System.out.println("A linha deve estar entre 1 e 3");
            temErro = true;
        }

        if (coluna < 1 || coluna > 3){
            System.out.println("A coluna deve estar entre 1 e 3");
            temErro = true;
        }

        if (temErro) return;

        if (tabDoJogo.haMarcaNaPosicao(linha, coluna)) {
            System.out.println("Posição já ocupada");
            return;
        }

        char marcaInput = sc.nextLine().strip().charAt(0);
        try {
            Marca marca = new Marca(marcaInput);
            tabDoJogo.setMarcaNaPosicao(marca, linha - 1, coluna - 1);


        } catch (Exception e){
            System.out.println("Marca inválida");
            return;
        }


    }

    public void vcGanhou ()
    {
        System.out.println("Parabéns, vocẽ ganhou.");
    }

    public void vcPerdeu ()
    {

        System.err.println("Parabéns, vocẽ perdeu.");
    }

    public void deuVelha ()
    {

        System.out.println("Parabéns, vocẽ empatou.");
    }

    public boolean querMaisUmaPartida () {

        char res;
        do{
            // perguntar se deseja jogar de novo
            System.out.printf("Quer jogar de novo? [S/N]: ");
            res = Character.toLowerCase(this.sc.nextLine().charAt(0));

        } while (res != 's' && res != 'n');

        // permitindo digitacao e retornando

        return res == 's';
        // true ou false, conforme a resposta
    }
}