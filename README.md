## Project
Program to process the investment details from comma-separated file and generate a json response of investment details.


## Code Details

Steps Involved in the code

1) Input file and optional date parameter can be passed as an argument to the program by using ./run.sh
2) Input records are read from the input file line by line and each record is processed to get total cash_raised, shares and the date for which the captable was requested.
    -> Main class is InvestmentCap
3) Map is used to keep a record of investor and his/her details of ownership details.
4) Total shares, cash_paid and ownership percentage are calculated for the investor and stored in map.
5) If any validation fails ,then customServiceException is thrown to get the appropriate message from the exception.
6) Ownership percentage is calculated based on the shares of investor/total shares.
7) Logger is used to log the SEVERE, INFO messages for the code.
8) Object Mapper(org.codehaus.jackson.map.ObjectMapper) library is used to map the output response object in output file in json format.


## Editor used - Intellij
## Language Used - Java 8
## Build - Gradle

## Installation and run details
1) Unzip the NANNA_CARTA
2) Build is already present with jar file in NANNA_CARTA/build/libs
3) Execute the run.sh shell script i.e use —>(./run.sh) to enter the input file and date(optional)
4) Input file is present in the test_data i.e use —> ./src/test_data/Input.csv and date format as yyyy-mm-yy.
5) the json response can be seen on the console as well as in the output.json file in the current working directory.

## Optional - if you want to build again
Execute the below steps for build in project path Nanna-Carta
1) ./gradlew clean
2) ./gradlew build
3) ./gradlew Test(only test case)
4) ./gradlew fatJar
To run manually
java -cp ./build/libs/com.cartaproject-all-1.0-SNAPSHOT.jar InvestmentCap <Input File path argument> <Optional-date>
For example : java -cp ./build/libs/com.cartaproject-all-1.0-SNAPSHOT.jar InvestmentCap ./src/test_data/Input.csv 2019-01-02

## Tests
CapTableCheckTest - Test class is used test the functionality of the method capTableCheck of InvestmentCap.class
 TestCases
1) Test for the success path
2) To check for the json response not null
3) To test when input file is empty
4) To test when input file is not valid i.e testing for exception.

ProcessInputTest - Test class is used test the functionality of the method processInput of InvestmentCap.class
                    TestCases

1) Test for the success path by processing one record
2) Testing for the invalid specified date(argument)
3) Testing for the invalid date input - specified date(2019-31-31)
4) Test case for future specified date (2019-09-09)
5) Invalid date format, as not in YYYY-MM-DD format
6) Test case for share data from input file as null
7) Test case for share data from input file as blank
8) Test case for share data from input file as 0(zero)
9) Test case for cash data from input file as null
10) Test case for cash data from input file as blank
11) Test case for missing column in the input file

Commands to run test cases from command line

./gradlew clean
./gradlew build


## sample run example

MacBook-Pro:Nanna-Carta->folder$ ./run.sh
Enter input file path
./src/test_data/Input.csv
Enter  Date (YYYY-MM-DD format)  (Enter to skip) :
2019-03-04
{
  "date" : "03/04/2019",
  "cash_raised" : "165500.00",
  "total_number_of_shares" : 9500,
  "ownership" : [ {
    "investor" : "Sandy Lerner",
    "shares" : 3000,
    "cash_paid" : "60000.00",
    "ownership" : "31.58"
  }, {
    "investor" : "Don Valentine",
    "shares" : 3000,
    "cash_paid" : "52000.00",
    "ownership" : "31.58"
  }, {
    "investor" : "Ann Miura-Ko",
    "shares" : 2000,
    "cash_paid" : "40000.00",
    "ownership" : "21.05"
  }, {
    "investor" : "Fred Wilson",
    "shares" : 1500,
    "cash_paid" : "13500.00",
    "ownership" : "15.79"
  } ]
}

output.json file in the current working directory
{"date":"03/04/2019","cash_raised":"165500.00","total_number_of_shares":9500,"ownership":[{"investor":"Sandy Lerner","shares":3000,"cash_paid":"60000.00","ownership":"31.58"},{"investor":"Don Valentine","shares":3000,"cash_paid":"52000.00","ownership":"31.58"},{"investor":"Ann Miura-Ko","shares":2000,"cash_paid":"40000.00","ownership":"21.05"},{"investor":"Fred Wilson","shares":1500,"cash_paid":"13500.00","ownership":"15.79"}]}

