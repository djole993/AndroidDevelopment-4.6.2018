package rs.aleph.android.example1.providers;

import java.util.ArrayList;
import java.util.List;

import rs.aleph.android.example1.model.Jelo;
import rs.aleph.android.example1.model.Kategorija;
import rs.aleph.android.example1.model.Sastojak;

public class JeloProvider {

    public static List<Jelo> getJelo(){

        Kategorija rostilj =  new Kategorija(0, "Rostilj");
        Kategorija KuvanaJela = new Kategorija(1, "Kuvana Jela");


        List<Jelo> jela = new ArrayList<>();

        jela.add(new Jelo(0,"cevapi.jpg","Cevapi","meso",200, 750, 4.0f, rostilj));
        jela.add(new Jelo(1,"sarma.jpg","Sarma","mleveno meso u kupusu",200, 750, 3.5f,KuvanaJela));
        return jela;
    }

    public static List<String> getJeloNames(){

        List<String> names = new ArrayList<>();
        names.add("Cevapi");
        names.add("Sarma");
        return names;

    }

    public static Jelo getJeloById(int id){

        Kategorija rostilj =  new Kategorija(0, "Rostilj");
        Kategorija KuvanaJela = new Kategorija(1, "Kuvana Jela");

        switch (id){
            case 0:
                Jelo jelo = new Jelo(0,"cevapi.jpg","Cevapi","meso",200, 750, 4.0f, rostilj);
                jelo.addSastojak(new Sastojak(0, "mleveno meso od govedine"));
                jelo.addSastojak(new Sastojak(1, "soja,so, biber"));
                return jelo;
            case 1:
                return new Jelo(1,"sarma.jpg","Sarma","mleveno meso u kupusu",200, 750, 3.5f,KuvanaJela);
            default:
                return null;
        }
    }
}
