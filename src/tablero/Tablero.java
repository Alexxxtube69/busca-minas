package tablero;

import tablero.casilla.Casilla;
import tablero.casilla.TipoCasilla;
import tablero.excepciones.CasillaMarcadaAlterada;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private Casilla[][] matriz;

    public Tablero(int medida) {
        this.matriz = new Casilla[medida][medida];
        // Inicializando las casillas
        for (int i = 0; i < medida; i++) {
            for (int j = 0; j < medida; j++) {
                this.matriz[i][j] = new Casilla(TipoCasilla.VACIO);
            }
        }
    }

    /**
     * Llena el tablero de minas. Requiere de una posicion inicial
     * para no poner una mina en esa posicion ya que es el primer
     * click del jugador.
     */
    public void ponerMinas(int longitude, int latitude) {
        // Lo que haremos sera ir fila por fila y poner dos minas por fila

        // Iteramos sobre las filas
        for (int y = 0; y < this.matriz.length; y++) {
            Casilla[] fila = this.matriz[y];
            ArrayList<Integer> posicionesMinas = this.obtenerNumerosAleatorios(2, fila.length);

            // Si estamos iterando la fila que nos ha dicho el usuario
            // y ademas en la casilla de esa fila tendria que ir una mina
            // repetimos esta iteracion del bucle
            if (posicionesMinas.contains(longitude) && y == latitude) {
                y--;
                continue;
            }

            for (int x = 0; x < fila.length; x++) {
                if (x == longitude && y == latitude) {
                    fila[x] = new Casilla(TipoCasilla.VACIO);
                    try {
                        fila[x].setVisible();
                    } catch (CasillaMarcadaAlterada e) {
                        System.exit(1);
                    }
                    continue;
                }

                // Poniendo mina en la posicion obtenida aleatoriamente
                if (posicionesMinas.contains(x)) {
                    int x1 = x - 1;
                    int y1 = y - 1;
                    int x2 = x + 1;
                    int y2 = y + 1;

                    fila[x] = new Casilla(TipoCasilla.MINA);
                    // Poner pistas en las casillas de al rededor
                    if (y == 0) {
                        y1 = y;
                    }
                    if (x == 0) {
                        x1 = x;
                    }
                    if (y == this.matriz.length - 1) {
                        y2 = y;
                    }
                    if (x == this.matriz.length - 1) {
                        x2 = x;
                    }

                    incrementarPistas(x1, y1, x2, y2);
                }
            }
        }
    }

    /**
     * Este metodo devolvera la cantidad de numeros aleatorios especificada sin que estos se repitan.
     * Este metodo usa dos arrays. Uno con los numeros entre 0 y max. Si queremos generar varios numeros
     * aleatorios sin que estos se repitan no podemos depender de la suerte que tengamos al generar esos numeors
     * y repetir si se repiten. Por eso usamos dos arrays. Uno con los numeros que no se han usado y otro
     * con los numeros que vamos a devolver.
     *
     * @param cantidad
     * @param max
     * @return Un array con los numeros generados
     */
    private ArrayList<Integer> obtenerNumerosAleatorios(int cantidad, int max) {
        ArrayList<Integer> numeros = new ArrayList<>(max);
        ArrayList<Integer> numerosGenerados = new ArrayList<>(cantidad);
        Random ranGen = new Random();

        // Rellenando el array con los numeros entre 0 y max
        for (int i = 0; i < max; i++) {
            numeros.add(i);
        }

        for (int i = 0; i < cantidad; i++) {
            // Obteniendo los numeros del array de numeros disponibles
            int indiceNumeros = ranGen.nextInt(0, numeros.size());
            int numAleatorio = numeros.get(indiceNumeros);

            numerosGenerados.add(numAleatorio);
        }

        return numerosGenerados;
    }

    /**
     * Este metodo devuelve el tablero en forma de String
     *
     * @return
     */
    public String toString() {
        StringBuilder tableroString = new StringBuilder();

        for (Casilla[] fila : this.matriz) {
            for (Casilla casilla : fila) {
                tableroString.append(" " + casilla + " ");
            }
            tableroString.append("\n");
        }

        return tableroString.toString();
    }

    private void incrementarPistas(int x1, int y1, int x2, int y2) {
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                Casilla casilla = this.matriz[y][x];

                if (casilla.getTipo().equals(TipoCasilla.MINA)) {
                    continue;
                }

                casilla.setPista(casilla.getPista() + 1);
            }
        }
    }

    public void setVisible() {
        for (Casilla[] fila : this.matriz) {
            for (int x = 0; x < fila.length; x++) {
                Casilla casilla = fila[x];

                try {
                    casilla.setVisible();
                } catch (CasillaMarcadaAlterada e) {
                    try {
                        casilla.desmarcar();
                    } catch (Exception exception) {
                        // Si de verdad llegamos aqui podemos irnos a la mierda con tranquilidad
                        continue;
                    }
                }
            }
        }
    }
}
