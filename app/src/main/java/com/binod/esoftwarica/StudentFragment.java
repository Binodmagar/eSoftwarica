package com.binod.esoftwarica;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.binod.adapter.Student;

public class StudentFragment extends Fragment implements View.OnClickListener {

    EditText etFullName, etAge, etAddress;
    RadioGroup radioSex;
    Button btnSave;

    String name, address, gender;
    int age;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_studentadd, container, false);

        //binding
        etFullName = view.findViewById(R.id.etFullName);
        etAge = view.findViewById(R.id.etAge);
        etAddress = view.findViewById(R.id.etAddress);
        btnSave = view.findViewById(R.id.btnSave);
        radioSex = view.findViewById(R.id.radioSex);
        btnSave.setOnClickListener(this);

        radioSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioMale){
                    gender = "Male";
                }
                if(checkedId == R.id.radioFemale){
                    gender = "Female";
                }
                if(checkedId == R.id.radioOther){
                    gender = "Other";
                }
            }
        });
        return view;

    }


    @Override
    public void onClick(View v) {
        validateStudent();
        name = etFullName.getText().toString();
        address = etAddress.getText().toString();
        age = Integer.parseInt(etAge.getText().toString());
        MainActivity.studentLists.add(new Student(name, address, gender, age));
        Toast.makeText(getContext(), "Student added sucessfully", Toast.LENGTH_SHORT).show();


    }

    private  void validateStudent(){
        if(TextUtils.isEmpty(etFullName.getText())){
            etFullName.setError("Enter your full name");
            etFullName.requestFocus();
            return;
        }else if(TextUtils.isEmpty(etAddress.getText())){
            etAddress.setError("Please enter address");
            etAddress.requestFocus();
            return;
        }else if(TextUtils.isEmpty(etAge.getText())){
            etAge.setError("Please enter age");
            etAge.requestFocus();
            return;
        }
    }
}
