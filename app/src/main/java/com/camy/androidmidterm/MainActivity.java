package com.camy.androidmidterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private Button btnCal;
    private EditText edSIN;
    private EditText fnm;
    private EditText lnm;
    private EditText mainAge;
    private RadioGroup rgMain;
    private RadioButton rbMale;
    private RadioButton rbFMale;
    private RadioButton rbOthers;
    String radio = "";
    private TextView txtDateOfBirth;
    DatePickerDialog datePickerDialog;
    DatePickerDialog taxDatePickerDialog;
    final Calendar calendar = Calendar.getInstance();
    final Calendar calendarTax = Calendar.getInstance();
    private TextView taxFilling;
    private TextView rrspMain;
    private  EditText grossIncome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCal = findViewById(R.id.btnCalculate);
        fnm = findViewById(R.id.txtFirstName);
        edSIN = findViewById(R.id.edSinNumber);
        lnm = findViewById(R.id.txtLastName);
        mainAge = findViewById(R.id.txtAge);
        rgMain = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFMale = findViewById(R.id.rbFemale);
        rbOthers = findViewById(R.id.rbOthers);
        txtDateOfBirth = findViewById(R.id.txtDOB);
        taxFilling = findViewById(R.id.txtFillingDate);
        rrspMain = findViewById(R.id.txtRRSPContributed);
        grossIncome = findViewById(R.id.edGrossIncome);

    }
}
