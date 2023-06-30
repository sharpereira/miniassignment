package Steps1;

import Utilities.TestContextSetup;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.cucumber.java.Before;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;


public class WeatherShopping {

    public int Temp;
    public WebDriver driver;

    @Given("I Launch Weather Shopper website")
    public void i_launch_weather_shopper_website() {
        driver = new ChromeDriver();
        driver.get("https://weathershopper.pythonanywhere.com/");
        driver.manage().window().maximize();

    }

    @When("I Identify the temperature and read info")
    public void i_identify_the_temperature_and_read_info() {
        String get_Temperature = driver.findElement(By.id("temperature")).getText();
        String Temperature = get_Temperature.replaceAll("[^\\d]","");
        int Temp=Integer.parseInt(Temperature);
    }

    @Then("Choose the moisturiser for low temperature and add products to cart")
    public void choose_the_moisturiser_for_low_temperature_and_add_products_to_cart() {
        if (Temp>=34)
        {
            String Info=driver.findElement(By.xpath("(//p[@class=\"text-justify\"])[1]")).getText();
            System.out.println(Info);
            driver.findElement(By.xpath("//button[text()=\"Buy sunscreens\"]")).click();
            List<String> SPF30=new ArrayList<>();
            List<String>SPF50=new ArrayList<>();

            List<WebElement> elements30= driver.findElements(By.xpath("//*[contains(text(),'-30')]/following-sibling::p "));
            List<WebElement> elements50=driver.findElements(By.xpath("//*[contains(text(),'-50')]/following-sibling::p "));

            // iterate and get text of webelements
            for(WebElement element:elements30)
            {
                SPF30.add(element.getText());
            }
            for(WebElement element:elements50)
            {
                SPF50.add(element.getText());
            }
            //create filter array and remove all not required charaters
            List<String>Filterarray30 =new ArrayList<>();
            List<String>Filterarray50 =new ArrayList<>();
            for( String str:SPF30)
            {
                String removedchars=str.replaceAll("[a-zA-Z:.\\s]","");
                Filterarray30.add(removedchars);


            }
            for( String str:SPF50)
            {
                String removedchars=str.replaceAll("[a-zA-Z:.\\s]","");
                Filterarray50.add(removedchars);
            }
            //convert string array into integer array
            List<Integer>integersList30=new ArrayList<>();
            for(String str:Filterarray30)
            {
                integersList30.add(Integer.parseInt(str));
            }
            Collections.sort(integersList30);
            //print sorted list
            List<Integer>integersList50=new ArrayList<>();
            for(String str:Filterarray50)
            {
                integersList50.add(Integer.parseInt(str));
            }
            Collections.sort(integersList50);
            //print sorted list

            // only consider top 2 least price
            if(integersList30.size()>2)
            {
                integersList30.subList(2,integersList30.size()).clear();
            }
            if(integersList50.size()>2)
            {
                integersList50.subList(2,integersList50.size()).clear();
            }
            //convert integer array into string array
            List<String> MoisturiserList30 =new ArrayList<>();
            for(Integer number: integersList30)
            {
                String stringvalue = String.valueOf(number);
                MoisturiserList30.add(stringvalue);
            }
            List<String> MoisturiserList50 =new ArrayList<>();
            for(Integer number: integersList50)
            {
                String stringvalue = String.valueOf(number);
                MoisturiserList50.add(stringvalue);
            }
            //iterate over both lists and elements
            for(int i=0;i<MoisturiserList30.size();i++)
            {
                String expectedText = MoisturiserList30.get(i);
                int k=0;
                for (int j=0;j<elements30.size();j++) {
                    WebElement element = elements30.get(j);
                    String actualText = element.getText();

                    if (actualText.contains(expectedText)) {
                        element.findElement(By.xpath("following-sibling::button")).click();
                    }

                }
            }
            for(int i=0;i<MoisturiserList50.size();i++)
            {
                String expectedText = MoisturiserList50.get(i);
                for (int j=0;j<elements50.size();j++) {
                    WebElement element = elements50.get(j);
                    String actualText = element.getText();

                    if (actualText.contains(expectedText)) {
                        element.findElement(By.xpath("following-sibling::button")).click();
                    }

                }
            }


        }


    }

    @Then("choose suncreen for high temperature and add products to cart")
    public void choose_suncreen_for_high_temperature_and_add_products_to_cart() {
        if(Temp<=19) {
            String Info = driver.findElement(By.xpath("(//p[@class=\"text-justify\"])[2]")).getText();
            System.out.println(Info);
            driver.findElement(By.xpath("//button[text()=\"Buy moisturizers\"]")).click();

            List<String> Aloe = new ArrayList<>();
            List<String> Almond = new ArrayList<>();

            List<WebElement> elements_aloe = driver.findElements(By.xpath("//*[contains(text(),'Aloe')]/following-sibling::p"));
            List<WebElement> elements_Almond = driver.findElements(By.xpath("//*[contains(text(),'Almond')]/following-sibling::p"));

            // iterate and get text of webelements
            for (WebElement element : elements_aloe) {
                Aloe.add(element.getText());
            }
            for (WebElement element : elements_Almond) {
                Almond.add(element.getText());
            }
            //create filter array and remove all not required charaters
            List<String> Filterarray_aloe = new ArrayList<>();
            List<String> Filterarray_almond = new ArrayList<>();
            for (String str : Aloe) {
                String removedchars = str.replaceAll("[a-zA-Z:.\\s]", "");
                Filterarray_aloe.add(removedchars);


            }
            for (String str : Almond) {
                String removedchars = str.replaceAll("[a-zA-Z:.\\s]", "");
                Filterarray_almond.add(removedchars);
            }


            //convert string array into integer array
            List<Integer> integersList_aloe = new ArrayList<>();
            for (String str : Filterarray_aloe) {
                integersList_aloe.add(Integer.parseInt(str));
            }
            Collections.sort(integersList_aloe);
            //print sorted list
            List<Integer> integersList_almond = new ArrayList<>();
            for (String str : Filterarray_almond) {
                integersList_almond.add(Integer.parseInt(str));
            }
            Collections.sort(integersList_almond);
            //print sorted list

            // only consider top 2 least price
            if (integersList_aloe.size() > 2) {
                integersList_aloe.subList(2, integersList_aloe.size()).clear();
            }
            if (integersList_almond.size() > 2) {
                integersList_almond.subList(2, integersList_almond.size()).clear();
            }
            //convert integer array into string array
            List<String> AloeList = new ArrayList<>();
            for (Integer number : integersList_aloe) {
                String stringvalue = String.valueOf(number);
                AloeList.add(stringvalue);
            }
            List<String> AlmondList = new ArrayList<>();
            for (Integer number : integersList_almond) {
                String stringvalue = String.valueOf(number);
                AlmondList.add(stringvalue);
            }
            //iterate over both lists and elements
            for (int i = 0; i < AloeList.size(); i++) {
                String expectedText = AloeList.get(i);
                for (int j = 0; j < elements_aloe.size(); j++) {
                    WebElement element = elements_aloe.get(j);
                    String actualText = element.getText();

                    if (actualText.contains(expectedText)) {
                        element.findElement(By.xpath("following-sibling::button")).click();
                    }

                }
            }
            for (int i = 0; i < AlmondList.size(); i++) {
                String expectedText = AlmondList.get(i);
                for (int j = 0; j < elements_Almond.size(); j++) {
                    WebElement element = elements_Almond.get(j);
                    String actualText = element.getText();

                    if (actualText.contains(expectedText)) {
                        element.findElement(By.xpath("following-sibling::button")).click();
                    }

                }
            }
        }

    }

    @Then("Verify the cart value and initiate a payment")
    public void verify_the_cart_value_and_initiate_a_payment() {
        //click on cart
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.xpath("//button[@class=\"stripe-button-el\"]")).click();

        //switch to frame
        driver.switchTo().frame(driver.findElement(By.className("stripe_checkout_app")));//1st i frame

        //enter card_details
        driver.findElement(By.xpath("//form[@class=\"checkoutView\"]//input[@id=\"email\"]")).sendKeys("asfr@gmail.com");
        String cardNumber="5555 5555 5555 4444";
        WebElement CardNumberInput = driver.findElement(By.id("card_number"));

        for(char digit :cardNumber.toCharArray())
        {
            CardNumberInput.sendKeys(Character.toString(digit));
            if (digit==' ')
            {
                CardNumberInput.sendKeys(Keys.SPACE);
            }
        }
        String date="12/30";
        WebElement dateinput=driver.findElement(By.id("cc-exp"));
        for(char c:date.toCharArray())
        {
            dateinput.sendKeys(Character.toString(c));
        }
        driver.findElement(By.id("cc-csc")).sendKeys("123");
        driver.findElement(By.id("billing-zip")).sendKeys("560078");

        //make_payment
        driver.findElement(By.id("submitButton")).click();
        driver.switchTo().defaultContent();

    }

    @Then("make payment and perform payment verification")
    public void make_payment_and_perform_payment_verification() {
        //payment verification
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://weathershopper.pythonanywhere.com/confirmation"));
        driver.switchTo().defaultContent();
        String Payment_message = driver.findElement(By.xpath("//h2")).getText();

        //Assertion
        Assert.assertEquals(Payment_message, "PAYMENT SUCCESS");
        if (Payment_message.equals("PAYMENT SUCCESS")) {
            System.out.println("Payment successful");

        } else {
            System.out.println("Payment Failure");
        }


    }






    @Then("Close the browser")
    public void close_the_browser() {
      driver.close();
    }
}

