package tablero.excepciones;

public class MinaExplotada extends Exception {
    public MinaExplotada() {
        super("Ha explotado una mina");
    }
}
