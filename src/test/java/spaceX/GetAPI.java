package spaceX;

import com.Result;
import com.Root;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAPI {

    @Test
    public void getRequest() throws IOException {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://swapi.dev/api/people/")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        //System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        Root root=objectMapper.readValue(response.getBody().print(), Root.class);
        List<Result> list=root.getResults();
        boolean flag=false;
        for (Result r:list
             ) {
            if(r.getName().equals("R2-D2"))
            {
                flag=true;
                Assert.assertEquals(r.getSkinColor(),"white, blue");
            }
        }
        Assert.assertTrue(flag);



    }
}
