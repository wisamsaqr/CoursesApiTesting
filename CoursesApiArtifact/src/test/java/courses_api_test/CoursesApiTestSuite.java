package courses_api_test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CoursesApiTestSuite
{
	String courseId = "100";
	@BeforeTest
	public void configs()
	{
		baseURI = "http://localhost:3000";
	}
	
	@Test(priority = 1)
	public void CreateCourse()
	{
		JSONObject courseData = new JSONObject();
		courseData.put("id", courseId);
		courseData.put("title", "Algorithms");
		courseData.put("author", "Alma");
		
		RequestSpecification request = given();
		request.header("content-type", "application/json");
		request.body(courseData.toString());
		Response response = request.post("/courses");
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
//		Assert.assertEquals(response.jsonPath().get("author"), "Banan");
	}
	
	@Test(priority = 2)
	public void getCourse()
	{
		Response response = get("/courses/"+courseId);
		System.out.println("Content Body:\n" + response.getBody().asPrettyString());
		
		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(response.jsonPath().get("author"), "Banan");
	}
	
	@Test(priority = 3)
	public void updateCoursePut()
	{
		JSONObject courseData = new JSONObject();
		courseData.put("id", courseId);
		courseData.put("title", "C00L");
		courseData.put("author", "Wisam");
		
		RequestSpecification request = given();
		request.header("content-type", "application/json");
		request.body(courseData.toString());
		Response response = request.put("/courses/"+courseId);
		
		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(response.jsonPath().get("author"), "Sami");
	}
	
	@Test(priority = 4)
	public void updateCoursePatch()
	{
		JSONObject courseData = new JSONObject();
		courseData.put("id", courseId);
		courseData.put("author", "Alma");
		
		RequestSpecification request = given();
		request.header("content-type", "application/json");
		request.body(courseData.toString());
		Response response = request.patch("/courses/"+courseId);
		
		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(response.jsonPath().get("author"), "Sami");
	}
	
	@Test(priority = 5)
	public void DeleteCourse()
	{
		JSONObject courseData = new JSONObject();
		courseData.put("id", courseId);
		
		RequestSpecification request = given();
		request.header("content-type", "application/json");
		request.body(courseData.toString());
		Response response = request.delete("/courses/"+courseId);
		
		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(response.jsonPath().get("author"), "Sami");
	}
	
	@Test(priority = 6)
	public void getCourses()
	{
		Response response = get("/courses");
		System.out.println("Content Body:\n" + response.getBody().asPrettyString());
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}