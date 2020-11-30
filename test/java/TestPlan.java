import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void init() {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Доступность сохранённого фрагмента через список записей данного пользователя")
    public static void test2() {
        driver.get(Utils.BASE_URL);
        TestSession2 session = new TestSession2(driver);
        session.press_mainpage_login();
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

    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }


}