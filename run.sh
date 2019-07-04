echo "Enter input file path"
read input_path

echo "Enter  Date (YYYY-MM-DD format)  (Enter to skip) :"
read date

#./gradlew clean fatjar > /dev/null


if [ -z "$date" ]
then
      #echo "Date is empty"
      java -cp ./build/libs/com.stocks-all-1.0-SNAPSHOT.jar com.captable.InvestmentCap  $input_path
else
      #echo "\Date is provided"
      java -cp ./build/libs/com.stocks-all-1.0-SNAPSHOT.jar com.captable.InvestmentCap  $input_path $date

fi
