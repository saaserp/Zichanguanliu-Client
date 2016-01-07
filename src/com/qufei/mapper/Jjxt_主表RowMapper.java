package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.util.Log;

import com.qufei.model.Jjxt_主表Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Jjxt_主表RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Jjxt_主表Model m=null;
		try {
			m=new Jjxt_主表Model(
					rs.getString("验收单编号"),
					rs.getString("资产编号"),
					rs.getString("资产名称"),
					rs.getString("分类号"),
					rs.getString("国资分类号"),
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
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("error",e.getMessage());
			e.printStackTrace();
			
		}
		return m;
	}

	@Override
	public Map<String, String> mappingRow(Model mdd) {
		// TODO Auto-generated method stub
		Jjxt_主表Model md=(Jjxt_主表Model)mdd;
		Map<String,String> j=new HashMap<String, String>();
		try {
		j.put("验收单编号", md.get验收单编号());
		j.put("资产编号", md.get资产编号() );
		j.put("资产名称", md.get资产名称() );
		j.put("分类号", md.get分类号() );
		j.put("国资分类号", md.get国资分类号() );
		j.put("采购编号", md.get采购编号() );
		j.put("型号", md.get型号() );
		j.put("规格", md.get规格() );
		j.put("资产来源", md.get资产来源() );
		j.put("经费来源", md.get经费来源() );
		j.put("购置日期", md.get购置日期() );
		j.put("单价", md.get单价() );
		j.put("数量", md.get数量() );
		j.put("附件总额", md.get附件总额() );
		j.put("附件总数量", md.get附件总数量() );
		j.put("总金额", md.get总金额() );
		j.put("国别", md.get国别() );
		j.put("出厂编号", md.get出厂编号() );
		j.put("出厂日期", md.get出厂日期() );
		j.put("生产厂家", md.get生产厂家() );
		j.put("领用人", md.get领用人() );
		j.put("领用日期", md.get领用日期() );
		j.put("现状", md.get现状() );
		j.put("使用方向", md.get使用方向() );
		j.put("管理单位", md.get管理单位() );
		j.put("使用单位", md.get使用单位() );
		j.put("条码", md.get条码() );
		j.put("资产原值", md.get资产原值() );
		j.put("资产原数量", md.get资产原数量() );
		j.put("资产原总金额", md.get资产原总金额() );
		j.put("附件原总额", md.get附件原总额() );
		j.put("附件原总数量", md.get附件原总数量() );
		j.put("录入人", md.get录入人() );
		j.put("录入时间", md.get录入时间() );
		j.put("管理人", md.get管理人() );
		j.put("存放地点", md.get存放地点() );
		j.put("使用年限", md.get使用年限() );
		j.put("折旧状态", md.get折旧状态() );
		j.put("折旧方法", md.get折旧方法() );
		j.put("折旧时间", md.get折旧时间() );
		j.put("已提折旧月数", md.get已提折旧月数() );
		j.put("月折旧率", md.get月折旧率() );
		j.put("月折旧额", md.get月折旧额() );
		j.put("减值准备", md.get减值准备() );
		j.put("残值率", md.get残值率() );
		j.put("累计折旧", md.get累计折旧() );
		j.put("净值", md.get净值() );
		j.put("供货商", md.get供货商() );
		j.put("备注", md.get备注() );
		j.put("审核人", md.get审核人() );
		j.put("审核时间", md.get审核时间() );
		j.put("报账日期", md.get报账日期() );
		j.put("对账人", md.get对账人() );
		j.put("财务凭单号", md.get财务凭单号() );
		j.put("财务分类", md.get财务分类() );
		j.put("图片1", md.get图片1() );
		j.put("图片2", md.get图片2() );
		j.put("图片3", md.get图片3() );
		j.put("清查状态", md.get清查状态() );
		j.put("清查日期", md.get清查日期() );
	
			j.put("盘亏盘盈原因", md.get盘亏盘盈原因() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return j;
	}

	
}
