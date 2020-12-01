import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebForm extends PageObject {

    public WebForm(WebDriver driver) {
        super(driver);
    }

    private final String SIMPLE_TITLE = "SimpleTitle123";
    public static final String LOGIN = "Ivan";
    public static final String PASSWORD = "12345";

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

        this.push_button_1.click();
        this.push_button_2.click();
    }
}