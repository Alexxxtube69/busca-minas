import tablero.Tablero;
import tablero.excepciones.CasillaMarcadaAlterada;
import tablero.excepciones.CasillaVisibleAlterada;
import tablero.excepciones.MinaExplotada;

import java.util.Scanner;

public class Buscaminas {

    static final String opcionTablero = """
            Que accion quieres hacer?
            1. Marcar casilla
            2. Hacer casilla visible
            """;

    static final String menuPrincipal = """
            Hola Bienvenido Al Busca Minas.
            ----------Menus----------------
            ===============================
            1-Jugar
            2-Como se juega
            3-Creditos
            4-Salir
            ===============================
            """;

    static final String menuDificultad = """
            Elije El Nivel De Dificultad :
            1- Nivel Facil
            2- Nivel Medio
            3- Nivel Dificil
            """;

    static final String menuInformacionJuego = """
            El Juego trata de ir colocando una bandera en donde creas que tiene una mina.
            Tienes 10 bandera y 10 minas, el juego se acaba cuando te encuentres una mina o ganes la partida.
            Para detectar una  mina te iran saliendo el numero 1 o 2 el numero te dice que tienens una mina cerca.
            Cada ocho segundos transcurridos se le restara un punto al jugador.
            Si ganas te saldra un mensaje con texto y si pierdes tambien.
            """;

    static final String menuCreditos = """
            Programa creado por:
            Alexander Acuña Garcia
            Juan Alejandro Marin Ruiz
            Oriol Puigdemont Martínez
            """;
    static final String menuFinal = """
            ------------------------------------------
            El programa buscaminas a llegado a su fin.
            Gracias por su tiempo!
            ------------------------------------------
            """;

    static final String menujuego = """
                      
            Introduce Un  Nombre De Usuario
                      
                      2
            """;

    public static void main(String[] args) {
        Scanner lectoor = new Scanner(System.in);
        int opcion = mostrarMenu(menuPrincipal);

        switch (opcion) {
            case 1:
                System.out.println(menuDificultad);
                opcion = lectoor.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has ecogido el nivel Facil");
                        iniciarJuego(16);
                        break;
                    case 2:
                        System.out.println("Has escogido el nivel Medio");
                        iniciarJuego(32);
                        break;
                    case 3:
                        System.out.println("Has escogido el nivel Dificil");
                        iniciarJuego(48);
                }
                break;
            case 2:
                System.out.println(menuInformacionJuego);
                break;

            case 3:
                System.out.println(menuCreditos);
                break;
            case 4:
                System.out.println(menuFinal);
                break;
        }
    }


    public static int mostrarMenu(String menu) {
        Scanner lector = new Scanner(System.in);
        boolean datoInvalido = true;
        int opcion1 = 0;

        while (datoInvalido) {
            System.out.print(menu);
            try {
                opcion1 = Integer.parseInt(lector.nextLine());
                datoInvalido = false;
            } catch (Exception e) {
                System.out.println("Dato no valido, introduce un valor valido (numeros).");
            }
        }

        return opcion1;
    }

    public static int[] pedirCordenadas(int nCordenadas) {
        Scanner stdin = new Scanner(System.in);
        int[] cordenadas = new int[nCordenadas];
        boolean datosValidos = false;
        String cordenadasBrutas;

        while (!datosValidos) {
            try {
                System.out.print("Introduce las cordenadas: ");
                cordenadasBrutas = stdin.nextLine();
                cordenadas = convertirCoordenadas(cordenadasBrutas, nCordenadas);
                datosValidos = true;
            } catch (Exception e) {
                System.out.println("Debes introducir dos cordenadas separadas por espacios");
            }
        }

        return cordenadas;
    }

    /**
     * Inicia el tablero
     */
    public static void iniciarJuego(int medida) {
        // Creamos el tablero con el tamaño especificado
        final Tablero tablero = new Tablero(medida);
        boolean seguirJugando = true;
        System.out.println(tablero);


        int[] cordenadas = pedirCordenadas(2);
        // Al poner las minas le mandamos las cordenadas iniciales del usuario
        // Asi nos aseguramos de que no habra ninguna mina en esas cordenadas
        tablero.ponerMinas(cordenadas[0], cordenadas[1]);

        // Mostramos el tablero por pantalla
        System.out.println(tablero);

        while (seguirJugando) {
            int opcion = mostrarMenu(opcionTablero);
            if (opcion != 1 && opcion != 2) {
                continue;
            }

            switch (opcion) {
                case 1:
                    // marcar casilla
                    cordenadas = pedirCordenadas(2);
                    if (cordenadas[0] < 0 || cordenadas[0] >= medida) {
                        System.out.println("Cordenadas fuera de tablero");
                        continue;
                    }

                    if (cordenadas[1] < 0 || cordenadas[1] >= medida) {
                        System.out.println("Cordenadas fuera de tablero");
                        continue;
                    }

                    try {
                        tablero.marcarCasilla(cordenadas[0], cordenadas[1]);
                    } catch (CasillaVisibleAlterada e) {
                        System.out.println("Casilla visible");
                    }
                    break;
                case 2:
                    // hacer casilla visible
                    cordenadas = pedirCordenadas(2);
                    if (cordenadas[0] < 0 || cordenadas[0] >= medida) {
                        System.out.println("Cordenadas fuera de tablero");
                        continue;
                    }

                    if (cordenadas[1] < 0 || cordenadas[1] >= medida) {
                        System.out.println("Cordenadas fuera de tablero");
                        continue;
                    }

                    try {
                        tablero.setCasillaVisible(cordenadas[0], cordenadas[1]);
                    } catch (CasillaMarcadaAlterada e) {
                        System.out.println("Casilla marcada!");
                    } catch (MinaExplotada e) {
                        seguirJugando = false;
                        tablero.setVisible();
                    }
                    break;
            }

            System.out.println(tablero);
        }
    }

    public static int[] convertirCoordenadas(String cordenadasTexto, int numCoordenadas) {
        // Quitamos los posibles espacios con el metodo trim()
        // Separamos las cordenadas con el metodo split
        String[] cordenadasBrutas = cordenadasTexto.trim().split(" ");
        int[] cordenadas = new int[numCoordenadas];

        // Tratamos de convertir las cordenadas a numeros
        for (int i = 0; i < numCoordenadas; i++) {
            cordenadas[i] = Integer.parseInt(cordenadasBrutas[i]);
        }

        // Devolvemos las cordenadas convertidas a numeros
        return cordenadas;
    }
}



