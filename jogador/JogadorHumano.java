package jogador;

import tabuleiro.*;

import java.io.*;
import java.util.Scanner;

public class JogadorHumano implements Jogador
{
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
    private BufferedReader teclado   = new BufferedReader
                                        (new InputStreamReader
                                        (System.in));

    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        // atribuir tab para tabDoJogo
        // pedir para o jogador escolher
        // sua marca e permitir digitacao
        // intanciando marca
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

    public void facaSuaJogada ()
    {
        // pergunta para o jogador onde ele quer
        // jogar, permitindo digitacao e chamando
        // os métodos apropriados de tabDoJogo
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

    public static boolean querMaisUmaPartida () {

        Scanner sc = new Scanner(System.in);
        char res;
        do{
            // perguntar se deseja jogar de novo
            System.out.printf("Quer jogar de novo? [S/N]: ");
            res = Character.toLowerCase(sc.nextLine().charAt(0));

        } while (res != 's' && res != 'n');

        // permitindo digitacao e retornando

        return res == 's';
        // true ou false, conforme a resposta
    }
}