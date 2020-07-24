package com.vella.dairyapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vella.dairyapplication.Activity.MainActivity;
import com.vella.dairyapplication.Models.UserData;
import com.vella.dairyapplication.R;

public class RegistrationPage2 extends Fragment {

    private FirebaseAuth mAuth;
    private static final String TAG = RegistrationPage2.class.getName();
    private String email = "harshal.mohite7707@gmail.com";
    private String password = "abc123";
    private Button btnRegister;
    private EditText editWing;
    private EditText editFlatNo;
    private EditText editSocietyName;
    private EditText editLandmark;
    private EditText editArea;
    private EditText editPincode;
    private UserData userData;
    private FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.registration_page2, null);

        btnRegister = view.findViewById(R.id.btn_register);
        editWing = view.findViewById(R.id.edit_wing);
        editFlatNo = view.findViewById(R.id.edit_flat_no);
        editSocietyName = view.findViewById(R.id.edit_society_name);
        editLandmark = view.findViewById(R.id.edit_landmark);
        editArea = view.findViewById(R.id.edit_area);
        editPincode = view.findViewById(R.id.edit_pincode);

        mAuth = FirebaseAuth.getInstance();
        Bundle bundle = getArguments();
        userData = (UserData) bundle.getSerializable("userData");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidData()) {
                    addUserData();
                    SignUpUser(userData.getEmailId(), userData.getPassword());
                }
            }
        });
        return view;
    }

    private boolean isValidData() {
        if (!editWing.getText().toString().isEmpty() &&
                !editFlatNo.getText().toString().isEmpty() &&
                !editSocietyName.getText().toString().isEmpty() &&
                !editLandmark.getText().toString().isEmpty() &&
                !editArea.getText().toString().isEmpty() &&
                !editPincode.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void addUserData() {
        userData.setWing(editWing.getText().toString());
        userData.setFlatNo(editFlatNo.getText().toString());
        userData.setSocietyName(editSocietyName.getText().toString());
        userData.setLandmark(editLandmark.getText().toString());
        userData.setArea(editArea.getText().toString());
        userData.setPincode(editPincode.getText().toString());

        addFirebaseData();
    }

    private void addFirebaseData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("User Data");
        myRef.child(userData.getMobileNo()).setValue(userData);
    }

    private void SignUpUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "welcome user : " + user, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
