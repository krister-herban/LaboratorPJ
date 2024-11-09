/*
Fișierul cantec_in.txt conține versurile unui cântec la alegere.
Să se scrie un program care creează fișierul cantec_out.txt,
care conține versurile cântecului original dar în plus în dreptul fiecărui rând
sunt afișate numărul de cuvinte de pe rând şi numărul de vocale de pe fiecare rând.
În dreptul rândurilor care se încheie cu o grupare de litere aleasă se va pune o steluță (*).
Rândurile pentru care un număr generat aleator este mai mic decât 0.1 vor fi scrise cu majuscule
(se vor genera aleator numere între 0 şi 1).  Se va defini o clasă Vers,
care are o variabilă membră privată un șir de caractere reprezentând versul
și se va dezvolta câte un operator pentru fiecare cerință
de mai sus (o metodă care returnează numărul de cuvinte, o metodă care returnează numărul de vocale, etc).
Se va crea un vector de obiecte de tip Vers care va conține informația preluată din fișierul de intrare.
*/
package exercitiul2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MainAPP {
    static class Vers{
        private String v; //vers modificat
        private String vo; //vers original
        public Vers(String v){
            this.v=v;
        }
        public Vers(){}

        //getCuvinte
        public int getCuvinte(){
            int j=0;
            for(int i=0; i<v.length(); i++){
                if(v.charAt(i)==' '){
                    j++;
                }
            }
            return j+1;
        }

        //toupper
        public int functie_toupper() {
            Random rand = new Random();
            float nr=rand.nextFloat(0,1);
            System.out.println("nr: "+nr);
            if(nr<=0.1){
                return 1;
            }
            return 0;
        }

        //getVocale
        public int getVocale(){
            int j=0;
            for(int i=0; i<v.length(); i++){
                if(v.charAt(i)=='a'||v.charAt(i)=='e'||v.charAt(i)=='i'||v.charAt(i)=='o'||v.charAt(i)=='u'){
                    j++;
                }
            }
            return j;
        }

        //gasire substring
        public int functie_substring(String s){
            int lungime=vo.length()-1;
            for(int i=s.length()-1; i>=0; i--){
                if(s.charAt(i)==vo.charAt(lungime--)){
                    return 1;
                }
                else
                    return 0;
            }
            return 0;
        }
        public void To_upper(int pozitie) {
            StringBuilder sb = new StringBuilder(this.v);
            sb.setCharAt(pozitie,(char)(sb.charAt(pozitie) - 32));
            this.v = sb.toString();
        }
        public String getVers(){
            return this.v;
        }
        public void setVers(String v){
            this.v=v;
        }
        public void setVo(String vo) {
            this.vo = vo;
        }
        public String getVo() {
            return vo;
        }
        public int getVoLength(){
            return this.vo.length();
        }
    }


    public static void main(String[] args) throws IOException {
        String nume_fisi="cantec_in.txt";
        String nume_fiso="cantec_out.txt";

        BufferedReader br =new BufferedReader(new FileReader(nume_fisi));
        BufferedWriter bw =new BufferedWriter(new FileWriter(nume_fiso));

        Vers[] versuri=new Vers[10];

        String linie;
        int i=0;

        while ((linie = br.readLine()) != null) {
            Vers v=new Vers(linie);
            versuri[i]=v;
            versuri[i++].setVo(linie);
        }

        String sub;
        System.out.println("Dati substringul: ");
        Scanner sc = new Scanner(System.in);
        sub = sc.nextLine();

        for(int k=0; k< versuri.length; k++){
            versuri[k].setVers(versuri[k].getVers()+" "+versuri[k].getCuvinte()+" "+versuri[k].getVocale());
        }

        for(int k=0; k<versuri.length; k++){
            if(versuri[k].functie_toupper()==1){
                for(int j=0; j< versuri[k].getVoLength(); j++){
                    if(versuri[k].getVo().charAt(j)<=122&&versuri[k].getVo().charAt(j)>=97) {
                        versuri[k].To_upper(j);
                    }
                }
            }
        }

        for(int k=0; k<versuri.length; k++){
            if(versuri[k].functie_substring(sub)==1) {
                versuri[k].setVers(versuri[k].getVers()+" "+"*");
            }
        }

        for(int k=0; k<versuri.length; k++){
            bw.write(versuri[k].getVers()+"\n");
            System.out.println(versuri[k].getVers());
        }

        br.close();
        bw.close();
    }
}
