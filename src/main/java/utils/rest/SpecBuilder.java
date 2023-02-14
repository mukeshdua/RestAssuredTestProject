package utils.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static utils.rest.Route.BASE_PATH;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                //setBaseUri(System.getProperty("BASE_URI")).
                setBaseUri("https://reqres.in/").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
