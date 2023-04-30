/**
 * 
 */
package api.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

/**
 * @author Santosh Sharma
 *
 */
public class UserTests {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void setPayload() {
	    
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		userpayload.setUserStatus(0);
	
	}
	
	
	@BeforeSuite
	void beforeSuite() throws IOException {
		ExtentReportManager.setUpReport();
	}
	
	
	@AfterSuite
	void afterSuite() {
		ExtentReportManager.closeReport();
	}
	
	
	@Test(priority = 0)
	void Test_addUser() {
		
		Response response = UserEndPoints.addUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority = 1)
	void Test_getUser() {
		
		Response response = UserEndPoints.getUser(userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority = 2)
	void Test_updateUser() {
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		
		Response response  = UserEndPoints.updateUser(userpayload.getUsername(), userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority = 3)
	void Test_deleteUser() {
		
		Response response = UserEndPoints.deleteUser(userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
