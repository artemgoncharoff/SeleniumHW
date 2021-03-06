import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestSession5 extends PageObject {
    public TestSession5(WebDriver driver) {
        super(driver);
    }

    /*
        Последовательность:
        1) Нажать на кнопку "Комментариев нет" в посте с названием SimpleTitle
        2) Подождать
        3) Ввести комментарий
        4) Нажать кнопку отправить комментарий
        5) Проверить наличие комментария
     */
    String author = "Unauthorized author";
    String email = "author123@author123.com";
    String url = "author123.com";
    String comment;

    //xpath = "//span[contains(text(),'Добавить')]"
    @FindBy(xpath = "//a[contains(text(), 'омментари')]")
    WebElement refToComment;
    @FindBy(id = "comment")
    WebElement commentTextArea;
    @FindBy(id = "author")
    WebElement authorInput;
    @FindBy(id = "email")
    WebElement emailInput;
    @FindBy(id = "url")
    WebElement urlInput;
    @FindBy(id = "submit")
    WebElement sendCommentButton;
    @FindBy(xpath = "//span[@class='fn']")
    WebElement lastAuthor;
    @FindBy(xpath = "//html/body/main/article/div[3]/div[1]/div[2]/div[last()]/article/div")
    WebElement lastNewComment;

    public void send_comment() {
        refToComment.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        comment = "Test comment " + formatter.format(date);
        commentTextArea.sendKeys(comment);
        authorInput.sendKeys(author);
        emailInput.sendKeys(email);
        urlInput.sendKeys(url);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        sendCommentButton.submit();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    public boolean verify_new_comment(){
        return lastNewComment.getText().contains(comment) && lastAuthor.getText().contains(author);
    }

}
