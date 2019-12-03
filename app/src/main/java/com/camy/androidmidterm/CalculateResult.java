package com.camy.androidmidterm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.NumberFormat;

public class CalculateResult extends AppCompatActivity {
    CRACustomer calCRA;
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
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
        Intent intent = getIntent();
        calCRA = intent.getParcelableExtra("CRACustomer");

        double pGrossIncome = calCRA.getGrossIncome();

        calSIN.setText("SIN:-- " + calCRA.getSinNumber());
        calculatedfullName.setText("FULLNAME:-- " + calCRA.getFullName());
        calAge.setText(calCRA.getAge());
        calGender.setText("GENDER:-- " + calCRA.getGender());
        calDOB.setText("DOB:-- " + calCRA.getDateOfBirth());
        calTaxFillingDate.setText("TAX FILLIN GDATE:-- "+ calCRA.getTaxFillingDate());
        txtDgrossIncome.setText("GROSS INCOME:--  \t" + numberFormat.format(pGrossIncome));
        txtRRSPContribution.setText("RRSP CONTRIBUTED:-- \t" + numberFormat.format(calCRA.getRrspContri()));
        performLogic();

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
        txtCPP.setText("CPP CONTRIBUTION IN YEAR:--\t" + numberFormat.format(cpp));
        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        txtEmpInsurance.setText("EMPLOYMENT INSURANCE:-- \t" + numberFormat.format(ei));
        // calculate RRSP
        rrsp = calCRA.getRrspContri();
        double maxRRSP = (grossIncome * 0.18); //18%
        if(rrsp > maxRRSP ){
            rrspCf = rrsp - maxRRSP;
            rrsp = maxRRSP;
        }else{
            rrsp = rrsp;
        }
        txtRRSPCarryForward.setText("RRSP CARRY FORWARD:-- \t"+ numberFormat.format(rrspCf));
        //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);
        //Toast.makeText(this, "(Double)taxableIncome" + taxableIncome, Toast.LENGTH_SHORT).show();
        txtTaxableIncome.setText("TAXABLE INCOME:--\t" + numberFormat.format(taxableIncome));
        //federal tax
        double calFederal = calculateFedralTax();
        txtDfederalTax.setText("FEDERAL TAX:-- \t" + numberFormat.format(calFederal));
        // Provincial Tax
        double calProvincial = calculateProvincialTax();
        txtDprovincialTax.setText("PROVINCIAL TAX:--\t" + numberFormat.format(calProvincial));
        // total tax paid
        double taxpaid = calculateTaxPaid();
        txtTaxPaid.setText("TOTAL TAX PAID:--\t" + numberFormat.format(taxpaid));
    }

    public double calculateCpp(){
        // calculate  cpp
        if(calCRA.getGrossIncome() > 57000.00){
            cpp = (57000.00 * (5.10 / 100));
        } else {
            cpp = (calCRA.getGrossIncome() * (5.10 / 100));
        }
        return cpp;
    }
    public double calculateFedralTax() {
        //calculate federal tax
        double mTemp = taxableIncome ;
        if(mTemp <= 12069.00){
            federalTax = 0;//0%
            mTemp = taxableIncome - 12069.00;
        }
        if(mTemp >= 12069.01){
            federalTax = (mTemp * 0.15);//15%
            mTemp = mTemp - 35561;
        }
        if(mTemp >= 47630.01){
            federalTax = (mTemp * 0.205); //20.50%
            mTemp = mTemp - 47628.99;
        }
        if(mTemp >= 95259.01){
            federalTax = (mTemp * 0.26); //26%
            mTemp = mTemp - 52407.99;
        }
        if (mTemp >= 147667.01){
            federalTax = (mTemp * 0.29);//29%
            mTemp = mTemp - 62703.99;
        }
        if(mTemp >= 210371.01){
            federalTax = (mTemp * 0.33);//33%
            //mTemp = mTemp - federalTax;
        }
        return federalTax;
    }
    public  double calculateProvincialTax(){
        //calculate provincial tax
        double mTemp = taxableIncome ;

        if(mTemp <= 10582.00){
            provincialTax = 0;
            mTemp = taxableIncome - 10582.00;
        }
        if(mTemp >= 10582.01){
            provincialTax = (mTemp * 0.0505); //5.05%
            mTemp = mTemp - 33323.99;
        }
        if(mTemp >= 43906.01){
            provincialTax = (mTemp * 0.0915); //9.15%
            mTemp = mTemp - 43906.99;
        }
        if(mTemp >= 87813.01){
            provincialTax = (mTemp * 0.1116); //11.16%
            mTemp = mTemp - 62187.99;
        }
        if (mTemp >= 150000.01){
            provincialTax = (mTemp * 0.1216);//12.16%
            mTemp = mTemp - 69999.99;
        }
        if(mTemp >= 220000.01){
            provincialTax = (mTemp * 0.1316);//13.16%

        }
        return provincialTax;
    }
    public  double calculateTaxPaid()
    {
        totalTaxPaid = federalTax + provincialTax;
        return totalTaxPaid;
    }
}
