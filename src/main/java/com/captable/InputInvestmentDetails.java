package com.captable;


public class InputInvestmentDetails {
    private String investmentDate;
    private String sharesPurchased;
    private String cashPaid;
    private String Investor;

    public InputInvestmentDetails(){

    }
    public InputInvestmentDetails(String investmentDate, String sharesPurchased, String cashPaid, String investor) {
        this.investmentDate = investmentDate;
        this.sharesPurchased = sharesPurchased;
        this.cashPaid = cashPaid;
        Investor = investor;
    }

    public String getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(String investmentDate) {
        this.investmentDate = investmentDate;
    }

    public String getSharesPurchased() {
        return sharesPurchased;
    }

    public void setSharesPurchased(String sharesPurchased) {
        this.sharesPurchased = sharesPurchased;
    }

    public String getCashPaid() {
        return cashPaid;
    }

    public void setCashPaid(String cashPaid) {
        this.cashPaid = cashPaid;
    }

    public String getInvestor() {
        return Investor;
    }

    public void setInvestor(String investor) {
        Investor = investor;
    }
}