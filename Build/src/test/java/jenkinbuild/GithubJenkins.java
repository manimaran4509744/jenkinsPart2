package jenkinbuild;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GithubJenkins {
	WebDriver driver;
	static ExtentReports report;
	static ExtentSparkReporter spark;

	@BeforeSuite
	private void report() {
		report = new ExtentReports();
		spark = new ExtentSparkReporter("./newReport.html");
		spark.config().setDocumentTitle("jenkins");
		spark.config().setReportName("JenkinsBuild");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setTimeStampFormat("dd-MM-yyyy");
		report.attachReporter(spark);
	}

	@BeforeMethod
	private void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
		report.createTest("setUp").info("The Chrome Browser is initialized");
	}

	@AfterMethod
	private void flush() {

		report.flush();

	}

	@Test
	private void launch() {
		driver.get("https://www.google.com/");
		report.createTest("Launch").pass("This test is passed");

	}

	@AfterSuite
	private void tearDown() {
		driver.close();
	}
}
