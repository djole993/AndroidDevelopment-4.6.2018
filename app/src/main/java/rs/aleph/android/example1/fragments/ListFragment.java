package rs.aleph.android.example1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import rs.aleph.android.example1.R;
import rs.aleph.android.example1.adapters.ProductsRecyclerViewAdapter;
import rs.aleph.android.example1.model.Jelo;
import rs.aleph.android.example1.providers.JeloProvider;

public class ListFragment extends Fragment implements ProductsRecyclerViewAdapter.OnProductClickedListener {

    @Override
    public void onProductClicked(int id) {

        if(listener !=null){
            listener.onItemSelected(id);
        }

    }

    public interface OnItemSelectedListener{

        void onItemSelected(int position);
    }

    OnItemSelectedListener listener;
    RecyclerView rvJela;


    // onAttach method is a life-cycle method that is called when a fragment is first attached to its context.
    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onAttach()", Toast.LENGTH_SHORT).show();

        try {
            listener = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
        }
    }
    public class GetProductsAsyncTask extends AsyncTask<Void, Void, List<Jelo>> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ListFragment.this.getActivity());
            progressDialog.setMessage("Loading fruits...");
            progressDialog.show();
        }

        @Override
        protected List<Jelo> doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
                return null;
            }
            return JeloProvider.getJelo();
        }

        @Override
        protected void onPostExecute(List<Jelo> jela) {
            super.onPostExecute(jela);
            if (progressDialog != null){
                progressDialog.dismiss();
            }
            setupProductList(jela);
        }
    }
    // onCreate method is a life-cycle method that is called when creating the fragment.
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onCreate()", Toast.LENGTH_SHORT).show();
    }

    // onCreateView method is a life-cycle method that is called to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListtFragment.onCreateView()", Toast.LENGTH_SHORT).show();

        if (container == null) {
            return null;
        }

        // Inflate fragment's layout
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    // onActivityCreated method is a life-cycle method that is called when the fragment's activity has been created and this fragment's view hierarchy instantiated.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        rvJela= (RecyclerView) getView().findViewById(R.id.lista_jela);
        new GetProductsAsyncTask().execute();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onActivityCreated()", Toast.LENGTH_SHORT).show();



    }

    private void setupProductList(List<Jelo> jela) {
        rvJela.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProductsRecyclerViewAdapter adapter = new ProductsRecyclerViewAdapter(jela, this);
        rvJela.setAdapter(adapter);
    }

    // onStart method is a life-cycle method that is called when the Fragment is visible to the user.
    @Override
    public void onStart() {
        super.onStart();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onStart()", Toast.LENGTH_SHORT).show();
    }

    // onResume method is a life-cycle method that is called when the fragment is visible to the user and actively running.
    @Override
    public void onResume() {
        super.onResume();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onResume()", Toast.LENGTH_SHORT).show();
    }

    // onPause method is a life-cycle method that is called when the Fragment is no longer resumed.
    @Override
    public void onPause() {
        super.onPause();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onPause()", Toast.LENGTH_SHORT).show();
    }

    // onStop method is a life-cycle method that is called when the Fragment is no longer started.
    @Override
    public void onStop() {
        super.onStop();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onStop()", Toast.LENGTH_SHORT).show();
    }

    // onDestroyView method is a life-cycle method that is called when the view previously created by onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.
    @Override
    public void onDestroyView() {

        super.onDestroyView();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    // onDestroy method is a life-cycle method that is called when the fragment is no longer in use.
    @Override
    public void onDestroy() {
        super.onDestroy();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "listFragment.onDestroy()", Toast.LENGTH_SHORT).show();
    }

    // onDetach method is a life-cycle method that is called when the fragment is no longer attached to its activity.
    @Override
    public void onDetach() {
        super.onDetach();

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onDetach()", Toast.LENGTH_SHORT).show();
    }

    // onSaveInstanceState method is a life-cycle method that is called to ask the fragment to save its current dynamic state, so it can later be reconstructed in a new instance of its process is restarted.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        // Shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "ListFragment.onSaveInstanceState()", Toast.LENGTH_SHORT).show();
    }

}
