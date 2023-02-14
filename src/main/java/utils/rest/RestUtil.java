package utils.rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static utils.rest.SpecBuilder.getRequestSpec;
import static utils.rest.SpecBuilder.getResponseSpec;

public class RestUtil {

    public static Response get(String path, Optional<Map<String,String>> headers)
    {
        RequestSpecification requestSpecification =  given(getRequestSpec());
        if(headers.isPresent())
        {
            requestSpecification.headers(headers.get());
        }
        return requestSpecification
                .when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String json,String path, Optional<Map<String,String>> headers){
        RequestSpecification requestSpecification =  given(getRequestSpec());
        if(headers.isPresent())
        {
            requestSpecification.headers(headers.get());
        }
        return requestSpecification.body(json).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response put(String json,String path, Optional<Map<String,String>> headers) {
        RequestSpecification requestSpecification = given(getRequestSpec());
        if (headers.isPresent()) {
            requestSpecification.headers(headers.get());
        }
        return requestSpecification.body(json).
                when().put(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
}
