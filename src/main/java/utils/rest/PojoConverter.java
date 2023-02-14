package utils.rest;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class PojoConverter {
    public static <T> T fromJson(Response response, Class<T> classOfT)
    {
        String json= response.getBody().asString();
        return new Gson().fromJson(json, classOfT);
    }

    public static <T> String toJson(T classOfT)
    {
        return new Gson().toJson(classOfT);
    }

}
