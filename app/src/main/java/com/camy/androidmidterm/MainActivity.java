package com.camy.androidmidterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
    private RadioButton rbOthers, radioGender;
    String radio = "";
    private TextView txtDateOfBirth;
    DatePickerDialog datePickerDialog;
    DatePickerDialog taxDatePickerDialog;
    final Calendar calendar = Calendar.getInstance();
    final Calendar calendarTax = Calendar.getInstance();
    private TextView taxFilling;
    private TextView rrspMain;
    private  EditText grossIncome;
    private String choosingGender = "";
    private TextView mainGender;


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
        rbMale = findViewById(R.id.rbXmlMale);
        rbFMale = findViewById(R.id.rbFemale);
        rbOthers = findViewById(R.id.rbOthers);
        txtDateOfBirth = findViewById(R.id.txtDOB);
        taxFilling = findViewById(R.id.txtFillingDate);
        rrspMain = findViewById(R.id.txtRRSPContributed);
        grossIncome = findViewById(R.id.edGrossIncome);
        mainGender = findViewById(R.id.txtGender);

//        radioAction();
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
                dateFormattar();
            }
        };

        txtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(MainActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

               // mainAge.setText("Age: " + txtDateOfBirth.getText().toString());
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
//        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(checkedId == R.id.rbXmlMale){
//                    choosingGender = rbMale.getText().toString();
//                }else if(checkedId == R.id.rbFemale){
//                    choosingGender = rbFMale.getText().toString();
//                }else {
//                    choosingGender = rbOthers.getText().toString();
//                }
//            }
//
//        });
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbXmlMale){
                    choosingGender = rbMale.getText().toString();
                }else if(checkedId == R.id.rbFemale){
                    choosingGender = rbFMale.getText().toString();
                }else {
                    choosingGender = rbOthers.getText().toString();
                }
            }

        });


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




//    public void radioAction()
//    {
//        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(checkedId == R.id.rbXmlMale){
//                    choosingGender = rbMale.getText().toString();
//                }else if(checkedId == R.id.rbFemale){
//                   choosingGender = rbFMale.getText().toString();
//                }else {
//                    choosingGender = rbOthers.getText().toString();
//                }
//            }
//
//        });
//    }

    public void calculateAll()
    {

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//

                    //https://stackoverflow.com/questions/27086808/android-check-null-or-empty-string-in-android

                    if (edSIN.getText().toString().length() <= 0  && fnm.getText().toString().length() <= 0 && lnm.getText().toString().length() <= 0  && rrspMain.getText().toString().length() <= 0  && grossIncome.getText().toString().length() <= 0)
                    {
                       // Toast.makeText(MainActivity.this, "Please FillUp All details", Toast.LENGTH_SHORT).show();
                        //Snackbar.make(getActivity().getWindow().getDecorView().getRootView(), "Testing Snackbar", Snackbar.LENGTH_LONG).show();
                        Snackbar.make(v,"Please FillUp All details",Snackbar.LENGTH_SHORT).show();
                        //Snackbar.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                    }
                    else
                        {
//                            String age= dateFormattar();
//                            if(Integer.parseInt(age)<18)
//                            {
//                                btnCal.setAlpha(.5f);
//                                btnCal.setClickable(false);
//
//
//                            }
//                            else {


                                CRACustomer cra = new CRACustomer(Integer.parseInt(edSIN.getText().toString()),
                                        Integer.parseInt(mainAge.getText().toString()),
                                        fnm.getText().toString(),
                                        lnm.getText().toString(),
                                        choosingGender,
                                        txtDateOfBirth.getText().toString(),
                                        taxFilling.getText().toString(),
                                        Double.parseDouble(grossIncome.getText().toString()),
                                        Double.parseDouble(rrspMain.getText().toString()));

                                Intent mIntent = new Intent(MainActivity.this, CalculateResult.class);
                                mIntent.putExtra("CRACustomer", cra);
//                      mIntent.putExtra("gender", radio);
                                startActivity(mIntent);
                            }


                }

            private Activity getActivity() {
                return null;
            }

        });
    }
//    private void dateFormat() {
//        String myFormat = "dd-MMM-yyyy"; //In which you need put here
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        txtDateOfBirth.setText(sdf.format(calendar.getTime()));
//    }

    private void dateFormatTax() {
        String myFormat = "yyyy-MMM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        taxFilling.setText(sdf.format(calendarTax.getTime()));
    }



    public  void checkedButton(View view){
        int radioId = rgMain.getCheckedRadioButtonId();
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbXmlMale){
                    choosingGender = rbMale.getText().toString();
                }else if(checkedId == R.id.rbFemale){
                    choosingGender = rbFMale.getText().toString();
                }else {
                    choosingGender = rbOthers.getText().toString();
                }
            }
        });
        radioGender = findViewById(radioId);
        mainGender.setText(choosingGender);
        Toast.makeText(this, "Gender:" + mainGender.getText(), Toast.LENGTH_SHORT).show();
    }


    private void dateFormattar() {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDateOfBirth.setText(sdf.format(calendar.getTime()));

//        LocalDate l = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        LocalDate now = LocalDate.now(); //gets localDate
//        Period diff = Period.between(l, now); //difference between the dates is calculated
//
//
//        String n1=String.valueOf(diff.getYears());
//        String n2=String.valueOf(diff.getMonths());
//        String n3=String.valueOf(diff.getDays());
//        String age="Age: "+n1+"Years, "+n2+"Months, "+n3+"Days";
//
//
//        mainAge.setText(age);
//        return  n1;



    }

}
