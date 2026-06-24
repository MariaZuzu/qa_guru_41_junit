package guru.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.SushiwokPage;


import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


@DisplayName("Класс с параметризованными веб-тестами")
public class SushiwokTest extends TestBase {
    SushiwokPage sushiwokPage = new SushiwokPage();

    @BeforeEach void setUp() {
        sushiwokPage.openPage();
    }

    @ParameterizedTest(name = "Пункт меню {0} должен отображаться в навигации")
    @DisplayName("Отображение пунктов меню в панели навигации вверху")
    @CsvSource({
            "Акции",
            "Меню",
            "Наши адреса",
            "Доставка",
            "Франшиза",
            "Вакансии"
    })

    void displayMenuItemsTopNavbar(String expectedMenuItem) {
        $(".horizontalHeaderMenu-module__root--xQ4cu").shouldHave(text(expectedMenuItem));
    }

    @ParameterizedTest(name = "При выборе языка {0} отображается текст {1}")
    @DisplayName("Смена языка в навбаре")
    @EnumSource(Language.class)

    void changeLanguage(Language language) {
        $(".selectLanguage-module__lang--AiS3h").click();
        $$(".selectLanguage-module__lang--AiS3h option").findBy(value(language.langCode)).click();
        $(".horizontalHeaderMenu-module__root--xQ4cu").shouldHave(text(language.expectedMenuItemText));
    }

    record NavItem(String name, String expectedTitle) {}

    static Stream<NavItem> navItemsProvider() {
        return Stream.of(
                new NavItem("Акции", "Акции"),
                new NavItem("Меню", "Меню"),
                new NavItem("Наши адреса", "НАЙТИ БЛИЖАЙШИЙ СУШИ WOK"),
                new NavItem("Доставка", "Доставка"),
                // "Франшиза" пропускаем, т.к. она не открывает страницу с аналогичным заголовком
                new NavItem("Вакансии", "Вакансии")
        );
    }

    @ParameterizedTest(name = "Клик по {0} -> заголовок страницы {1}")
    @DisplayName("Переход по пунктам навигации (кроме Франшизы)")
    @MethodSource("navItemsProvider")
    void navigationRedirectsToCorrectPage(NavItem navItem) {
        $(".horizontalHeaderMenu-module__root--xQ4cu").find(byText(navItem.name)).click();
        $("h1").shouldHave(text(navItem.expectedTitle));
    }

}
