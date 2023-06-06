package com.bestbuy.bestbuyinfoCRUD;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {

    @Step("Creating product with Id : {0}, type: {1}, price: {2}, shipping: {3}, price: {4},descriptionand: {5},manufacturer: {6}, model: {7},url: {8},image: {9}")
    public ValidatableResponse createProduct(String name, String type, double price,
                                             int shipping, String upc, String description, String manufacturer,
                                             String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post(EndPoints.CREATE_SINGLE_PRODUCTS)
                .then();

    }

    @Step("Getting the product information with productId: {0}")
    public HashMap<String, Object> getProductsInfoByid(int productId) {

        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
                .pathParam("productId",productId)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

        return productMap;
    }

    @Step("Updating product information with productId: {0},  name: {1}, type {2}, price {3}, shipping {4}, upc {5}, description {6}, manufacturer {7}, model {8}, url {9}, image {10}")
    public ValidatableResponse updateProduct(int productId,String name, String type, double price,
                                             int shipping,String upc, String description,String manufacturer,
                                             String model,String url,String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }
    @Step ("Getting the product information with name: {1}")
    public HashMap<String, Object> getProductInfoByname(String name) {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(1)";
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }
    @Step ("Deleting product information with productId: {0}")
    public ValidatableResponse deleteProduct(int productId){
        return SerenityRest
                .given().log().all()
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
    @Step ("Getting product information with productID: {0}")
    public ValidatableResponse getProductById1(int productId){
        return SerenityRest
                .given().log().all()
                .pathParam("productId", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

   }






