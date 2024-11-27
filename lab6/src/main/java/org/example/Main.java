package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        List<Angajat> list=mapper.readValue(new File("src/main/resources/angajati.json"), new TypeReference<List<Angajat>>(){});

        //afisare
        list.stream().forEach(System.out::println);

        //aifasre angajati cu salariu mai mare de 2500
        System.out.println();
        System.out.println("SALARII MAI MARI DE 2500");
        list.stream().filter(a->a.getSalariu()>2500).forEach(System.out::println);

        //creare lista noua
        System.out.println();
        System.out.println("ANGAJATII DE DUPA APRILIE ANUL TRECUT");
        int anulTrecut = LocalDate.now().getYear() - 1;

        List<Angajat> angajatiConducereAprilie = list.stream()
                .filter(angajat -> {String postul = angajat.getPostul().toLowerCase();
                    return (postul.contains("șef") || postul.contains("director")) && angajat.getData_angajarii().getYear() == anulTrecut && angajat.getData_angajarii().getMonthValue() == 4;})
                .collect(Collectors.toList());
        System.out.println("\nAngajați din aprilie anul trecut cu funcție de conducere:");
        angajatiConducereAprilie.forEach(System.out::println);

        //afisare angajati care nu au functie de conducere
        System.out.println();
        System.out.println("ANGAJATI CARE NU SUNT DIRECTOR/SEF");
        list.stream().filter(a -> !(a.getPostul().toLowerCase().contains("director")||a.getPostul().toLowerCase().contains("sef"))).forEach(System.out::println);

        //extragere lista de string-uri
        System.out.println();
        System.out.println("LISTA DE NUME SCRISA CU MAJUSCULE");
        List<String> numeMajuscule = list.stream().map(angajat -> angajat.getNume().toUpperCase()).collect(Collectors.toList());
        numeMajuscule.stream().forEach(System.out::println);

        //afisare salarii mai mici de 3000 lei
        System.out.println();
        System.out.println("LISTA SALARIILOR MAI MICI DE 3000 LEI");
        list.stream().filter(a->a.getSalariu()<3000).forEach(angajat -> System.out.println(angajat.getSalariu()));

        //afisare date primului angajat din firma
        System.out.println();
        System.out.println("AFIASRE DEATE PRIMULUI ANGAJAT DIN FIRMA");
        Optional<Angajat> primulAngajat = list.stream().min(Comparator.comparing(Angajat::getData_angajarii));
        primulAngajat.ifPresent(System.out::println);

        //daca exista cel putin un ion
        System.out.println();
        System.out.println("VERIFICARE DACA EXISTA UN ION");
        list.stream().filter(a->a.getNume().contains("Ion")).findAny().
                ifPresentOrElse(angajat -> System.out.println("Firma are un Ion"), () -> System.out.println("Firma nu are un Ion"));

        //nr pers angajate in vara anului precedent
        int anulPrecedent = LocalDate.now().getYear() - 1;

        // Calcularea numărului de persoane angajate în vara anului precedent
        double numarAngajatiVara = list.stream()
                .filter(angajat -> {
                    int luna = angajat.getData_angajarii().getMonthValue();
                    int an = angajat.getData_angajarii().getYear();
                    return an == anulPrecedent && (luna == 6 || luna == 7 || luna == 8);}).count();

        System.out.println("\nNumărul de persoane angajate în vara anului precedent: " + numarAngajatiVara);
    }
}