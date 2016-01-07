package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Dzxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Dzxt_主表RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Dzxt_主表Model m = null;
		try {
			m = new Dzxt_主表Model(
					rs.getString("验收单编号"),
					rs.getString("资产编号"),
					rs.getString("资产名称"),
					rs.getString("分类号"),
					rs.getString("国资分类号"),
					rs.getString("国资大类"),
					rs.getString("采购编号"),
					rs.getString("型号"),
					rs.getString("规格"),
					rs.getString("资产来源"),
					rs.getString("经费来源"),
					rs.getString("购置日期"),
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
					rs.getString("供货商"),
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
		Dzxt_主表Model m=(Dzxt_主表Model)md;
		j.put("验收单编号",m.get验收单编号()==null?"":m.get验收单编号());
		j.put("资产编号",m.get资产编号()==null?"":m.get资产编号());
		j.put("资产名称",m.get资产名称()==null?"":m.get资产名称());
		j.put("分类号",m.get分类号()==null?"":m.get分类号());
		j.put("国资分类号",m.get国资分类号()==null?"":m.get国资分类号());
		j.put("国资大类",m.get国资大类()==null?"":m.get国资大类());
		j.put("采购编号",m.get采购编号()==null?"":m.get采购编号());
		j.put("型号",m.get型号()==null?"":m.get型号());
		j.put("规格",m.get规格()==null?"":m.get规格());
		j.put("资产来源",m.get资产来源()==null?"":m.get资产来源());
		j.put("经费来源",m.get经费来源()==null?"":m.get经费来源());
		j.put("购置日期",m.get购置日期()==null?"":m.get购置日期());
		j.put("单价",m.get单价()==null?"":m.get单价());
		j.put("数量",m.get数量()==null?"":m.get数量());
		j.put("附件总额",m.get附件总额()==null?"":m.get附件总额());
		j.put("附件总数量",m.get附件总数量()==null?"":m.get附件总数量());
		j.put("总金额",m.get总金额()==null?"":m.get总金额());
		j.put("国别",m.get国别()==null?"":m.get国别());
		j.put("出厂编号",m.get出厂编号()==null?"":m.get出厂编号());
		j.put("出厂日期",m.get出厂日期()==null?"":m.get出厂日期());
		j.put("生产厂家",m.get生产厂家()==null?"":m.get生产厂家());
		j.put("领用人",m.get领用人()==null?"":m.get领用人());
		j.put("领用日期",m.get领用日期()==null?"":m.get领用日期());
		j.put("现状",m.get现状()==null?"":m.get现状());
		j.put("使用方向",m.get使用方向()==null?"":m.get使用方向());
		j.put("管理单位",m.get管理单位()==null?"":m.get管理单位());
		j.put("使用单位",m.get使用单位()==null?"":m.get使用单位());
		j.put("条码",m.get条码()==null?"":m.get条码());
		j.put("资产原值",m.get资产原值()==null?"":m.get资产原值());
		j.put("资产原数量",m.get资产原数量()==null?"":m.get资产原数量());
		j.put("资产原总金额",m.get资产原总金额()==null?"":m.get资产原总金额());
		j.put("附件原总额",m.get附件原总额()==null?"":m.get附件原总额());
		j.put("附件原总数量",m.get附件原总数量()==null?"":m.get附件原总数量());
		j.put("录入人",m.get录入人()==null?"":m.get录入人());
		j.put("录入时间",m.get录入时间()==null?"":m.get录入时间());
		j.put("管理人",m.get管理人()==null?"":m.get管理人());
		j.put("存放地点",m.get存放地点()==null?"":m.get存放地点());
		j.put("使用年限",m.get使用年限()==null?"":m.get使用年限());
		j.put("折旧状态",m.get折旧状态()==null?"":m.get折旧状态());
		j.put("折旧方法",m.get折旧方法()==null?"":m.get折旧方法());
		j.put("折旧时间",m.get折旧时间()==null?"":m.get折旧时间());
		j.put("已提折旧月数",m.get已提折旧月数()==null?"":m.get已提折旧月数());
		j.put("月折旧率",m.get月折旧率()==null?"":m.get月折旧率());
		j.put("月折旧额",m.get月折旧额()==null?"":m.get月折旧额());
		j.put("减值准备",m.get减值准备()==null?"":m.get减值准备());
		j.put("残值率",m.get残值率()==null?"":m.get残值率());
		j.put("累计折旧",m.get累计折旧()==null?"":m.get累计折旧());
		j.put("净值",m.get净值()==null?"":m.get净值());
		j.put("供货商",m.get供货商()==null?"":m.get供货商());
		j.put("备注",m.get备注()==null?"":m.get备注());
		j.put("审核人",m.get审核人()==null?"":m.get审核人());
		j.put("审核时间",m.get审核时间()==null?"":m.get审核时间());
		j.put("报账日期",m.get报账日期()==null?"":m.get报账日期());
		j.put("对账人",m.get对账人()==null?"":m.get对账人());
		j.put("财务凭单号",m.get财务凭单号()==null?"":m.get财务凭单号());
		j.put("财务分类",m.get财务分类()==null?"":m.get财务分类());
		j.put("图片1",m.get图片1()==null?"":m.get图片1());
		j.put("图片2",m.get图片2()==null?"":m.get图片2());
		j.put("图片3",m.get图片3()==null?"":m.get图片3());
		j.put("清查状态",m.get清查状态()==null?"":m.get清查状态());
		j.put("清查日期",m.get清查日期()==null?"":m.get清查日期());
		return j;
	}

}
