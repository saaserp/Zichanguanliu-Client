package com.qufei.tools;

import java.util.Map;

import org.json.JSONObject;

public interface IRowMapper {
	Model mappingRow(JSONObject jsobj);
	Map<String,String> mappingRow(Model md);
}
