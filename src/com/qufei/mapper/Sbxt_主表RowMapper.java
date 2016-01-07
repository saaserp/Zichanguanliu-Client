package com.qufei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.qufei.model.Sbxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Sbxt_主表RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(org.json.JSONObject rs) {
		// TODO Auto-generated method stub
		Sbxt_主表Model pd=null;

		try {
			pd=new Sbxt_主表Model(
					rs.getString("验收单编号"),
					rs.getString("资产编号"),
					rs.getString("资产名称"),
					rs.getString("英文名称"),
					rs.getString("分类号"),
					rs.getString("型号"),
					
					rs.getString("规格"),
					rs.getString("购置日期"),
					rs.getString("资产来源"),
					rs.getString("经费来源"),
					rs.getString("国资分类号"),
					rs.getString("国资大类"),
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
					rs.getString("技术指标"),
					rs.getString("用途及应用范围"),
					rs.getString("设备性质"),
					rs.getString("采购编号"),
					rs.getString("发票号"),
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
					rs.getString("盘亏盘盈原因"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pd;

	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stubmap.put("验收单编号",mdd.get验收单编号());
		Sbxt_主表Model mdd=(Sbxt_主表Model)md;
		Map<String,String> map=new HashMap<String, String>();
		map.put("资产编号",mdd.get资产编号() );
		map.put("资产名称",mdd.get资产名称() );
		map.put("英文名称",mdd.get英文名称() );
		map.put("分类号",mdd.get分类号() );
		map.put("型号",mdd.get型号() );
		map.put("规格",mdd.get规格() );
		map.put("购置日期",mdd.get购置日期() );
		map.put("资产来源",mdd.get资产来源() );
		map.put("经费来源",mdd.get经费来源() );
		map.put("国资分类号",mdd.get国资分类号() );
		map.put("国资大类",mdd.get国资大类() );
		map.put("单价",mdd.get单价() );
		map.put("数量",mdd.get数量() );
		map.put("附件总额",mdd.get附件总额() );
		map.put("附件总数量",mdd.get附件总数量());
		map.put("总金额",mdd.get总金额() );
		map.put("国别",mdd.get国别() );
		map.put("出厂编号",mdd.get出厂编号() );
		map.put("出厂日期",mdd.get出厂日期() );
		map.put("生产厂家",mdd.get生产厂家() );
		map.put("领用人",mdd.get领用人() );
		map.put("领用日期",mdd.get领用日期() );
		map.put("现状",mdd.get现状() );
		map.put("使用方向",mdd.get使用方向() );
		map.put("管理单位",mdd.get管理单位() );
		map.put("使用单位",mdd.get使用单位() );
		map.put("条码",mdd.get条码() );
		map.put("资产原值",mdd.get资产原值() );
		map.put("资产原数量",mdd.get资产原数量());
		map.put("资产原总金额",mdd.get资产原总金额() );
		map.put("附件原总额",mdd.get附件原总额() );
		map.put("附件原总数量",mdd.get附件原总数量() );
		map.put("录入人",mdd.get录入人() );
		map.put("录入时间",mdd.get录入时间() );
		map.put("管理人",mdd.get管理人() );
		map.put("存放地点",mdd.get存放地点() );
		map.put("使用年限",mdd.get使用年限() );
		map.put("技术指标",mdd.get技术指标() );
		map.put("用途及应用范围",mdd.get用途及应用范围() );
		map.put("设备性质",mdd.get设备性质() );
		map.put("采购编号",mdd.get采购编号() );
		map.put("发票号",mdd.get发票号() );
		map.put("供货商",mdd.get供货商() );
		map.put("备注",mdd.get备注() );
		map.put("审核人",mdd.get审核人() );
		map.put("审核时间",mdd.get审核时间() );
		map.put("报账日期",mdd.get报账日期() );
		map.put("对账人",mdd.get对账人() );
		map.put("财务凭单号",mdd.get财务凭单号() );
		map.put("财务分类",mdd.get财务分类() );
		map.put("图片1",mdd.get图片1() );
		map.put("图片2",mdd.get图片2() );
		map.put("图片3",mdd.get图片3());
		map.put("清查状态",mdd.get清查状态() );
		map.put("清查日期",mdd.get清查日期() );
		map.put("盘亏盘盈原因",mdd.get盘亏盘盈原因() );
		return map;
	}



}
