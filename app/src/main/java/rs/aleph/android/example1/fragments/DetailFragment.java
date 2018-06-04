package rs.aleph.android.example1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import rs.aleph.android.example1.R;
import rs.aleph.android.example1.model.Sastojak;
import rs.aleph.android.example1.providers.JeloProvider;
import rs.aleph.android.example1.providers.KategorijaProvider;
import rs.aleph.android.example1.providers.SastojciProvider;

// Each fragment extends Fragment class
public class DetailFragment extends Fragment {

    // Position of the item to be displayed in the detail fragment
    private int position = 0;

    // onAttach method is a life-cycle method that is called when a fragment is first attached to its context.
    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onAttach()", Toast.LENGTH_SHORT).show();
    }

    // onCreate method is a life-cycle method that is called when creating the fragment.
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onCreate()", Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
        }
    }

    // onCreateView method is a life-cycle method that is called  to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onCreateView()", Toast.LENGTH_SHORT).show();

        // Finds view in the view hierarchy and returns it.
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    // onActivityCreated method is a life-cycle method that is called when the fragment's activity has been created and this fragment's view hierarchy instantiated.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onActivityCreated()", Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position", 0);
        }

        // Finds "tvName" TextView and sets "text" property
        ImageView ivImage= (ImageView) getView().findViewById(R.id.iv_image);
        InputStream is =null;
        try {
            is = getActivity().getAssets().open(JeloProvider.getJeloById(position).getSlika());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView tvName = (TextView) getView().findViewById(R.id.tv_name);
        tvName.setText(JeloProvider.getJeloById(position).getNaziv());


        // Finds "tvDescription" TextView and sets "text" property
        TextView tvDescription = (TextView) getView().findViewById(R.id.tv_description);
        tvDescription.setText(JeloProvider.getJeloById(position).getOpis());

        TextView kaloriskVr= (TextView) getView().findViewById(R.id.tv_kaloriska_vrednost);
        String kaloStr =String.valueOf(JeloProvider.getJeloById(position).getKlVresnost());
        kaloriskVr.setText(kaloStr);

        TextView cena= (TextView) getView().findViewById(R.id.tv_cena_hrane);
        String cenaStr = String.valueOf(JeloProvider.getJeloById(position).getCena());
        cena.setText(cenaStr);

        // Finds "rbRating" RatingBar and sets "rating" property
        RatingBar rbRating = (RatingBar) getView().findViewById(R.id.rb_rating);
        rbRating.setRating(JeloProvider.getJeloById(position).getRating());


        // Finds "spCategory" Spiner and sets "selection" property
        Spinner category = (Spinner) getView().findViewById(R.id.sp_kategorija);
        List<String> katogrijaNames = KategorijaProvider.getKatogrijaNames();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, katogrijaNames);
        category.setAdapter(adapter);
        category.setSelection((int)JeloProvider.getJeloById(position).getKategorija().getId());
//
//        //spiner za ssastojke
        Spinner sastojak = (Spinner) getView().findViewById(R.id.sp_lista_sastojka);
        List<Sastojak> sastojakName = JeloProvider.getJeloById(position).getSastojci();
        ArrayAdapter<Sastojak> adapterr = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sastojakName);
        sastojak.setAdapter(adapterr);
        sastojak.setSelection((int)SastojciProvider.getSastojakById(position).getId());

        // Finds "btnBuy" Button and sets "onClickListener" listener
        Button btnBuy = (Button) getView().findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "You've bought " + JeloProvider.getJeloById(position).getNaziv() + "!", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        // Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getActivity(), "SecondActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//
//            // PERMISSIONS_REQUEST_INTERNET is an app-defined int constant.
//            // The callback method gets the result of the request.
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSIONS_REQUEST_INTERNET);
//
//        } else {
//
//            // Loads an URL into the WebView
//            String URL = getIntent().getStringExtra("URL");
//            if (!URL.trim().equalsIgnoreCase("")) {
//                WebView myWebView = (WebView) findViewById(R.id.pageInfo);
//                myWebView.getSettings().setJavaScriptEnabled(true);
//                myWebView.setWebViewClient(new MyWebViewClient());
//                myWebView.loadUrl(URL.trim());
//            }
//
//        }

    }


    // onStart method is a life-cycle method that is called when the Fragment is visible to the user.
    @Override
    public void onStart() {
        super.onStart();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onStart()", Toast.LENGTH_SHORT).show();
    }

    // onResume method is a life-cycle method that is called when the fragment is visible to the user and actively running.
    @Override
    public void onResume() {
        super.onResume();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onResume()", Toast.LENGTH_SHORT).show();
    }

    // onPause method is a life-cycle method that is called when the Fragment is no longer resumed.
    @Override
    public void onPause() {
        super.onPause();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onPause()", Toast.LENGTH_SHORT).show();
    }

    // onStop method is a life-cycle method that is called when the Fragment is no longer started.
    @Override
    public void onStop() {
        super.onStop();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onStop()", Toast.LENGTH_SHORT).show();
    }

    // onDestroyView method is a life-cycle method that is called when the view previously created by onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.
    @Override
    public void onDestroyView() {

        super.onDestroyView();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    // onDestroy method is a life-cycle method that is called when the fragment is no longer in use.
    @Override
    public void onDestroy() {
        super.onDestroy();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onDestroy()", Toast.LENGTH_SHORT).show();
    }

    // onDetach method is a life-cycle method that is called when the fragment is no longer attached to its activity.
    @Override
    public void onDetach() {
        super.onDetach();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onDetach()", Toast.LENGTH_SHORT).show();
    }

    // onSaveInstanceState method is a life-cycle method that is called to ask the fragment to save its current dynamic state, so it can later be reconstructed in a new instance of its process is restarted.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onSaveInstanceState()", Toast.LENGTH_SHORT).show();

        savedInstanceState.putInt("position", position);
    }

    public void btnOpenCameraClicked (View view) {
//        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
//        }else{
        invokeCamera();
//        }
    }


    private void invokeCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivity(takePictureIntent);
    }

//    private class MyWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//            return false;
//        }
//    }

//     This method is invoked asynchronously for every call on requestPermissions

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_INTERNET: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // Permission was granted
//
//                    // Loads an URL into the WebView
//                    String URL = getIntent().getStringExtra("URL");
//                    if (!URL.trim().equalsIgnoreCase("")) {
//                        WebView myWebView = (WebView) findViewById(R.id.pageInfo);
//                        myWebView.getSettings().setJavaScriptEnabled(true);
//                        myWebView.setWebViewClient(new MyWebViewClient());
//                        myWebView.loadUrl(URL.trim());
//                    }
//
//                } else {
//
//                    // Permission denied
//                }
//                return;
//            }
//            case CAMERA_REQUEST_CODE:{
//
//                    if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                        invokeCamera();
//                    }else{
//                        Toast.makeText(this, getString(R.string.unable_to_invoke_camera), Toast.LENGTH_LONG).show();
//                    }
//                return;
//
//            }
//            // other 'case' lines to check for other permissions this app might request
//     }
//    }


    // Called to set fragment's content.
    public void setContent(final int position) {

        this.position = position;

        Log.v("DetailFragment", "setContent() sets position to " + position);
    }

    // Called to update fragment's content.
    public void updateContent(final int position) {

        this.position = position;

        Log.v("DetailFragment", "updateContent() sets position to " + position);

        ImageView ivImage= (ImageView) getView().findViewById(R.id.iv_image);
        InputStream is =null;
        try {
            is = getActivity().getAssets().open(JeloProvider.getJeloById(position).getSlika());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView tvName = (TextView) getView().findViewById(R.id.tv_name);
        tvName.setText(JeloProvider.getJeloById(position).getNaziv());


        // Finds "tvDescription" TextView and sets "text" property
        TextView tvDescription = (TextView) getView().findViewById(R.id.tv_description);
        tvDescription.setText(JeloProvider.getJeloById(position).getOpis());

        TextView kaloriskVr= (TextView) getView().findViewById(R.id.tv_kaloriska_vrednost);
        String kaloStr =String.valueOf(JeloProvider.getJeloById(position).getKlVresnost());
        kaloriskVr.setText(kaloStr);

        TextView cena= (TextView) getView().findViewById(R.id.tv_cena_hrane);
        String cenaStr = String.valueOf(JeloProvider.getJeloById(position).getCena());
        cena.setText(cenaStr);

        // Finds "rbRating" RatingBar and sets "rating" property
        RatingBar rbRating = (RatingBar) getView().findViewById(R.id.rb_rating);
        rbRating.setRating(JeloProvider.getJeloById(position).getRating());


        // Finds "spCategory" Spiner and sets "selection" property
        Spinner category = (Spinner) getView().findViewById(R.id.sp_kategorija);
        List<String> katogrijaNames = KategorijaProvider.getKatogrijaNames();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, katogrijaNames);
        category.setAdapter(adapter);
        category.setSelection((int)JeloProvider.getJeloById(position).getKategorija().getId());
//
//        //spiner za ssastojke
        Spinner sastojak = (Spinner) getView().findViewById(R.id.sp_lista_sastojka);
        List<Sastojak> sastojakName = JeloProvider.getJeloById(position).getSastojci();
        ArrayAdapter<Sastojak> adapterr = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sastojakName);
        sastojak.setAdapter(adapterr);
        sastojak.setSelection((int)SastojciProvider.getSastojakById(position).getId());

        // Finds "btnBuy" Button and sets "onClickListener" listener
        Button btnBuy = (Button) getView().findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "You've bought " + JeloProvider.getJeloById(position).getNaziv() + "!", Toast.LENGTH_LONG);
                toast.show();
            }
        });


    }
}
