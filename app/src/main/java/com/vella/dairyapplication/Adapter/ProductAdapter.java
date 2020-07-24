package com.vella.dairyapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vella.dairyapplication.Models.Product;
import com.vella.dairyapplication.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> productArrayList;
    private ProductListener productListener;

    public interface ProductListener{
        void onClickProduct(Product product);
    }
    public ProductAdapter(Context context, ArrayList<Product> productArrayList, ProductListener productListener){
        this.context = context;
        this.productArrayList = productArrayList;
        this.productListener = productListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_recyclerview,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product = productArrayList.get(position);
        holder.prodName.setText(product.getProdName());
        holder.prodPrice.setText("₹ "+product.getProdPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListener.onClickProduct(product);
            }
        });
        holder.prodImage.setBackgroundResource(product.getProdImage());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView prodImage;
        private TextView prodName;
        private TextView prodPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImage = itemView.findViewById(R.id.product_image);
            prodName = itemView.findViewById(R.id.product_name);
            prodPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
