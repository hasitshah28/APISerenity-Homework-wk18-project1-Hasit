package com.bestbuy.constants;

/**
 * Created by Jay
 */
public class EndPoints {

    /**
     * This is Endpoints of products api
     */
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String CREATE_SINGLE_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "products/{productId}";
    public static final String UPDATE_PRODUCT_BY_ID = "products/{productId}";
    public static final String DELETE_PRODUCT_BY_ID = "products/{productId}";

    //This Endpoints is for STORES

    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeId}";
    public static final String CREATE_SINGLE_STORE = "/stores";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storeId}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeId}";
}
