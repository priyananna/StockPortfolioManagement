package com.captable;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Logger;


public class InvestmentCap {

    private static Logger LOGGER = Logger.getLogger(InvestmentCap.class.getName());
    private String dateOutput;
    private double cash_raised = 0.00d;
    private Integer total_number_of_shares = 0;
    private DecimalFormat df1 = new DecimalFormat("0.00");


    public String capTableCheck(String csvFile, String date) {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        BufferedReader br = null;
        Map<String, Ownership> map = new HashMap<String, Ownership>();
        OutputObj outputObj = new OutputObj();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String line = br.readLine();
            if (line == null) {
                LOGGER.info("Input File is Empty ");
            }
            while ((line = br.readLine()) != null) {
                String[] cap = line.split(",");
                processInput(cap, date, map);

            }
            //setting the output object with the details
            outputObj = new OutputObj();
            outputObj.setDate(dateOutput);
            outputObj.setCash_raised(df1.format(cash_raised));
            outputObj.setTotal_number_of_shares(total_number_of_shares);
            List<Ownership> ownerInvestor = new ArrayList<Ownership>();

            for (String key : map.keySet()) {
                Ownership owner = map.get(key);
                double value = (double) owner.getShares() / total_number_of_shares;

                owner.setOwnership(df1.format(value * 100));
                map.put(key, owner);
                ownerInvestor.add(owner);
            }
            //sorting the investors based on the no of highest shares
            ownerInvestor.sort(Comparator.comparing(Ownership::getShares).reversed());
            outputObj.setOwnership(ownerInvestor);

            //writing the output in json format in the /NANNA-CARTA/output.json
            mapper.writeValue(new File("./output.json"), outputObj);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputObj);


        } catch (FileNotFoundException e) {
            LOGGER.severe("Error : File not found " + e.getMessage());
        } catch (IOException e) {
            LOGGER.severe("Error : Input/output error" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }


    public void processInput(String[] cap, String date, Map<String, Ownership> map) throws  CustomServiceException{

        try {
            LocalDate todayDate = LocalDate.now();

            //specified date is used as todayDate if date is null
            LocalDate specifiedDate = todayDate;

            if (cap.length != 4) {
                LOGGER.severe("Input record has missing column, record should have INVESTMENT DATE, SHARES PURCHASED, CASH PAID, INVESTOR");
            }

            //map the record from file to the object of class InputInvestmentDetails
            InputInvestmentDetails investDtls = new InputInvestmentDetails(cap[0], cap[1], cap[2], cap[3].trim());


            //if the date is given, then it is used as specifiedDate. If specified date is future date then today's date is used
            if (date != null) {
                specifiedDate = LocalDate.parse(date);
                if (specifiedDate.isAfter(todayDate)) {
                    LOGGER.info("Specified Date cannot be greater than Today's date, So filter is today's date");
                    specifiedDate = todayDate;
                }
            }

            LocalDate investDate = LocalDate.parse(investDtls.getInvestmentDate());

            if (investDate.isEqual(specifiedDate) || investDate.isBefore(specifiedDate)) {
                dateOutput = specifiedDate.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));

                // total cash raised and num of shares
                cash_raised = cash_raised + Double.valueOf(investDtls.getCashPaid());
                total_number_of_shares = total_number_of_shares + Integer.valueOf(investDtls.getSharesPurchased());

                //Maintaining a map for the ownership details
                Ownership owner = new Ownership();
                if (map.containsKey(investDtls.getInvestor())) {
                    owner = map.get(investDtls.getInvestor());
                    owner.setShares(owner.getShares() + Integer.valueOf(investDtls.getSharesPurchased()));
                    double cashPaid = Double.valueOf(owner.getCash_paid()) + Double.valueOf(investDtls.getCashPaid());
                    owner.setCash_paid(df1.format(cashPaid));
                } else {
                    owner.setShares(Integer.valueOf(investDtls.getSharesPurchased()));
                    owner.setCash_paid(investDtls.getCashPaid());
                    owner.setInvestor(investDtls.getInvestor());
                }
                map.put(investDtls.getInvestor().trim(), owner);
            }
        } catch (DateTimeParseException e) {
            LOGGER.severe("Date Format should be YYYY-MM-DD" + e.getMessage());

        } catch (Exception e) {
            LOGGER.severe("CustomServiceException " + e.getClass().getCanonicalName() +" "+ e.getMessage());
            throw new CustomServiceException("CustomServiceException ");
        }
    }


    public static void main(String args[]) {
        String csvFile = args[0];
        String date = null;
        if (args.length > 1) {
            date = args[1];
        }

        InvestmentCap investmentCap = new InvestmentCap();
        String json = investmentCap.capTableCheck(csvFile, date);
      //output.json is created in project folder, to display on console below is used.
        System.out.println(json);

    }
}


