package rough;



import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;


public class SplitString {
	
	public static void main(String[] args) {
		Response response=null;
		response=(Response) given().
		 auth().
	    preemptive().
	    basic("hel_desk", "Edcritical@123").
	  when().
	    get("https://myaccount-test.eurodw.eu/api/Common/SearchCompanyName?name=Sapient_Org1");
	  int status= response.getStatusCode();
	  System.out.println(status);
		    	}
	      

}


