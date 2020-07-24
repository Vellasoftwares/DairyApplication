package com.vella.dairyapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vella.dairyapplication.Models.UserData;
import com.vella.dairyapplication.R;

public class RegistrationPage1 extends Fragment {

    private Button btnNext;
    private EditText editFirstname;
    private EditText editLastName;
    private EditText editMobileNo;
    private EditText editAltMobileNo;
    private EditText editEmailId;
    private EditText editPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.registration_page1, null);

        btnNext = view.findViewById(R.id.btn_next);
        editFirstname = view.findViewById(R.id.edit_first_name);
        editLastName = view.findViewById(R.id.edit_last_name);
        editMobileNo = view.findViewById(R.id.edit_mobile_no);
        editAltMobileNo = view.findViewById(R.id.edit_alternative_mb);
        editEmailId = view.findViewById(R.id.edit_emailid);
        editPassword = view.findViewById(R.id.edit_password);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidData()){
                    UserData userData = getUserData();
                    loadFragment(new RegistrationPage2(), userData);
                }
                else {
                    Toast.makeText(getActivity(), "Please fill complete data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private UserData getUserData() {
        UserData userData = new UserData();
        userData.setFname(editFirstname.getText().toString());
        userData.setLname(editLastName.getText().toString());
        userData.setMobileNo(editMobileNo.getText().toString());
        userData.setAltMobileNo(editAltMobileNo.getText().toString());
        userData.setEmailId(editEmailId.getText().toString().trim());
        userData.setPassword(editPassword.getText().toString().trim());
        return userData;
    }

    private boolean isValidData() {
        if (!editFirstname.getText().toString().isEmpty() &&
        !editLastName.getText().toString().isEmpty() &&
        !editMobileNo.getText().toString().isEmpty() &&
        !editEmailId.getText().toString().isEmpty() &&
        !editPassword.getText().toString().isEmpty())
        {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Method to load fragment
     *
     * @param fragment name of fragment screen
     */
    private void loadFragment(Fragment fragment, UserData userData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("userData", userData);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.registration_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
