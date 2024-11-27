package org.example;

import java.util.List;

/**
 * Clasa Mobilier reprezinta un obiect de tip mobilier, conținand o lista de componente (placi).
 * Fiecare mobilier are un nume si o lista de placi care definesc dimensiunile si specificatiile fiecarei componente.
 */

public class Mobilier {
    private String nume;
    private List<Placa> placi;

    /**
     * Constructor implicit pentru clasa Mobilier.
     */
    public Mobilier(){}
    /**
     * Constructor pentru clasa Mobilier, initializeaa obiectul cu un nume si o lista de placi.
     *
     * @param nume Numele mobilierului.
     * @param placi Lista de placi care alcatuiesc mobilierul.
     */
    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }
    /**
     * Obține numele mobilierului.
     *
     * @return Numele mobilierului.
     */
    public String getNume() {
        return nume;
    }
    /**
     * Seteaza numele mobilierului.
     *
     * @param nume Noul nume al mobilierului.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }
    /**
     * Obtine lista de placi componente ale mobilierului.
     *
     * @return Lista de placi.
     */
    public List<Placa> getPlaci() {
        return placi;
    }
    /**
     * Seteaza lista de placi componente ale mobilierului.
     *
     * @param placi Lista de placi care definesc mobilierul.
     */
    public void setPlaci(List<Placa> placi) {
        this.placi = placi;
    }
    /**
     * Reprezentarea text a unui obiect Mobilier.
     *
     * @return Reprezentare sub forma de text a mobilierului.
     */
    @Override
    public String toString() {
        return (nume+", "+placi.toString());
    }
}
