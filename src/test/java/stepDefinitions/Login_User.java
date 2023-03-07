package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Login_User {
    private WebDriver driver;
    @Before public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }
public void clickOutside() {
    Actions action =new Actions(driver);
    action.moveByOffset(0,0).click().build().perform();
}

    @After
    public void tearDown() {
        driver.quit();
    }
    public String generateRandomNumber (int length) {
        return RandomStringUtils.randomNumeric(length);
    }
    public String generateRandomString (int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
    @Given("I access the Automation Exercise website homepage")
    public void ı_access_the_automation_exercise_website_homepage() {
        driver.get("https://www.automationexercise.com/");
    }
    @When("I should be presented verified home page")
    public void ı_should_be_presented_verified_home_page() {
        WebElement registerUser_submission_message = driver.findElement(By.xpath("//div[@class=\"left-sidebar\"]/h2[text()='Category']"));
        Assert.assertEquals(registerUser_submission_message.getText(), "CATEGORY");
    }
    @And("I click on SingupLogin button")
    public void ı_click_on_singup_login_button() {
        driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
    }
    @And("I enter the correct mail address")
    public void ı_enter_the_correct_mail_address() {
        driver.findElement(By.xpath("//input[@data-qa=\"login-email\"]")).sendKeys("time@mail.com");
    }
    @And("I enter the correct password")
    public void ı_enter_the_correct_password() {
        driver.findElement(By.xpath("//input[@data-qa=\"login-password\"]")).sendKeys("time");
    }
    @And("I click to login button")
    public void ı_click_to_login_button() {
        driver.findElement(By.xpath("//button[@data-qa=\"login-button\"]")).click();
    }
    @And("I verified that logged in as username")
    public void ı_verified_that_logged_in_as_username() throws InterruptedException {
        WebElement registerUser_submission_message = driver.findElement(By.xpath("//ul[@class=\"nav navbar-nav\"]/li[10]/a[text()=' Logged in as ']"));
        Assert.assertEquals(registerUser_submission_message.getText(), "Logged in as time");
        Thread.sleep(5000);
    }
    @And("I click on Delete Acoount button")
    public void ı_click_on_delete_acoount_button() throws InterruptedException {

        driver.findElement(By.xpath("//i[@class=\"fa fa-trash-o\"]")).click();
        Thread.sleep(2000);
        /*driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(5000);*/
    }
    @And("I click the GoogleAds")
    public void ı_click_the_googleads() throws InterruptedException {
        clickOutside();
        Thread.sleep(2000);
    }
    @And("I enter the uncorrect mail address")
    public void ı_enter_the_uncorrect_mail_address() {
        driver.findElement(By.xpath("//input[@data-qa=\"login-email\"]")).sendKeys("AutoMail"+ generateRandomNumber(2)+ "@mail.com");

    }
    @And("I enter the uncorrect password")
    public void ı_enter_the_uncorrect_password() {
        driver.findElement(By.xpath("//input[@data-qa=\"login-password\"]")).sendKeys("AutoPass"+generateRandomNumber(3));
    }
    @Then("I verified error incorrect message")
    public void ı_verified_error_incorrect_message() throws InterruptedException {
        WebElement registerUser_submission_message = driver.findElement(By.xpath("//form[@method=\"POST\"]/p[text()='Your email or password is incorrect!']"));
        Assert.assertEquals(registerUser_submission_message.getText(),"Your email or password is incorrect!");
        Thread.sleep(3000);
    }


    @Then("I verified that Account Deleted")
    public void ı_verified_that_account_deleted() throws InterruptedException {

        WebElement registerUser_submission_message = driver.findElement(By.xpath("//h2[@data-qa=\"account-deleted\"]/b"));
        Assert.assertEquals(registerUser_submission_message.getText(),"ACCOUNT DELETED!");
        Thread.sleep(3000);
    }



}
