package com.binod.esoftwarica;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditStudent extends AppCompatActivity implements View.OnClickListener {

    EditText etNameU, etAddressU, etAgeU;
    RadioGroup radioGroupU;
    RadioButton radioMaleU, radioFemaleU, radioOtherU;
    Button btnSaveU;
    String name, address, gender;
    int age;
    public static int index;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        etNameU = findViewById(R.id.etNameU);
        etAddressU = findViewById(R.id.etAddressU);
        etAgeU = findViewById(R.id.etAgeU);
        radioGroupU = findViewById(R.id.radioGenderU);
        btnSaveU = findViewById(R.id.btnSaveU);
        radioMaleU = findViewById(R.id.radioMaleU);
        radioFemaleU = findViewById(R.id.radioFemaleU);
        radioOtherU = findViewById(R.id.radioOtherU);

        etNameU.setText(MainActivity.studentLists.get(index).getName());
        etAddressU.setText(MainActivity.studentLists.get(index).getAddress());
        etAgeU.setText(MainActivity.studentLists.get(index).getAge() + "");

        gender = MainActivity.studentLists.get(index).getGender();
        if(gender == "Male"){
            radioMaleU.setChecked(true);
            radioFemaleU.setChecked(false);
            radioOtherU.setChecked(false);
        }else if (gender == "Female"){
            radioFemaleU.setChecked(true);
            radioMaleU.setChecked(false);
            radioOtherU.setChecked(false);
        }else if(gender == "Other"){
            radioOtherU.setChecked(true);
            radioMaleU.setChecked(false);
            radioFemaleU.setChecked(false);
        }


        btnSaveU.setOnClickListener(this);
        radioGroupU.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioMaleU){
                    gender = "Male";
                }
                if(checkedId == R.id.radioFemaleU){
                    gender = "Female";
                }
                if(checkedId == R.id.radioOtherU){
                    gender = "Other";
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        Validate();
        name = etNameU.getText().toString();
        address = etAddressU.getText().toString();
        age = Integer.parseInt(etAgeU.getText().toString());

        try {
            MainActivity.studentLists.get(index).setName(name);
            MainActivity.studentLists.get(index).setAddress(address);
            MainActivity.studentLists.get(index).setAge(age);
            MainActivity.studentLists.get(index).setGender(gender);
            Toast.makeText(EditStudent.this, "update success Please refresh in home", Toast.LENGTH_SHORT).show();
            finish();

        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(EditStudent.this, ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void Validate(){
        if(TextUtils.isEmpty(etNameU.getText())){
            etNameU.setError("Please enter full name");
            etNameU.requestFocus();
            return;
        }else if(TextUtils.isEmpty(etAddressU.getText())){
            etAddressU.setError("Please enter address");
            etAddressU.requestFocus();
            return;
        }else if(TextUtils.isEmpty(etAgeU.getText())){
            etAgeU.setError("Please enter age");
            etAgeU.requestFocus();
            return;
        }
    }
}
