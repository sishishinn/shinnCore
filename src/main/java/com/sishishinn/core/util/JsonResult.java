package com.sishishinn.core.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonResult {

	// 操作状态
	private String state = "";
	// 操作信息
	private String msg = "";
	// 额外附带的信息
	private Map<String, Object> data = new HashMap<String, Object>();
	// JSON config
	JsonConfig jsonConfig = null;

	public JsonConfig getJsonConfig() {
		return jsonConfig;
	}

	public void setJsonConfig(JsonConfig jsonConfig) {
		this.jsonConfig = jsonConfig;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void putData(String name, Object value) {
		this.data.put(name, value);
	}

	public JSON toJson() {
		if (EmptyUtil.isEmpty(jsonConfig))
			return JSONObject.fromObject(this);
		else
			return JSONObject.fromObject(this, jsonConfig);
	}

	/**
	 * 获得成功的操作结果,直接以JSON表示
	 */
	public JSON successASJson(String message) {
		this.setState("ok");
		this.setMsg(message);
		return this.toJson();
	}
	
	/**
	 * 获得失败的操作结果,直接以JSON表示
	 */
	public JSON failureASJson(String message) {
		this.setState("fail");
		this.setMsg(message);
		return this.toJson();
	}
	
	/**
	 * 获得成功的操作结果,直接以JSON表示
	 */
	public static JSON successToJson(String message) {
		JsonResult result = new JsonResult();
		result.setState("ok");
		result.setMsg(message);
		return result.toJson();
	}
	
	/**
	 * 获得失败的操作结果,直接以JSON表示
	 */
	public static JSON failureToJson(String message) {
		JsonResult result = new JsonResult();
		result.setState("fail");
		result.setMsg(message);
		return result.toJson();
	}
}
