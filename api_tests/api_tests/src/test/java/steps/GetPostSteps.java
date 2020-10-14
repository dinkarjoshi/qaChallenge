package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.util.ArrayList;
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


    @And("all emails should be valid")
    public void allEmailsShouldBeValid() {
        ArrayList<String> list;
        Pattern validEmail =
                Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        String string1 = response.then().extract().path("email").toString();
        list = cleanResponse(string1);

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
        ArrayList<String> list;
        String string1 = response.then().extract().path("email").toString();

        String catchPhrases = response.then().extract().path("company.catchPhrase").toString();
        list = cleanResponse(catchPhrases);
        list.forEach(c -> Assert.assertTrue(c.length() < 50));
    }

    private ArrayList<String> cleanResponse(String string1) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        for (int x = 0; x < string1.length(); x++) {
            char c = string1.charAt(x);
            String compare = Character.toString(c);
            if (!(compare.equalsIgnoreCase("[") || compare.equalsIgnoreCase("]"))) {
                stringBuilder.append(string1.charAt(x));
            }
            if (compare.equalsIgnoreCase(",")) {
                String string = stringBuilder.toString().trim();
                string = string.replace(",", "").trim();
                list.add(string);
                stringBuilder.replace(0, stringBuilder.length(), "");
            }
        }
        return list;
    }
}