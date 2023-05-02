/**
 * 
 */
package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Santosh Sharma
 *
 */
public class UserEndPoints {

	// returns url's
	static ResourceBundle getURL() {

		ResourceBundle route = ResourceBundle.getBundle("routes");
		return route;
	}

	// Post request
	public static Response addUser(User payload) {

		String postRequestUrl = getURL().getString("user.posturl");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(postRequestUrl);

		return response;
	}

	// get request
	public static Response getUser(String userName) {

		String getRequestUrl = getURL().getString("user.geturl");

		Response response = given().pathParam("username", userName)

				.when().get(getRequestUrl);

		return response;
	}

	// put request
	public static Response updateUser(String userName, User payload) {

		String putRequestUrl = getURL().getString("user.puturl");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload)

				.when().put(putRequestUrl);

		return response;
	}

	// delete request
	public static Response deleteUser(String userName) {

		String deleteRequestUrl = getURL().getString("user.deleteurl");

		Response response = given().pathParam("username", userName)

				.when().delete(deleteRequestUrl);

		return response;
	}

}
