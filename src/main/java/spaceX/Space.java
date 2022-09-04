package spaceX;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.List;

public class Space extends BaseTest {

    public static By searchBtn=By.xpath("//button[text()='Search']");
    public static By searchTxt=By.id("search-input");
    public static By viewAllLnk=By.xpath("//a[text()='View all']");
    public static By productLnk=By.xpath("//a[.=concat('Men',\"'\",'s SpaceX Polo')]");
    public static By productTitles=By.xpath("//span[@class='ProductItem__Title Heading']/a[contains(@href,'sid')]");
    public static By productPrice=By.xpath("//span[@class='money pre-money']");
    public static By itemPrice=By.xpath("//span[@class='money cash-money pre-money']");
    public static By itemDescription=By.xpath("//div[@class='ProductMeta__Description']/div/p[2]");
    public static By results=By.xpath("//div[contains(text(),'results for')]");
    public static By quantity=By.name("quantity");
    public static By addToCart=By.xpath("//span[text()='Add to cart']");
    public static By totalPrice=By.xpath("//p[text()='Total: ']/..//span");
    public static By cartQuantity=By.xpath("//span[@data-action='increase-quantity']");
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public void searchItemsAndPrint(String searchText)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBtn));
        driver.findElement(searchBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTxt));
        driver.findElement(searchTxt).sendKeys(searchText);
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewAllLnk));
        driver.findElement(viewAllLnk).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(results));
        //Print All Product Name and Price
        List<WebElement> productTitlesEle=driver.findElements(productTitles);
        List<WebElement> productPriceEle=driver.findElements(productPrice);
        for(int i=0;i<productPriceEle.size();i++)
        {
            System.out.println(productTitlesEle.get(i).getText()+" "+productPriceEle.get(i).getText());
        }

    }

    public void clickOnItem()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLnk));
        driver.findElement(productLnk).click();
    }

    public void verifyProductDetails(String price,String details)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice));
        Assert.assertEquals(driver.findElement(itemPrice).getText(),price);
        Assert.assertEquals(driver.findElement(itemDescription).getText(),details);
    }

    public void addQuantityAndVerifyPrice(int q, double price)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantity));
        quantity(q);
        driver.findElement(addToCart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
        String TotalPrice=driver.findElement(totalPrice).getText();
        String actualPrice= df.format((q*price));
        Assert.assertEquals(TotalPrice,"$"+actualPrice,"");
    }

    public void quantity(int i)
    {
        for (int j=1;j<i;j++)
        {
            wait.until(ExpectedConditions.elementToBeClickable(cartQuantity));
            driver.findElement(cartQuantity).click();
        }
    }
}
