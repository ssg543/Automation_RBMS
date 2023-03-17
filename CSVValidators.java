import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVValidators 
{
	public static void main(String[] args) throws IOException 
	{
		try 
		{
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\gopi_s\\Cucumber_Project\\Cucumber\\src\\test\\java\\sampledata.csv"));
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("C:\\Users\\gopi_s\\Cucumber_Project\\Cucumber\\src\\test\\java\\outputs.csv"));

			String headerLine = reader.readLine();
			String[] headers = headerLine.split(",");

			int[] fieldTypes = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2,
					3 };

			StringBuilder headerLineBuilder = new StringBuilder();
			for (int i = 0; i < headers.length; i++) 
			{
				headerLineBuilder.append(headers[i]);
				if (i != headers.length - 1) 
				{
					headerLineBuilder.append(",");
				}
			}
			/*writer.write(headerLineBuilder.toString());
			writer.newLine(); */

			String row;
			while ((row = reader.readLine()) != null) 
			{
				String[] fields = row.split(",");
				String[] results = new String[headers.length];
				for (int i = 0; i < headers.length; i++) 
				{
					if (fields.length > i) 
					{
						String fieldValue = fields[i];

						if (fieldValue.isEmpty()) 
						{
							results[i] = "FAIL";
							continue;
						}

						if (fieldTypes[i] == 1) 
						{
							results[i] = "PASS";
						} 
						else if (fieldTypes[i] == 2) 
						{
							if (fieldValue.matches("-?\\d+")) 
							{
								results[i] = "PASS";
							} 
							else 
							{
								results[i] = "FAIL";
							}
						} 
						else if (fieldTypes[i] == 3) 
						{ 
							// Field is a decimal
							if (fieldValue.matches("-?\\d+(\\.\\d+)?")) {
								results[i] = "PASS";
							} 
							else 
							{
								results[i] = "FAIL";
							}
						} 
						else if (fieldTypes[i] == 4) {
							if (fieldValue.matches("\\d{9}")) 
							{
								int sum = 0;
								for (int j = 0; j < 9; j++) 
								{
									int digit = Character.getNumericValue(fieldValue.charAt(j));
									int weight = (j == 7) ? 7 : (j + 1) % 3;
									sum += digit * weight;
								}
								if (sum % 10 == 0) 
								{
									results[i] = "PASS";
								} 
								else 
								{
									results[i] = "FAIL";
								}
							} 
							else 
							{
								results[i] = "FAIL";
							}
						} 
						else 
						{
							results[i] = "FAIL";
						}
						writer.write(headers[i] + ": " + results[i] + "  \t");
					} 
					else 
					{
					results[i] = "FAIL";
					writer.write(headers[i] + ": " + results[i] + "  \t");
					}
					}
					writer.newLine();
					}
			reader.close();
			writer.close();
			System.out.println("Validation Done and File Saved Successfully");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
