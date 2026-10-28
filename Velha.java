
import jogador.*;
import tabuleiro.Marca;
import tabuleiro.Tabuleiro;

public class Velha {

    public static void main(String args[]) {
        Tabuleiro tabDoJogo;

        Jogador jogador = null;
        JogadorHumano jogadorH = null;
        JogadorAutomatico jogadorA = null;

        AnalistaSituacional analista;

        boolean jogadorHumanoComeca = (int) (Math.random() * 2) == 0;

        do {
            try {
                tabDoJogo = new Tabuleiro();

                if (jogadorHumanoComeca) {
                    jogadorH = new JogadorHumano(tabDoJogo);
                    jogadorA = new JogadorAutomatico(tabDoJogo,
                            new Marca(jogadorH.getMarca().getSimbolo() == 'x' ? 'o' : 'x'));
                } else {
                    jogadorA = new JogadorAutomatico(tabDoJogo);
                    jogadorH = new JogadorHumano(tabDoJogo,
                            new Marca(jogadorA.getMarca().getSimbolo() == 'x' ? 'o' : 'x'));
                }

                analista = new AnalistaSituacional(tabDoJogo);

                if (jogadorHumanoComeca) {
                    jogador = jogadorH; 
                }else {
                    jogador = jogadorA;
                }

                do {
                    if (jogador == jogadorH) {
                        System.out.println(tabDoJogo);
                    }

                    jogador.facaSuaJogada();

                    if (jogador == jogadorH) {
                        jogador = jogadorA; 
                    }else {
                        jogador = jogadorH;
                    }
                } while (!analista.fimDeJogo());

                System.out.println(tabDoJogo);

                Marca vencedor = analista.getMarcaDoVencedor();
                if (vencedor == null) {
                    jogadorH.deuVelha();
                }else if (vencedor.equals(jogadorH.getMarca())) {
                    jogadorH.vcGanhou();
                }else {
                    jogadorH.vcPerdeu();
                }

                jogadorHumanoComeca = !jogadorHumanoComeca;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            // jogadorH so e null se deu erro antes de cria-lo (na primeira partida);
            // ai nao tem a quem perguntar, entao encerra
        } while (jogadorH != null && jogadorH.querMaisUmaPartida());
    }
}
