package com.camy.androidmidterm;

public class CRACustomer
{
    Integer sinNumber;
    Integer age;
    String fName,lName;
    String fullName;
    String gender;
    String dateOfBirth;
    String taxFillingDate;
    double grossIncome, federalTax, provicialTax, empInsurance;
    double rrspContri, rrspCarryForward, taxableIncome, taxPaid;

    public Integer getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(Integer sinNumber) {
        this.sinNumber = sinNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTaxFillingDate() {
        return taxFillingDate;
    }

    public void setTaxFillingDate(String taxFillingDate) {
        this.taxFillingDate = taxFillingDate;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getFederalTax() {
        return federalTax;
    }

    public void setFederalTax(double federalTax) {
        this.federalTax = federalTax;
    }

    public double getProvicialTax() {
        return provicialTax;
    }

    public void setProvicialTax(double provicialTax) {
        this.provicialTax = provicialTax;
    }

    public double getEmpInsurance() {
        return empInsurance;
    }

    public void setEmpInsurance(double empInsurance) {
        this.empInsurance = empInsurance;
    }

    public double getRrspContri() {
        return rrspContri;
    }

    public void setRrspContri(double rrspContri) {
        this.rrspContri = rrspContri;
    }

    public double getRrspCarryForward() {
        return rrspCarryForward;
    }

    public void setRrspCarryForward(double rrspCarryForward) {
        this.rrspCarryForward = rrspCarryForward;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(double taxPaid) {
        this.taxPaid = taxPaid;
    }
}
