package Akamai.NewDataETE;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataReaderETE {

	public List<HashMap<String, String>> getJsonDataToMapETE() throws IOException
	{
		//this method wil read data of json and convert it into string variable
		String jsonContentETE=FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//Akamai//NewDataETE//PurcheshOrder.json"), 
				StandardCharsets.UTF_16);
		
		//convert String to Hashmap
ObjectMapper mapper=new ObjectMapper();
List<HashMap<String,String>> data=mapper.readValue(jsonContentETE, new TypeReference<List<HashMap<String,String>>>(){
});
return data;
	}
}
