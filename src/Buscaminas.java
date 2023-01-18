import java.util.Scanner;

public class Buscaminas {
    public static void main(String[] args) {

        Scanner lector = new Scanner(System.in);
        int menu1;
        int menu2;

        menuprincipal();
        menu1 = lector.nextInt();


        switch (menu1) {

            case 1:
                menujuego();
                menu2 = lector.nextInt();

                switch (menu2) {
                    case 1:
                        nivell();

                    case 2:
                        nivell();
                        ;

                    case 3:
                        nivell();
                }

                break;

            case 2:
                informacionjuego();

                break;

            case 3:
                creditos();

                break;

            case 4:
                System.out.println("Adeu ");

                break;


        }

    }

    private static void creditos() {
        System.out.println("Programa creado por:");
        System.out.println("Alexsander Acuña Garcia");
        System.out.println("Juan Alejandro Marin Ruiz");
        System.out.println("Oriol Puigdemont Martínez");
        System.out.println("17/01/2023");
    }

    private static void informacionjuego() {
        System.out.println("El Juego Trata de ir colocando una bandera en donde crees que tienes una mina");
        System.out.println("Tienes 10 Banderas y 10 minas el jeugo se acaba cuando pises una mina o ganes la partida");
        System.out.println("Para detectar una biena te iran saliendo 1 o 2 el 2 te dice que tienes una mina serca");
        System.out.println("Si ganas te saldra un mensaje con texto y si pierdes tambien ");


    }

    private static void menujuego() {

        int nivell = 0;

        System.out.println("Elije El Nivel De Difultad ");
        System.out.println("1-Nivel Facil");
        System.out.println("2-Nivel Medio");
        System.out.println("3-Nivel Dificil");

    }

    private static void menuprincipal() {
        System.out.println("Hola Bien venido al Busca Minas");
        System.out.println("--------Menus--------");
        System.out.println("=====================");
        System.out.println("1-Jugar");
        System.out.println("2-Como se juega");
        System.out.println("3-Creditos");
        System.out.println("4-Salir");
        System.out.println("=====================");

    }

    private static void nivell() {
        Scanner leer = new Scanner(System.in);
        int tablero[][] = new int[20][20];

        double w = 0;
        double z = 0;

        int ntminas = 0;

        int intento = 75;

        int tminas = 10;

        for (int j = 0; j < 20; j++)
            for (int i = 0; i < 20; i++)
                tablero[j][i] = 0;

        do {
            w = Math.random() * 20;
            z = Math.random() * 20;
            w = (int) w;
            z = (int) z;
            if (z != 0 && w != 0 && z != 20 - 1 && w != 20 - 1) {
                tablero[(int) w][(int) z] = 1;
                ntminas++;
            }
        } while (ntminas <= 10);


        System.out.println("\n-x----------------------------------------------------x-");
        System.out.println("  Ingresa las coordenas en el rango del 1 al 20");
        do {
            int x = 0, y = 0;
            int nminas = 0;

            do {
                System.out.println("\n-x----------------------------------------------------x-");
                System.out.println("\n	Intento :" + intento + " , Cual es su coordenada? \n");
                System.out.print("		Fila:  ");
                x = leer.nextInt();
                System.out.println();
                System.out.print("		Columna:  ");
                y = leer.nextInt();

                if (x == 0 || y == 0 || x > 20 || y > 20)
                    System.out.println("\n	==>Coordenadas incorrectas<== \n");
            } while (x == 0 || y == 0 || x > 20 || y > 20);

            x = x - 1;
            y = y - 1;

            if (tablero[x][y] == 0) {

                if (x == 0 && y == 0) {
                    if (x + 1 >= 0 && x + 1 < 20 && y < 20)
                        nminas = nminas + tablero[x + 1][y];
                    if (x + 1 >= 0 && x + 1 < 20 && y + 1 < 20 && y + 1 >= 0)
                        nminas = nminas + tablero[x + 1][y + 1];
                    if (x >= 0 && x < 20 && y + 1 < 20 && y + 1 >= 0)
                        nminas = nminas + tablero[x][y + 1];
                } else {
                    if (y == 0) {
                        if (x + 1 >= 0 && x + 1 < 20 && y < 20)
                            nminas = nminas + tablero[x + 1][y];
                        if (x - 1 >= 0 && x - 1 < 20 && y < 20)
                            nminas = nminas + tablero[x - 1][y];
                        if (x - 1 >= 0 && x - 1 < 20 && y + 1 < 20 && y + 1 >= 0)
                            nminas = nminas + tablero[x - 1][y + 1];
                        if (x >= 0 && x < 20 && y + 1 < 20 && y + 1 >= 0)
                            nminas = nminas + tablero[x][y + 1];
                        if (x + 1 >= 0 && x + 1 < 20 && y + 1 < 20 && y + 1 >= 0)
                            nminas = nminas + tablero[x + 1][y + 1];

                    } else {

                        if (x - 1 >= 0 && x - 1 < 20 && y - 1 < 20)
                            nminas = nminas + tablero[x - 1][y - 1];
                        if (x >= 0 && x < 20 && y - 1 < 20)
                            nminas = nminas + tablero[x][y - 1];
                        if (x + 1 >= 0 && x + 1 < 20 && y - 1 < 20)
                            nminas = nminas + tablero[x + 1][y - 1];
                        if (x + 1 >= 0 && x + 1 < 20 && y < 20)
                            nminas = nminas + tablero[x + 1][y];
                        if (x - 1 >= 0 && x - 1 < 20 && y < 20)
                            nminas = nminas + tablero[x - 1][y];
                        if (x - 1 >= 0 && x - 1 < 20 && y + 1 < 20 && y + 1 >= 0)
                            nminas = nminas + tablero[x - 1][y + 1];
                        if (x >= 0 && x < 20 && y + 1 < 20 && y + 1 >= 0)
                            nminas = nminas + tablero[x][y + 1];
                        if (x + 1 >= 0 && x + 1 < 20 && y + 1 < 20 && y + 1 >= 0)
                            nminas = nminas + tablero[x + 1][y + 1];
                    }
                }
                System.out.print("\n	Mina no encontrada. ");

                if (intento > tminas)
                    System.out.print("	Alrededor hay: " + nminas);
                System.out.println("\n	Intentos restantes: " + (intento - 1));
            } else {
                tminas = tminas - 1;
                System.out.println("\n	Acertaste!, Mina Eliminada Restan " + tminas + " Minas");
                System.out.println("\n	Intentos restantes: " + (intento - 1));
            }

            intento = intento - 1;
        } while (intento >= tminas && intento > 0 && tminas > 0);

        if (tminas == 0)
            System.out.println("		GANASTE!");

        if (tminas > intento)
            System.out.println("		PERDISTE!    (Exceso de minas restantes " + tminas + ")\n\n");

        if (intento == 0)
            System.out.println("		PERDISTE!");
    }
}
