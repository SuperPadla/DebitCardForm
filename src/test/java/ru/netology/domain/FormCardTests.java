package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class FormCardTests {

    @BeforeEach
    void open() {
        Selenide.open("http://localhost:9999");
    }

    @Test
    void successForm() {
        $("[data-test-id = name] input").setValue("Биба Бобов");
        $("[data-test-id = phone] input").setValue("+79336657088");
        $("[data-test-id = agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
