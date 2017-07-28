package com.sishishinn.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonDataGrid extends JsonResult{

	JsonConfig jsonConfig = null;
	// 记录集合
	private List<Object> rows = new ArrayList<Object>();
	// 总计路数
	private long total = 0;
	// 总页数
	private long pageCount = 0;
	// 当前页码
	private int pageIndex = 0;

	public JsonConfig getJsonConfig() {
		return jsonConfig;
	}

	public void setJsonConfig(JsonConfig jsonConfig) {
		this.jsonConfig = jsonConfig;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 添加数据集合
	 */
	public void addAll(Collection<Object> items) {
		this.rows.addAll(items);
	}

	/**
	 * 添加数据
	 */
	public void addItem(Object obj) {
		this.rows.add(obj);
	}

	/**
	 * 移除数据
	 */
	public void removeItem(Object obj) {
		this.rows.remove(obj);
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
	public static JSON successToJson(String message) {
		JsonDataGrid result = new JsonDataGrid();
		result.setState("ok");
		result.setMsg(message);
		return result.toJson();
	}

	/**
	 * 获得失败的操作结果,直接以JSON表示
	 */
	public static JSON failureToJson(String message) {
		JsonDataGrid result = new JsonDataGrid();
		result.setState("fail");
		result.setMsg(message);
		return result.toJson();
	}
}
