package application;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        ParseMetro.initGraph(graph);
        Scanner scanner = new Scanner(System.in);
        String choix;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("" +
                    " __  __      _                   ____            _     \n" +
                    "|  \\/  | ___| |_ _ __ ___       |  _ \\ __ _ _ __(_)___ \n" +
                    "| |\\/| |/ _ \\ __| '__/ _ \\ _____| |_) / _` | '__| / __|\n" +
                    "| |  | |  __/ |_| | | (_) |_____|  __/ (_| | |  | \\__ \\\n" +
                    "|_|  |_|\\___|\\__|_|  \\___/      |_|   \\__,_|_|  |_|___/");

            System.out.println("\nVeillez à saisir les noms des stations comme indiqué dans le fichier metro.txt\n");
            System.out.print("Veuillez saisir le nom de la station de départ\n> ");
            //saisie du depart
            String dep = scanner.nextLine();

            System.out.print("Veuillez saisir le nom de la station d'arrivée\n> ");
            //saisie arrivée
            String arr = scanner.nextLine();
            if(dep.equals(arr)) {
                choix = "O";
                continue;
            }
            System.out.println("\n" + graph.getPath(dep, arr));

            System.out.print("Voulez-vous continuer? (O/n) : ");
            choix = scanner.nextLine();

        } while (choix.toLowerCase().equals("o") || choix.equals(""));
    }
}
