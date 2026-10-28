package tabuleiro;

public class Marca {

    private char simbolo;

    public Marca(char smb) throws Exception {
        char lower = Character.toLowerCase(smb);
        if (lower != 'x' && lower != 'o') {
            throw new Exception("Marca inválida!");
        }

        this.simbolo = lower;
    }

    public char getSimbolo() {
        return this.simbolo;
    }

    @Override
    public String toString() {
        return "" + simbolo;
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

        Marca outra = (Marca) obj;
        return this.simbolo == outra.simbolo;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(simbolo);
    }
}
