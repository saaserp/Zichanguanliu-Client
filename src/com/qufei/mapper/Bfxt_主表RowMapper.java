package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Bfxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Bfxt_主表RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Bfxt_主表Model bm=null;
		try {
			bm=new Bfxt_主表Model(
					rs.getString("验收单编号"),
					rs.getString("资产编号"),
					rs.getString("资产名称"),
					rs.getString("分类号"),
					rs.getString("型号"),
					rs.getString("规格"),
					rs.getString("购置日期"),
					rs.getString("资产来源"),
					rs.getString("经费来源"),
					rs.getString("国资分类号"),
					rs.getString("单价"),
					rs.getString("数量"),
					rs.getString("附件总额"),
					rs.getString("附件总数量"),
					rs.getString("总金额"),
					rs.getString("国别"),
					rs.getString("出厂编号"),
					rs.getString("出厂日期"),
					rs.getString("生产厂家"),
					rs.getString("领用人"),
					rs.getString("领用日期"),
					rs.getString("现状"),
					rs.getString("使用方向"),
					rs.getString("管理单位"),
					rs.getString("使用单位"),
					rs.getString("条码"),
					rs.getString("资产原值"),
					rs.getString("资产原数量"),
					rs.getString("资产原总金额"),
					rs.getString("附件原总额"),
					rs.getString("附件原总数量"),
					rs.getString("录入人"),
					rs.getString("录入时间"),
					rs.getString("管理人"),
					rs.getString("存放地点"),
					rs.getString("使用年限"),
					rs.getString("折旧状态"),
					rs.getString("折旧方法"),
					rs.getString("折旧时间"),
					rs.getString("已提折旧月数"),
					rs.getString("月折旧率"),
					rs.getString("月折旧额"),
					rs.getString("减值准备"),
					rs.getString("残值率"),
					rs.getString("累计折旧"),
					rs.getString("净值"),
					rs.getString("取得方式"),
					rs.getString("采购编号"),
					rs.getString("备注"),
					rs.getString("审核人"),
					rs.getString("审核时间"),
					rs.getString("报账日期"),
					rs.getString("对账人"),
					rs.getString("财务凭单号"),
					rs.getString("财务分类"),
					rs.getString("图片1"),
					rs.getString("图片2"),
					rs.getString("图片3"),
					rs.getString("清查状态"),
					rs.getString("清查日期"),
					rs.getString("盘亏盘盈原因")
					);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bm;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>map;
		map=new HashMap<String, String>();
		Bfxt_主表Model mdd=(Bfxt_主表Model)md;
		
		map.put("验收单编号",mdd.get验收单编号()==null?"":mdd.get验收单编号());
		map.put("资产编号",mdd.get资产编号()==null?"":mdd.get资产编号());
		map.put("资产名称",mdd.get资产名称()==null?"":mdd.get资产名称());
		map.put("分类号",mdd.get分类号()==null?"":mdd.get分类号());
		map.put("型号",mdd.get型号()==null?"":mdd.get型号());
		map.put("规格",mdd.get规格()==null?"":mdd.get规格());
		map.put("购置日期",mdd.get购置日期()==null?"":mdd.get购置日期());
		map.put("资产来源",mdd.get资产来源()==null?"":mdd.get资产来源());
		map.put("经费来源",mdd.get经费来源()==null?"":mdd.get经费来源());
		map.put("国资分类号",mdd.get国资分类号()==null?"":mdd.get国资分类号());
		map.put("单价",mdd.get单价()==null?"":mdd.get单价());
		map.put("数量",mdd.get数量()==null?"":mdd.get数量());
		map.put("附件总额",mdd.get附件总额()==null?"":mdd.get附件总额());
		map.put("附件总数量",mdd.get附件总数量()==null?"":mdd.get附件总数量());
		map.put("总金额",mdd.get总金额()==null?"":mdd.get总金额());
		map.put("国别",mdd.get国别()==null?"":mdd.get国别());
		map.put("出厂编号",mdd.get出厂编号()==null?"":mdd.get出厂编号());
		map.put("出厂日期",mdd.get出厂日期()==null?"":mdd.get出厂日期());
		map.put("生产厂家",mdd.get生产厂家()==null?"":mdd.get生产厂家());
		map.put("领用人",mdd.get领用人()==null?"":mdd.get领用人());
		map.put("领用日期",mdd.get领用日期()==null?"":mdd.get领用日期());
		map.put("现状",mdd.get现状()==null?"":mdd.get现状());
		map.put("使用方向",mdd.get使用方向()==null?"":mdd.get使用方向());
		map.put("管理单位",mdd.get管理单位()==null?"":mdd.get管理单位());
		map.put("使用单位",mdd.get使用单位()==null?"":mdd.get使用单位());
		map.put("条码",mdd.get条码()==null?"":mdd.get条码());
		map.put("资产原值",mdd.get资产原值()==null?"":mdd.get资产原值());
		map.put("资产原数量",mdd.get资产原数量()==null?"":mdd.get资产原数量());
		map.put("资产原总金额",mdd.get资产原总金额()==null?"":mdd.get资产原总金额());
		map.put("附件原总额",mdd.get附件原总额()==null?"":mdd.get附件原总额());
		map.put("附件原总数量",mdd.get附件原总数量()==null?"":mdd.get附件原总数量());
		map.put("录入人",mdd.get录入人()==null?"":mdd.get录入人());
		map.put("录入时间",mdd.get录入时间()==null?"":mdd.get录入时间());
		map.put("管理人",mdd.get管理人()==null?"":mdd.get管理人());
		map.put("存放地点",mdd.get存放地点()==null?"":mdd.get存放地点());
		map.put("使用年限",mdd.get使用年限()==null?"":mdd.get使用年限());
		map.put("折旧状态",mdd.get折旧状态()==null?"":mdd.get折旧状态());
		map.put("折旧方法",mdd.get折旧方法()==null?"":mdd.get折旧方法());
		map.put("折旧时间",mdd.get折旧时间()==null?"":mdd.get折旧时间());
		map.put("已提折旧月数",mdd.get已提折旧月数()==null?"":mdd.get已提折旧月数());
		map.put("月折旧率",mdd.get月折旧率()==null?"":mdd.get月折旧率());
		map.put("月折旧额",mdd.get月折旧额()==null?"":mdd.get月折旧额());
		map.put("减值准备",mdd.get减值准备()==null?"":mdd.get减值准备());
		map.put("残值率",mdd.get残值率()==null?"":mdd.get残值率());
		map.put("累计折旧",mdd.get累计折旧()==null?"":mdd.get累计折旧());
		map.put("净值",mdd.get净值()==null?"":mdd.get净值());
		map.put("取得方式",mdd.get取得方式()==null?"":mdd.get取得方式());
		map.put("采购编号",mdd.get采购编号()==null?"":mdd.get采购编号());
		map.put("备注",mdd.get备注()==null?"":mdd.get备注());
		map.put("审核人",mdd.get审核人()==null?"":mdd.get审核人());
		map.put("审核时间",mdd.get审核时间()==null?"":mdd.get审核时间());
		map.put("报账日期",mdd.get报账日期()==null?"":mdd.get报账日期());
		map.put("对账人",mdd.get对账人()==null?"":mdd.get对账人());
		map.put("财务凭单号",mdd.get财务凭单号()==null?"":mdd.get财务凭单号());
		map.put("财务分类",mdd.get财务分类()==null?"":mdd.get财务分类());
		map.put("图片1",mdd.get图片1()==null?"":mdd.get图片1());
		map.put("图片2",mdd.get图片2()==null?"":mdd.get图片2());
		map.put("图片3",mdd.get图片3()==null?"":mdd.get图片3());
		map.put("清查状态",mdd.get清查状态()==null?"":mdd.get清查状态());
		map.put("清查日期",mdd.get清查日期()==null?"":mdd.get清查日期());
		map.put("盘亏盘盈原因",mdd.get盘亏盘盈原因()==null?"":mdd.get盘亏盘盈原因());
		return map;
	}



}
