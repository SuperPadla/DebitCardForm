package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
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
    @Test
    void wrongName() {
        $("[data-test-id = name] input").setValue("Biba Bobov");
        $("[data-test-id = phone] input").setValue("+79336657088");
        $("[data-test-id = agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void wrongPhone() {
        $("[data-test-id = name] input").setValue("Биба Бобов");
        $("[data-test-id = phone] input").setValue("89336657088");
        $("[data-test-id = agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void emptyName() {
        $("[data-test-id = name] input").setValue("");
        $("[data-test-id = phone] input").setValue("+79336657088");
        $("[data-test-id = agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    //Необходимо также проверить форму с пустым полем ввода номера телефона, пропущенный тест прошу добавить.
    @Test
    void emptyPhone() {
        $("[data-test-id = name] input").setValue("Биба Бобов");
        $("[data-test-id = phone] input").setValue("");
        $("[data-test-id = agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldFirstErrorField() {
        $("[data-test-id = name] input").setValue("Biba Bobov");
        $("[data-test-id = phone] input").setValue("89336657088");
        $("[data-test-id = agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldFirstErrorField1() {
        $("[data-test-id = name] input").setValue("Биба Бобов");
        $("[data-test-id = phone] input").setValue("89336657088");
        $("button[type=button]").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void invalidCheckBox() {
        $("[data-test-id = name] input").setValue("Биба Бобов");
        $("[data-test-id = phone] input").setValue("+79336657088");
        $("button[type=button]").click();
        $("[data-test-id = agreement]").shouldHave(Condition.cssClass("input_invalid"));
    }

}
