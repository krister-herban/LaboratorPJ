package exercitiu1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainAPP {
    public static void main(String[] args) {

        List<Parabola> list = new ArrayList<Parabola>();

        int opt;

        do {
            System.out.println("1. adaugare parabola");
            System.out.println("2. varful parabolei");
            System.out.println("3. afisarea parabolei");
            System.out.println("4. mijlocul dintre doua varfuri");
            System.out.println("5. mijlocul dintre doua varfuri statica");
            System.out.println("6. distanta dintre doua varfuri");
            System.out.println("7. distanta dintre doua varfuri statica");
            System.out.println("0. iesire");
            System.out.println("Dati opt: ");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    double aa,bb,cc;
                    System.out.println("a=");aa=sc.nextDouble();
                    System.out.println("b=");bb=sc.nextDouble();
                    System.out.println("c=");cc=sc.nextDouble();
                    Parabola parabola = new Parabola(aa,bb,cc);
                    list.add(parabola);
                    break;
                case 2:
                    int n;
                    System.out.println("Dati numarul parabolei: ");
                    n = sc.nextInt();
                    System.out.println("Varf: " + list.get(n).varf_parabole()[0] + " " + list.get(n).varf_parabole()[1]);
                    break;
                case 3:
                    System.out.println("Dati numarul parabolei: ");
                    n = sc.nextInt();
                    System.out.println("Ecuatie: " + list.get(n).toString());
                    break;
                case 4:
                    System.out.println("Dati numarul parabolei: ");
                    n = sc.nextInt();
                    double a, b, c;
                    System.out.println("a=");
                    a = sc.nextDouble();
                    System.out.println("b=");
                    b = sc.nextDouble();
                    System.out.println("c=");
                    c = sc.nextDouble();

                    Parabola p = new Parabola(a, b, c);
                    System.out.println("mijlocul dintre cele doua parabole: x=" + list.get(n).mij_doua_parabole(p)[0] + " y=" + list.get(n).mij_doua_parabole(p)[1]);
                    break;
                case 5:
                    double a1, b1, c1;
                    System.out.println("a1=");
                    a = sc.nextDouble();
                    System.out.println("b1=");
                    b = sc.nextDouble();
                    System.out.println("c1=");
                    c = sc.nextDouble();
                    Parabola p1 = new Parabola(a, b, c);
                    System.out.println("a2=");
                    a1 = sc.nextDouble();
                    System.out.println("b2=");
                    b1 = sc.nextDouble();
                    System.out.println("c2=");
                    c1 = sc.nextDouble();
                    Parabola p2 = new Parabola(a1, b1, c1);
                    double mij[] = Parabola.mij_doua_parabole_static(p1, p2);
                    System.out.println("mijlocul dintre cele doua parabole: x=" + mij[0] + " y=" + mij[1]);
                    break;
                case 6:
                    System.out.println("Dati numarul parabolei: ");
                    n = sc.nextInt();

                    System.out.println("a=");
                    a = sc.nextDouble();
                    System.out.println("b=");
                    b = sc.nextDouble();
                    System.out.println("c=");
                    c = sc.nextDouble();
                    Parabola p3 = new Parabola(a, b, c);
                    double dist=list.get(n).distanta_varfuri(p3);
                    System.out.println("distanta: "+dist);
                    break;
                case 7:
                    System.out.println("a1=");
                    a = sc.nextDouble();
                    System.out.println("b1=");
                    b = sc.nextDouble();
                    System.out.println("c1=");
                    c = sc.nextDouble();
                    p1 = new Parabola(a, b, c);
                    System.out.println("a2=");
                    a1 = sc.nextDouble();
                    System.out.println("b2=");
                    b1 = sc.nextDouble();
                    System.out.println("c2=");
                    c1 = sc.nextDouble();
                    p2 = new Parabola(a1, b1, c1);
                    double d=Parabola.distanta_varfuri_static(p1, p2);
                    System.out.println("distanta: "+d);
                    break;
                    case 0:
                        return;
            }
        }while(true);
    }
}
