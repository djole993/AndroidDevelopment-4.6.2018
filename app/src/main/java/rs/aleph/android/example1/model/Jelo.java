package rs.aleph.android.example1.model;

import java.util.ArrayList;
import java.util.List;

public class Jelo {

    private int id;
    private String slika;
    private String naziv;
    private String opis;
    private int klVresnost;
    private double cena;
    private float rating;
    private Kategorija kategorija;

   private List<Sastojak> sastojci;

    public Jelo(){

        sastojci =  new ArrayList<>();

    }

    public Jelo(int id, String slika, String naziv, String opis, int klVresnost, double cena, float rating, Kategorija kategorija) {
        this.id = id;
        this.slika = slika;
        this.naziv = naziv;
        this.opis = opis;
        this.klVresnost = klVresnost;
        this.cena = cena;
        this.rating = rating;
        this.kategorija = kategorija;

        sastojci =  new ArrayList<>();

    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKlVresnost() {
        return klVresnost;
    }

    public void setKlVresnost(int klVresnost) {
        this.klVresnost = klVresnost;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public List<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(List<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }
    public void addSastojak(Sastojak sastojak) {

        sastojci.add(sastojak);
    }

    public void removeJelo(Sastojak sastojak) {

        sastojci.remove(sastojak);
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Jelo{" +
                "id=" + id +
                ", slika='" + slika + '\'' +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", klVresnost=" + klVresnost +
                ", cena=" + cena +
                '}';
    }
}
