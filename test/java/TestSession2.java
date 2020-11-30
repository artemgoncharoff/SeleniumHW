import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestSession2 extends PageObject {

    public TestSession2(WebDriver driver) {
        super(driver);
    }


    public static void test1() {
        // Нажать на кнопку Войти
        // <a href="https://ruswizard.site/test/wp-login.php">Войти</a>
        // Подождать
        // Ввести логин Mike в поле
        // id = user_login
        // Ввести пароль verysecretpass в поле
        // id = user_pass
        // Нажать на кнопку Войти
        // id = wp-submit
        // Подождать
        // Нажать кнопку добавить запись
        // <a href="post-new.php">Добавить новую</a>
        // отредактировать запись
            /* Header block
                <textarea id="post-title-0" class="editor-post-title__input"
                placeholder="Добавить заголовок" rows="1"
                style="overflow: hidden; overflow-wrap: break-word; resize: none; height: 136px;"
                spellcheck="false"></textarea>
             */
            /* Text block
                <span data-rich-text-placeholder="Начните писать или нажмите / для выбора блока"
                contenteditable="false"></span>
             */
        // сохранить черновик
        // <button type="button" class="components-button editor-post-save-draft is-tertiary">Сохранить черновик</button>
        // открыть экран записей
            /*<a href="edit.php?post_type=post"
             class="components-button edit-post-fullscreen-mode-close has-icon"
             aria-label="Просмотр записей"><svg width="36" height="36" xmlns="http://www.w3.org/2000/svg" viewBox="blah-blah-blah"></path></svg></a>
             */
        // проверить что сохраненная запись содержится в списке записей
            /*
            <a class="row-title" href="https://ruswizard.site/test/wp-admin/post.php?post=489&amp;action=edit" aria-label="«asd» (Изменить)">asd</a>
             */
        // открыть запись
            /*
            <a class="row-title" href="https://ruswizard.site/test/wp-admin/post.php?post=489&amp;action=edit" aria-label="«asd» (Изменить)">asd</a>
             */
        // сверить текст записи
            /* Header block
                <textarea id="post-title-0" class="editor-post-title__input"
                placeholder="Добавить заголовок" rows="1"
                style="overflow: hidden; overflow-wrap: break-word; resize: none; height: 136px;"
                spellcheck="false"></textarea>
             */
            /* Text block
                <span data-rich-text-placeholder="Начните писать или нажмите / для выбора блока"
                contenteditable="false"></span>
             */
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

    public void press_mainpage_login() {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", mainpage_login_button);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void enter_login() {
        login_textfield.sendKeys(credentials_login);

    }

    public void enter_password() {
        password_textfield.sendKeys(credentials_pass);
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
        post_body_textfield_parent.click();
        post_body_textfield.sendKeys(bodyText);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void save_post() {
        save_button.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void open_post_list() {
        driver.get("https://ruswizard.site/test/wp-admin/edit.php");
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
