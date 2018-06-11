package rs.aleph.android.example1.model;

public class Sastojak {

    private int id;
    private String naziv;
    private Jelo jelo;


    public Sastojak(){


    }

    public Sastojak(int idSastojci, String nazivSastojic) {
        this.id = idSastojci;
        this.naziv = nazivSastojic;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Jelo getJelo() {
        return jelo;
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }




    public String getNazivSastojic() {
        return naziv;
    }




    @Override
    public String toString() {
        return  naziv;

    }
}
