package exercitiul2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class MainAPP {

    public static void scriere(Set<InstrumentMuzical> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/instrumente2.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> citire() {
        try {
            File file=new File("src/main/resources/instrumente.json");
            ObjectMapper mapper=new ObjectMapper();
            Set<InstrumentMuzical> instrumente = mapper
                    .readValue(file, new TypeReference<Set<InstrumentMuzical>>(){});
            return instrumente;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Set<InstrumentMuzical> instrumente = new HashSet<>();
        //adaugare instante
        instrumente.add(new Chitara("Yamaha", 1200.00, TipChitara.CLASICA, 6));
        instrumente.add(new Chitara("Fender", 1500, TipChitara.ACUSTICA, 12));
        instrumente.add(new Chitara("Gibson", 2000, TipChitara.CLASICA, 6));
        instrumente.add(new SetTobe("Roland", 5000.0, TipTobe.ELECTRONICE, 5, 3));
        instrumente.add(new SetTobe("Pearl", 3000.0, TipTobe.ACUSTICE, 5, 4));
        instrumente.add(new SetTobe("Yamaha", 3500.0, TipTobe.ACUSTICE, 6, 4));

        System.out.println("\nColectia: ");
        instrumente.stream().forEach(System.out::println);
        //salvare in instrumente2.json
        scriere(instrumente);

        //citire din instrumente.json
        System.out.println("\nColectia din json:");

        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Set<InstrumentMuzical> instrumente2 = mapper.readValue(new File("src/main/resources/instrumente.json"), new TypeReference<Set<InstrumentMuzical>>() {});
        if (instrumente2 != null) {
            instrumente2.stream().forEach(System.out::println);
        }

        //afisare clase din instrumente2
        System.out.println("\nColectia din set: "+instrumente2.getClass().getSimpleName());

        //se verifica daca se permit duplicate
        Chitara chitara_duplicata=new Chitara("Yamaha", 1200.00, TipChitara.CLASICA, 6);
        boolean adaugat=instrumente2.add(chitara_duplicata);
        if (adaugat) {
            System.out.println("\nDuplicate permise");
        }
        else {
            System.out.println("\nDuplicate nepermise");
        }

        //stergere instrumente al carui pret e mai mare de 3000
        System.out.println("\nStergere instrumente cu pret mai mare dde 3000");
        instrumente2.removeIf(i->i.getPret()>3000);
        instrumente2.stream().forEach(System.out::println);

        //afisare chitari instanceof
        System.out.println("\nAfisare chitari:");
        instrumente2.stream().filter(i->i instanceof Chitara).forEach(System.out::println);

        //afisare tobe getClass()
        System.out.println("\nAfisare tobe:");
        instrumente2.stream().filter(i->i.getClass().equals(SetTobe.class)).forEach(System.out::println);

        //chiatara cu cele mai multe corzi
        System.out.println("\nChitara cu cele mai multe corzi: ");
        instrumente2.stream().filter(i->i instanceof Chitara).map(i->(Chitara)i).max(Comparator.comparing(Chitara::getNr_corzi)).ifPresent(System.out::println);

        //tobe ordonate dupa nr de tobe
        System.out.println("\nTobe ordonate dupa nr de tobe: ");
        instrumente2.stream().filter(i->i instanceof SetTobe).map(i->(SetTobe)i).sorted(Comparator.comparing(SetTobe::getNr_tobe)).forEach(System.out::println);

    }
}
