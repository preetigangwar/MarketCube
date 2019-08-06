package Pages;

import Utilities.ConfigLoader;
import com.typesafe.config.Config;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static Utilities.LoadProperties.getValueFromPropertyFile;


public class LoginPage extends PageObject {

    @FindBy(id ="email")
    public WebElementFacade email;

    @FindBy(id ="password")
    public WebElementFacade password;

    @FindBy(id ="submitButton")
    public WebElementFacade submit_button;


    public static Config conf= ConfigLoader.load();

    public static void setEnvironMent(String environment){
        System.setProperty("env",environment);
        conf=ConfigLoader.load();
    }

    public void iAmOnHomePage(){
        setEnvironMent("test");
        getDriver().get(conf.getString("base_url"));
    }


    public void login(String user) throws  InterruptedException {
        withTimeoutOf(20, TimeUnit.SECONDS).waitFor(email);
        email.sendKeys(getValueFromPropertyFile("testData1",user+"Email"));
        password.sendKeys(getValueFromPropertyFile("testData1",user+"Password"));
        submit_button.click();
    }

}
