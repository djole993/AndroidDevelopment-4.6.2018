package rs.aleph.android.example1.model;

import java.util.ArrayList;
import java.util.List;

public class Kategorija {

    private int id;
    private String naziv;
    private List<Jelo> jela;


    public Kategorija(){

        jela =  new ArrayList<>();
    }
    public Kategorija(int idKategorije, String nazivKategorije) {
        this.id = idKategorije;
        this.naziv = nazivKategorije;

        jela =  new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int idKategorije) {
        this.id = idKategorije;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void addJelo(Jelo jelo) {

       jela.add(jelo);
    }

    public void removeJelo(Jelo jelo) {

       jela.remove(jelo);
    }

    @Override
    public String toString() {
        return "Kategorija{" +
                "id=" + id +
                ", nazivKategorije='" + naziv+ '\'' +
                '}';
    }
}
