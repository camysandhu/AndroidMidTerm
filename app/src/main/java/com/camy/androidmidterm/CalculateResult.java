package com.camy.androidmidterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CalculateResult extends AppCompatActivity {
    private TextView calculatedfullName;
    private TextView calSIN;
    private TextView calAge;
    private TextView calGender;
    private TextView calDOB;
    private TextView calTaxFillingDate;

    private TextView  txtDgrossIncome;
    private TextView txtDfederalTax;
    private TextView txtDprovincialTax;
    private TextView txtCPP;
    private TextView txtEmpInsurance;
    private TextView txtRRSPContribution;
    private TextView txtRRSPCarryForward;
    private TextView txtTaxableIncome;
    private TextView txtTaxPaid;

    private double cpp = 0, ei = 0;  double rrsp = 0, rrspCf = 0, taxableIncome, federalTax,
            provincialTax, totalTaxPaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_result);

        calculatedfullName = findViewById(R.id.txtCalFullName);
        calSIN =findViewById(R.id.edCalSinNumber);
        calAge = findViewById(R.id.txtCalAge);
        calGender = findViewById(R.id.calRadioGender);
        calDOB = findViewById(R.id.txtCalDOB);
        calTaxFillingDate = findViewById(R.id.txtCalFillingDate);

        txtDgrossIncome = findViewById(R.id.txtCalGrossIncome);
        txtDfederalTax = findViewById(R.id.txtCalFederalTax);
        txtDprovincialTax = findViewById(R.id.txtCalProvincialTax);
        txtCPP = findViewById(R.id.txtCalCPP);
        txtEmpInsurance= findViewById(R.id.txtCalEI);
        txtRRSPContribution = findViewById(R.id.txtCalRRSPContributed);
        txtRRSPCarryForward = findViewById(R.id.txtCalCarryForwardRRSP);
        txtTaxableIncome = findViewById(R.id.txtCalTotalTaxableIncome);
        txtTaxPaid = findViewById(R.id.txtCalTotalTaxPaid);
    }
}
