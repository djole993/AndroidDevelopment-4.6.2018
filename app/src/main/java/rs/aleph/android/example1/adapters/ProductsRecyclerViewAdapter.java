package rs.aleph.android.example1.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rs.aleph.android.example1.R;
import rs.aleph.android.example1.fragments.ListFragment;
import rs.aleph.android.example1.model.Jelo;

public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder>{

    private List<Jelo> jela;
    private OnProductClickedListener listener;



    public ProductsRecyclerViewAdapter(List<Jelo> productList, OnProductClickedListener listener){
        jela = productList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(tv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int productId = jela.get(position).getId();
        holder.tvProductName.setText(jela.get(position).getNaziv());
        holder.tvProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onProductClicked(productId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jela.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvProductName;
        ViewHolder(View itemView) {
            super(itemView);
            tvProductName = (TextView) itemView;
        }
    }

    public interface OnProductClickedListener{
        void onProductClicked(int id);
    }
}
