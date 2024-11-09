/*Fișierul judete_in.txt, conține lista neordonată a județelor din țară.
Să se încarce datele din fișier într-un tablou de String-uri și
să se ordoneze acest tablou cu ajutorul metodei  sort() din clasa Arrays.
Să se returneze pe ce poziție se află în vectorul ordonat un județ introdus de la tastatură.
Se va utiliza metoda de căutare binară din clasa Arrays.
*/
package exercitiul1;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class MainAPP {
    public static void main(String[] args) throws IOException {
        String nume_fisi="in.txt";
        String nume_fiso="out.txt";
        BufferedReader br = new BufferedReader(new FileReader(nume_fisi));
        BufferedWriter bw = new BufferedWriter(new FileWriter(nume_fiso));

        String[] lista_judete = new String[41];
        String line;
        int i=0;
        while ((line = br.readLine()) != null) {
            lista_judete[i++]=line;
        }
        br.close();


        Arrays.sort(lista_judete);
        for(int j=0;j<41;j++){
            bw.write(lista_judete[j]+"\n");
            System.out.println(lista_judete[j]);
        }
        System.out.println("Numele orasului cautat: ");
        Scanner sc = new Scanner(System.in);
        String s=sc.nextLine();
        int pozitie=Arrays.binarySearch(lista_judete,s);
        System.out.println("pozitie oras cautat: "+pozitie);

        bw.close();

    }
}
