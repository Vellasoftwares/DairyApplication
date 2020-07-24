package com.vella.dairyapplication.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vella.dairyapplication.Models.Product;
import com.vella.dairyapplication.Models.Purchase;
import com.vella.dairyapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class SelectQuantityFragment extends Fragment {

    private TextView startDate;
    private Calendar mCalender;
    private Button proceed_button;
    private Product product;
    private RadioGroup packageRadioGroup;
    private RadioGroup dayTypeRadioGroup;
    private RadioGroup dayTimeRadioGroup;
    private EditText editQuantity;
    private Purchase purchase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_quantity_fragment, null);
        mCalender = Calendar.getInstance();
        startDate = view.findViewById(R.id.edit_start_date);
        proceed_button = view.findViewById(R.id.proceed_button);
        packageRadioGroup = view.findViewById(R.id.package_radio_grp);
        dayTypeRadioGroup = view.findViewById(R.id.day_type_radiogroup);
        dayTimeRadioGroup = view.findViewById(R.id.day_time_radiogroup);
        editQuantity =  view.findViewById(R.id.edit_quantity);
        product = (Product) getArguments().getSerializable("product");
        purchase = new Purchase();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view1, int year, int month, int dayOfMonth) {
                mCalender.set(Calendar.YEAR, year);
                mCalender.set(Calendar.MONTH, month);
                mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SelectQuantityFragment.this.setDateEditText(startDate, mCalender);
            }
        };

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Purchase purchase = addData();
                if (purchase.getDays() != null && purchase.getDaytime() != null && purchase.getDaytype() != null
                        && purchase.getQuantity() != null && purchase.getDate() != null){
                    loadFragment(new ConfirmDetailsFragment(), purchase);
                }
                else {
                    Toast.makeText(getActivity(), "Please add details..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Objects.requireNonNull(SelectQuantityFragment.this.getActivity()),
                        date, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH),
                        mCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

    private Purchase addData() {

        int packageId = packageRadioGroup.getCheckedRadioButtonId();
        int dayTypeId = dayTypeRadioGroup.getCheckedRadioButtonId();
        int dayTimeId = dayTimeRadioGroup.getCheckedRadioButtonId();

        if (packageId == R.id.month){
            purchase.setDays("30 days");
        }else if (packageId == R.id.one_day){
            purchase.setDays("1 day");
        }
        if (dayTypeId == R.id.daily){
            purchase.setDaytype("daily");
        }
        else if (dayTypeId == R.id.alternative_days){
            purchase.setDaytype("alternative");
        }
        if (dayTimeId == R.id.morning){
            purchase.setDaytime("morning");
        }else if (dayTimeId == R.id.evening){
            purchase.setDaytime("evenning");
        }

        if (!editQuantity.getText().toString().isEmpty()){
            purchase.setQuantity(editQuantity.getText().toString());
        }

        return purchase;

    }

    private void setDateEditText(TextView case_date, Calendar mCalender) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        case_date.setText(sdf.format(mCalender.getTime()));
        purchase.setDate(sdf.format(mCalender.getTime()));
    }

    private void loadFragment(Fragment fragment, Purchase purchase) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",product);
        bundle.putSerializable("purchase",purchase);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.my_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
    

}
