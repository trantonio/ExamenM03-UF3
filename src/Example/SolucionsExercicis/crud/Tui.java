/*
 * Tui.java 		
 *
 * Copyright 2010 Mònica Ramírez Arceda <mramirez@escoladeltreball.org>
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 *
 */

import java.util.Scanner;

/**
 * CRUD TUI
 */
public class Tui {

    /**
     * Main program.
     */
    public static void main(String[] args) {
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        while (cont) {
            System.out.print("\n\nMANTENIMENT DE FITXERS\n\n");
            System.out.print("Menú principal\n\n");
            System.out.print(" 1 Executar el procés.\n");
            System.out.print(" 2 Ajuda.\n");
            System.out.print(" 0 Sortir.\n");
            System.out.print("\nOpció (1, 2, 0) ? ");
            int op = sc.nextInt();
            switch (op) {
            case 1:
                System.out.print("\nNom del fitxer mestre ? ");
                String masterFile = sc.next();
                System.out.print("Nom del fitxer d'altes ? ");
                String createFile = sc.next();
                System.out.print("Nom del fitxer de baixes ? ");
                String deleteFile = sc.next();
                System.out.print("Nom del fitxer de modificacions ? ");
                String updateFile = sc.next();
                System.out.print("Separador de camps ? ");
                String delimiter = sc.next();
                Crud mf = new Crud(masterFile, createFile, deleteFile, updateFile, delimiter);
                mf.create();
                System.out.print("\nS'ha fet el manteniment d'altes.");
                mf.delete();
                System.out.print("\nS'ha fet el manteniment de baixes.");
                mf.update();
                System.out.print("\nS'ha fet el manteniment de modificacions.");
                break;
            case 2:
                showHelp();
                break;
            default:
                System.out.print("\nFi del programa\n");
                cont = false;
            }
        }
    }

    /**
     * Shows online help.
     */
    private static void showHelp() {
        System.out.print("\nAJUDA\n");
        System.out.print("\nFa el manteniment de fixers CSV amb el primer registre com clau primària.");
        System.out.print("\nEls registres han d'estar ordenats per la clau primària.\n");
    }

}
