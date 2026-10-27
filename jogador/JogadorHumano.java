package jogador;

import tabuleiro.*;

import java.io.*;
import java.util.Scanner;

public class JogadorHumano implements Jogador
{

    // static: um Scanner so para todas as partidas; varios Scanners no System.in perdem entrada
    private static Scanner sc = new Scanner(System.in);
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
//    private BufferedReader teclado   = new BufferedReader
//                                        (new InputStreamReader
//                                        (System.in));

    // Construtor que pergunta ao usuário qual marca o JogadorHumano quer ter.
    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        if (tab == null) throw new Exception("Tabuleiro não deve ser nulo");
        
        // Pergunta a marca que o usuário quer ter.
        Marca mrc = null;
        while (mrc == null)
        {
            System.out.println("Qual a marca deseja [x][o]?");
            String resposta = this.sc.nextLine().strip();

            try {
                mrc = new Marca(resposta.charAt(0));
            } catch (Exception e) {
                System.out.println("Marca inválida, digite x ou o.");
            }
        }

        this.tabDoJogo = tab;
        this.marca = mrc;
    }

    // Construtor que recebe a marca que sobrou, quando o JogadorAutomatico começa e sorteia primeiro.
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

        // repete ate o jogador digitar uma jogada valida
        for (;;) {
            System.out.println("Onde você quer jogar? digite no formato 'linha-coluna', separado por traço(-)");
            String onde = sc.nextLine().strip().replace("-", "");

            if (onde.length() != 2 || !Character.isDigit(onde.charAt(0)) || !Character.isDigit(onde.charAt(1))) {
                System.out.println("Formato inválido, digite por exemplo 1-3");
                continue;
            }

            // '1' - '0' == 1, '2' - '0' == 2, ...
            int linha = onde.charAt(0) - '0';
            int coluna = onde.charAt(1) - '0';

            boolean temErro = false;
            if (linha < 1 || linha > 3) {
                System.out.println("A linha deve estar entre 1 e 3");
                temErro = true;
            }

            if (coluna < 1 || coluna > 3){
                System.out.println("A coluna deve estar entre 1 e 3");
                temErro = true;
            }

            if (temErro) continue;

            // o usuario digita de 1 a 3, mas o tabuleiro usa de 0 a 2
            if (tabDoJogo.haMarcaNaPosicao(linha - 1, coluna - 1)) {
                System.out.println("Posição já ocupada");
                continue;
            }

            tabDoJogo.setMarcaNaPosicao(this.marca, linha - 1, coluna - 1);
            return;
        }
    }

    public void vcGanhou ()
    {
        System.out.println("Parabéns, voce ganhou.");
    }

    public void vcPerdeu ()
    {
        System.out.println("Que pena, voce perdeu. Tente de novo!");
    }

    public void deuVelha ()
    {
        System.out.println("Deu velha! Ninguem ganhou.");
    }

    public boolean querMaisUmaPartida () {

        char res;
        do{
            // perguntar se deseja jogar de novo
            System.out.printf("Quer jogar de novo? [S/N]: ");
            String resposta = this.sc.nextLine().strip();

            // se so apertou Enter, nao tem charAt(0); usa ' ' para o laco perguntar de novo
            if (resposta.isEmpty())
                res = ' ';
            else
                res = Character.toLowerCase(resposta.charAt(0));

        } while (res != 's' && res != 'n');

        return res == 's';
    }

}