import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

        //add one post in case none were found
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


    @Test(testName = "Addition a new post")
    public static void simpleNewPost(){
        driver.get(Utils.BASE_URL + "wp-admin/about.php");
        WebForm webform = new WebForm(driver);
        webform.signIn();
        webform.addNewNode();
    }

    @Test(testName = "Add new comment")
    public static void test5() {
        driver.get(Utils.BASE_URL);
        TestSession5 session = new TestSession5(driver);
        session.send_comment();
        assertTrue(session.verify_new_comment());
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
