package spaceX;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class SpaceTest extends BaseTest {
    Space space=new Space();

    @Test(description = "Search the Product")
    public void searchSpace()
    {

        String desc="Fabric Content:\n" +
                "• 47.5% Cotton\n" +
                "• 47.5% Modal\n" +
                "• 5% Polyester";
        space.searchItemsAndPrint("Space");
        space.clickOnItem();
        space.verifyProductDetails("$40.00",desc);
        space.addQuantityAndVerifyPrice(2,40);
    }


}
