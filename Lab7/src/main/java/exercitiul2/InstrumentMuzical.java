package exercitiul2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class InstrumentMuzical {
    protected String producator;
    protected double pret;

    public InstrumentMuzical(String producator, double pret) {
        this.producator = producator;
        this.pret = pret;
    }
    public InstrumentMuzical() {}
    public String getProducator() {
        return producator;
    }
    public void setProducator(String producator) {
        this.producator = producator;
    }
    public double getPret() {
        return pret;
    }
    public void setPret(float pret) {
        this.pret = pret;
    }
    @Override
    public String toString() {
        return "Producator: " + producator + ", Pret: " + pret;
    }
}
