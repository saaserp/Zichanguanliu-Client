package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Tsxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Tsxt_主表RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Tsxt_主表Model m=null;
		try {
			m=new Tsxt_主表Model(
					jsobj.getString("验收单编号"),
					jsobj.getString("资产编号"),
					jsobj.getString("资产名称"),
					jsobj.getString("分类号"),
					jsobj.getString("购置日期"),
					jsobj.getString("资产来源"),
					jsobj.getString("经费来源"),
					jsobj.getString("国资分类号"),
					jsobj.getString("单价"),
					jsobj.getString("数量"),
					jsobj.getString("附件总额"),
					jsobj.getString("附件总数量"),
					jsobj.getString("总金额"),
					jsobj.getString("图书号"),
					jsobj.getString("出版日期"),
					jsobj.getString("出版商"),
					jsobj.getString("采购人"),
					jsobj.getString("现状"),
					jsobj.getString("使用方向"),
					jsobj.getString("管理单位"),
					jsobj.getString("使用单位"),
					jsobj.getString("资产原值"),
					jsobj.getString("资产原数量"),
					jsobj.getString("资产原总金额"),
					jsobj.getString("附件原总额"),
					jsobj.getString("附件原总数量"),
					jsobj.getString("录入人"),
					jsobj.getString("录入时间"),
					jsobj.getString("管理人"),
					jsobj.getString("存放地点"),
					jsobj.getString("使用年限"),
					jsobj.getString("折旧状态"),
					jsobj.getString("折旧方法"),
					jsobj.getString("折旧时间"),
					jsobj.getString("已提折旧月数"),
					jsobj.getString("月折旧率"),
					jsobj.getString("月折旧额"),
					jsobj.getString("减值准备"),
					jsobj.getString("残值率"),
					jsobj.getString("累计折旧"),
					jsobj.getString("净值"),
					jsobj.getString("发票号"),
					jsobj.getString("供货商"),
					jsobj.getString("备注"),
					jsobj.getString("审核人"),
					jsobj.getString("审核时间"),
					jsobj.getString("报账日期"),
					jsobj.getString("对账人"),
					jsobj.getString("财务凭单号"),
					jsobj.getString("财务分类"),
					jsobj.getString("图片1"),
					jsobj.getString("图片2"),
					jsobj.getString("图片3"),
					jsobj.getString("清查状态"),
					jsobj.getString("清查日期"),
					jsobj.getString("盘亏盘盈原因")
					
					);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>map=new HashMap<String, String>();
		Tsxt_主表Model d=(Tsxt_主表Model) md;
		map.put("验收单编号",d.get验收单编号()==null?"":d.get验收单编号());
		map.put("资产编号",d.get资产编号()==null?"":d.get资产编号());
		map.put("资产名称",d.get资产名称()==null?"":d.get资产名称());
		map.put("分类号",d.get分类号()==null?"":d.get分类号());
		map.put("购置日期",d.get购置日期()==null?"":d.get购置日期());
		map.put("资产来源",d.get资产来源()==null?"":d.get资产来源());
		map.put("经费来源",d.get经费来源()==null?"":d.get经费来源());
		map.put("国资分类号",d.get国资分类号()==null?"":d.get国资分类号());
		map.put("单价",d.get单价()==null?"":d.get单价());
		map.put("数量",d.get数量()==null?"":d.get数量());
		map.put("附件总额",d.get附件总额()==null?"":d.get附件总额());
		map.put("附件总数量",d.get附件总数量()==null?"":d.get附件总数量());
		map.put("总金额",d.get总金额()==null?"":d.get总金额());
		map.put("图书号",d.get图书号()==null?"":d.get图书号());
		map.put("出版日期",d.get出版日期()==null?"":d.get出版日期());
		map.put("出版商",d.get出版商()==null?"":d.get出版商());
		map.put("采购人",d.get采购人()==null?"":d.get采购人());
		map.put("现状",d.get现状()==null?"":d.get现状());
		map.put("使用方向",d.get使用方向()==null?"":d.get使用方向());
		map.put("管理单位",d.get管理单位()==null?"":d.get管理单位());
		map.put("使用单位",d.get使用单位()==null?"":d.get使用单位());
		map.put("资产原值",d.get资产原值()==null?"":d.get资产原值());
		map.put("资产原数量",d.get资产原数量()==null?"":d.get资产原数量());
		map.put("资产原总金额",d.get资产原总金额()==null?"":d.get资产原总金额());
		map.put("附件原总额",d.get附件原总额()==null?"":d.get附件原总额());
		map.put("附件原总数量",d.get附件原总数量()==null?"":d.get附件原总数量());
		map.put("录入人",d.get录入人()==null?"":d.get录入人());
		map.put("录入时间",d.get录入时间()==null?"":d.get录入时间());
		map.put("管理人",d.get管理人()==null?"":d.get管理人());
		map.put("存放地点",d.get存放地点()==null?"":d.get存放地点());
		map.put("使用年限",d.get使用年限()==null?"":d.get使用年限());
		map.put("折旧状态",d.get折旧状态()==null?"":d.get折旧状态());
		map.put("折旧方法",d.get折旧方法()==null?"":d.get折旧方法());
		map.put("折旧时间",d.get折旧时间()==null?"":d.get折旧时间());
		map.put("已提折旧月数",d.get已提折旧月数()==null?"":d.get已提折旧月数());
		map.put("月折旧率",d.get月折旧率()==null?"":d.get月折旧率());
		map.put("月折旧额",d.get月折旧额()==null?"":d.get月折旧额());
		map.put("减值准备",d.get减值准备()==null?"":d.get减值准备());
		map.put("残值率",d.get残值率()==null?"":d.get残值率());
		map.put("累计折旧",d.get累计折旧()==null?"":d.get累计折旧());
		map.put("净值",d.get净值()==null?"":d.get净值());
		map.put("发票号",d.get发票号()==null?"":d.get发票号());
		map.put("供货商",d.get供货商()==null?"":d.get供货商());
		map.put("备注",d.get备注()==null?"":d.get备注());
		map.put("审核人",d.get审核人()==null?"":d.get审核人());
		map.put("审核时间",d.get审核时间()==null?"":d.get审核时间());
		map.put("报账日期",d.get报账日期()==null?"":d.get报账日期());
		map.put("对账人",d.get对账人()==null?"":d.get对账人());
		map.put("财务凭单号",d.get财务凭单号()==null?"":d.get财务凭单号());
		map.put("财务分类",d.get财务分类()==null?"":d.get财务分类());
		map.put("图片1",d.get图片1()==null?"":d.get图片1());
		map.put("图片2",d.get图片2()==null?"":d.get图片2());
		map.put("图片3",d.get图片3()==null?"":d.get图片3());
		map.put("清查状态",d.get清查状态()==null?"":d.get清查状态());
		map.put("清查日期",d.get清查日期()==null?"":d.get清查日期());
		map.put("盘亏盘盈原因",d.get盘亏盘盈原因()==null?"":d.get盘亏盘盈原因());
		return map;
	}

	

}
