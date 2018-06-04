package rs.aleph.android.example1.providers;

import java.util.ArrayList;
import java.util.List;

import rs.aleph.android.example1.model.Kategorija;

public class KategorijaProvider {

    public  static List<Kategorija> getKategorije(){

        List<Kategorija> kategorije = new ArrayList<>();
        kategorije.add(new Kategorija(0, "Rostilj"));
        kategorije.add(new Kategorija(1, "Kuvana Jela"));
        return kategorije;
    }

    public static List<String> getKatogrijaNames(){
         List<String> names = new ArrayList<>();
         names.add("Rostilj");
         names.add("Kuvana Jela");
         return names;

    }
    public static Kategorija getKategorijaById(int id){

        switch (id){
            case 0:
                return new Kategorija(0, "Rostilj");
            case 1:
                return new Kategorija(1, "Kuvana Jela");
                default:
                    return null;
        }
    }
}
