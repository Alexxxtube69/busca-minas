package tablero.excepciones;

public class CasillaMarcadaAlterada extends Exception{
    public CasillaMarcadaAlterada(String errorMessage) {
        super(errorMessage);
    }
}
