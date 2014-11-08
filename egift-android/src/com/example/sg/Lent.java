package com.example.sg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Lent extends Activity {
	String result = null;
	InputStream is = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alent);
	}
	
	void send(){
		try {
			 HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://localhost/egift/send.php");
		   System.out.println("connect to website first time");
		     // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
	            String name = null;
				nameValuePairs.add(new BasicNameValuePair("name", name));
	            String amount = null;
				nameValuePairs.add(new BasicNameValuePair("amount", amount));
	            String email = null;
				nameValuePairs.add(new BasicNameValuePair("email", email));
	            String phno = null;
				nameValuePairs.add(new BasicNameValuePair("phno", phno));
	            String time = null;
				nameValuePairs.add(new BasicNameValuePair("time", time));
	            
	           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
       // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
        
	       HttpEntity entity = response.getEntity();

   	      is = entity.getContent();
   	    System.out.println("..............");
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
    
download();
try{
	System.out.println("inside try catch1");
JSONArray jArray = new JSONArray(result);
System.out.println("inside try catch2");

	int k=0;
 int y = jArray.length();
 System.out.println("y="+y);

	System.out.println("inside try catch3");
	
JSONObject json_data = jArray.getJSONObject(0);
System.out.println("inside try catch 3.5");

System.out.println(json_data.getString("Status"));


System.out.println("inside try catch4");
	


} catch (JSONException e) {System.out.println("caught");

}

	}
	void download()
	{ System.out.println("in download");
		
		try{
		           
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		           StringBuilder sb = new StringBuilder();
		           String line = null;
		           int y=0;
		           while ((line = reader.readLine()) != null&&y==0) {
		                   sb.append(line + "\n");y=1;
		                  
		           }
		        
		           is.close();
		           result  = sb.toString();
		          
		           System.out.println("end of first half");
		   }catch(Exception e){
		           Log.e("log_tag", "Error converting result "+e.toString());
		           System.out.println("caught");
		           
		   }
	}
}
