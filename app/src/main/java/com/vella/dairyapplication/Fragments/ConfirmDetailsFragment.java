package com.vella.dairyapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vella.dairyapplication.Models.Product;
import com.vella.dairyapplication.Models.ProductPurchase;
import com.vella.dairyapplication.Models.Purchase;
import com.vella.dairyapplication.R;

public class ConfirmDetailsFragment extends Fragment {

    private Button btnConfirmAddress;
    private ImageView prodImage;
    private TextView textProductName;
    private TextView textDays;
    private TextView textQuantity;
    private TextView textDayTime;
    private TextView textToPay;
    private Product product;
    private Purchase purchase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.confirm_details_fragment, null);
        Bundle bundle = getArguments();
        assert bundle != null;
        product = (Product) bundle.getSerializable("product");
        purchase = (Purchase) bundle.getSerializable("purchase");

        btnConfirmAddress = view.findViewById(R.id.btn_confirm_address);
        prodImage = view.findViewById(R.id.product_image_confirm_details);
        textProductName = view.findViewById(R.id.text_product_value);
        textDays = view.findViewById(R.id.text_package_value);
        textQuantity = view.findViewById(R.id.text_total_quantity_value);
        textDayTime = view.findViewById(R.id.text_timming_value);
        textToPay = view.findViewById(R.id.text_to_pay_value);

        setData();
        btnConfirmAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductPurchase productPurchase = new ProductPurchase();
                productPurchase.setProductName(product.getProdName());
                productPurchase.setPrice(product.getProdPrice());
                productPurchase.setPurchase(purchase);
                loadFragment(new ConfirmAddressFragment(),productPurchase);
            }
        });
        return view;
    }

    private void setData() {
        prodImage.setBackgroundResource(product.getProdImage());
        textProductName.setText(product.getProdName());
        textDays.setText(purchase.getDays());
        textDayTime.setText(purchase.getDaytime());
        textQuantity.setText(purchase.getQuantity());
        int total = calculateTotal();
        purchase.setTotalAmmount(String.valueOf(total));
        textToPay.setText(String.valueOf(total));
    }

    private int calculateTotal() {
        int price = Integer.parseInt(product.getProdPrice());
        int quantity = Integer.parseInt(purchase.getQuantity());
        if (purchase.getDays().equals("1 day")) {
            return price * quantity;
        } else {
            return price * quantity * 30;
        }

    }

    private void loadFragment(Fragment fragment, ProductPurchase productPurchase) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("productPurchase",productPurchase);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.my_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
