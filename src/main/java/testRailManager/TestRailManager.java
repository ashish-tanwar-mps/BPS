package testRailManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRailManager {
public static String Test_Run_ID = "6041";
	
	public static String Test_Rail_Username="gaswal@highwirepress.com";
	public static String Test_Rail_Password="Mps@1234";
	public static String Test_Rail_URL="https://highwire.testrail.net";
	public static int Test_Case_Pass=1;
	public static int Test_Case_Fail=5;
	
	public static void addResultForTestCase(String testcaseID,int status, String error) {
		
		String testrunid=Test_Run_ID;
		APIClient client=new APIClient(Test_Rail_URL);
		client.setUser(Test_Rail_Username);
		client.setPassword(Test_Rail_Password);
		
		Map<String,Object> data= new HashMap<String,Object>();
		data.put("status_id", status);
		data.put("comment",error);
		try {
			client.sendPost("add_result_for_case/"+testrunid+"/"+testcaseID, data);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
public void addResultsToTestrail(String result,String testcase_id,String comment) {
		
		if(result=="Pass") {
			TestRailManager.addResultForTestCase(testcase_id, TestRailManager.Test_Case_Pass, comment);
		}else if(result=="Fail") {
			TestRailManager.addResultForTestCase(testcase_id, TestRailManager.Test_Case_Fail, comment);
		}
		
	}
}
