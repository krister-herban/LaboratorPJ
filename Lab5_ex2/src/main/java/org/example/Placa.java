package org.example;

import java.util.Arrays;

/**
 * Enum-ul Orientare este utilizat pentru a specifica
 * orientarea placii în raport cu mobilierul (LUNGIME, LATIME, ORICARE).
 */
enum Orientare {
    LUNGIME,
    LATIME,
    ORICARE
};

/**
 * Clasa Placa reprezinta o componenta individuala a unui obiect de tip Mobilier.
 * Fiecare placa are atribute precum dimensiuni, orientare, canturi aplicate si numar de bucati necesare.
 */
public class Placa {
    private String descriere;
    private int lungime;
    private int latime;
    private Orientare orientare;
    private boolean[] canturi;
    private int nr_bucati;

    /**
     * Constructor implicit pentru clasa Placa.
     */
    public Placa() {}
    /**
     * Constructor pentru clasa Placa, initializeaza obiectul cu descrierea, dimensiunile, orientarea, canturile si numarul de bucati.
     *
     * @param descriere Descrierea placii.
     * @param lungime Lungimea placii in milimetri.
     * @param latime Latimea placii in milimetri.
     * @param orientare Orientarea placii (LUNGIME, LATIME, ORICARE).
     * @param canturi Array boolean care indica aplicarea cantului pe fiecare latura a placii.
     * @param nr_bucati Numarul de bucati de acest tip necesar.
     */
    public Placa(String descriere, int lungime, int latime, Orientare orientare, boolean[] canturi, int nr_bucati) {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.canturi = canturi;
        this.nr_bucati = nr_bucati;
    }

    //Gettere si Setere
    /**
     * Obține descrierea placii.
     *
     * @return Descrierea placii.
     */
    public String getDescriere() {
        return descriere;
    }
    /**
     * Obține lungimea placii.
     *
     * @return Lungimea in milimetri.
     */
    public int getLungime() {
        return lungime;
    }
    /**
     * Obține latimea placii.
     *
     * @return Latimea in milimetri.
     */
    public int getLatime() {
        return latime;
    }
    /**
     * Obtine orientarea placii.
     *
     * @return Orientarea placii.
     */
    public Orientare getOrientare() {
        return orientare;
    }
    /**
     * Obtine canturile placii.
     *
     * @return Array boolean care indica aplicarea cantului pe fiecare latura a placii.
     */
    public boolean[] getCanturi() {
        return canturi;
    }
    /**
     * Obtine numarul de bucati de acest tip necesar.
     *
     * @return Numarul de bucati.
     */
    public int getNr_bucati() {
        return nr_bucati;
    }
    /**
     * Seteaza descrierea placii.
     *
     * @param descriere Noul descriere a placii.
     */
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    /**
     * Seteaza lungimea placii.
     *
     * @param lungime Noua lungime a placii.
     */
    public void setLungime(int lungime) {
        this.lungime = lungime;
    }
    /**
     * Seteaza latimea placii.
     *
     * @param latime Noua latime a placii.
     */
    public void setLatime(int latime) {
        this.latime = latime;
    }
    /**
     * Seteaza orientarea placii.
     *
     * @param orientare Noua orientare a placii.
     */
    public void setOrientare(Orientare orientare) {
        this.orientare = orientare;
    }
    /**
     * Seteaza canturile placii.
     *
     * @param canturi Noul array de canturi.
     */
    public void setCanturi(boolean[] canturi) {
        this.canturi = canturi;
    }
    /**
     * Seteaza numarul de buctii pentru acest tip de placa.
     *
     * @param nr_bucati Numarul de bucati.
     */
    public void setNr_bucati(int nr_bucati) {
        this.nr_bucati = nr_bucati;
    }

    /**
     * Reprezentarea text a unei placi
     *
     * @return Reprezentarea toString
     */
    @Override
    public String toString() {
        return (descriere+", "+ lungime +", "+latime+", "+orientare+", "+ Arrays.toString(canturi)+"\n");
    }
}
