package com.zj.myfuncdemos.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson {


	public static String parstHtmlContent(String result) {
		String optString = null;
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray optJSONArray = jsonObject.optJSONArray("datas");
			JSONObject optJSONObject = optJSONArray.optJSONObject(0);
			optString = optJSONObject.optString("content");
			System.out.println("optString == " + optString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return optString;
	}

}
