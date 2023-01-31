package tablero.casilla;

import tablero.excepciones.CasillaMarcadaAlterada;
import tablero.excepciones.CasillaVisibleAlterada;

public class Casilla {
    TipoCasilla tipo;
    boolean visible;
    int valor;
    boolean marcada;

    public Casilla(TipoCasilla tipo) {
        this.tipo = tipo;
        this.visible = false;
        this.marcada = false;
        this.valor = 0;
    }

    public void setVisible() throws CasillaMarcadaAlterada {
        if (this.visible) {
            return;
        }

        if (this.marcada) {
            throw new CasillaMarcadaAlterada("La casilla esta marcada");
        }

        this.visible = true;
    }

    public void setOculto() throws CasillaMarcadaAlterada {
        if (!this.visible) {
            return;
        }

        if (this.marcada) {
            throw new CasillaMarcadaAlterada("La casilla esta marcada");
        }

        this.visible = false;
    }

    public void marcar() throws CasillaVisibleAlterada {
        if (this.visible) {
            throw new CasillaVisibleAlterada("La casilla es visible");
        }

        this.marcada = true;
    }

    public void desmarcar() throws CasillaVisibleAlterada {
        if (this.visible) {
            throw new CasillaVisibleAlterada("La casilla es visible");
        }

        this.marcada = false;
    }

    public void setPista(int valor) {
        this.tipo = TipoCasilla.PISTA;
        this.valor = valor;
    }

    public int getPista() {
        return this.valor;
    }

    public TipoCasilla getTipo() {
        return this.tipo;
    }

    public String toString() {
        if (!this.visible) {
            return "X";
        }

        if (this.marcada) {
            return "-";
        }

        if (this.tipo.equals(TipoCasilla.MINA)) {
            return "M";
        }

        if (this.tipo.equals(TipoCasilla.PISTA)) {
            return this.valor + "";
        }

        return "_";
    }
}
