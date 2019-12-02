package com.camy.androidmidterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

        radioAction();
        calculateAll();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth)
            {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // DatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dateFormat();
            }
        };

        txtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(MainActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth)
            {

                calendarTax.set(Calendar.YEAR, year);
                calendarTax.set(Calendar.MONTH, monthOfYear);
                calendarTax.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // DatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dateFormatTax();
            }
        };


        taxFilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(MainActivity.this, date1, calendarTax
                        .get(Calendar.YEAR), calendarTax.get(Calendar.MONTH),
                        calendarTax.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

    }




    public void radioAction()
    {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                if(checkedId==R.id.rbMale)
                {
                    radio = rbMale.getText().toString();
                    Toast.makeText(getApplicationContext(),"Male",Toast.LENGTH_SHORT).show();
                    rbMale.setSelected(true);
                }
                else if(checkedId==R.id.rbFemale)
                {

                    radio = rbFMale.getText().toString();
                    Toast.makeText(getApplicationContext(),"Female",Toast.LENGTH_SHORT).show();
                    rbFMale.setSelected(true);
                    //rbFMale.setText("Female!");
                }
                else if(checkedId==R.id.rbOthers)
                {

                    radio = rbOthers.getText().toString();
                    Toast.makeText(getApplicationContext(),"Others",Toast.LENGTH_SHORT).show();
                    rbOthers.setSelected(true);
                    // rbOthers.setText("Others!");
                }
            }
        });
    }

    public void calculateAll()
    {

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                CRACustomer cra = new CRACustomer(Integer.parseInt(edSIN.getText().toString()),
                        Integer.parseInt(mainAge.getText().toString()),
                        fnm.getText().toString(),
                        lnm.getText().toString(),
                        rgMain.toString(),
                        txtDateOfBirth.getText().toString(),
                        taxFilling.getText().toString(),
                        Double.parseDouble(grossIncome.getText().toString()),
                        Double.parseDouble(rrspMain.getText().toString()));

                Intent mIntent = new Intent(MainActivity.this, CalculateResult.class);
                mIntent.putExtra("CRACustomer", cra);
//                      mIntent.putExtra("gender", radio);
                startActivity(mIntent);


            }
        });
    }
    private void dateFormat() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txtDateOfBirth.setText(sdf.format(calendar.getTime()));
    }

    private void dateFormatTax() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        taxFilling.setText(sdf.format(calendarTax.getTime()));
    }





}
