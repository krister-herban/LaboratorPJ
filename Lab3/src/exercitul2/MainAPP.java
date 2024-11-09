package exercitul2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainAPP {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        String nume_fis="produse.csv";
        BufferedReader br=new BufferedReader(new FileReader(nume_fis));
        List<Produs> lista=new ArrayList<Produs>();
        //citire
        String linie;
        while((linie= br.readLine())!=null)
        {
            String[] data=linie.split(",");
            String nume=data[0];
            double pret=Double.parseDouble(data[1]);
            int cantitate=Integer.parseInt(data[2]);
            LocalDate data_exp=LocalDate.parse(data[3]);
            Produs p=new Produs(nume,pret,cantitate,data_exp);
            lista.add(p);
        }
        //afisare
        for(int i=0;i<lista.size();i++)
        {
            System.out.println(lista.get(i).toString());
        }
        //produse expirate
        System.out.println("Produse expirate: ");
        for(int i=0;i<lista.size();i++)
        {
            if(lista.get(i).check_expirare()==false)
            {
                System.out.println(lista.get(i).toString());
            }
        }
        //vanzare produs
        String nume_cumparat;
        System.out.print("Dati numele de cumparat: ");
        nume_cumparat= scanner.next();
        int nr_produse;
        System.out.print("Dati nr de produse de cumparat: ");
        nr_produse= scanner.nextInt();

        for(int i=0;i<lista.size();i++) {
            if(lista.get(i).getDenumire().equals(nume_cumparat)) {
                System.out.println(lista.get(i).toString());
                if(nr_produse<=lista.get(i).getCantitate()){
                    int ramase=lista.get(i).getCantitate()-nr_produse;
                    lista.get(i).setCantitate(ramase);
                    System.out.println("Vanzarea a fost realizata cu succes!");
                    System.out.println("Au mai ramas: "+ramase);
                }
                else {
                    System.out.println("nu sunt destule in stoc");
                }

            }
        }

        //pret minim
        double min=100d;
        System.out.println("Produse cu pret minim");
        for(int i=0; i<lista.size();i++) {
            if(Double.parseDouble(lista.get(i).getPret())<min) {
                min=Double.parseDouble(lista.get(i).getPret());
            }
        }
        for(int i=0;i<lista.size();i++) {
            if(Double.parseDouble(lista.get(i).getPret())==min) {
                System.out.println(lista.get(i).toString());
            }
        }

        //salvare produse cu o anumita cantitate
        System.out.println("Cantitate minima: ");
        int cantitate_citita=scanner.nextInt();
        String nume_fis_min="fisier_minim.txt";
        BufferedWriter bw=new BufferedWriter(new FileWriter(nume_fis_min));

        for(int i=0;i<lista.size();i++) {
            if(lista.get(i).getCantitate()<=cantitate_citita) {
                bw.write(lista.get(i).toString());
                bw.newLine();
            }
        }
        bw.close();
    }
}
