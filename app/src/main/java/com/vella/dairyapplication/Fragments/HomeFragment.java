package com.vella.dairyapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vella.dairyapplication.Adapter.ProductAdapter;
import com.vella.dairyapplication.Models.Product;
import com.vella.dairyapplication.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements ProductAdapter.ProductListener {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ArrayList<Product> productArrayList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.main_recyclerview);

        productArrayList = new ArrayList<>();
        Product product = new Product();
        product.setProdName("Milk");
        product.setProdPrice("50");
        product.setProdImage(R.drawable.milk);
        productArrayList.add(product);

        Product product1 = new Product();
        product1.setProdName("Curd");
        product1.setProdPrice("80");
        product1.setProdImage(R.drawable.curd);
        productArrayList.add(product1);

        Product product2 = new Product();
        product2.setProdName("Paneer");
        product2.setProdPrice("100");
        product2.setProdImage(R.drawable.paneer);
        productArrayList.add(product2);

        Product product3 = new Product();
        product3.setProdName("Basundi");
        product3.setProdPrice("100");
        product3.setProdImage(R.drawable.basundi);
        productArrayList.add(product3);


        productAdapter = new ProductAdapter(getActivity(), productArrayList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(productAdapter);
        return root;
    }

    @Override
    public void onClickProduct(Product product) {
        loadFragment(new SelectQuantityFragment(), product);
    }

    private void loadFragment(Fragment fragment, Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        fragment.setArguments(bundle);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.my_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

    }
}