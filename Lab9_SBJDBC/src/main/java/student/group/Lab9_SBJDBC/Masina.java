package student.group.Lab9_SBJDBC;

public class Masina{

    private String numarInmatriculare;
    private String marca;
    private int anulFabricatiei;
    private String culoarea;
    private int nrKm;

    public Masina(){}
    public Masina(String nrIm, String mar, int an, String cul, int km){
        this.numarInmatriculare = nrIm;
        this.marca = mar;
        this.anulFabricatiei = an;
        this.culoarea = cul;
        this.nrKm = km;
    }

    //Gettere si settere
    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }
    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public int getAnulFabricatiei() {
        return anulFabricatiei;
    }
    public void setAnulFabricatiei(int anulFabricatiei) {
        this.anulFabricatiei = anulFabricatiei;
    }
    public String getCuloarea() {
        return culoarea;
    }
    public void setCuloarea(String culoarea) {
        this.culoarea = culoarea;
    }
    public int getNrKm() {
        return nrKm;
    }
    public void setNrKm(int nrKm) {
        this.nrKm = nrKm;
    }
    //ToString
    @Override
    public String toString() {
        return "Numar inmatriculare: " + numarInmatriculare+", Marca: "+marca
                +", An fabricatie: "+anulFabricatiei+", Culoare: "+ culoarea +", Nr Km: "+nrKm;
    }


}
