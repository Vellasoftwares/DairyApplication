package com.vella.dairyapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vella.dairyapplication.Activity.MainActivity;
import com.vella.dairyapplication.Models.ProductPurchase;
import com.vella.dairyapplication.Models.UserData;
import com.vella.dairyapplication.R;


public class ConfirmAddressFragment extends Fragment {

    private RadioGroup radioGroupAddress;
    private EditText editDelieveryAddress;
    private RadioButton rbRgisteredAddress;
    private RadioButton rbOtherAddress;
    private Button btnPlaceOrder;
    private FirebaseDatabase firebaseDatabase;
    private UserData userData;
    private ProductPurchase productPurchase;
    private String confirmAddress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.confirm_address_fragment, null);
        radioGroupAddress = view.findViewById(R.id.radiogrp_address);
        editDelieveryAddress = view.findViewById(R.id.edit_delievery_address);
        rbRgisteredAddress = view.findViewById(R.id.radiobtn_registered_address);
        rbOtherAddress = view.findViewById(R.id.radiobtn_other_address);
        btnPlaceOrder = view.findViewById(R.id.btn_place_order);
        Bundle bundle = getArguments();
        productPurchase = (ProductPurchase) bundle.getSerializable("productPurchase");
        getUserData();


        rbRgisteredAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDelieveryAddress.setVisibility(View.GONE);
            }
        });

        rbOtherAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDelieveryAddress.setVisibility(View.VISIBLE);
            }
        });


        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataToFirebase();
            }
        });

        return view;

    }

    private void setDataToFirebase() {
        int addressId = radioGroupAddress.getCheckedRadioButtonId();
        if (addressId == R.id.radiobtn_registered_address) {
            productPurchase.setConfirmAddress(confirmAddress);
            navigateToHomeActivity();
        } else if (addressId == R.id.radiobtn_other_address) {
            if (!editDelieveryAddress.getText().toString().isEmpty()) {
                productPurchase.setConfirmAddress(editDelieveryAddress.getText().toString());
                navigateToHomeActivity();
            } else {
                Toast.makeText(getActivity(), "Please add your address", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void navigateToHomeActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        Toast.makeText(getActivity(), "Order Placed Successfully..!!", Toast.LENGTH_SHORT).show();
        getActivity().finish();
        startActivity(intent);
    }

    private void getUserData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("User Data").getChildren()) {
                    UserData userData = dataSnapshot1.getValue(UserData.class);
                    if (userData.getEmailId().equals(firebaseUser.getEmail())) {
                        setUserData(userData);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setUserData(UserData userData) {
        this.userData = userData;
        confirmAddress = userData.getSocietyName() + " " + userData.getWing() + " " +
                userData.getFlatNo() + " " + userData.getLandmark() + " " + userData.getArea() + " " + userData.getPincode();
        rbRgisteredAddress.setText(confirmAddress);
    }


}
