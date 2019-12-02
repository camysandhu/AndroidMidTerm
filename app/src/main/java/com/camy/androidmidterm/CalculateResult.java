package com.camy.androidmidterm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CalculateResult extends AppCompatActivity {
    CRACustomer calCRA;
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
        backButton();

    }

    public void backButton()
    {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void performLogic()
    {

        // calculate  cpp
        double grossIncome = calCRA.getGrossIncome();
        if(grossIncome > 57400.00)
        {
            cpp = (57400.00 * 0.051); //5.10%
        } else
        {
            cpp = (grossIncome * 0.051);
        }
        txtCPP.setText("CPP Contribution in Year:\t" + cpp);
        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        txtEmpInsurance.setText("Employeement Insurance: \t" + ei);
        // calculate RRSP
        rrsp = calCRA.getRrspContri();
        double maxRRSP = (grossIncome * 0.18); //18%
        if(rrsp > maxRRSP ){
            rrspCf = rrsp - maxRRSP;
            rrsp = maxRRSP;
        }else{
            rrsp = rrsp;
        }
        txtRRSPCarryForward.setText("RRSP Carry forward: \t"+ rrspCf);
        //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);
        //Toast.makeText(this, "(Double)taxableIncome" + taxableIncome, Toast.LENGTH_SHORT).show();
        txtTaxableIncome.setText("Taxable income:\t" + (double) taxableIncome);
        //federal tax
        double calFederal = calcFedralTax();
        txtDfederalTax.setText("Federal Tax: \t" + calFederal);
        // Provincial Tax
        double calProvincial = calcProvincialTax();
        txtDprovincialTax.setText("Provincial Tax:\t" + calProvincial);
        // total tax paid
        double taxpaid = calTaxPaid();
        txtTaxPaid.setText("Total tax Paid:\t" + taxpaid);
    }

    public double calcCpp(){
        // calculate  cpp
        if(calCRA.getGrossIncome() > 57000.00){
            cpp = (57000.00 * (5.10 / 100));
        } else {
            cpp = (calCRA.getGrossIncome() * (5.10 / 100));
        }
        return cpp;
    }
    public double calcFedralTax() {
        //calculate federal tax
        double temp = taxableIncome;
        if (taxableIncome < 12069.00) {
            federalTax = 0;
            temp = taxableIncome - 12069.00;
        } else if (temp < 47630.00) {
            federalTax = (temp * 0.15);
            temp = temp - federalTax;
        } else if (temp < 95259.00) {
            federalTax = (temp * 0.205); //20.50%
            temp = temp - federalTax;
        } else if (temp < 147667.00) {
            federalTax = (temp * 0.26); //26%
            temp = temp - federalTax;
        } else if (temp < 210371.00) {
            federalTax = (temp * 0.29);//29%
            temp = temp - federalTax;
        } else {
            federalTax = (temp * 0.33);//33%
            temp = temp - federalTax;
        }
        return federalTax;
    }
    public  double calcProvincialTax(){
        //calculate provincial tax
        return provincialTax;
    }
    public  double calTaxPaid()
    {
        return totalTaxPaid;
    }
}
