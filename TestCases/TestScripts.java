package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScripts {
    WebDriver driver;
    FileInputStream file;
    Workbook workbook;
    Sheet sheet;
    
    @BeforeTest
    public void setUp() throws IOException {
        // Load the Excel file
        File file = new File("C:\\Users\\gopi_s\\Music\\TestData.xlsx");
        FileInputStream fis = new FileInputStream(file);

        // Load the workbook
        workbook = WorkbookFactory.create(fis);

        // Load the sheet
        sheet = workbook.getSheetAt(0);

        // Initialize the WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\\\BrowserDriver\\\\Chrome_110\\\\chromedriver_win32 (3)\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        // Loop through each row in the sheet
        for (Row row : sheet) {
            // Get the URL from the first cell in the row
            Cell urlCell = row.getCell(0);
            String url = urlCell.getStringCellValue();

            // Navigate to the URL
            driver.get(url);

            // Loop through each subsequent cell in the row and perform the corresponding action
            for (int i = 1; i < row.getLastCellNum(); i++) {
                Cell actionCell = row.getCell(i);
                String action = actionCell.getStringCellValue();

                // Perform the action based on the value in the cell
                if (action.equalsIgnoreCase("click")) {
                    Cell elementCell = sheet.getRow(0).getCell(i);
                    WebElement element = driver.findElement(By.cssSelector(elementCell.getStringCellValue()));
                    element.click();
                } else if (action.equalsIgnoreCase("input")) {
                    Cell elementCell = sheet.getRow(0).getCell(i);
                    String[] elementArray = elementCell.getStringCellValue().split(",");
                    WebElement element = driver.findElement(By.cssSelector(elementArray[0]));
                    element.sendKeys(elementArray[1]);
                }
            }
        }
    }

    @AfterTest
    public void tearDown() {
        // Quit the driver and close the workbook
        driver.quit();
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
