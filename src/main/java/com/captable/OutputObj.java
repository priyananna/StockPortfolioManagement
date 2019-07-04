package com.captable;

import java.util.List;


class OutputObj{
    String date;
    String cash_raised;
    Integer total_number_of_shares;
    List<Ownership> ownership;

    public OutputObj(){

    }

    public OutputObj(String date, String cash_raised, Integer total_number_of_shares, List<Ownership> ownership) {
        this.date = date;
        this.cash_raised = cash_raised;
        this.total_number_of_shares = total_number_of_shares;
        this.ownership = ownership;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCash_raised() {
        return cash_raised;
    }

    public void setCash_raised(String cash_raised) {
        this.cash_raised = cash_raised;
    }

    public Integer getTotal_number_of_shares() {
        return total_number_of_shares;
    }

    public void setTotal_number_of_shares(Integer total_number_of_shares) {
        this.total_number_of_shares = total_number_of_shares;
    }

    public List<Ownership> getOwnership() {
        return ownership;
    }

    public void setOwnership(List<Ownership> ownership) {
        this.ownership = ownership;
    }
}
