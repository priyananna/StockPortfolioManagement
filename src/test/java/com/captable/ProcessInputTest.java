package com.captable;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class ProcessInputTest {
    InvestmentCap investmentCap = new InvestmentCap();

    @Test
    public void processInputTest_Success_Path() throws CustomServiceException{
            String[] input = {"2016-04-03", "1000", "10000.00", "Sandy Lerner"};
            String date = "2019-01-02";
            Map<String, Ownership> map = new HashMap<>();
            investmentCap.processInput(input, date, map);
            Assert.assertNotNull(map.get("Sandy Lerner"));
    }

    @Test
    public void processInputTest_Invalid_Date_InFile() throws CustomServiceException{
        String[] input = {"2019-31-12","1000","10000.00","Sandy Lerner"};
        String date = "2019-01-02";
        String message = "SEVERE: Date Format should be YYYY-MM-DDText '2019-31-12' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 31";
        Map<String, Ownership> map = new HashMap<>();
        try {
            investmentCap.processInput(input, date, map);
        }
        catch (Exception e){
            Assert.assertEquals(message, e.getMessage());
        }

    }

    @Test
    public void processInputTest_Invalid_Date_Input() throws CustomServiceException{
        String[] input = {"2019-01-02","1000","10000.00","Sandy Lerner"};
        String date = "2019-31-31";
        String message = "SEVERE: Date Format should be YYYY-MM-DDText '2019-31-12' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 31";
        Map<String, Ownership> map = new HashMap<>();
        try {
            investmentCap.processInput(input, date, map);
        }
        catch (Exception e){
            Assert.assertEquals(message, e.getMessage());
        }

    }

    @Test
    public void processInputTest_Future_Specifiedate(){
        String[] input = {"2019-01-01","1000","10000.00","Sandy Lerner"};
        String date = "2019-09-09";
        String message = "INFO: Specified Date cannot be greater than Today's date";
        Map<String, Ownership> map = new HashMap<>();
        try {
            investmentCap.processInput(input, date, map);
        }
        catch (Exception e){
            Assert.assertEquals(message, e.getMessage());
        }

    }


    @Test
    public void processInputTest_Invalid_Formatdate()throws CustomServiceException{
        String[] input = {"2019-01-01","1000","10000.00","Sandy Lerner"};
        String date = "20190909";
        String message = "INFO: Specified Date cannot be greater than Today's date";
        Map<String, Ownership> map = new HashMap<>();
         investmentCap.processInput(input, date, map);


    }


    @Test(expected = CustomServiceException.class)
    public void processInputTest_SharesAsNull() throws CustomServiceException{
        String[] input = {"2016-04-03", "null", "10000.00", "Sandy Lerner"};
        String date = "2019-01-02";
        Map<String, Ownership> map = new HashMap<>();
        investmentCap.processInput(input, date, map);

    }

    @Test(expected = CustomServiceException.class)
    public void processInputTest_SharesAsBlank() throws CustomServiceException{
        String[] input = {"2016-04-03", "", "10000.00", "Sandy Lerner"};
        String date = "2019-01-02";
        Map<String, Ownership> map = new HashMap<>();
        investmentCap.processInput(input, date, map);

    }

    @Test
    public void processInputTest_SharesAsZero() throws CustomServiceException{
        String[] input = {"2016-04-03", "0", "10000.00", "Sandy Lerner"};
        String date = "2019-01-02";
        Map<String, Ownership> map = new HashMap<>();
        investmentCap.processInput(input, date, map);

    }

    @Test(expected = CustomServiceException.class)
    public void processInputTest_CashAsNull() throws CustomServiceException{
        String[] input = {"2016-04-03", "1000", "null", "Sandy Lerner"};
        String date = "2019-01-02";
        Map<String, Ownership> map = new HashMap<>();
        investmentCap.processInput(input, date, map);

    }

    @Test(expected = CustomServiceException.class)
    public void processInputTest_CashAsBlank() throws CustomServiceException{
        String[] input = {"2016-04-03", "1000", "", "Sandy Lerner"};
        String date = "2019-01-02";
        Map<String, Ownership> map = new HashMap<>();
        investmentCap.processInput(input, date, map);

    }

    @Test(expected = CustomServiceException.class)
    public void processInputTest_Missing_InputRecord_Column() throws CustomServiceException{
        String[] input = {"2016-04-03", "1000", "Sandy Lerner"};
        String date = "2019-01-02";
        Map<String, Ownership> map = new HashMap<>();
        investmentCap.processInput(input, date, map);
    }

}


