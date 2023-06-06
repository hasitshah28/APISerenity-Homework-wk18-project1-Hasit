package com.bestbuy.bestbuyinfoCRUD;


import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase
{
    static String name = "Duracell - AAA Batteries" + TestUtils.getRandomValue();
    static String type = "HardGood1" + TestUtils.getRandomValue();
    static int price = 599;
    static int shipping = 0;
    static String upc = "041333424012";
    static String description = "Compatible with select electronic devices";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4A";
    static String url = "This is url for Duracell battery Pro";
    static String image = "This is image for Duracell pack";
    static int productId;


    @Steps
    ProductSteps productSteps;


    @Title("This will create a new product")
    @Test
    public void test001() {

        ValidatableResponse response = productSteps.createProduct(name,type,price,shipping,upc,
                description,manufacturer,model,url,image).log().all().statusCode(201);
        productId = response.log().all().extract().path("id");
        System.out.println(productId);

    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = productSteps.getProductsInfoByid(productId);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";
        productSteps.updateProduct(productId, name, type, price, shipping, upc,description,
                manufacturer,model,url,image).log().all().statusCode(200);

        HashMap<String, Object> productMap = productSteps.getProductsInfoByid(productId);
        Assert.assertThat(productMap, hasValue(name));

    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId).statusCode(200);
        productSteps.getProductById1(productId).statusCode(404);
    }
}