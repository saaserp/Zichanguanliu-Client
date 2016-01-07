package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Clxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Clxt_主表RowMapper implements IRowMapper{


	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Clxt_主表Model m = null;
		try {
			m=new Clxt_主表Model(
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
					rs.getString("车架号"),
					rs.getString("车牌颜色"),
					rs.getString("车身颜色"),
					rs.getString("出厂日期"),
					rs.getString("生产厂家"),
					rs.getString("车辆类型"),
					rs.getString("品牌型号"),
					rs.getString("发动机号"),
					rs.getString("排气量"),
					rs.getString("座位数"),
					rs.getString("车辆用途"),
					rs.getString("采购形式"),
					rs.getString("车牌号"),
					rs.getString("燃料种类"),
					rs.getString("领用人"),
					rs.getString("领用日期"),
					rs.getString("现状"),
					rs.getString("使用方向"),
					rs.getString("管理单位"),
					rs.getString("使用单位"),
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
		return m;
	}


	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>j=new HashMap<String, String>();
		Clxt_主表Model d=(Clxt_主表Model) md;
		j.put("验收单编号",d.get验收单编号()==null?"":d.get验收单编号());
		j.put("资产编号",d.get资产编号()==null?"":d.get资产编号());
		j.put("资产名称",d.get资产名称()==null?"":d.get资产名称());
		j.put("分类号",d.get分类号()==null?"":d.get分类号());
		j.put("型号",d.get型号()==null?"":d.get型号());
		j.put("规格",d.get规格()==null?"":d.get规格());
		j.put("购置日期",d.get购置日期()==null?"":d.get购置日期());
		j.put("资产来源",d.get资产来源()==null?"":d.get资产来源());
		j.put("经费来源",d.get经费来源()==null?"":d.get经费来源());
		j.put("国资分类号",d.get国资分类号()==null?"":d.get国资分类号());
		j.put("单价",d.get单价()==null?"":d.get单价());
		j.put("数量",d.get数量()==null?"":d.get数量());
		j.put("附件总额",d.get附件总额()==null?"":d.get附件总额());
		j.put("附件总数量",d.get附件总数量()==null?"":d.get附件总数量());
		j.put("总金额",d.get总金额()==null?"":d.get总金额());
		j.put("国别",d.get国别()==null?"":d.get国别());
		j.put("车架号",d.get车架号()==null?"":d.get车架号());
		j.put("车牌颜色",d.get车牌颜色()==null?"":d.get车牌颜色());
		j.put("车身颜色",d.get车身颜色()==null?"":d.get车身颜色());
		j.put("出厂日期",d.get出厂日期()==null?"":d.get出厂日期());
		j.put("生产厂家",d.get生产厂家()==null?"":d.get生产厂家());
		j.put("车辆类型",d.get车辆类型()==null?"":d.get车辆类型());
		j.put("品牌型号",d.get品牌型号()==null?"":d.get品牌型号());
		j.put("发动机号",d.get发动机号()==null?"":d.get发动机号());
		j.put("排气量",d.get排气量()==null?"":d.get排气量());
		j.put("座位数",d.get座位数()==null?"":d.get座位数());
		j.put("车辆用途",d.get车辆用途()==null?"":d.get车辆用途());
		j.put("采购形式",d.get采购形式()==null?"":d.get采购形式());
		j.put("车牌号",d.get车牌号()==null?"":d.get车牌号());
		j.put("燃料种类",d.get燃料种类()==null?"":d.get燃料种类());
		j.put("领用人",d.get领用人()==null?"":d.get领用人());
		j.put("领用日期",d.get领用日期()==null?"":d.get领用日期());
		j.put("现状",d.get现状()==null?"":d.get现状());
		j.put("使用方向",d.get使用方向()==null?"":d.get使用方向());
		j.put("管理单位",d.get管理单位()==null?"":d.get管理单位());
		j.put("使用单位",d.get使用单位()==null?"":d.get使用单位());
		j.put("资产原值",d.get资产原值()==null?"":d.get资产原值());
		j.put("资产原数量",d.get资产原数量()==null?"":d.get资产原数量());
		j.put("资产原总金额",d.get资产原总金额()==null?"":d.get资产原总金额());
		j.put("附件原总额",d.get附件原总额()==null?"":d.get附件原总额());
		j.put("附件原总数量",d.get附件原总数量()==null?"":d.get附件原总数量());
		j.put("录入人",d.get录入人()==null?"":d.get录入人());
		j.put("录入时间",d.get录入时间()==null?"":d.get录入时间());
		j.put("管理人",d.get管理人()==null?"":d.get管理人());
		j.put("存放地点",d.get存放地点()==null?"":d.get存放地点());
		j.put("使用年限",d.get使用年限()==null?"":d.get使用年限());
		j.put("折旧状态",d.get折旧状态()==null?"":d.get折旧状态());
		j.put("折旧方法",d.get折旧方法()==null?"":d.get折旧方法());
		j.put("折旧时间",d.get折旧时间()==null?"":d.get折旧时间());
		j.put("已提折旧月数",d.get已提折旧月数()==null?"":d.get已提折旧月数());
		j.put("月折旧率",d.get月折旧率()==null?"":d.get月折旧率());
		j.put("月折旧额",d.get月折旧额()==null?"":d.get月折旧额());
		j.put("减值准备",d.get减值准备()==null?"":d.get减值准备());
		j.put("残值率",d.get残值率()==null?"":d.get残值率());
		j.put("累计折旧",d.get累计折旧()==null?"":d.get累计折旧());
		j.put("净值",d.get净值()==null?"":d.get净值());
		j.put("备注",d.get备注()==null?"":d.get备注());
		j.put("审核人",d.get审核人()==null?"":d.get审核人());
		j.put("审核时间",d.get审核时间()==null?"":d.get审核时间());
		j.put("报账日期",d.get报账日期()==null?"":d.get报账日期());
		j.put("对账人",d.get对账人()==null?"":d.get对账人());
		j.put("财务凭单号",d.get财务凭单号()==null?"":d.get财务凭单号());
		j.put("财务分类",d.get财务分类()==null?"":d.get财务分类());
		j.put("图片1",d.get图片1()==null?"":d.get图片1());
		j.put("图片2",d.get图片2()==null?"":d.get图片2());
		j.put("图片3",d.get图片3()==null?"":d.get图片3());
		j.put("清查状态",d.get清查状态()==null?"":d.get清查状态());
		j.put("清查日期",d.get清查日期()==null?"":d.get清查日期());
		j.put("盘亏盘盈原因",d.get盘亏盘盈原因()==null?"":d.get盘亏盘盈原因());
		return j;
	}

}
