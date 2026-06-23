package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SushiwokPage {

    private final SelenideElement consentPopup = $(".cookie-consent, .cookies, [class*='cookie']");
    private final SelenideElement acceptButton = consentPopup.$("button, .button, [role='button']").$(byText("Принимаю"));

    public SushiwokPage openPage() {
        open("/spb/");

        return this;
    }

    public boolean isConsentPopupVisible() {
        return consentPopup.isDisplayed();
    }

    public void acceptCookies() {
        if (isConsentPopupVisible()) {
            acceptButton.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
            consentPopup.shouldNotBe(Condition.visible, Duration.ofSeconds(5));
        }
    }
}