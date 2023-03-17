package BaseFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    public static WebDriver driver;
    static File file;
    public static Properties prop;
    static FileInputStream fip;
    public static String filepath;
    protected static Logger logger = Logger.getLogger(BaseClass.class.getName()); // initialize logger

    public static void initializeProp(String filepath) {
        BaseClass.filepath = filepath;
        file = new File(filepath); // initialize the file path
        try {
            fip = new FileInputStream(file);
            prop = new Properties();
            prop.load(fip);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "FileNotFoundException found in initializeProp: " + e.getMessage(), e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException found in initializeProp: " + e.getMessage(), e);
        }
    }

    public static String readProperty(String property) {
        return prop.getProperty(property);
    }

    public void launchBrowser() {
        try {
            String browserName = BaseClass.readProperty("chrome");
            String driverPath = BaseClass.readProperty("C:\\BrowserDriver\\Chrome_110\\chromedriver_win32 (3)\\chromedriver.exe");
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
                logger.log(Level.INFO, "Chrome driver initialized successfully.");
            } else {
                logger.log(Level.INFO, "Please enter valid browser name.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception found in launchBrowser: " + e.getMessage(), e);
        }
    } 

    public static void closeBrowser() {
        try {
            driver.close();
            logger.log(Level.INFO, "Browser closed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception found in closeBrowser: " + e.getMessage(), e);
        }
    }
} 
