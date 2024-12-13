package exercitiul1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public record Carte(String titlul, String autorul, int anul){}

    public static Map<Integer, Carte> citire() {
        try {
            File file=new File("src/main/resources/Carti.json");
            ObjectMapper mapper=new ObjectMapper();
            Map<Integer, Carte> persoane = mapper
                    .readValue(file, new TypeReference<Map<Integer, Carte>>(){});
            return persoane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void scriere(Map<Integer, Carte> map) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/Carti.json");
            mapper.writeValue(file,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Map<Integer,Carte> map = new HashMap<>();
        ObjectMapper mapper=new ObjectMapper();
        map=mapper.readValue(new File("src/main/resources/Carti.json"), new TypeReference<>(){});
        if (map == null) {
            System.out.println("Eroare la citirea datelor din fișier.");
            return;
        }
        //afisare colectie carti
        System.out.println("AFISARE: ");
        map.forEach((id, carte)-> System.out.println("ID: "+id+" "+"Carte: "+carte));

        //stergere o carte
        Scanner scanner=new Scanner(System.in);
        System.out.println("\nDati nr cartii pt stergere: ");
        int id_stergere=scanner.nextInt();
        map.remove(id_stergere);
        System.out.println("\nCarti dupa stergere: ");
        map.forEach((id, carte)-> System.out.println("ID: "+id+" "+"Carte: "+carte));

        //adaugare carte
        System.out.println("\nAdaugare carte");
        System.out.println("Titlu carte: ");
        String titlu=scanner.next();
        System.out.println("Autor carte: " );
        String autor=scanner.next();
        System.out.println("Anul carte: ");
        int anul=scanner.nextInt();
        Carte carte_adaugare=new Carte(titlu, autor, anul);
        System.out.println("Id carte: ");
        int id_adaugare=scanner.nextInt();
        map.putIfAbsent(id_adaugare, carte_adaugare);
        System.out.println("Noua colectie: ");
        map.forEach((id, carte)-> System.out.println("ID: "+id+" "+"Carte: "+carte));

        //salvare in json modificarile facute asupra colectiei
        scriere(map);

        //Copiere carti cu autorul Yual Noah Harari.
        System.out.println("\nCarti ale autorului 'Yuval Noah Harari':");
        Set<Carte> set=new HashSet<>();
        set=map.values().stream().filter(c-> "Yuval Noah Harari".equals(c.autorul)).collect(Collectors.toSet());
        set.stream().forEach(System.out::println);

        //afisare ordonata dupa titlu din set
        System.out.println("\nSetul ordonat: ");
        set.stream().sorted(Comparator.comparing(Carte::titlul)).forEach(System.out::println);

        //datele celei mai vechi carti din set
        System.out.println("\nCea mai veche carte din set:");
        Optional<Carte> cea_mai_veche=set.stream().min(Comparator.comparing(Carte::anul));
        System.out.println(cea_mai_veche.get());
    }
}