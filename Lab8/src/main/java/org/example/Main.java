package org.example;

import java.sql.*;
import java.time.Year;
import java.util.Scanner;

class ExceptieVarsta extends Exception {
    public ExceptieVarsta(String m) {
        super(m);
    }

    public static void verificaVarsta(int varsta) throws ExceptieVarsta {
        if (varsta < 0 || varsta > 100) {
            throw new ExceptieVarsta("Varsta trebuie sa fie intre 0 si 100.");
        }
    }
}

class ExceptieAnExcursie extends Exception {
    public ExceptieAnExcursie(String m) {
        super(m);
    }

    public static void verificaAn(int anul, int anulNasterii) throws ExceptieAnExcursie {
        int anulCurent = Year.now().getValue();
        if (anul < anulNasterii || anul > anulCurent) {
            throw new ExceptieAnExcursie("Anul trebuie sa fie intre " + anulNasterii + " si " + anulCurent + ".");
        }
    }
}

public class Main {

    public static void creare_bd(Statement stmt) throws SQLException {
        String tabela_persoane="CREATE TABLE IF NOT EXISTS persoane (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nume VARCHAR(45), " +
                "varsta INT" + ");";
        String tabela_excursii="CREATE TABLE IF NOT EXISTS excursii (" +
                "id_excursie INT AUTO_INCREMENT PRIMARY KEY, " +
                "id_persoana INT, " +
                "destinatia VARCHAR(100), " +
                "anul INT, " +
                "FOREIGN KEY (id_persoana) REFERENCES persoane(id) ON DELETE CASCADE" + ");";

        //creare tabela persoane
        stmt.executeUpdate(tabela_persoane);
        //creare tabela excursii
        stmt.executeUpdate(tabela_excursii);
    }

    public static void adaugarePersoane(PreparedStatement stmt) throws SQLException, ExceptieVarsta {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nume: ");
        String nume=sc.nextLine();
        System.out.println("Varsta: ");
        int varsta=sc.nextInt();

       ExceptieVarsta.verificaVarsta(varsta);

        stmt.setString(1,nume);
        stmt.setInt(2,varsta);
        stmt.executeUpdate();
        System.out.println("Persoana adaugata");

    }

    public static void adaugareExcursii(Connection con) throws SQLException, ExceptieAnExcursie {
        Scanner sc=new Scanner(System.in);
        System.out.println("Id persoana: ");
        String id_persoana=sc.next();

        //verificare id
        String verificare="SELECT COUNT(*) FROM persoane WHERE id = ?";
        PreparedStatement verificareStmt= con.prepareStatement(verificare);
        verificareStmt.setInt(1,Integer.parseInt(id_persoana));
        ResultSet rs=verificareStmt.executeQuery();
        rs.next();
        if(rs.getInt(1)==0) {
            System.out.println("Id-ul persoanei nu exista");
            return;
        }

        System.out.println("Destinatia: ");
        String destinatia=sc.next();
        System.out.println("Anul: ");
        int anul=sc.nextInt();

        //verificare an
        String obtineVarsta = "SELECT varsta FROM persoane WHERE id = ?";
        int varsta = 0;
        try (PreparedStatement obtineVarstaStmt = con.prepareStatement(obtineVarsta)) {
            obtineVarstaStmt.setInt(1, Integer.parseInt(id_persoana));
            try (ResultSet rs1 = obtineVarstaStmt.executeQuery()) {
                if (rs1.next()) {
                    varsta = rs1.getInt("varsta");
                }
            }
        }
        int anulNasterii=Year.now().getValue()-varsta;
        ExceptieAnExcursie.verificaAn(anul, anulNasterii);

        //adaugare
        String sql = "INSERT INTO excursii (id_persoana,destinatia,anul) VALUES (?,?,?)";
        PreparedStatement adaugareStmt= con.prepareStatement(sql);
        adaugareStmt.setInt(1,Integer.parseInt(id_persoana));
        adaugareStmt.setString(2,destinatia);
        adaugareStmt.setInt(3,anul);
        adaugareStmt.executeUpdate();
        System.out.println("Excursie adaugata");
    }

    public static void afisarePersoaneSiExcursii(Connection con) throws SQLException {
        String sqlPersoane = "SELECT * FROM persoane";
        String sqlExcursii = "SELECT * FROM excursii WHERE id_persoana = ?";

        Statement stmtPersoane=con.createStatement();
        ResultSet rsPersoane=stmtPersoane.executeQuery(sqlPersoane);

        while(rsPersoane.next()) {
            int idPersoana=rsPersoane.getInt("id");
            String nume=rsPersoane.getString("nume");
            int varsta=rsPersoane.getInt("varsta");

            System.out.println("Persoana id: "+idPersoana+", Nume: "+nume+", Varsta: "+varsta);

            PreparedStatement stmtExcursii=con.prepareStatement(sqlExcursii);
            stmtExcursii.setInt(1,idPersoana);
            ResultSet rsExcursii=stmtExcursii.executeQuery();
            boolean aux=false;//pt verificare daca are excursii
            while(rsExcursii.next()) {
                aux=true;
                System.out.println("Excursii id: "+rsExcursii.getInt("id_excursie")+", Destinatia: "+rsExcursii.getString("destinatia")
                +", Anul: "+rsExcursii.getInt("anul"));
            }
            if(!aux){
                System.out.println("nu are excursii");
            }
            System.out.println();
        }
    }

    public static void afisareExcursiiDupaNume(Connection con) throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nume: ");
        String nume=sc.next();

        String gasesteIdPersoana = "SELECT id FROM persoane WHERE nume = ?";
        int persoanaId = -1;

        PreparedStatement stmtPersoane=con.prepareStatement(gasesteIdPersoana);
        stmtPersoane.setString(1,nume);
        ResultSet rsPersoane=stmtPersoane.executeQuery();
        if(rsPersoane.next()) {
            persoanaId = rsPersoane.getInt("id");
        }
        else {
            System.out.println("Persoana nu are excursii\n");
            return;
        }

        String gasesteExcursii= "SELECT id_excursie, destinatia, anul FROM excursii WHERE id_persoana = ?";
        PreparedStatement stmtExcursii=con.prepareStatement(gasesteExcursii);
        stmtExcursii.setInt(1,persoanaId);
        ResultSet rsExcursii=stmtExcursii.executeQuery();
        boolean aux=false;
        System.out.println("Excursiile persoanei cu numele "+nume+" sunt: ");
        while(rsExcursii.next()) {
            aux=true;
            System.out.println("Excursii id: "+rsExcursii.getInt("id_excursie")+", Destinatia: "+rsExcursii.getString("destinatia")
                    +", Anul: "+rsExcursii.getInt("anul"));
        }

    }

    public static void afisarePersoaneDupaDestinatie(Connection con) throws SQLException {
        Scanner sc=new Scanner(System.in);

        System.out.println("Destinatia: ");
        String destinatia=sc.next();

        String sql="SELECT p.id, p.nume, p.varsta " +
                "FROM persoane p, excursii e " +
                "WHERE p.id = e.id_persoana AND e.destinatia = ?;";

        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1,destinatia);
        ResultSet rs=stmt.executeQuery();
        boolean aux=false;
        System.out.println("Persoanele care au vizitat sunt: ");
        while(rs.next()) {
            aux=true;
            System.out.println("Persoana id: "+rs.getInt("id")+", Nume: "+rs.getString("nume")
                    +", Varsta: "+rs.getInt("varsta"));
        }
        if(!aux){
            System.out.println("Nu exista persoane pt destinatia ceruta\n");
        }
    }

    public static void afisarePersoaneDintrunAn(Connection con) throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Anul: ");
        int an=sc.nextInt();

        String sql="SELECT p.id, p.nume, p.varsta "+
                "FROM persoane p, excursii e "+
                "WHERE p.id = e.id_persoana AND e.anul = ?";

        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setInt(1,an);
        ResultSet rs=stmt.executeQuery();
        boolean aux=false;
        System.out.println("Persoane care au fost in exccursii in anul "+an+": ");
        while(rs.next()) {
            aux=true;
            System.out.println("Persoana id: "+rs.getInt("id")+" Nume: "+rs.getString("nume")
                    +" Varsta: "+rs.getInt("varsta"));
        }
        if(!aux){
            System.out.println("Nu exista persoane cu excurii in acel an\n");
        }
    }

    public static void stergereExcursie(Connection con) throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Id excursie de sters: ");
        int idSters=sc.nextInt();
        String sql="SELECT COUNT(*) FROM excursii WHERE id_excursie = ?";
        PreparedStatement stmtVerif=con.prepareStatement(sql);
        stmtVerif.setInt(1,idSters);
        ResultSet rs=stmtVerif.executeQuery();
        rs.next();
        if(rs.getInt(1)==0){
            System.out.println("Nu exista excursia cu id-ul "+idSters+"\n");
            return;
        }

        //stergere daca exista
        String sql1="DELETE FROM excursii WHERE id_excursie = ?";
        PreparedStatement stmtStergere=con.prepareStatement(sql1);
        stmtStergere.setInt(1,idSters);
        int i=stmtStergere.executeUpdate();
        if(i>0){
            System.out.println("Excursia a fost stearsa\n");
        }
        else{
            System.out.println("Nu s-a sters\n");
            return;
        }
    }

    public static void stergerePersaona(Connection con) throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Id persoana de sters: ");
        int idPers=sc.nextInt();

        String sqlVerificare="SELECT COUNT(*) FROM persoane WHERE id = ?";
        PreparedStatement stmtVerif=con.prepareStatement(sqlVerificare);
        stmtVerif.setInt(1,idPers);
        ResultSet rs=stmtVerif.executeQuery();
        rs.next();
        if(rs.getInt(1)==0){
            System.out.println("Nu exista persoana\n");
            return;
        }

        //stergere daca exista
        String sql1="DELETE FROM persoane WHERE id = ?";
        PreparedStatement stmtStergere=con.prepareStatement(sql1);
        stmtStergere.setInt(1,idPers);
        int i=stmtStergere.executeUpdate();
        if(i>0){
            System.out.println("Persoana cu id: "+idPers+" a fost stearsa\n");
        }
        else{
            System.out.println("Nu s-a sters\n");
            return;
        }


    }

    public static void main(String[] args) throws SQLException, ExceptieVarsta, ExceptieAnExcursie {
        String URL = "jdbc:mysql://localhost:3306/lab8";
        String USER = "root";
        String PASSWORD = "root";
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        //creare
        Statement stmt = con.createStatement();
        creare_bd(stmt);
        int opt;
        Scanner sc=new Scanner(System.in);

        do{
            System.out.println("1. adaugare persoane");
            System.out.println("2. adaugare excursie");
            System.out.println("3. afisarea tuturor persoanelor si pt fiecare persoana excursiile");
            System.out.println("4. afisare excursii ale unui nume citit de la tastatura");
            System.out.println("5. afisare persoane care au vizitat o anumita destinatie");
            System.out.println("6. afisare persoane care au facut excursii intr-un an introdus");
            System.out.println("7. stergerea unei excurii");
            System.out.println("8. stergerea unei persoane cu excursiile in care a fost");
            System.out.println("opt: ");
            opt = sc.nextInt();
            switch(opt){
                case 1:
                    //adaugare pers
                    String sql = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
                    PreparedStatement pstmt=con.prepareStatement(sql);
                    adaugarePersoane(pstmt);
                    break;
                case 2:
                    //adaugare excursie + verificare persoana
                    adaugareExcursii(con);
                    break;
                case 3:
                    //afisarea tuturor persoanelor si pt fiecare persoana excursiile
                    afisarePersoaneSiExcursii(con);
                    break;
                case 4:
                    //afisare excursii ale unui nume citit de la tastatura
                    afisareExcursiiDupaNume(con);
                    break;
                case 5:
                    //afisare persoane care au vizitat o anumita destinatie
                    afisarePersoaneDupaDestinatie(con);
                    break;
                case 6:
                    //afisare persoane care au facut excursii intr-un an introdus
                    afisarePersoaneDintrunAn(con);
                    break;
                case 7:
                    //stergerea unei excurii
                    stergereExcursie(con);
                    break;
                case 8:
                    //stergerea unei persoane cu excursiile in care a fost
                    stergerePersaona(con);
                    break;
                case 0:
                    return;
            }
        }while(true);
    }
}