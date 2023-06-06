package com.bestbuy.bestbuyinfoCRUD;

import com.bestbuy.testbase.TestBase;
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
public class StoresCURDTest extends TestBase{

    static String name = "Hopkins";
    static String type = "BigBox";
    static String address = "10 dowmtown";
    static String address2 = "London Bridge";
    static String city = "London";
    static String state = "Surey";
    static String zip = "55305";
    static double lat = 44.958216;
    static double lng = -91.442336;
    static String hours= "Mon: 10-7; Tue: 10-7; Wed: 10-8; Thurs: 10-8; Fri: 10-9; Sat: 10-7; Sun: 10-5";
    static int storeId;


    @Steps
    StoreSteps storesSteps;

    @Title("This will create new service")
    @Test
    public void test001(){
        HashMap<Object,Object> services = new HashMap<>();
        services.put(1,"Krishna");
        services.put(2,"Rama");

        ValidatableResponse response = storesSteps.createStore(name, type, address, address2, city,
                state, zip, lat, lng,hours,services);
        response.log().all().statusCode(201);

        storeId = response.log().all().extract().path("id");
        System.out.println(storeId);

    }
    @Title("Verify if the strore was created in application")
    @Test
    public void test002(){
        HashMap<Object,Object> services = new HashMap<>();
        services.put(1,"Krishna");
        services.put(2,"Rama");
        HashMap<String, Object> value = storesSteps.getStoreinfoById(storeId);
        Assert.assertThat(value, hasValue(storeId));
        System.out.println(value);
    }
    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
        HashMap<Object, Object> services = new HashMap<>();
        services.put(1,"Krishna");
        services.put(2,"Rama");

        name = name + "_updated";
        storesSteps.updateStore(storeId,name, type, address, address2, city,state, zip, lat,
                lng,hours,services).log().all().statusCode(200);
        HashMap<String, Object> storeMap = storesSteps.getStoreinfoById(storeId);
        Assert.assertThat(storeMap, hasValue(name));

    }
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);
    }

}


