import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

public class Rest_Patch_Reference {

	public static void main(String[] args) {
	//Declare the base URL
	RestAssured.baseURI="https://reqres.in/";
	    
	//Declare requestbody string variable
	String requestbody="{\r\n"
	    		+ "    \"name\": \"morpheus\",\r\n"
	    		+ "    \"job\": \"zion resident\"\r\n"
	    		+ "}";
	//Declare the expected result
	JsonPath JspRequest = new JsonPath(requestbody);
    String req_name = JspRequest.getString("name");
    String req_job = JspRequest.getString("job");
	LocalDateTime currentdate = LocalDateTime.now();
	String expecteddate=currentdate.toString().substring(0,11);
    //Declare given,when then method 
	//String responseBody=given().header("Content-Type","application/json").body(requestBody).log().all().
	//when().patch("api/users").then().log().all().extract().response().asString();
	String responsebody = given().header("Content-Type","application/json").body(requestbody).
	    when().patch("api/users/2").then().extract().response().asString();
	    
	//System.out.println(responseBody);
    //Create an object of JSONpath to parse the response body
	JsonPath Jspresponse = new JsonPath(responsebody);
	String res_name = Jspresponse.getString("name");
    String res_job = Jspresponse.getString("job");
	System.out.println(res_name);
	System.out.println(res_job);
		
	String res_updatedAt = Jspresponse.getString("updatedAt");
	res_updatedAt = res_updatedAt.substring(0,11);
	System.out.println(res_updatedAt);
		
	//Validate the response body parameter
	Assert.assertEquals(res_name, req_name);
	Assert.assertEquals(res_job, req_job);
	Assert.assertEquals(res_updatedAt , expecteddate); 
	}

}
