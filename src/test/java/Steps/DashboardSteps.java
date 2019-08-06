package Steps;


import Pages.DashboardPage;
import Utilities.ConfigLoader;
import com.typesafe.config.Config;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.configuration.ConfigurationException;

public class DashboardSteps {
    DashboardPage dashboardPage;
    Config conf = ConfigLoader.load();

    @Given("^I am on dashboard page$")
    public void i_Am_On_Dashboard_Page()throws InterruptedException, ConfigurationException,InterruptedException {
        dashboardPage.verifyandstoreTextInPropeties();
        dashboardPage.click_on_product();


    }
    @When("^I go to add product page and fill all fields and click on submit$")
    public void i_Go_To_Add_Product_Page_And_Fill_All_Fields_And_Click_On_Submit()throws InterruptedException, ConfigurationException,InterruptedException {
        dashboardPage.verify_products_page();
        dashboardPage.click_on_product();
        dashboardPage.addproductbutton();
        dashboardPage.select_product_dropdown();
        dashboardPage.fill_form();
        dashboardPage.chooseFileToUpload();
        dashboardPage.select_vendor();
        dashboardPage.click_on_submit();
        dashboardPage.verifySuccessMessage();
    }

    @Then("^product must be displayed in  product list page$")
    public void product_Must_Be_Displayed_In_Product_List_Page()throws InterruptedException,ConfigurationException{
        dashboardPage.verify_product_added();

    }


    @When("^I go to add product page and add a corrupt image in add product page and click on submit$")
    public void  I_go_to_add_product_page_and_add_a_corrupt_image_in_add_product_page_and_click_on_submit()throws InterruptedException, ConfigurationException,InterruptedException {
        dashboardPage.click_on_product();
        dashboardPage.addproductbutton();
        dashboardPage.select_product_dropdown();
        dashboardPage.fill_form();
        dashboardPage.Upload_corrupted_file();
    }
    @Then("^Verification message should be displayed$")
    public void Verification_message_should_be_displayed()throws InterruptedException, ConfigurationException,InterruptedException {
        dashboardPage.verifyfailureMessage();


    }

}
