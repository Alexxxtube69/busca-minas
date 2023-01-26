package tablero;

import tablero.excepciones.CasillaMarcadaAlterada;
import tablero.excepciones.CasillaVisibleAlterada;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private Casilla[][] matriz;

    public Tablero(int medida) {
        this.matriz = new Casilla[medida][medida];
        // Inicializando las casillas
        for (int i = 0; i < medida; i++) {
            for (int j = 0; j < medida; j++) {
                this.matriz[i][j] = Casilla.VACIO;
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
        for(int y = 0; y < this.matriz.length; y ++) {
            Casilla[] fila = this.matriz[y];
            ArrayList<Integer> posicionesMinas = this.obtenerNumerosAleatorios(2, fila.length);

            for(int x = 0; x < fila.length; x ++) {
                if (x == longitude && y == latitude) {
                    System.out.println("Casilla encontrada");
                    fila[x] = Casilla.VACIO;
                    try {
                        fila[x].setVisible();
                    } catch(CasillaMarcadaAlterada e) {
                        System.out.println(e);
                        System.exit(1);
                    }
                    continue;
                }

                // Poniendo mina en la posicion obtenida aleatoriamente
                if (posicionesMinas.contains(x)) {
                    fila[x] = Casilla.MINA;
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
     * @return
     */
    public String toString() {
        StringBuilder tableroString = new StringBuilder();

        for (Casilla[] fila: this.matriz) {
            for (Casilla casilla : fila) {
                tableroString.append(casilla);
            }
            tableroString.append("\n");
        }

        return tableroString.toString();
    }
}
