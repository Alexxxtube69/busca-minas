import tablero.Tablero;

import java.util.Scanner;

public class Buscaminas {

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
                        break;
                    case 2:
                        System.out.println("Has escogido el nivel Medio");
                        break;
                    case 3:
                        System.out.println("Has escogido el nivel Dificil");

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

    public static int[] pedirCordenadas() {
        Scanner stdin = new Scanner(System.in);
        int[] cordenadas = new int[2];
        boolean datosValidos = false;
        String[] cordenadasBrutas;

        while (!datosValidos) {
            System.out.print("Introduce las coordenadas: ");
            cordenadasBrutas = stdin.nextLine().trim().split(" ");
            for (int i = 0; i < 2; i++) {
                try {
                    cordenadas[i] = Integer.parseInt(cordenadasBrutas[i]);
                } catch (Exception e) {
                    System.out.println("Las cordenadas tienen que ser enteros");
                    break;
                }

                datosValidos = true;
            }
        }

        return cordenadas;
    }
    public static void iniciarJuego() {



            final Tablero tablero = new Tablero(10);
            System.out.println(tablero);
            int[] cordenadas = pedirCordenadas();
            tablero.ponerMinas(cordenadas[0], cordenadas[1]);
            System.out.println(tablero);




    }
}



