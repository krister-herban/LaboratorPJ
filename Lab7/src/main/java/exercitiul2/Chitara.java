package exercitiul2;

enum TipChitara{
    ELECTRICA,
    ACUSTICA,
    CLASICA
}

public class Chitara extends InstrumentMuzical{
    private int nr_corzi;
    private TipChitara tipChitara;

    public Chitara(){}
    public Chitara(String producator, double pret,  TipChitara tipChitara,int nr_corzi){
        super(producator, pret);
        this.nr_corzi = nr_corzi;
        this.tipChitara = tipChitara;
    }
    public int getNr_corzi(){
        return nr_corzi;
    }
    public TipChitara getTipChitara(){
        return tipChitara;
    }
    public void setNr_corzi(int nr_corzi){
        this.nr_corzi = nr_corzi;
    }
    public void setTipChitara(TipChitara tipChitara){
        this.tipChitara = tipChitara;
    }
    @Override
    public String toString(){
        return super.toString() + " TipChitara: " + tipChitara+" Nr corzi: "+nr_corzi;
    }
}
