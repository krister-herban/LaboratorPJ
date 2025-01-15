package com.example.Lab111;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name="evenimente")
public class Eveniment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String denumire;
    private String locatie;
    private LocalDate data;
    private LocalTime timp;
    private float pret_bile;

    public Eveniment() {}
    public Eveniment(String denumire, String locatie, LocalDate data, LocalTime time, float pret_bile) {
        this.denumire = denumire;
        this.locatie = locatie;
        this.data = data;
        this.timp = time;
        this.pret_bile = pret_bile;
    }
    //gettere si settere
    public void setId(int id) {
        this.id = id;
    }
    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setTime(LocalTime time) {
        this.timp = time;
    }
    public void setPret_bile(float pret_bile) {
        this.pret_bile = pret_bile;
    }
    public int getId() {
        return id;
    }
    public String getDenumire() {
        return denumire;
    }
    public String getLocatie() {
        return locatie;
    }
    public LocalDate getData() {
        return data;
    }
    public LocalTime getTime() {
        return timp;
    }
    public float getPret_bile() {
        return pret_bile;
    }
}