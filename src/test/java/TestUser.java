import annotation.Team;
import extensions.TestClassExtension;
import io.restassured.response.Response;
import models.userrequest.CreateUser;
import models.userresponse.User;
import models.userresponse.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.rest.PojoConverter;
import utils.rest.RestUtil;

import java.util.Optional;
import java.util.stream.Stream;

@Team("User")
@ExtendWith({TestClassExtension.class})
public class TestUser {

    @Test
    public void testGetUsers()
    {
        Response response = RestUtil.get("/users", Optional.empty());
        Assertions.assertEquals(response.getStatusCode(),200);
        Users users = PojoConverter.fromJson(response, Users.class);
        System.out.println(users.data.size());
        Assertions.assertTrue(users.data.size()>0);
    }

    //Test-NG@Test(dataProvider = "user-test-data")
    @ParameterizedTest
    @MethodSource("userTestData")
    public void testGetUser(String id)
    {
        int tempId=Integer.valueOf(id);
        Response response = RestUtil.get("/users/"+tempId, Optional.empty());
        Assertions.assertEquals(response.getStatusCode(),200);
        User user = PojoConverter.fromJson(response, User.class);
        System.out.println(user.data.id);
        Assertions.assertTrue(user.data.id==tempId);
    }

    @Test
    public void testAddUser()
    {
        CreateUser createUser= new CreateUser();
        createUser.name="Mukesh";
        createUser.job="Software Engineer";
        String json = PojoConverter.toJson(createUser);

        Response response = RestUtil.post(json,"/users/", Optional.empty());
        Assertions.assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void testUpdateUser()
    {
        CreateUser createUser= new CreateUser();
        createUser.name="Mukesh";
        createUser.job="Software Engineer";
        String json = PojoConverter.toJson(createUser);

        Response response = RestUtil.put(json,"/users/", Optional.empty());
        Assertions.assertEquals(response.getStatusCode(),200);
    }

    // Test-NG
//    @DataProvider(name = "user-test-data")
//    public Object[][] dataProvFunc(){
//        return new Object[][]{
//                {"2"},{"3"},{"4"}
//        };
//    }

    private static Stream<Arguments> userTestData() {
        return Stream.of(
                Arguments.of("1"),
                Arguments.of("2"),
                Arguments.of("3")
        );
    }
}
