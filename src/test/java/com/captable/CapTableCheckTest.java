package com.captable;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;


public class CapTableCheckTest {

    InvestmentCap investmentCap = new InvestmentCap();
    String csv = "src/test_data/Input.csv";
    String date = "2019-07-02";
    String expected = "{\n" +
            "  \"date\" : \"07/02/2019\",\n" +
            "  \"cash_raised\" : \"165500.00\",\n" +
            "  \"total_number_of_shares\" : 9500,\n" +
            "  \"ownership\" : [ {\n" +
            "    \"investor\" : \"Sandy Lerner\",\n" +
            "    \"shares\" : 3000,\n" +
            "    \"cash_paid\" : \"60000.00\",\n" +
            "    \"ownership\" : \"31.58\"\n" +
            "  }, {\n" +
            "    \"investor\" : \"Don Valentine\",\n" +
            "    \"shares\" : 3000,\n" +
            "    \"cash_paid\" : \"52000.00\",\n" +
            "    \"ownership\" : \"31.58\"\n" +
            "  }, {\n" +
            "    \"investor\" : \"Ann Miura-Ko\",\n" +
            "    \"shares\" : 2000,\n" +
            "    \"cash_paid\" : \"40000.00\",\n" +
            "    \"ownership\" : \"21.05\"\n" +
            "  }, {\n" +
            "    \"investor\" : \"Fred Wilson\",\n" +
            "    \"shares\" : 1500,\n" +
            "    \"cash_paid\" : \"13500.00\",\n" +
            "    \"ownership\" : \"15.79\"\n" +
            "  } ]\n" +
            "}";


    @Test
    public void capTableCheckTest_Success_Path(){

       String actual = investmentCap.capTableCheck(csv, date);
     //   Assert.assertEquals(expect,investmentCap.capTableCheck(csv, date));
       assertEquals(expected,actual);
    }

    @Test
    public void capTableCheckTest_notNullCheck(){

        assertNotNull(investmentCap.capTableCheck(csv, date));
    }


    @Test(expected = FileNotFoundException.class)
    public void capTableCheckTest_Invalid_File_Error() throws FileNotFoundException{
        String noFile = "src/test_data/nofile.csv";
        BufferedReader br  = new BufferedReader(new FileReader(noFile));
        fail("Code should not make this far, Exception would be thrown before this statement else test case will fail if reached here");
    }

    @Test
    public void capTableCheckTest_Empty_InputFile(){
        String emptyFile = "src/test_data/emptyFile.csv";
        String message = "{\n" +
                "  \"date\" : null,\n" +
                "  \"cash_raised\" : \"0.00\",\n" +
                "  \"total_number_of_shares\" : 0,\n" +
                "  \"ownership\" : [ ]\n" +
                "}";
        assertEquals(message, investmentCap.capTableCheck(emptyFile, date));

    }

}
