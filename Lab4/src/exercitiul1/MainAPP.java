package exercitiul1;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

enum Stare{
    achizitionat, expus, vandut
}
enum ModTiparire{
    color, alb_negru
}
enum FormatCopiere{
    A3, A4
}
enum SistemDeOperare{
    Windows, Linux
}

class Echipament implements Serializable {
    protected String denumire;
    protected int nr_inv;
    protected float pret;
    protected String zona_mag;
    protected Stare stare;

    public Echipament(){}
    public Echipament(String denumire, int nr_inv, float pret, String zona_mag, Stare stare){
        this.denumire = denumire;
        this.nr_inv = nr_inv;
        this.pret = pret;
        this.zona_mag = zona_mag;
        this.stare = stare;
    }
    public void afisare(){
        System.out.print(denumire+", "+nr_inv+", "+pret+", "+zona_mag+", "+stare);
    }

    public void setStare(Stare stare){
        this.stare = stare;
    }
    public String getDenumire(){
        return denumire;
    }
    public Stare getStare(){
        return stare;
    }
}

class Imprimanta extends Echipament{
    private int ppm;
    private String rezolutie;
    private int p_car;
    private ModTiparire mod_tiparire;

    public Imprimanta(){}
    public Imprimanta(String denumire, int nr_inv, float pret, String zona_mag, Stare stare, int ppm, String rezolutie, int p_car, ModTiparire mod_tiparire){
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.ppm = ppm;
        this.rezolutie = rezolutie;
        this.p_car = p_car;
        this.mod_tiparire = mod_tiparire;
    }
    @Override
    public void afisare(){
        Echipament e = new Echipament();
        e=this;
        super.afisare();
        System.out.println(", "+ppm+", "+rezolutie+", "+p_car+", "+mod_tiparire);
    }

    public void setMod_tiparire(ModTiparire mod_tiparire){
        this.mod_tiparire = mod_tiparire;
    }
    public ModTiparire getMod_tiparire(){
        return mod_tiparire;
    }
}

class Copiatoare extends Echipament{
    private int p_ton;
    private FormatCopiere format_copier;

    public Copiatoare(){}
    public Copiatoare(String denumire, int nr_inv, float pret, String zona_mag, Stare stare, int p_ton, FormatCopiere format_copier){
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.p_ton = p_ton;
        this.format_copier = format_copier;
    }
    @Override
    public void afisare(){
        super.afisare();
        System.out.println(", "+p_ton+", "+format_copier);
    }
    public void setFormat_copier(FormatCopiere format_copier){
        this.format_copier = format_copier;
    }
}

class Sisteme_Calcul extends Echipament{
    private String tip_mon;
    private float vit_proc;
    private int c_hdd;
    private SistemDeOperare sistem_operare;

    public Sisteme_Calcul(){}
    public Sisteme_Calcul(String denumire, int nr_inv, float pret, String zona_mag, Stare stare, String tip_mon, float vit_proc, int c_hdd, SistemDeOperare sistem_operare){
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.sistem_operare=sistem_operare;
    }

    @Override
    public void afisare(){
        super.afisare();
        System.out.println(", "+tip_mon+", "+vit_proc+", "+c_hdd+", "+sistem_operare);
    }
    public void setSistem_operare(SistemDeOperare so){
        this.sistem_operare=so;
    }
}



public class MainAPP {

    // Serializarea colecției de obiecte în fișierul "echip.bin"
    public static void serializare(List<Echipament> list) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("echip.bin"))) {
            oos.writeObject(list);
        }
    }

    // Deserializarea colecției de obiecte din fișierul "echip.bin"
    public static List<Echipament> deserializare() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("echip.bin"))) {
            return (List<Echipament>) ois.readObject();
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Echipament> list = new ArrayList<Echipament>();
        Scanner sc=new Scanner(System.in);
        String nume_fis="electronice.txt";
        Scanner sc1=new Scanner(new File(nume_fis));

        while(sc1.hasNext()) {
            String[] linie=sc1.nextLine().split(";");
            String denumire=linie[0];
            int nr_inv= parseInt(linie[1]);
            float pret=Float.parseFloat(linie[2]);
            String zona_mag=linie[3];
            Stare stare=Stare.valueOf(linie[4]);
            String tip=linie[5];
            switch (tip){
                case "imprimanta":
                    int ppm= parseInt(linie[6]);
                    String rezolutie= linie[7];
                    int p_car= parseInt(linie[8]);
                    ModTiparire mod_tiparire=ModTiparire.valueOf(linie[9]);
                    Imprimanta imprimanta=new Imprimanta(denumire, nr_inv, pret, zona_mag, stare,ppm, rezolutie, p_car, mod_tiparire);
                    list.add(imprimanta);
                    break;
                case "copiator":
                    int p_ton= parseInt(linie[6]);
                    FormatCopiere format_copiere=FormatCopiere.valueOf(linie[7]);
                    Copiatoare copiatoare=new Copiatoare(denumire, nr_inv, pret, zona_mag, stare, p_ton, format_copiere);
                    list.add(copiatoare);
                    break;
                case "sistem de calcul":
                    String tip_mon=linie[6];
                    float vit_proc=Float.parseFloat(linie[7]);
                    int c_hdd= parseInt(linie[8]);
                    SistemDeOperare so=SistemDeOperare.valueOf(linie[9]);
                    Sisteme_Calcul sisteme_calcul=new Sisteme_Calcul(denumire, nr_inv, pret, zona_mag, stare, tip_mon, vit_proc, c_hdd, so);
                    list.add(sisteme_calcul);
                    break;
            }
        }

        //afisare imprimante
        System.out.println("Imprimante: ");
        for(Echipament echipament:list) {
            if(echipament instanceof Imprimanta){
                echipament.afisare();
            }
        }

        //afisare copiatoare
        System.out.println("Copiatoare: ");
        for(Echipament echipament:list) {
            if(echipament instanceof Copiatoare){
                echipament.afisare();
            }
        }

        //afisare sisteme de calcul
        System.out.println("Sisteme calcul: ");
        for(Echipament echipament:list) {
            if(echipament instanceof Sisteme_Calcul) {
                echipament.afisare();
            }
        }

        //modificarea starii in care se afla un echipament
        System.out.println("Dati numele produsului la care schimbati starea: ");
        String nume_stare=sc.nextLine();
        for(Echipament echipament:list) {
            if(echipament.getDenumire().equals(nume_stare)){
                System.out.println("Starea echipamentului: "+echipament.getStare());
                System.out.println("Noua stare: ");
                Stare stare_noua= Stare.valueOf(sc.next());
                echipament.setStare(stare_noua);
                echipament.afisare();
            }
        }


        //Setarea unui anumit mod de scriere pentru o imprimanta
        System.out.println("Nume echipament (imprimanta): ");
        String nume_schimb_stare=sc.next();
        for(Echipament echipament:list) {
            if(echipament instanceof Imprimanta && echipament.getDenumire().equals(nume_schimb_stare)){
                System.out.println("Noul mod de tiparire: ");
                ModTiparire md= ModTiparire.valueOf(sc.next().trim());
                ((Imprimanta) echipament).setMod_tiparire(md);
                echipament.afisare();
            }
        }

        //Setarea unui format de copiere pentru copiatoare
        System.out.println("Numele echipament: ");
        for(Echipament echipament:list) {
            if(echipament instanceof Copiatoare && echipament.getDenumire().equals(nume_stare)){
                System.out.println("Noul format de copiere: ");
                FormatCopiere formatCopiere=FormatCopiere.valueOf(sc.next().trim());
                ((Copiatoare)echipament).setFormat_copier(formatCopiere);
                echipament.afisare();
            }
        }

        //Instalarea unui anumit sistem de operare pe un sistem de calcul
        System.out.println("Numele echipament: ");
        String nume_sis_calc=sc.next();
        for(Echipament echipament:list) {
            if(echipament instanceof Sisteme_Calcul && echipament.getDenumire().equals(nume_sis_calc)){
                System.out.println("Sistemul de operare: ");
                SistemDeOperare so=SistemDeOperare.valueOf(sc.next().trim());
                ((Sisteme_Calcul) echipament).setSistem_operare(so);
                echipament.afisare();
            }
        }

        //Afişarea echipamentelor vândute
        System.out.println("Echipamente vandute: ");
        for(Echipament echipament:list) {
            if((String.valueOf( echipament.getStare())).equals("vandut")){
                echipament.afisare();
            }
        }

        //Să se realizeze două metode statice pentru serializarea / deserializarea colecției de
        //obiecte în fișierul echip.bin
        //BufferedWriter wr=new BufferedWriter(new FileWriter("echip.bin"));
        PrintStream wr=new PrintStream(System.out);
        System.out.println("Aveti urmatoarele optiuni:");
        System.out.println("1.Serializarea colectiei.");
        System.out.println("2.Deserializarea colectiei.");
        System.out.println("Introduceti optiunea dorita: ");
        int optiune = sc.nextInt();

        if (optiune == 1) {
            // Serializare
            serializare(list);
            System.out.println("Colecția a fost serializată cu succes în echip.bin");
        } else if (optiune == 2) {
            // Deserializare
            List<Echipament> deserializedList = deserializare();
            System.out.println("Colecția a fost deserializată cu succes din echip.bin");

            // Afișare a colecției deserializate
            for (Echipament e : deserializedList) {
                e.afisare();
            }
        }
        else {
            System.out.println("Nu ati introdus opt buna");
        }
    }
}
