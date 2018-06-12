package rs.aleph.android.example1.async;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import rs.aleph.android.example1.fragments.ListFragment;
import rs.aleph.android.example1.tools.ReviewerTools;


/**
 * Created by milossimic on 10/22/16.
 * AsyncTask klasa prima tri parametra prilikom specijalizacije
 * Korisnici sami definisu tip u zavisnosti od posla koji zele da obave.
 *
 * Prvi argument predstavlja ulazne parametre, ono so zelimo
 * da posaljemo zadatku. Recimo ime fajla koji zelimo da skinemo
 *
 * Drugi argument je indikator kako ce se meriti progres. Koliko je posla
 * zavrseno i koliko je opsla ostalo.
 *
 * Treci parametar je povratna vrednost, tj sta ce metoda doInBackground
 * vratiti kao poratnu vrednost metodi onPostExecute
 */
public class SimpleSyncTask extends AsyncTask<Void, Void, Integer>{


    private Context context;

    public SimpleSyncTask(Context context){
        this.context = context;
    }




//        private Activity activity;
//    private ListFragment.OnItemSelectedListener listener;
//
//    public SimpleSyncTask(Activity activity) {
//        this.activity = activity;
//        listener = (ListFragment.OnItemSelectedListener) activity;
//    }

    /**
     * Metoda se poziva pre samog starta pozadinskog zadatka
     * Sve pripreme odraditi u ovoj metodi, ako ih ima.
     */
    @Override
    protected void onPreExecute() {
    }

    /**
     * Posao koji se odvija u pozadini, ne blokira glavnu nit aplikacije.
     * Sav posao koji dugo traje izvrsavati unutar ove metode.
     */
    @Override
    protected Integer doInBackground(Void... params) {
        try {
            //simulacija posla koji se obavlja u pozadini i traje duze vreme
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ReviewerTools.getConnectivityStatus(context);
    }

    /**
     *
     * Kada se posao koji se odvija u pozadini zavrsi, poziva se ova metoda
     * Ako je potrebno osloboditi resurse ili obrisati elemente koji vise ne trebaju.
     */
    @Override
    protected void onPostExecute(Integer status) {
        String msg = "Uredjaj nije povezan na mrezu";
        switch (status){
            case ReviewerTools.TYPE_MOBILE:
                msg = "Uredjaj je povezan preko mobilne mreze";
                break;
            case ReviewerTools.TYPE_WIFI:
                msg = "Uredjaj je povezan preko WIFI";
                break;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
