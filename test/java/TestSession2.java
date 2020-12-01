import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class TestSession2 extends PageObject {

    public TestSession2(WebDriver driver) {
        super(driver);
    }

    String credentials_login = "Mike";
    String credentials_pass = "verysecretpass";
    String headerText = "That is a header";
    String bodyText = "That is a body";

    @FindBy(xpath = "//a[contains(text(),'Войти')]")
    WebElement mainpage_login_button;
    @FindBy(id = "user_login")
    WebElement login_textfield;
    @FindBy(id = "user_pass")
    WebElement password_textfield;
    @FindBy(id = "wp-submit")
    WebElement login_button;
    @FindBy(xpath = "//span[contains(text(),'Добавить')]")
    WebElement add_post_button;
    @FindBy(id = "post-title-0")
    WebElement post_header_textfield;
    @FindBy(xpath = "//div[@class='block-editor-block-list__layout is-root-container']")
    WebElement post_body_textfield_parent;
    //@FindBy(xpath = "//p[@aria-label='Пустой блок; начните писать или нажмите прямой слэш (/) для выбора блока']")
    @FindBy(xpath = "//p[@role='textbox']")
    WebElement post_body_textfield;
    @FindBy(xpath = "//button[contains(text(), 'Сохранить черновик')]")
    WebElement save_button;
    @FindBy(xpath = "//a[@class='row-title']")
    WebElement post_in_list;
    @FindBy(xpath = "//button[contains(text(),'Переместить в корзину')]")
    WebElement delete_btn;

    public void enter_login() {
        login_textfield.sendKeys(credentials_login);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void enter_password() {
        password_textfield.sendKeys(credentials_pass);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void press_form_login() {
        login_button.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void press_add_post() {
        add_post_button.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void edit_post() {
        post_header_textfield.sendKeys(headerText);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        post_body_textfield_parent.click();
        post_body_textfield.sendKeys(bodyText);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void save_post() {
        save_button.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void open_post_list() {
        driver.get(Utils.BASE_URL + "wp-admin/edit.php");
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public boolean verify_post_in_list() {
        return post_in_list.getText().equals(headerText);
    }

    public void open_post() {
        post_in_list.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public boolean verify_post_content() {
        return post_header_textfield.getText().equals(headerText) && post_body_textfield.getText().equals(bodyText);
    }

    public void deletePost() {
        delete_btn.click();
    }
}
