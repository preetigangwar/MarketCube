package Pages;

import Utilities.ConfigLoader;
import Utilities.RandomGenerator;
import com.typesafe.config.Config;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Scroll;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Utilities.LoadProperties.getValueFromPropertyFile;
import static Utilities.LoadProperties.storeInGivenFile;
import static Utilities.LoadProperties.storeInSaveTestDataFile;
import static Utilities.RandomGenerator.randomInteger;


public class DashboardPage extends PageObject {
    String product_name,descriptiontext="";

    String product_price,compare_at_price,sku_data,product_barcode="";

    @FindBy(xpath = "//h1[text()=\"Dashboard\"]")
    public WebElementFacade verifydashboard;
    @FindBy(xpath = "//button[@class=\"Polaris-Header-Action\"]")
    public WebElementFacade menu_button;
    @FindBy(css ="div>button[class='Polaris-Header-Action']")
    public WebElementFacade menu1_button;
    @FindBy(xpath = "//div[text()='Products']")
    public WebElementFacade products;
    @FindBy(xpath = "//div[text()='Products']/..")
    public WebElementFacade products1;
    @FindBy(xpath = "(//span[text()=\"Add Product\"])[2]")
    public WebElementFacade add_product_button;
    @FindBy(css = "select[id=\"chooseTypeSelect\"]")
    public WebElementFacade add_product_dropdown;
    @FindBy(css = "input[placeholder='Product title']")
    public WebElementFacade add_product_title_textbox;
    @FindBy(id = "tinymce")
    public WebElementFacade description;
    @FindBy(xpath = "//span[text()='Add image']")
    public WebElementFacade add_image;
    @FindBy(name = "singleLineTextprice")
    public WebElementFacade price;
    @FindBy(id = "comparePrice")
    public WebElementFacade compare_price;
    @FindBy(name = "singleLineTextsku")
    public WebElementFacade sku;
    @FindBy(xpath="//input[@name=\"singleLineTextbarcode\"]")
    public WebElementFacade barcode;
    @FindBy(id = "dropdownvendorId")
    public WebElementFacade select_vendor;
    @FindBy(xpath="//span[text()=\"Submit\"]")
    public WebElementFacade submit_button;
    @FindBy(xpath = "//h2/button")
    private List<WebElement> productNames;
    @FindBy(xpath="//p[text()='Some of the images were ignored:']")
    private WebElementFacade corrupt_image_failure_message;
    @FindBy(xpath="//p[text()='Seller']")
    public WebElementFacade profile_button;
    @FindBy(xpath="//div[text()=\"Log Out\"]")
    public WebElementFacade logout_button;
    @FindBy(xpath="//span[text()=\"Push to shopify\"]")
    public WebElementFacade pushtoshopify_button;
    @FindBy(xpath="//p[text()='Your request is running in background.']")
    public WebElementFacade verify_approved_product;
    @FindBy(xpath="//div[@class='Polaris-Banner__Heading']//p")
    public WebElementFacade success_message;
    @FindBy(xpath="//li[text()=\"File type must be .gif, .jpg, .jpeg or .png.\"]")
    public WebElementFacade approve_verification ;

    public DashboardPage() throws IOException {
    }


    public static Config conf = ConfigLoader.load();

    public static void setEnvironMent(String environment) {
        System.setProperty("env", environment);
        conf = ConfigLoader.load();
    }

    public void load_url() {
        setEnvironMent("test");
        getDriver().get(conf.getString("base_url"));
    }

    public void verifyandstoreTextInPropeties() throws ConfigurationException, InterruptedException {
        String Dashboard_verification_Text = withTimeoutOf(30, TimeUnit.SECONDS).waitFor(verifydashboard).getText();
        storeInSaveTestDataFile("Dashboard_verification", Dashboard_verification_Text);
        waitABit(1000);
    }

    public void click_on_product() {
        Actions act = new Actions(getDriver());
        waitFor(menu_button).withTimeoutOf(50,TimeUnit.SECONDS);
        act.moveToElement(menu_button).click().perform();
        waitFor(products).withTimeoutOf(30,TimeUnit.SECONDS);
        act.moveToElement(products).click().perform();
        waitABit(5000);
    }

    public void verify_products_page() {
        String products_page_url = getDriver().getCurrentUrl();
        System.out.println(products_page_url);
    }

    public void addproductbutton() throws InterruptedException {
        waitFor(add_product_button).withTimeoutOf(4,TimeUnit.SECONDS).waitUntilEnabled();
        add_product_button.click();

    }

    public void select_product_dropdown() throws InterruptedException {
        String add_products_page_url = getDriver().getCurrentUrl();
        System.out.println(add_products_page_url);
        waitFor(add_product_dropdown).waitUntilEnabled();
        add_product_dropdown.click();
        Select sel = new Select(add_product_dropdown);
        sel.selectByValue("manual");

    }


    public void fill_form() throws InterruptedException, ConfigurationException {
        product_name="Automation Product"+randomInteger(5);
        storeInGivenFile("productname",product_name, "testData1");
        add_product_title_textbox.sendKeys(product_name);
        getDriver().switchTo().frame("message_ifr");
        withTimeoutOf(2, TimeUnit.SECONDS).waitFor(description).waitUntilClickable();
        descriptiontext="product_description"+randomInteger(5);
        storeInGivenFile("productdescription",descriptiontext, "testData1");
        description.sendKeys(descriptiontext);
        getDriver().switchTo().defaultContent();
    }


    public void chooseFileToUpload(String image) throws InterruptedException, IOException {
        String filePath= new File(".").getCanonicalPath()+"\\src\\test\\resources\\testData\\"+image+".png";
        getDriver().findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);

    }

    public void select_vendor() throws InterruptedException {
        waitFor(select_vendor).withTimeoutOf(3,TimeUnit.SECONDS).waitUntilEnabled();
        select_vendor.click();
        Select sel = new Select(select_vendor);
        sel.selectByVisibleText("vendor_test");
    }

    public void click_on_submit() throws InterruptedException, ConfigurationException {
        price.click();
        product_price= RandomGenerator.randomInteger(8);
        storeInGivenFile("productPrice",product_price, "testData1");
        price.sendKeys(product_price);

        compare_at_price= RandomGenerator.randomInteger(8);
        storeInGivenFile("compareprice",compare_at_price, "testData1");
        compare_price.sendKeys(compare_at_price);

        sku_data= RandomGenerator.randomInteger(8);
        storeInGivenFile("skudata",sku_data, "testData1");
        sku.sendKeys(sku_data);

        product_barcode= RandomGenerator.randomInteger(8);
        storeInGivenFile("barcode_data",product_barcode, "testData1");
        barcode.sendKeys(product_barcode);
        submit_button.click();
    }
        public void verifySuccessMessage() throws InterruptedException {
            withTimeoutOf(60,TimeUnit.SECONDS).waitFor(success_message).waitUntilVisible();
           Assert.assertTrue(success_message.isVisible());
    }

    public void verify_product_added() throws InterruptedException {
        Actions act = new Actions(getDriver());
        menu1_button.click();
        products1.click();
        waitABit(5000);
        String firstProductName = productNames.get(0).getText();
        String expName = getValueFromPropertyFile("testData1", "productname");
        waitABit(300);
        Assert.assertEquals(firstProductName,expName);
        withTimeoutOf(40, TimeUnit.SECONDS).waitFor(profile_button).waitUntilClickable();
        pushtoshopify_button.click();
        profile_button.click();
        logout_button.click();

    }
    public void verifyfailureMessage() {
        waitFor(corrupt_image_failure_message).withTimeoutOf(50,TimeUnit.SECONDS);
        Boolean result=corrupt_image_failure_message.isDisplayed();
        System.out.println(result);
    }
    public void uploadallowedimage() throws InterruptedException, IOException {
        waitABit(5000);
        Scroll.to(add_image);
        chooseFileToUpload("product");
    }

    public void uploadcorruptedimage() throws InterruptedException, IOException {
        chooseFileToUpload("corrupted");
    }
public void verify_approved_products() throws InterruptedException {


    withTimeoutOf(60,TimeUnit.SECONDS).waitFor(approve_verification).waitUntilVisible();
    Assert.assertTrue(approve_verification.isVisible());
    }






}
