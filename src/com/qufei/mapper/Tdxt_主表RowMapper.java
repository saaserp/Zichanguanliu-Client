package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.qufei.model.Tdxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Tdxt_主表RowMapper implements IRowMapper{

	

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Tdxt_主表Model m=null;
		try {
			m=new Tdxt_主表Model(
					rs.getString("验收单编号"),
					rs.getString("资产编号"),
					rs.getString("资产名称"),
					rs.getString("分类号"),
					rs.getString("国资分类号"),
					rs.getString("凭证号"),
					rs.getString("土地地址"),
					rs.getString("土地用途"),
					rs.getString("规划用途"),
					rs.getString("使用权状况"),
					rs.getString("使用权类型"),
					rs.getString("土地证办理情况"),
					rs.getString("土地证编号"),
					rs.getString("土地使用者"),
					rs.getString("土地证字号"),
					rs.getString("地号"),
					rs.getString("土地证土地用途"),
					rs.getString("使用等级"),
					rs.getString("土地证使用权类型"),
					rs.getString("总面积"),
					rs.getString("土地证发证机关"),
					rs.getString("经费来源"),
					rs.getString("购置日期"),
					rs.getString("使用权面积"),
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String> j=new HashMap<String, String>();
		Tdxt_主表Model mdd=(Tdxt_主表Model)md;
		try {
			j.put("验收单编号",mdd.get验收单编号());
			j.put("资产编号",mdd.get资产编号());
			j.put("资产名称",mdd.get资产名称());
			j.put("分类号",mdd.get分类号());
			j.put("国资分类号",mdd.get国资分类号());
			j.put("凭证号",mdd.get凭证号());
			j.put("土地地址",mdd.get土地地址());
			j.put("土地用途",mdd.get土地用途());
			j.put("规划用途",mdd.get规划用途());
			j.put("使用权状况",mdd.get使用权状况());
			j.put("使用权类型",mdd.get使用权类型());
			j.put("土地证办理情况",mdd.get土地证办理情况());
			j.put("土地证编号",mdd.get土地证编号());
			j.put("土地使用者",mdd.get土地使用者());
			j.put("土地证字号",mdd.get土地证字号());
			j.put("地号",mdd.get地号());
			j.put("土地证土地用途",mdd.get土地证土地用途());
			j.put("使用等级",mdd.get使用等级());
			j.put("土地证使用权类型",mdd.get土地证使用权类型());
			j.put("总面积",mdd.get总面积());
			j.put("土地证发证机关",mdd.get土地证发证机关());
			j.put("经费来源",mdd.get经费来源());
			j.put("购置日期",mdd.get购置日期());
			j.put("使用权面积",mdd.get使用权面积());
			j.put("单价",mdd.get单价());
			j.put("数量",mdd.get数量());
			j.put("附件总额",mdd.get附件总额());
			j.put("附件总数量",mdd.get附件总数量());
			j.put("总金额",mdd.get总金额());
			j.put("现状",mdd.get现状());
			j.put("使用方向",mdd.get使用方向());
			j.put("使用单位",mdd.get使用单位());
			j.put("资产原值",mdd.get资产原值());
			j.put("资产原数量",mdd.get资产原数量());
			j.put("资产原总金额",mdd.get资产原总金额());
			j.put("附件原总额",mdd.get附件原总额());
			j.put("附件原总数量",mdd.get附件原总数量());
			j.put("录入人",mdd.get录入人());
			j.put("录入时间",mdd.get录入时间());
			j.put("使用年限",mdd.get使用年限());
			j.put("折旧状态",mdd.get折旧状态());
			j.put("折旧方法",mdd.get折旧方法());
			j.put("折旧时间",mdd.get折旧时间());
			j.put("已提折旧月数",mdd.get已提折旧月数());
			j.put("月折旧率",mdd.get月折旧率());
			j.put("月折旧额",mdd.get月折旧额());
			j.put("减值准备",mdd.get减值准备());
			j.put("残值率",mdd.get残值率());
			j.put("累计折旧",mdd.get累计折旧());
			j.put("净值",mdd.get净值());
			j.put("备注",mdd.get备注());
			j.put("审核人",mdd.get审核人());
			j.put("审核时间",mdd.get审核时间());
			j.put("报账日期",mdd.get报账日期());
			j.put("对账人",mdd.get对账人());
			j.put("财务凭单号",mdd.get财务凭单号());
			j.put("财务分类",mdd.get财务分类());
			j.put("图片1",mdd.get图片1());
			j.put("图片2",mdd.get图片2());
			j.put("图片3",mdd.get图片3());
			j.put("清查状态",mdd.get清查状态());
			j.put("清查日期",mdd.get清查日期());
			j.put("盘亏盘盈原因",mdd.get盘亏盘盈原因());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return j;
	}

}
