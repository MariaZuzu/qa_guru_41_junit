package guru.qa;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://sushiwok.ru";
        Configuration.timeout = 10000;
    }

    @AfterEach void afterEach() {
        closeWebDriver();
    }
}
