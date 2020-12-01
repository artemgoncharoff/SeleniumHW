import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestPlan {
    private static WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Delete a post")
    public static void deletePost() throws InterruptedException {
        driver.get(Utils.BASE_URL + "wp-login.php");
        WebForm webForm = new WebForm(driver);
        webForm.signIn();

        webForm.openMyPostsList();

        if (webForm.getPostsCounterValue() == 0) {
            webForm.addNewNode();
            Thread.sleep(5000);

            webForm.clickOpenAdminPanelButtonFromCreatedPost();
            webForm.openMyPostsList();
        } else {

            webForm.pressRemoveButton();
            AssertJUnit.assertNotNull(webForm.getDeletedNotification());
        }
        Thread.sleep(1000);
    }

    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}