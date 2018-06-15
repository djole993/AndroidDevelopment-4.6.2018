package rs.aleph.android.example1.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import rs.aleph.android.example1.R;

/**
 * Created by milossimic on 10/23/16.
 */
public class ReviewerTools {
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectionType(Integer type){
        switch (type){
            case 1:
                return "WIFI";
            case 2:
                return "Mobilni internet";
            default:
                return "Uredjaj nije povezan na Internet Mrezu";
        }
    }
    public static int calculateTimeTillNextSync(int minutes){
        return 1000 * 60 * minutes;
    }
    public void writeToFile(String data, Context context, String fileName) throws IOException {
        try {
        FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write(data);
        outputStreamWriter.close();
    }catch (IOException e){
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    public static String readFromFile(Context context, String file){
        String ret = "";
        try {
            InputStream stream = context.openFileInput(file);
            if (stream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String reciveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((reciveString = bufferedReader.readLine()) != null){
                    stringBuilder.append(reciveString);
                }
                stream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }
    public static void fillAdapter(String []products, Context context){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.list_item,products);
        ListView listView =(ListView) ((Activity)context).findViewById(R.id.lista_jela);
        listView.setAdapter(adapter);
    }
    public static void readFile(Context context){
        String text = ReviewerTools.readFromFile(context, "myfile.txt");
        String[] data = text.split("\n");
        fillAdapter(data,context);
    }
    public static boolean isFileExist(Context context,String filename){
        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + filename);
        if (file.exists()){
            return true;
        }else{
            return false;
        }
    }
}