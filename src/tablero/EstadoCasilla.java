package tablero;

public enum EstadoCasilla {
    OCULTO("X"),
    MARCADO("-"),
    VISIBLE("_");

    private final String valor;

    EstadoCasilla(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
