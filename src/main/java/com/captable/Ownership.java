package com.captable;

/**
 * Created by priyankananna on 6/30/19.
 */

class Ownership{
    String investor;
    Integer shares;
    String cash_paid;
    String  ownership;

    public Ownership(){

    }

    public Ownership(String investor, Integer shares, String cash_paid, String ownership) {
        this.investor = investor;
        this.shares = shares;
        this.cash_paid = cash_paid;
        this.ownership = ownership;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public String getCash_paid() {
        return cash_paid;
    }

    public void setCash_paid(String cash_paid) {
        this.cash_paid = cash_paid;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }


}