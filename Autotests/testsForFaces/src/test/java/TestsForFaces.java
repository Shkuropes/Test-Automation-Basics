import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.SelectOptionByValue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TestsForFaces {

    @BeforeClass
    public static void before(){
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void test1(){  //Input of standard correct data
        open("http://localhost:7000");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $(By.className("btn-primary")).click();
        $(By.id("firstName")).sendKeys("Daria");
        $(By.id("lastName")).sendKeys("Kolpakova");
        //$(By.id("titleForNewEmployee")).click();
        $(By.id("titleForNewEmployee")).selectOptionByValue("63b097be-346b-461e-81bf-dbd88246a88f");
        $(By.className("mt-3")).click();
        $("body > div > div > main > div.row > div.col-md-9.order-md-1 > div > div:nth-child(6)").should(exist); //проверка

    }

    @Test
    public void test2(){  //Input of numbers in the field Имя
        open("http://localhost:7000");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $(By.className("btn-primary")).click();
        $(By.id("firstName")).sendKeys("123");
        $(By.id("lastName")).sendKeys("Kolpakova");
        $(By.id("titleForNewEmployee")).selectOptionByValue("63b097be-346b-461e-81bf-dbd88246a88f");
        $(By.className("mt-3")).click();
        $("body").shouldHave(exactText("В поле Имя введены недопустимые символы"));
    }

    @Test
    public void test3(){  //Input of spaces in the field Имя
        open("http://localhost:7000");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $(By.className("btn-primary")).click();
        $(By.id("firstName")).sendKeys("   ");
        $(By.id("lastName")).sendKeys("Kolpakova");
        $(By.id("titleForNewEmployee")).selectOptionByValue("63b097be-346b-461e-81bf-dbd88246a88f");
        $(By.className("mt-3")).click();
        $("body").shouldHave(exactText("Данные в поле Имя не соответствуют правилам валидации"));
    }

    @Test
    public void test4(){  //Input of special characters in the field Фамилия
        open("http://localhost:7000");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $(By.className("btn-primary")).click();
        $(By.id("firstName")).sendKeys("Daria");
        $(By.id("lastName")).sendKeys("@#$%");
        $(By.id("titleForNewEmployee")).selectOptionByValue("63b097be-346b-461e-81bf-dbd88246a88f");
        $(By.className("mt-3")).click();
        $("body").shouldHave(exactText("В поле Фамилия введены недопустимые символы"));
    }

    @Test
    public void test5(){  //Leave the field Имя blank
        open("http://localhost:7000");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $(By.className("btn-primary")).click();
        $(By.id("lastName")).sendKeys("Kolpakova");
        $(By.id("titleForNewEmployee")).selectOptionByValue("63b097be-346b-461e-81bf-dbd88246a88f");
        $(By.className("mt-3")).click();
        $("body").shouldHave(exactText("Поле Имя не заполнено"));
    }
}
