package com.camy.androidmidterm;

import android.os.Parcel;
import android.os.Parcelable;

public class CRACustomer implements Parcelable
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

    protected CRACustomer(Parcel in) {
        if (in.readByte() == 0) {
            sinNumber = null;
        } else {
            sinNumber = in.readInt();
        }
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
        fName = in.readString();
        lName = in.readString();
        fullName = in.readString();
        gender = in.readString();
        dateOfBirth = in.readString();
        taxFillingDate = in.readString();
        grossIncome = in.readDouble();
        federalTax = in.readDouble();
        provicialTax = in.readDouble();
        empInsurance = in.readDouble();
        rrspContri = in.readDouble();
        rrspCarryForward = in.readDouble();
        taxableIncome = in.readDouble();
        taxPaid = in.readDouble();
    }
    public CRACustomer(Integer sinNumber, Integer age, String fName, String lName, String gender, String dateOfBirth, String taxFillingDate, double grossIncome, double rrspContri) {
        this.sinNumber = sinNumber;
        this.age = age;
        this.fName = fName;
        this.lName = lName;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.taxFillingDate = taxFillingDate;
        this.grossIncome = grossIncome;
        this.rrspContri = rrspContri;
    }



    public static final Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

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
        return lName.toUpperCase() + ", " +
                fName.substring(0,1).toUpperCase() + fName.substring(1);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (sinNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(sinNumber);
        }
        if (age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(age);
        }
        parcel.writeString(fName);
        parcel.writeString(lName);
        parcel.writeString(fullName);
        parcel.writeString(gender);
        parcel.writeString(dateOfBirth);
        parcel.writeString(taxFillingDate);
        parcel.writeDouble(grossIncome);
        parcel.writeDouble(federalTax);
        parcel.writeDouble(provicialTax);
        parcel.writeDouble(empInsurance);
        parcel.writeDouble(rrspContri);
        parcel.writeDouble(rrspCarryForward);
        parcel.writeDouble(taxableIncome);
        parcel.writeDouble(taxPaid);
    }
}
