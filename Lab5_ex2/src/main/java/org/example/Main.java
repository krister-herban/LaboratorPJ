package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clasa Main contine gestioneaza obiecte de tip Mobilier
 * citite dintr-un fișier JSON. Programul permite afisarea caracteristicilor fiecarui mobilier
 * si estimarea numarului de coli de PAL necesare pentru fiecare piesa de mobilier.
 */

public class Main {

    /**
     * Metoda principala citeste obiecte de tip Mobilier
     * dintr-un fișier JSON, le afișeaza, permite utilizatorului sa selecteze o piesa
     * specifica pentru a vedea detalii si estimeaza numarul de coli de PAL necesare.
     *
     * @param args Argumentele liniei de comanda.
     * @throws IOException In cazul in care fisierul JSON nu poate fi citit.
     */

    public static void main(String[] args) throws IOException {

        //citire
        File f = new File("src/main/resources/mobilier.json");
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        List<Mobilier> list = mapper.readValue(f, new TypeReference<List<Mobilier>>() {});
        //afisare
        for(Mobilier mobilier:list)
            System.out.println(mobilier.toString());

        //afiseaza caracteristicile unei anumite piese de mobilier
        System.out.println();
        System.out.println("Dati mobilierul: ");
        Scanner sc=new Scanner(System.in);
        String nume_mobilier=sc.next();
        for(Mobilier mobilier: list){
            if(mobilier.getNume().equals(nume_mobilier))
                System.out.println(mobilier.toString());
        }

        //Afișează estimativ numărul colilor de pal necesare pentru realizarea unui anumit corp
        //de mobile știind că o coală de pal are dimensiunea 2800 x 2070 mm (pentru simplitate
        //se va ţine cont doar de arie, nu şi de posibilitatea de a realiza tăieturile)
        System.out.println();
        int lungime=2800;
        int latime=2070;
        for(Mobilier mobilier: list){
            List<Placa> lista_aux=mobilier.getPlaci();
            for(Placa p: lista_aux){
                System.out.println(p.toString());
                int total_lung=p.getLungime()/lungime;
                if(p.getLungime()%lungime!=0)
                    total_lung++;
                int total_lat=p.getLatime()/latime;
                if(p.getLatime()%latime!=0)
                    total_lat++;
                if(total_lung>total_lat)
                    System.out.println("Total numar colilor: "+total_lung);
                else
                    System.out.println("Total numar colilor: "+total_lat);
            }
        }
    }
}