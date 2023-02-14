import io.restassured.response.Response;
import models.userrequest.CreateUser;
import models.userresponse.User;
import models.userresponse.Users;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.rest.PojoConverter;
import utils.rest.RestUtil;

import java.util.Optional;

public class TestUser {

    @Test
    public void testGetUsers()
    {
        Response response = RestUtil.get("/users", Optional.empty());
        Assert.assertEquals(response.getStatusCode(),200);
        Users users = PojoConverter.fromJson(response, Users.class);
        System.out.println(users.data.size());
        Assert.assertTrue(users.data.size()>0);
    }

    @Test(dataProvider = "user-test-data")
    public void testGetUser(String id)
    {
        int tempId=Integer.valueOf(id);
        Response response = RestUtil.get("/users/"+tempId, Optional.empty());
        Assert.assertEquals(response.getStatusCode(),200);
        User user = PojoConverter.fromJson(response, User.class);
        System.out.println(user.data.id);
        Assert.assertTrue(user.data.id==tempId);
    }

    @Test
    public void testAddUser()
    {
        CreateUser createUser= new CreateUser();
        createUser.name="Mukesh";
        createUser.job="Software Engineer";
        String json = PojoConverter.toJson(createUser);

        Response response = RestUtil.post(json,"/users/", Optional.empty());
        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void testUpdateUser()
    {
        CreateUser createUser= new CreateUser();
        createUser.name="Mukesh";
        createUser.job="Software Engineer";
        String json = PojoConverter.toJson(createUser);

        Response response = RestUtil.put(json,"/users/", Optional.empty());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @DataProvider(name = "user-test-data")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"2"},{"3"},{"4"}
        };
    }
}
