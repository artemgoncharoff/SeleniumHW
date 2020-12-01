import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebForm extends PageObject {

    public static final String LOGIN = "Ivan";
    public static final String PASSWORD = "12345";

    public WebForm(WebDriver driver) {
        super(driver);
    }

    private final String SIMPLE_TITLE = "SimpleTitle123";

    @FindBy(xpath = "//*[@id=\"user_login\"]")
    private WebElement loginField;
    @FindBy(xpath = "//*[@id=\"user_pass\"]")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id=\"wp-submit\"]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"post-title-0\"]")
    private WebElement title_name;

    @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div/div/div[2]/div[2]/button")
    private WebElement teaching_closing_button_1;

    @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div/div/div[2]/div[2]/button[2]")
    private WebElement teaching_closing_button_2;

    @FindBy(xpath = "//*[@id=\"wp-admin-bar-new-content\"]/a/span[2]")
    private WebElement add_button;

    //кнопка опубликовать
    @FindBy(xpath = "//*[@id=\"editor\"]/div/div/div[1]/div/div[1]/div/div[2]/button[2]")
    private WebElement push_button_1;

    // вылезающая поверх первой кнопки опубликовать новая кнопка опубликовать
    @FindBy(xpath = "//*[@id=\"editor\"]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[1]/div[1]/button")
    private WebElement push_button_2;

    @FindBy(xpath = "//*/td[1]/div[3]/span[3]/a")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[@id=\"message\"]/p")
    private WebElement deletedNotification;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/table/tbody/tr[1]")
    private WebElement firstPost;

    @FindBy(xpath = "//*[@id=\"menu-posts\"]/a/div[2]")
    private WebElement allPostsButton;

    @FindBy(xpath = "//*[@id=\"wpbody-content\"]/div[3]/ul/li[2]/a")
    private WebElement myPostsButton;

    @FindBy(xpath = "//*[@id=\"wpbody-content\"]/div[3]/ul/li[2]/a/span")
    private WebElement myPostsCounter;

    @FindBy(xpath = "//*[@id=\"editor\"]/div/div/div[1]/div/div[1]/div/a")
    private WebElement openAdminPanelButtonFromCreatedPost;

    @FindBy(xpath = "//*[@id=\"editor\"]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div[3]/h2")
    private WebElement changeTime;

    @FindBy(xpath = "//*[@id=\"editor\"]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[1]/div[1]/button")
    private WebElement plan;

    @FindBy(xpath = "//*[@id=\"editor\"]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div[3]/div/div[1]/fieldset[2]/div/div/input[2]")
    private WebElement minutes;

    @FindBy(xpath = "//*[@id=\"editor\"]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div[1]/text()[1]")
    private WebElement planed;

    public void openMyPostsList() {
        allPostsButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        myPostsButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void clickOpenAdminPanelButtonFromCreatedPost() {
        openAdminPanelButtonFromCreatedPost.click();
    }

    public void signIn() {
        this.loginField.sendKeys(LOGIN);
        this.passwordField.sendKeys(PASSWORD);
        this.signInButton.click();
    }

    public void addNewNode() {
        this.add_button.click();
        this.teaching_closing_button_1.click();

        for (int i =0; i <= 2; i++)
            this.teaching_closing_button_2.click();

        driver.get(Utils.BASE_URL + "wp-admin/post-new.php");

        this.title_name.sendKeys(SIMPLE_TITLE);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.push_button_1.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.push_button_2.click();
    }

    public void addNewNodeWithDelay() {
        this.add_button.click();
        this.teaching_closing_button_1.click();

        for (int i = 0; i <= 2; i++)
            this.teaching_closing_button_2.click();

        driver.get(Utils.BASE_URL + "wp-admin/post-new.php");

        this.title_name.sendKeys(SIMPLE_TITLE);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.push_button_1.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.changeTime.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.minutes.sendKeys(String.valueOf(Integer.parseInt(this.minutes.getAttribute("value")) + 1));

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.plan.click();
    }

    public Integer getPostsCounterValue() {
        return Integer.valueOf(myPostsCounter.getText().substring(1, myPostsCounter.getText().length() - 1));
    }

    public void pressRemoveButton() {
        this.firstPost.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.deleteButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public WebElement getDeletedNotification() {
        return deletedNotification;
    }
}