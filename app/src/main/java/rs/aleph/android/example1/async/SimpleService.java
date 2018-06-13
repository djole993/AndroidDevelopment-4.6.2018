package rs.aleph.android.example1.async;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import rs.aleph.android.example1.tools.ReviewerTools;

public class SimpleService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Metoda koja se poziva prilikom izvrsavanja zadatka servisa
     * Koristeci Intent mozemo prilikom startovanja servisa proslediti
     * odredjene parametre.
     * */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        /**
         * Provericemo trenutnu povezanost sa mrezom.
         * Za ovo koristimo dostupne pozive android operativnog sistema
         * */

        //        int status = ReviewerTools.getConnectivityStatus(getApplicationContext());

                int status = intent.getExtras().getInt("STATUS");

        /**
         * Primer poziva asinhronog zadatka ako ima veze ka mrezi
         * npr. sinhronizacija mail-ova fotografija, muzike dokumenata isl.
         * */

        new SimpleSyncTask(getApplicationContext()).execute();// treba .execute(status);

        if(status == ReviewerTools.TYPE_WIFI){

        }

        /**
         * Zaustaviti servis nakon obavljenog pokretanja asinhronog zadatka.
         * Ovu metodu nije potrebno pozvati ako zelimo da nasa aplikacija
         * konstantno osluskuje na neke izmene (npr. novi email, viber poruka isl)
         * */
        stopSelf();

        /**
         * Ako iz nekog razloga operativni sistem ubije servis
         * ne kreirati novi.
         * */
        return START_NOT_STICKY;
    }
}
