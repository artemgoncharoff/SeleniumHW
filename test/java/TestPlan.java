import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestPlan {
    private static WebDriver driver;

    @BeforeSuite
    public static void suiteInit() {
        System.err.println("suiteinit");
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @BeforeMethod
    public static void testInit() {
        System.err.println("testinit");
        driver = new ChromeDriver();
    }


    @Test(testName = "Add a new post")
    public static void addPost() throws InterruptedException {
        driver.get(Utils.BASE_URL + "wp-admin/about.php");
        WebForm webform = new WebForm(driver);
        webform.signIn();
        webform.addNewNode();
        Thread.sleep(5000);
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

    @Test(testName = "Save post draft in my posts list")
    public static void savePost() {
        driver.get(Utils.BASE_URL + "wp-login.php");
        TestSession2 session = new TestSession2(driver);
        session.enter_login();
        session.enter_password();
        session.press_form_login();
        session.press_add_post();
        session.edit_post();
        session.save_post();
        session.open_post_list();;
        assertTrue(session.verify_post_in_list());
        session.open_post();
        assertTrue(session.verify_post_content());
        session.deletePost();
    }

    @Test(testName = "Add new comment")
    public static void sendComment() throws InterruptedException {
        driver.get(Utils.BASE_URL);
        TestSession5 session = new TestSession5(driver);
        Thread.sleep(1000);
        session.send_comment();
        assertTrue(session.verify_new_comment());
    }

    @AfterMethod
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
        System.err.println("cleanup");

    }
}
