import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner lector = new Scanner(System.in);
        int menu1;
        int menu2;

        menuprincipal();
        menu1 = lector.nextInt();


        switch (menu1) {

            case 1:
                menujuego();

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
        System.out.println("17/01/2023");
    }

    private static void informacionjuego() {
        System.out.println("El Juego Trata de ir colocando una bandera en donde crees que tienes una mina");
        System.out.println("Tienes 10 Banderas y 10 minas el jeugo se acaba cuando pises una mina o ganes la partida");
        System.out.println("Para detectar una biena te iran saliendo 1 o 2 el 2 te dice que tienes una mina serca");
        System.out.println("Si ganas te saldra un mensaje con texto y si pierdes tambien ");


    }

    private static void menujuego() {

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
}