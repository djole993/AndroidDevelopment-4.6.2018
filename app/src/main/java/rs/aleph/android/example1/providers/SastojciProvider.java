package rs.aleph.android.example1.providers;

import java.util.ArrayList;
import java.util.List;

import rs.aleph.android.example1.model.Sastojak;


public class SastojciProvider {


    public  static List<Sastojak> getKategorije(){

        List<Sastojak> sastojaks = new ArrayList<>();
        sastojaks.add(new Sastojak(0, "svinjetina i govedina"));
        sastojaks.add(new Sastojak(1,"mleveno meso i kiseo kupus"));
        return sastojaks;
    }

    public static List<String> getSastojakNames(){
        List<String> names = new ArrayList<>();
        names.add("svinjetina i govedina");
        names.add("mleveno meso i kiseo kupus");
        return names;

    }
    public static Sastojak getSastojakById(int id){

        switch (id){
            case 0:
                return new Sastojak(0, "svinjetina i govedina");
            case 1:
                return new Sastojak(1,"mleveno meso i kiseo kupus");
            default:
                return null;
        }
    }
}