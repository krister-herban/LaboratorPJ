package exrcitiul1;

import java.util.Scanner;

public class MainAPP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("lungime: ");
        int lungime = sc.nextInt();
        System.out.println("latime: ");
        int latime = sc.nextInt();
        int arie=lungime*latime;
        int perimetru=lungime*2+latime*2;
        System.out.println("perimetru: "+perimetru);
        System.out.println("arie: "+arie);
        sc.close();
    }
}
