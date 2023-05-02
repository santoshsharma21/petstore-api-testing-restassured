/**
 * 
 */
package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Santosh Sharma
 *
 */
public class StoreEndPoints {

	// returns url's
	static ResourceBundle getStoreUrl() {
		ResourceBundle route = ResourceBundle.getBundle("routes");
		return route;
	}

	// post request
	public static Response petOrder(Store payload) {

		String postRequestUrl = getStoreUrl().getString("store.posturl");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(postRequestUrl);

		return response;
	}

	// get request
	public static Response getPetOrder(int orderId) {

		String getRequestUrlOrder = getStoreUrl().getString("store.geturl.order");

		Response response = given().pathParam("orderId", orderId)

				.when().get(getRequestUrlOrder);

		return response;
	}

	// get request
	public static Response getPetInventory() {

		String getRequestUrlInventory = getStoreUrl().getString("store.geturl.inventory");

		Response response = when().get(getRequestUrlInventory);

		return response;
	}

	// delete request
	public static Response deleteOrder(int orderId) {

		String deleteRequestUrl = getStoreUrl().getString("store.deleteurl");

		Response response = given().pathParam("orderId", orderId)

				.when().delete(deleteRequestUrl);

		return response;
	}

}
