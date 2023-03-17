	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	public class CSVNew {
	public static void main(String[] args) throws IOException {
	try {
	// Load input CSV file
	BufferedReader reader = new BufferedReader(
	new FileReader("C:\\Users\\gopi_s\\Cucumber_Project\\Cucumber\\src\\test\\java\\sampledata.csv"));
	// Create output CSV file
	BufferedWriter writer = new BufferedWriter(
	new FileWriter("C:\\Users\\gopi_s\\Cucumber_Project\\Cucumber\\src\\test\\java\\output.csv"));
	// Define header fields for output file
	String[] headers = { "_Class", "_id", "address1", "address2", "bankName", "bdpRecordInd", "bachInfo",
	"cityName", "completeAddress", "completeAddress", "CountryCode", "CountryName", "doNotIseFlag",
	"expandedRemitanceFlag", "hoganBankId", "manualUpdateInd", "modificationInd", "nationalId",
	"overrideFlag", "parentBankCode", "PrefferedBankId", "source", "sourceCreateTimeStamp",
	"sourceCreateUser", "sourceModifiedUser", "sourceModifyTimeStamp", "state", "wfFlag", "zipCode" };
	StringBuilder headerLine = new StringBuilder();
	for (int i = 0; i < headers.length; i++) {
	headerLine.append(headers[i]);
	if (i != headers.length - 1) {
	headerLine.append(",");
	}
	}
	writer.write(headerLine.toString());
	writer.newLine();
	// Define field types for each header field
	int[] fieldTypes = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	1 };
	// Process each row and perform validation
	String row;
	while ((row = reader.readLine()) != null) {
	String[] fields = row.split(",");
	StringBuilder outputLine = new StringBuilder();
	for (int i = 0; i < headers.length; i++) {
	if (fields.length > i) {
	String fieldValue = fields[i];
	// Check if field value is empty
	if (fieldValue.isEmpty()) {
	outputLine.append("FAIL,");
	continue;
	}
	// Check if field value is of the correct type
	if (fieldTypes[i] == 1) { // Field is a string
	if (fieldValue.matches("^[a-zA-Z.\\s]+$")) {
	outputLine.append("PASS,");
	} else {
	outputLine.append("FAIL,");
	}
	}
	if (fieldTypes[i] == 2) { // Field is an integer
	if (fieldValue.matches("\\d+")) {
	outputLine.append("PASS,");
	} else {
	outputLine.append("FAIL,");
	}
	}
	} else {
	outputLine.append("FAIL,");
	}
	}
	writer.write(outputLine.toString());
	writer.newLine();
	}
	reader.close();
	writer.close();
	System.out.println("Validation Done, Output Written in output.csv file successfully");
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	//Validate national ID
	public static boolean isValidNationalId(int nationalId) {
	String nationalIdStr = String.valueOf(nationalId);
	// Check if national ID is 9 digits long
	if (nationalIdStr.length() != 9) {
	return false;
	}
	// Check if national ID is not all zeros
	if (nationalIdStr.matches("^0*$")) {
	return false;
	}
	// Check if the first digit is not zero or one
	if (nationalIdStr.charAt(0) == '0' || nationalIdStr.charAt(0) == '1') {
	return false;
	}
	// Check if the last digit is a valid check digit
	int sum = 0;
	for (int i = 0; i < 8; i++) {
	int digit = Character.getNumericValue(nationalIdStr.charAt(i));
	sum += digit * (i + 2);
	}
	
	int checkDigit = (11 - (sum % 11)) % 10;
	if (checkDigit != Character.getNumericValue(nationalIdStr.charAt(8))) {
	return false;
	}
	return true;
	}
	}
