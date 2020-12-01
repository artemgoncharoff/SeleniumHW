import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class WebForm extends PageObject {

    public static final String LOGIN = "Ivan";
    public static final String PASSWORD = "12345";

    public WebForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*/td[1]/div[3]/span[3]/a")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[@id=\"message\"]/p")
    private WebElement deletedNotification;

    @FindBy(xpath = "//*[@id=\"user_login\"]")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"user_pass\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"wp-submit\"]")
    private WebElement signInButton;

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

    public void openMyPostsList() {
        allPostsButton.click();

        myPostsButton.click();
    }

    public void clickOpenAdminPanelButtonFromCreatedPost() {
        openAdminPanelButtonFromCreatedPost.click();
    }

    public void signIn() {
        this.loginField.sendKeys(LOGIN);
        this.passwordField.sendKeys(PASSWORD);
        this.signInButton.click();
    }

    public Integer getPostsCounterValue() {
        return Integer.valueOf(myPostsCounter.getText().substring(1, myPostsCounter.getText().length() - 1));
    }

    public void pressRemoveButton() {
        this.firstPost.click();
        this.deleteButton.click();
    }

    public WebElement getDeletedNotification() {
        return deletedNotification;
    }
}