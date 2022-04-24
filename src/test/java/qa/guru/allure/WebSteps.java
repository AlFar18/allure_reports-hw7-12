package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step ("Открывание главной страницы")
    public void openMainPage(){
        open("https://github.com");
    }

    @Step ("Поиск репозитория {repo}")
    public void searchForRepository(String repo){
        $(".header-search-input").click();
        $(".header-search-input").setValue(repo);
        $(".header-search-input").submit();
    }

    @Step ("Переход по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo){
        $(linkText(repo)).click();
    }

    @Step ("Клик по табу Issues")
    public void openIssuesTab(){
        $(partialLinkText("Issues")).click();
    }

    @Step ("Проверка существования Issue с номером {numb}")
    public void shouldSeeIssuesWithNumber(int numb){
        $(withText("#" + numb)).should(Condition.visible);
        attachScreenshot();
    }

    @Attachment(value = "Cкриншот", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
