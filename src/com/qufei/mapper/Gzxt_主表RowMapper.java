package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Gzxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Gzxt_主表RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Gzxt_主表Model m=null;
		try {
			m=new Gzxt_主表Model(
					rs.getString("验收单编号"),
					rs.getString("资产编号"),
					rs.getString("资产名称"),
					rs.getString("分类号"),
					rs.getString("国资分类号"),
					rs.getString("凭证号"),
					rs.getString("坐落位置"),
					rs.getString("占地面积"),
					rs.getString("设计单位"),
					rs.getString("施工单位"),
					rs.getString("竣工日期"),
					rs.getString("经费来源"),
					rs.getString("计量单位"),
					rs.getString("计量"),
					rs.getString("单价"),
					rs.getString("数量"),
					rs.getString("附件总额"),
					rs.getString("附件总数量"),
					rs.getString("总金额"),
					rs.getString("现状"),
					rs.getString("使用方向"),
					rs.getString("使用单位"),
					rs.getString("资产原值"),
					rs.getString("资产原数量"),
					rs.getString("资产原总金额"),
					rs.getString("附件原总额"),
					rs.getString("附件原总数量"),
					rs.getString("录入人"),
					rs.getString("录入时间"),
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
		Gzxt_主表Model m=(Gzxt_主表Model) md;
		j.put("验收单编号",m.get验收单编号()==null?"":m.get验收单编号());
		j.put("资产编号",m.get资产编号()==null?"":m.get资产编号());
		j.put("资产名称",m.get资产名称()==null?"":m.get资产名称());
		j.put("分类号",m.get分类号()==null?"":m.get分类号());
		j.put("国资分类号",m.get国资分类号()==null?"":m.get国资分类号());
		j.put("凭证号",m.get凭证号()==null?"":m.get凭证号());
		j.put("坐落位置",m.get坐落位置()==null?"":m.get坐落位置());
		j.put("占地面积",m.get占地面积()==null?"":m.get占地面积());
		j.put("设计单位",m.get设计单位()==null?"":m.get设计单位());
		j.put("施工单位",m.get施工单位()==null?"":m.get施工单位());
		j.put("竣工日期",m.get竣工日期()==null?"":m.get竣工日期());
		j.put("经费来源",m.get经费来源()==null?"":m.get经费来源());
		j.put("计量单位",m.get计量单位()==null?"":m.get计量单位());
		j.put("计量",m.get计量()==null?"":m.get计量());
		j.put("单价",m.get单价()==null?"":m.get单价());
		j.put("数量",m.get数量()==null?"":m.get数量());
		j.put("附件总额",m.get附件总额()==null?"":m.get附件总额());
		j.put("附件总数量",m.get附件总数量()==null?"":m.get附件总数量());
		j.put("总金额",m.get总金额()==null?"":m.get附件总数量());
		j.put("现状",m.get现状()==null?"":m.get现状());
		j.put("使用方向",m.get使用方向()==null?"":m.get使用方向());
		j.put("使用单位",m.get使用单位()==null?"":m.get使用单位());
		j.put("资产原值",m.get资产原值()==null?"":m.get资产原值());
		j.put("资产原数量",m.get资产原数量()==null?"":m.get资产原数量());
		j.put("资产原总金额",m.get资产原总金额()==null?"":m.get资产原总金额());
		j.put("附件原总额",m.get附件原总额()==null?"":m.get附件原总额());
		j.put("附件原总数量",m.get附件原总数量()==null?"":m.get附件原总数量());
		j.put("录入人",m.get录入人()==null?"":m.get录入人());
		j.put("录入时间",m.get录入时间()==null?"":m.get录入时间());
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
		j.put("备注",m.get备注()==null?"":m.get备注());
		j.put("审核人",m.get审核人()==null?"":m.get审核人());
		j.put("审核时间",m.get审核时间()==null?"":m.get审核时间());
		j.put("报账日期",m.get报账日期()==null?"":m.get报账日期());
		j.put("对账人",m.get对账人()==null?"":m.get对账人());
		j.put("财务凭单号",m.get财务凭单号()==null?"":m.get财务凭单号());
		j.put("财务分类",m.get财务分类()==null?"":m.get财务凭单号());
		j.put("图片1",m.get图片1()==null?"":m.get图片1());
		j.put("图片2",m.get图片2()==null?"":m.get图片2());
		j.put("图片3",m.get图片3()==null?"":m.get图片3());
		j.put("清查状态",m.get清查状态()==null?"":m.get清查状态());
		j.put("清查日期",m.get清查日期()==null?"":m.get清查日期());

		j.put("盘亏盘盈原因",m.get盘亏盘盈原因()==null?"":m.get盘亏盘盈原因());
		return j;
	}

	
}
