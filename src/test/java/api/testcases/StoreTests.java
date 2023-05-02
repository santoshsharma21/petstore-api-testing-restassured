/**
 * 
 */
package api.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

/**
 * @author Santosh Sharma
 *
 */
public class StoreTests {

	public static Faker faker;
	public static Store storepayload;

	@BeforeClass
	void setStorePayload() throws IOException {

		ExtentReportManager.setUpReport("Store-API_Test-Report", "Store");
		faker = new Faker();
		storepayload = new Store();

		storepayload.setId(faker.number().randomDigitNotZero());
		storepayload.setPetId(faker.number().randomDigitNotZero());
		storepayload.setQuantity(faker.number().randomDigitNotZero());
		storepayload.setShipDate("2023-05-02T10:47:39.474Z");
		storepayload.setStatus("placed");
		storepayload.setComplete(true);
	}

	@AfterClass
	void afterSuite() {
		ExtentReportManager.closeReport();
	}

	@Test(priority = 0)
	void Test_OrderPet() {

		Response response = StoreEndPoints.petOrder(storepayload);
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 1)
	void Test_getPetOrder() {

		Response response = StoreEndPoints.getPetOrder(storepayload.getId());
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	void Test_getPetInventory() {

		Response response = StoreEndPoints.getPetInventory();
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	void Test_deleteOrder() {

		Response response = StoreEndPoints.deleteOrder(storepayload.getId());
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
	}
}