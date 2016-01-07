package com.qufei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.qufei.model.Sbxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Sbxt_����RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(org.json.JSONObject rs) {
		// TODO Auto-generated method stub
		Sbxt_����Model pd=null;

		try {
			pd=new Sbxt_����Model(
					rs.getString("���յ����"),
					rs.getString("�ʲ����"),
					rs.getString("�ʲ�����"),
					rs.getString("Ӣ������"),
					rs.getString("�����"),
					rs.getString("�ͺ�"),
					
					rs.getString("���"),
					rs.getString("��������"),
					rs.getString("�ʲ���Դ"),
					rs.getString("������Դ"),
					rs.getString("���ʷ����"),
					rs.getString("���ʴ���"),
					rs.getString("����"),
					rs.getString("����"),
					rs.getString("�����ܶ�"),
					rs.getString("����������"),
					rs.getString("�ܽ��"),
					rs.getString("����"),
					rs.getString("�������"),
					rs.getString("��������"),
					rs.getString("��������"),
					rs.getString("������"),
					rs.getString("��������"),
					rs.getString("��״"),
					rs.getString("ʹ�÷���"),
					rs.getString("����λ"),
					rs.getString("ʹ�õ�λ"),
					rs.getString("����"),
					rs.getString("�ʲ�ԭֵ"),
					rs.getString("�ʲ�ԭ����"),
					rs.getString("�ʲ�ԭ�ܽ��"),
					rs.getString("����ԭ�ܶ�"),
					rs.getString("����ԭ������"),
					rs.getString("¼����"),
					rs.getString("¼��ʱ��"),
					rs.getString("������"),
					rs.getString("��ŵص�"),
					rs.getString("ʹ������"),
					rs.getString("����ָ��"),
					rs.getString("��;��Ӧ�÷�Χ"),
					rs.getString("�豸����"),
					rs.getString("�ɹ����"),
					rs.getString("��Ʊ��"),
					rs.getString("������"),
					rs.getString("��ע"),
					rs.getString("�����"),
					rs.getString("���ʱ��"),
					rs.getString("��������"),
					rs.getString("������"),
					rs.getString("����ƾ����"),
					rs.getString("�������"),
					rs.getString("ͼƬ1"),
					rs.getString("ͼƬ2"),
					rs.getString("ͼƬ3"),
					rs.getString("���״̬"),
					rs.getString("�������"),
					rs.getString("�̿���ӯԭ��"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pd;

	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stubmap.put("���յ����",mdd.get���յ����());
		Sbxt_����Model mdd=(Sbxt_����Model)md;
		Map<String,String> map=new HashMap<String, String>();
		map.put("�ʲ����",mdd.get�ʲ����() );
		map.put("�ʲ�����",mdd.get�ʲ�����() );
		map.put("Ӣ������",mdd.getӢ������() );
		map.put("�����",mdd.get�����() );
		map.put("�ͺ�",mdd.get�ͺ�() );
		map.put("���",mdd.get���() );
		map.put("��������",mdd.get��������() );
		map.put("�ʲ���Դ",mdd.get�ʲ���Դ() );
		map.put("������Դ",mdd.get������Դ() );
		map.put("���ʷ����",mdd.get���ʷ����() );
		map.put("���ʴ���",mdd.get���ʴ���() );
		map.put("����",mdd.get����() );
		map.put("����",mdd.get����() );
		map.put("�����ܶ�",mdd.get�����ܶ�() );
		map.put("����������",mdd.get����������());
		map.put("�ܽ��",mdd.get�ܽ��() );
		map.put("����",mdd.get����() );
		map.put("�������",mdd.get�������() );
		map.put("��������",mdd.get��������() );
		map.put("��������",mdd.get��������() );
		map.put("������",mdd.get������() );
		map.put("��������",mdd.get��������() );
		map.put("��״",mdd.get��״() );
		map.put("ʹ�÷���",mdd.getʹ�÷���() );
		map.put("����λ",mdd.get����λ() );
		map.put("ʹ�õ�λ",mdd.getʹ�õ�λ() );
		map.put("����",mdd.get����() );
		map.put("�ʲ�ԭֵ",mdd.get�ʲ�ԭֵ() );
		map.put("�ʲ�ԭ����",mdd.get�ʲ�ԭ����());
		map.put("�ʲ�ԭ�ܽ��",mdd.get�ʲ�ԭ�ܽ��() );
		map.put("����ԭ�ܶ�",mdd.get����ԭ�ܶ�() );
		map.put("����ԭ������",mdd.get����ԭ������() );
		map.put("¼����",mdd.get¼����() );
		map.put("¼��ʱ��",mdd.get¼��ʱ��() );
		map.put("������",mdd.get������() );
		map.put("��ŵص�",mdd.get��ŵص�() );
		map.put("ʹ������",mdd.getʹ������() );
		map.put("����ָ��",mdd.get����ָ��() );
		map.put("��;��Ӧ�÷�Χ",mdd.get��;��Ӧ�÷�Χ() );
		map.put("�豸����",mdd.get�豸����() );
		map.put("�ɹ����",mdd.get�ɹ����() );
		map.put("��Ʊ��",mdd.get��Ʊ��() );
		map.put("������",mdd.get������() );
		map.put("��ע",mdd.get��ע() );
		map.put("�����",mdd.get�����() );
		map.put("���ʱ��",mdd.get���ʱ��() );
		map.put("��������",mdd.get��������() );
		map.put("������",mdd.get������() );
		map.put("����ƾ����",mdd.get����ƾ����() );
		map.put("�������",mdd.get�������() );
		map.put("ͼƬ1",mdd.getͼƬ1() );
		map.put("ͼƬ2",mdd.getͼƬ2() );
		map.put("ͼƬ3",mdd.getͼƬ3());
		map.put("���״̬",mdd.get���״̬() );
		map.put("�������",mdd.get�������() );
		map.put("�̿���ӯԭ��",mdd.get�̿���ӯԭ��() );
		return map;
	}



}
