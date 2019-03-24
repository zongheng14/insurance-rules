package com.drools.dao;

public class InsuranceRules {

    private Integer count;

    private String insurancePeriod; //保险期间

    private String saleArea; //销售区域

    private Integer insuredAge; //被保人年龄


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod(String insurancePeriod) {
        this.insurancePeriod = insurancePeriod;
    }

    public Integer getInsuredAge() {
        return insuredAge;
    }

    public void setInsuredAge(Integer insuredAge) {
        this.insuredAge = insuredAge;
    }

    public String getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}