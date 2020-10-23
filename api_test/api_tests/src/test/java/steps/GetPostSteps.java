package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetPostSteps {

    Response response;

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) {
        given().contentType(ContentType.JSON);

    }

    @And("I GET for {string}")
    public void iGETFor(String users) {
        response = given().get("https://jsonplaceholder.typicode.com/users");

    }

    private List<String> getList(String key) {
        List<String> list = new ArrayList<>();
        try {
            var jsonString = response.getBody().asString();
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonString);

            jsonArray.forEach(a -> {
                list.add(((JSONObject) a).get(key).toString());
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @And("all emails should be valid")
    public void allEmailsShouldBeValid() {
        List<String> list;
        Pattern validEmail =
                Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        list = getList("email");

        for (String emails : list) {
            Matcher matcher = validEmail.matcher(emails);
            Assert.assertTrue(matcher.find() && matcher.group().equalsIgnoreCase(emails));
        }
    }

    @Then("all users should have a name")
    public void allUsersShouldHaveAName() throws ParseException {
        response.then().body("name", notNullValue());
    }

    @And("All users have emails")
    public void allUsersHaveEmails() {
        response.then().body("email", notNullValue());
    }

    @And("all users have username")
    public void allUsersHaveUsername() {
        response.then().body("username", notNullValue());
    }

    @And("Company Catchphrases must have less than {int} characters")
    public void companyCatchphrasesMustHaveLessThanCharacters(int arg) {
        List<String> list;
        list = getList("company");
        list.forEach(c -> {
            JSONParser parser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(c);
                Assert.assertTrue(jsonObject.get("catchPhrase").toString().length() < 50);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }

}
