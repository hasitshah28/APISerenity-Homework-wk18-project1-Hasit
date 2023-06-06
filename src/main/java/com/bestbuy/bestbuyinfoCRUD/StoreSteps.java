package com.bestbuy.bestbuyinfoCRUD;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {

   @Step("Creating store with Id: {0},name: {1}, type: {2}, address: {3}, address2: {4}, city: {5}, " +
           "state: {6}, zip: {7}, lat: {8}, lng: {9}, hours: {10},services: {11}")
   public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                          String state, String zip, double lat, double lng, String hours, HashMap<Object,Object> services) {
      StoresPojo storesPojo = new StoresPojo();
      storesPojo.setName(name);
      storesPojo.setType(type);
      storesPojo.setAddress(address);
      storesPojo.setAddress2(address2);
      storesPojo.setCity(city);
      storesPojo.setState(state);
      storesPojo.setZip(zip);
      storesPojo.setLat(lat);
      storesPojo.setLng(lng);
      storesPojo.setHours(hours);
      storesPojo.setServices(services);
      return SerenityRest.given().log().all()
              .contentType(ContentType.JSON)
              .body(storesPojo)
              .when()
              .post(EndPoints.CREATE_SINGLE_STORE)
              .then();

   }

   @Step("Getting the store information with ID: {0}")
   public HashMap<String, Object> getStoreinfoById(int storeId) {
      HashMap<String, Object> storeMap = SerenityRest.given().log().all()
              .when()
              .pathParam("storeId", storeId)
              .get(EndPoints.GET_SINGLE_STORE_BY_ID)
              .then()
              .statusCode(200)
              .extract().path("");

      return storeMap;
   }
   @Step("Updating store information with storeId: {0},name: {1}, type: {2}, address: {3}, address2: {4}, city: {5}, " +
           "state: {6}, zip: {7}, lat: {8}, lng: {9}, hours: {10},services: {11}")
   public ValidatableResponse updateStore(int storeId, String name, String type, String address, String address2, String city,
                                          String state, String zip, double lat, double lng, String hours, HashMap<Object,Object> services) {


      StoresPojo storesPojo = new StoresPojo();
      storesPojo.setName(name);
      storesPojo.setType(type);
      storesPojo.setAddress(address);
      storesPojo.setAddress2(address2);
      storesPojo.setCity(city);
      storesPojo.setState(state);
      storesPojo.setZip(zip);
      storesPojo.setLat(lat);
      storesPojo.setLng(lng);
      storesPojo.setHours(hours);
      storesPojo.setServices(services);

      return SerenityRest.given().log().all()
              .header("Content-Type", "application/json")
              .pathParam("storeId", storeId)
              .body(storesPojo)
              .when()
              .put(EndPoints.UPDATE_STORE_BY_ID)
              .then();
   }
   @Step ("Deleting store information with storeID: {0}")
   public ValidatableResponse deleteStore(int storeId){
      return SerenityRest
              .given()
              .pathParam("storeId", storeId)
              .when()
              .delete(EndPoints.DELETE_STORE_BY_ID)
              .then();
   }

   @Step("Getting store information with storeID: {0}")
   public ValidatableResponse getStoreById(int storeId){
      return SerenityRest
              .given()
              .pathParam("storeId", storeId)
              .when()
              .get(EndPoints.GET_SINGLE_STORE_BY_ID)
              .then();
   }
   }



