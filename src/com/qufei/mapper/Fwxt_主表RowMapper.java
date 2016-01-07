package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Fwxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Fwxt_主表RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Fwxt_主表Model m=null;
		try {
			m=new Fwxt_主表Model(
					jsobj.getString("验收单编号"),
					jsobj.getString("资产编号"),
					jsobj.getString("资产名称"),
					jsobj.getString("分类号"),
					jsobj.getString("国资分类号"),
					jsobj.getString("凭证号"),
					jsobj.getString("房屋地址"),
					jsobj.getString("地上层数"),
					jsobj.getString("地下层数"),
					jsobj.getString("使用面积"),
					jsobj.getString("建筑面积"),
					jsobj.getString("占地面积"),
					jsobj.getString("房屋用途"),
					jsobj.getString("产权状况"),
					jsobj.getString("房屋结构"),
					jsobj.getString("规划用途"),
					jsobj.getString("设计单位"),
					jsobj.getString("施工单位"),
					jsobj.getString("竣工日期"),
					jsobj.getString("人防级别"),
					jsobj.getString("产权证办理情况"),
					jsobj.getString("产权证编号"),
					jsobj.getString("产权证字号"),
					jsobj.getString("产权证单位名称"),
					jsobj.getString("产权证建筑面积"),
					jsobj.getString("产权证房屋用途"),
					jsobj.getString("经费来源"),
					jsobj.getString("单价"),
					jsobj.getString("数量"),
					jsobj.getString("附件总额"),
					jsobj.getString("附件总数量"),
					jsobj.getString("总金额"),
					jsobj.getString("现状"),
					jsobj.getString("使用方向"),
					jsobj.getString("使用单位"),
					jsobj.getString("资产原值"),
					jsobj.getString("资产原数量"),
					jsobj.getString("资产原总金额"),
					jsobj.getString("附件原总额"),
					jsobj.getString("附件原总数量"),
					jsobj.getString("录入人"),
					jsobj.getString("录入时间"),
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
		Fwxt_主表Model m=(Fwxt_主表Model)md;
		map.put("验收单编号",m.get验收单编号());
		map.put("资产编号",m.get资产编号());
		map.put("资产名称",m.get资产名称());
		map.put("分类号",m.get分类号());
		map.put("国资分类号",m.get国资分类号());
		map.put("凭证号",m.get凭证号());
		map.put("房屋地址",m.get房屋地址());
		map.put("地上层数",m.get地上层数());
		map.put("地下层数",m.get地下层数());
		map.put("使用面积",m.get使用面积());
		map.put("建筑面积",m.get建筑面积());
		map.put("占地面积",m.get占地面积());
		map.put("房屋用途",m.get房屋用途());
		map.put("产权状况",m.get产权状况());
		map.put("房屋结构",m.get房屋结构());
		map.put("规划用途",m.get规划用途());
		map.put("设计单位",m.get设计单位());
		map.put("施工单位",m.get施工单位());
		map.put("竣工日期",m.get竣工日期());
		map.put("人防级别",m.get人防级别());
		map.put("产权证办理情况",m.get产权证办理情况());
		map.put("产权证编号",m.get产权证编号());
		map.put("产权证字号",m.get产权证字号());
		map.put("产权证单位名称",m.get产权证单位名称());
		map.put("产权证建筑面积",m.get产权证建筑面积());
		map.put("产权证房屋用途",m.get产权证房屋用途());
		map.put("经费来源",m.get经费来源());
		map.put("单价",m.get单价());
		map.put("数量",m.get数量());
		map.put("附件总额",m.get附件总额());
		map.put("附件总数量",m.get附件总数量());
		map.put("总金额",m.get总金额());
		map.put("现状",m.get现状());
		map.put("使用方向",m.get使用方向());
		map.put("使用单位",m.get使用单位());
		map.put("资产原值",m.get资产原值());
		map.put("资产原数量",m.get资产原数量());
		map.put("资产原总金额",m.get资产原总金额());
		map.put("附件原总额",m.get附件原总额());
		map.put("附件原总数量",m.get附件原总数量());
		map.put("录入人",m.get录入人());
		map.put("录入时间",m.get录入时间());
		map.put("使用年限",m.get使用年限());
		map.put("折旧状态",m.get折旧状态());
		map.put("折旧方法",m.get折旧方法());
		map.put("折旧时间",m.get折旧时间());
		map.put("已提折旧月数",m.get已提折旧月数());
		map.put("月折旧率",m.get月折旧率());
		map.put("月折旧额",m.get月折旧额());
		map.put("减值准备",m.get减值准备());
		map.put("残值率",m.get残值率());
		map.put("累计折旧",m.get累计折旧());
		map.put("净值",m.get净值());
		map.put("备注",m.get备注());
		map.put("审核人",m.get审核人());
		map.put("审核时间",m.get审核时间());
		map.put("报账日期",m.get报账日期());
		map.put("对账人",m.get对账人());
		map.put("财务凭单号",m.get财务凭单号());
		map.put("财务分类",m.get财务分类());
		map.put("图片1",m.get图片1());
		map.put("图片2",m.get图片2());
		map.put("图片3",m.get图片3());
		map.put("清查状态",m.get清查状态());
		map.put("清查日期",m.get清查日期());
		map.put("盘亏盘盈原因",m.get盘亏盘盈原因());
		return map;
	}



}
