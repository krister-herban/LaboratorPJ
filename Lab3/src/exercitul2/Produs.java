package exercitul2;

import java.time.LocalDate;

public class Produs {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate data_expirare;

    public Produs (String denumire, double pret, int cantitate, LocalDate data_expirare) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.data_expirare = data_expirare;
    }
    public Produs() {}

    @Override
    public String toString() {
        return("Denumire: "+denumire+", Pret: "+pret+", Cantitate: "+cantitate+", Data Expirare: "+data_expirare);
    }
    public String getDenumire() {
        return denumire;
    }
    public String getPret() {
        return Double.toString(pret);
    }
    public int getCantitate() {
        return cantitate;
    }
    public LocalDate getData_expirare() {
        return data_expirare;
    }
    public boolean check_expirare(){
        LocalDate data_expirare = this.data_expirare;
        LocalDate data_curenta=LocalDate.now();
        return data_expirare.isAfter(data_curenta);
    }
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

}