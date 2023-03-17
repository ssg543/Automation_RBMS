import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;

public class CSVValidator {
	public static void main(String[] args) {

		String[] headers = { "_Class", "_id", "address1", "address2", "bankName", "bdpRecordInd", "bachInfo",
				"cityName", "completeAddress", "completeAddress", "CountryCode", "CountryName", "doNotIseFlag",
				"expandedRemitanceFlag", "hoganBankId", "manualUpdateInd", "modificationInd", "nationalId",
				"overrideFlag", "parentBankCode", "PrefferedBankId", "source", "sourceCreateTimeStamp",
				"sourceCreateUser", "sourceModifiedUser", "sourceModifyTimeStamp", "state", "wfFlag", "zipCode" };

		String[] dataTypes = { "string", "string", "string", "string", "string", "boolean", "string", "string",
				"string", "string", "string", "string", "boolean", "boolean", "int", "boolean", "boolean", "nationalId",
				"boolean", "string", "int", "string", "string", "string", "string", "string", "boolean", "string",
				"string" };

		try {
			Scanner scanner = new Scanner(
					new File("C:\\Users\\gopi_s\\Cucumber_Project\\Cucumber\\src\\test\\java\\sampledata.csv"));

			String[] headerFields = scanner.nextLine().split(",");
			boolean hasFailed = false;

			for (int i = 0; i < headers.length; i++) {
				if (!headerFields[i].equals(headers[i])) {
					hasFailed = true;
					System.out.print("FAIL\t");
				} else {
					System.out.print("PASS\t");
				}
			}

			System.out.println();

			while (scanner.hasNextLine()) {
				String[] fields = scanner.nextLine().split(",");

				for (int i = 0; i < fields.length; i++) {
					String dataType = dataTypes[i];
					String fieldValue = fields[i];

					if (dataType.equals("string")) {
						System.out.print("PASS\t");
					} else if (dataType.equals("int")) {
						if (!fieldValue.isEmpty()) {
							try {
								Integer.parseInt(fieldValue);
								System.out.print("PASS\t");
							} catch (NumberFormatException e) {
								System.out.print("FAIL\t");
								hasFailed = true;
							}
						} else {
							System.out.print("FAIL\t");
							hasFailed = true;
						}
					} else if (dataType.equals("boolean")) {
						if (fieldValue.equals("true") || fieldValue.equals("false")) {
							System.out.print("PASS\t");
						} else {
							System.out.print("FAIL\t");
							hasFailed = true;
						}
					} else if (dataType.equals("nationalId")) {
						if (isValidNationalId(fieldValue)) {
							System.out.print("PASS\t");
							fields[i] = "PASS";
						} else {
							System.out.print("FAIL\t");
							fields[i] = "FAIL";
							hasFailed = true;
						}
					}
				}

				fields[17] = fields[17].equals("FAIL") ? "FAIL" : "PASS";
				System.out.println();
			}

			scanner.close();

			if (!hasFailed) {
				System.out.println("CSV file is valid.");
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}

	public static boolean isValidNationalId(String nationalId) {
	    	    
		if (!nationalId.matches("\\d{9}")) {

			return false;

			}
			int sum = 0;
			for (int i = 0; i < 9; i++) {
			int digit = Character.getNumericValue(nationalId.charAt(i));
			int weight = (i == 8) ? 1 : ((i % 2 == 0) ? 1 : 7);
			sum += digit * weight;
			}
			return sum % 10 == 0;
			}
}