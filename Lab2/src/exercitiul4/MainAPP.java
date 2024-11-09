/*
Să se realizeze un program care citește numele si CNP-ul pe care îl au n persoane.
Valoarea lui n se citește de la tastatură. Programul va afișa informațiile introduse și în plus
pentru fiecare persoana va afișa vârsta. Cât timp un CNP-ul este introdus greșit programul va
cere reintroducerea acestuia. Pentru simplitate se consideră că CNP-ul este valid dacă
îndeplinește următoarele condiții:
• Are 13 caractere
• Toate caracterele sunt cifre
• Prima cifră are una din valorile 1, 2, 5, 6
• Cifra de control a CNP-ului are o valoare validă.
Se va crea clasa Persoana cu variabile membre private nume (String) şi cnp (String).
Clasa va avea constructor cu parametri, gettere si settere în funcție de necesități şi metoda
getVarsta() care va calcula şi va returna vârsta persoanei extrăgând data nașterii din CNP şi
citind din sistem data curentă. Se va utiliza clasa LocalDate. Se va crea un vector în care se
vor adăuga obiectele de tip Persoana. Fiecare element din vectorul va fi afișat pe un rând în
formatul nume, CNP, varsta.
 */
package exercitiul4;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;

public class MainAPP {
    static class Persoana{
        private String nume;
        private String cnp;
        public Persoana(String nume, String cnp){
            this.nume = nume;
            this.cnp = cnp;
        }

        public String getNume() {
            return nume;
        }

        public String getCnp() {
            return cnp;
        }

        public int getVarsta() {
            LocalDate ld = LocalDate.now();
            int an = ld.getYear();
            int luna = ld.getMonthValue();
            int zi = ld.getDayOfMonth();
            int an_pers, luna_pers, zi_pers;
            if(cnp.charAt(0)<=2){
                an_pers=1900+(cnp.charAt(1)-'0')*10+cnp.charAt(2)-'0';
            }
            else{
                an_pers=2000+(cnp.charAt(1)-'0')*10+cnp.charAt(2)-'0';
            }
            luna_pers=(cnp.charAt(3)-'0')*10+cnp.charAt(4)-'0';
            zi_pers=(cnp.charAt(5)-'0')*10+cnp.charAt(6)-'0';
            int varsta=0;
            if(luna_pers<luna) {
                varsta=an-an_pers;
            }
            else if(luna_pers == luna)
            {
                if (zi_pers < zi)
                {
                    varsta = an - an_pers;
                }
                else
                {
                    varsta = an - an_pers - 1;
                }
            }
            else {
                varsta = an - an_pers - 1;
            }
            return varsta;

        }
    }

    public static int funtie_cnp(String cnp) {
        float aprobat = 0f;
        int[] vector = {2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
        int suma = 0;
        if (cnp.length() == 13) {
            if (cnp.charAt(0) == '1' || cnp.charAt(0) == '2' || cnp.charAt(0) == '5' || cnp.charAt(0) == '6') {

                suma = 2 * (cnp.charAt(0) - '0') + 7 * (cnp.charAt(1) - '0') + 9 * (cnp.charAt(2) - '0')
                        + (cnp.charAt(3) - '0') + 4 * (cnp.charAt(4) - '0') + 6 * (cnp.charAt(5) - '0') + 3 * (cnp.charAt(6) - '0')
                        + 5 * (cnp.charAt(7) - '0') + 8 * (cnp.charAt(8) - '0') + 2 * (cnp.charAt(9) - '0') + 7 * (cnp.charAt(10) - '0')
                        + 9 * (cnp.charAt(11) - '0');
                if ((cnp.charAt(12) - '0' == suma % 11 && suma % 11 != 10) || (cnp.charAt(12) - '0' == suma % 11 - 9 && suma % 11 == 10)) {
                    aprobat += 0.5;
                }
            }
        }
        int k=0;
        for (int i = 0; i < cnp.length(); i++) {
            if (cnp.charAt(i) >= '0' && cnp.charAt(i) <= '9') {
                k++;
            }
        }
        if(k==13)
            aprobat+=0.5;
        return (int)aprobat;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dati nr de persoane: ");
        int n = sc.nextInt();
        Persoana[] persoan = new Persoana[n];
        for(int i=0; i<n; i++){
            System.out.print("nume: ");
            String nume = sc.next();
            String cnp;
            while (true) {
                System.out.print("cnp: ");
                cnp = sc.next();

                if (funtie_cnp(cnp) == 1) {
                    break;
                } else {
                    System.out.println("CNP invalid! Reîncercați.");
                }
            }
            Persoana p=new Persoana(nume,cnp);
            persoan[i]=p;
        }

        System.out.println();
        System.out.println("Date: ");
        for(int i=0; i<n; i++){
            System.out.println(persoan[i].getNume()+" "+persoan[i].getCnp()+" "+persoan[i].getVarsta());
        }

    }
}
