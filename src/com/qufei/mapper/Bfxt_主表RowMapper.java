package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Bfxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Bfxt_����RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Bfxt_����Model bm=null;
		try {
			bm=new Bfxt_����Model(
					rs.getString("���յ����"),
					rs.getString("�ʲ����"),
					rs.getString("�ʲ�����"),
					rs.getString("�����"),
					rs.getString("�ͺ�"),
					rs.getString("���"),
					rs.getString("��������"),
					rs.getString("�ʲ���Դ"),
					rs.getString("������Դ"),
					rs.getString("���ʷ����"),
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
					rs.getString("�۾�״̬"),
					rs.getString("�۾ɷ���"),
					rs.getString("�۾�ʱ��"),
					rs.getString("�����۾�����"),
					rs.getString("���۾���"),
					rs.getString("���۾ɶ�"),
					rs.getString("��ֵ׼��"),
					rs.getString("��ֵ��"),
					rs.getString("�ۼ��۾�"),
					rs.getString("��ֵ"),
					rs.getString("ȡ�÷�ʽ"),
					rs.getString("�ɹ����"),
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
					rs.getString("�̿���ӯԭ��")
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
		Bfxt_����Model mdd=(Bfxt_����Model)md;
		
		map.put("���յ����",mdd.get���յ����()==null?"":mdd.get���յ����());
		map.put("�ʲ����",mdd.get�ʲ����()==null?"":mdd.get�ʲ����());
		map.put("�ʲ�����",mdd.get�ʲ�����()==null?"":mdd.get�ʲ�����());
		map.put("�����",mdd.get�����()==null?"":mdd.get�����());
		map.put("�ͺ�",mdd.get�ͺ�()==null?"":mdd.get�ͺ�());
		map.put("���",mdd.get���()==null?"":mdd.get���());
		map.put("��������",mdd.get��������()==null?"":mdd.get��������());
		map.put("�ʲ���Դ",mdd.get�ʲ���Դ()==null?"":mdd.get�ʲ���Դ());
		map.put("������Դ",mdd.get������Դ()==null?"":mdd.get������Դ());
		map.put("���ʷ����",mdd.get���ʷ����()==null?"":mdd.get���ʷ����());
		map.put("����",mdd.get����()==null?"":mdd.get����());
		map.put("����",mdd.get����()==null?"":mdd.get����());
		map.put("�����ܶ�",mdd.get�����ܶ�()==null?"":mdd.get�����ܶ�());
		map.put("����������",mdd.get����������()==null?"":mdd.get����������());
		map.put("�ܽ��",mdd.get�ܽ��()==null?"":mdd.get�ܽ��());
		map.put("����",mdd.get����()==null?"":mdd.get����());
		map.put("�������",mdd.get�������()==null?"":mdd.get�������());
		map.put("��������",mdd.get��������()==null?"":mdd.get��������());
		map.put("��������",mdd.get��������()==null?"":mdd.get��������());
		map.put("������",mdd.get������()==null?"":mdd.get������());
		map.put("��������",mdd.get��������()==null?"":mdd.get��������());
		map.put("��״",mdd.get��״()==null?"":mdd.get��״());
		map.put("ʹ�÷���",mdd.getʹ�÷���()==null?"":mdd.getʹ�÷���());
		map.put("����λ",mdd.get����λ()==null?"":mdd.get����λ());
		map.put("ʹ�õ�λ",mdd.getʹ�õ�λ()==null?"":mdd.getʹ�õ�λ());
		map.put("����",mdd.get����()==null?"":mdd.get����());
		map.put("�ʲ�ԭֵ",mdd.get�ʲ�ԭֵ()==null?"":mdd.get�ʲ�ԭֵ());
		map.put("�ʲ�ԭ����",mdd.get�ʲ�ԭ����()==null?"":mdd.get�ʲ�ԭ����());
		map.put("�ʲ�ԭ�ܽ��",mdd.get�ʲ�ԭ�ܽ��()==null?"":mdd.get�ʲ�ԭ�ܽ��());
		map.put("����ԭ�ܶ�",mdd.get����ԭ�ܶ�()==null?"":mdd.get����ԭ�ܶ�());
		map.put("����ԭ������",mdd.get����ԭ������()==null?"":mdd.get����ԭ������());
		map.put("¼����",mdd.get¼����()==null?"":mdd.get¼����());
		map.put("¼��ʱ��",mdd.get¼��ʱ��()==null?"":mdd.get¼��ʱ��());
		map.put("������",mdd.get������()==null?"":mdd.get������());
		map.put("��ŵص�",mdd.get��ŵص�()==null?"":mdd.get��ŵص�());
		map.put("ʹ������",mdd.getʹ������()==null?"":mdd.getʹ������());
		map.put("�۾�״̬",mdd.get�۾�״̬()==null?"":mdd.get�۾�״̬());
		map.put("�۾ɷ���",mdd.get�۾ɷ���()==null?"":mdd.get�۾ɷ���());
		map.put("�۾�ʱ��",mdd.get�۾�ʱ��()==null?"":mdd.get�۾�ʱ��());
		map.put("�����۾�����",mdd.get�����۾�����()==null?"":mdd.get�����۾�����());
		map.put("���۾���",mdd.get���۾���()==null?"":mdd.get���۾���());
		map.put("���۾ɶ�",mdd.get���۾ɶ�()==null?"":mdd.get���۾ɶ�());
		map.put("��ֵ׼��",mdd.get��ֵ׼��()==null?"":mdd.get��ֵ׼��());
		map.put("��ֵ��",mdd.get��ֵ��()==null?"":mdd.get��ֵ��());
		map.put("�ۼ��۾�",mdd.get�ۼ��۾�()==null?"":mdd.get�ۼ��۾�());
		map.put("��ֵ",mdd.get��ֵ()==null?"":mdd.get��ֵ());
		map.put("ȡ�÷�ʽ",mdd.getȡ�÷�ʽ()==null?"":mdd.getȡ�÷�ʽ());
		map.put("�ɹ����",mdd.get�ɹ����()==null?"":mdd.get�ɹ����());
		map.put("��ע",mdd.get��ע()==null?"":mdd.get��ע());
		map.put("�����",mdd.get�����()==null?"":mdd.get�����());
		map.put("���ʱ��",mdd.get���ʱ��()==null?"":mdd.get���ʱ��());
		map.put("��������",mdd.get��������()==null?"":mdd.get��������());
		map.put("������",mdd.get������()==null?"":mdd.get������());
		map.put("����ƾ����",mdd.get����ƾ����()==null?"":mdd.get����ƾ����());
		map.put("�������",mdd.get�������()==null?"":mdd.get�������());
		map.put("ͼƬ1",mdd.getͼƬ1()==null?"":mdd.getͼƬ1());
		map.put("ͼƬ2",mdd.getͼƬ2()==null?"":mdd.getͼƬ2());
		map.put("ͼƬ3",mdd.getͼƬ3()==null?"":mdd.getͼƬ3());
		map.put("���״̬",mdd.get���״̬()==null?"":mdd.get���״̬());
		map.put("�������",mdd.get�������()==null?"":mdd.get�������());
		map.put("�̿���ӯԭ��",mdd.get�̿���ӯԭ��()==null?"":mdd.get�̿���ӯԭ��());
		return map;
	}



}
