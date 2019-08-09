package Steps;

import Pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
public class LoginSteps {

    LoginPage loginPage;
    @Given("^I am on MarketCube login page$")
    public void iAmOnMarketCubeLoginPage() {
        loginPage.iAmOnHomePage();
    }


    @And("^I login as \"([^\"]*)\"$")
    public void iLoginAs(String user) throws Throwable {
        loginPage.login(user);
    }
}
