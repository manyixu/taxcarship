package com.derun.controller.busiQuery.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
  
	
	public static JSONArray getJsonArray(ResultSet rs) throws SQLException{
		
		ResultSetMetaData rsData = rs.getMetaData();
		int num = rsData.getColumnCount();
		JSONArray jsonArray = new JSONArray();
		while (rs.next()) {
			JSONObject jsonObject = new JSONObject();
			for(int i=0;i<=num;i++){
				jsonObject.put(rsData.getCatalogName(i), rs.getObject(i));
			}
			jsonArray.add(jsonObject);
			System.out.println(jsonArray);
			
		}
		
		return jsonArray;
	}
}
