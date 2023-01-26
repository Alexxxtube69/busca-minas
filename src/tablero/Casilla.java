package tablero;

import tablero.excepciones.CasillaMarcadaAlterada;
import tablero.excepciones.CasillaVisibleAlterada;

public enum Casilla {
    VACIO,
    MINA,
    PISTA;

    private EstadoCasilla estado;
    private int pista;
    private String valor;

    Casilla() {
        this.pista = 0;
        this.estado = EstadoCasilla.OCULTO;
        this.valor = this.estado.getValor();
    }

    /**
     * Marca una casilla siempre y cuando sea una casilla oculta
     * @throws CasillaVisibleAlterada
     */
    public void marcar() throws CasillaVisibleAlterada {
        if (this.estado.equals(EstadoCasilla.VISIBLE)) {
            throw new CasillaVisibleAlterada("La casilla es visible no se puede marcar");
        }

        this.valor = EstadoCasilla.MARCADO.getValor();
    }

    public void setVisible() throws CasillaMarcadaAlterada {
        if (this.estado.equals(EstadoCasilla.MARCADO)) {
            throw new CasillaMarcadaAlterada("La casilla esta marcada desmarcala antes de hacerla visible");
        }

        if (this.equals(Casilla.MINA)) {
            this.valor = "B";
        } else if (this.equals(Casilla.VACIO)) {
            this.valor = EstadoCasilla.VISIBLE.getValor();
        } else if (this.equals(Casilla.PISTA)) {
            this.valor = this.pista + "";
        }
    }

    public void setPista(int pista) {
        this.pista = pista;

        if (this.estado.equals(EstadoCasilla.VISIBLE) && this.equals(Casilla.PISTA)) {
            this.valor = pista + "";
        }
    }

    public int getPista() {
        return this.pista;
    }

    public String toString() {
        return " " + this.valor + " ";
    }
}
