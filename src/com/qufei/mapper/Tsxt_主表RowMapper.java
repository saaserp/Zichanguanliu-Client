package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Tsxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Tsxt_����RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Tsxt_����Model m=null;
		try {
			m=new Tsxt_����Model(
					jsobj.getString("���յ����"),
					jsobj.getString("�ʲ����"),
					jsobj.getString("�ʲ�����"),
					jsobj.getString("�����"),
					jsobj.getString("��������"),
					jsobj.getString("�ʲ���Դ"),
					jsobj.getString("������Դ"),
					jsobj.getString("���ʷ����"),
					jsobj.getString("����"),
					jsobj.getString("����"),
					jsobj.getString("�����ܶ�"),
					jsobj.getString("����������"),
					jsobj.getString("�ܽ��"),
					jsobj.getString("ͼ���"),
					jsobj.getString("��������"),
					jsobj.getString("������"),
					jsobj.getString("�ɹ���"),
					jsobj.getString("��״"),
					jsobj.getString("ʹ�÷���"),
					jsobj.getString("����λ"),
					jsobj.getString("ʹ�õ�λ"),
					jsobj.getString("�ʲ�ԭֵ"),
					jsobj.getString("�ʲ�ԭ����"),
					jsobj.getString("�ʲ�ԭ�ܽ��"),
					jsobj.getString("����ԭ�ܶ�"),
					jsobj.getString("����ԭ������"),
					jsobj.getString("¼����"),
					jsobj.getString("¼��ʱ��"),
					jsobj.getString("������"),
					jsobj.getString("��ŵص�"),
					jsobj.getString("ʹ������"),
					jsobj.getString("�۾�״̬"),
					jsobj.getString("�۾ɷ���"),
					jsobj.getString("�۾�ʱ��"),
					jsobj.getString("�����۾�����"),
					jsobj.getString("���۾���"),
					jsobj.getString("���۾ɶ�"),
					jsobj.getString("��ֵ׼��"),
					jsobj.getString("��ֵ��"),
					jsobj.getString("�ۼ��۾�"),
					jsobj.getString("��ֵ"),
					jsobj.getString("��Ʊ��"),
					jsobj.getString("������"),
					jsobj.getString("��ע"),
					jsobj.getString("�����"),
					jsobj.getString("���ʱ��"),
					jsobj.getString("��������"),
					jsobj.getString("������"),
					jsobj.getString("����ƾ����"),
					jsobj.getString("�������"),
					jsobj.getString("ͼƬ1"),
					jsobj.getString("ͼƬ2"),
					jsobj.getString("ͼƬ3"),
					jsobj.getString("���״̬"),
					jsobj.getString("�������"),
					jsobj.getString("�̿���ӯԭ��")
					
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
		Tsxt_����Model d=(Tsxt_����Model) md;
		map.put("���յ����",d.get���յ����()==null?"":d.get���յ����());
		map.put("�ʲ����",d.get�ʲ����()==null?"":d.get�ʲ����());
		map.put("�ʲ�����",d.get�ʲ�����()==null?"":d.get�ʲ�����());
		map.put("�����",d.get�����()==null?"":d.get�����());
		map.put("��������",d.get��������()==null?"":d.get��������());
		map.put("�ʲ���Դ",d.get�ʲ���Դ()==null?"":d.get�ʲ���Դ());
		map.put("������Դ",d.get������Դ()==null?"":d.get������Դ());
		map.put("���ʷ����",d.get���ʷ����()==null?"":d.get���ʷ����());
		map.put("����",d.get����()==null?"":d.get����());
		map.put("����",d.get����()==null?"":d.get����());
		map.put("�����ܶ�",d.get�����ܶ�()==null?"":d.get�����ܶ�());
		map.put("����������",d.get����������()==null?"":d.get����������());
		map.put("�ܽ��",d.get�ܽ��()==null?"":d.get�ܽ��());
		map.put("ͼ���",d.getͼ���()==null?"":d.getͼ���());
		map.put("��������",d.get��������()==null?"":d.get��������());
		map.put("������",d.get������()==null?"":d.get������());
		map.put("�ɹ���",d.get�ɹ���()==null?"":d.get�ɹ���());
		map.put("��״",d.get��״()==null?"":d.get��״());
		map.put("ʹ�÷���",d.getʹ�÷���()==null?"":d.getʹ�÷���());
		map.put("����λ",d.get����λ()==null?"":d.get����λ());
		map.put("ʹ�õ�λ",d.getʹ�õ�λ()==null?"":d.getʹ�õ�λ());
		map.put("�ʲ�ԭֵ",d.get�ʲ�ԭֵ()==null?"":d.get�ʲ�ԭֵ());
		map.put("�ʲ�ԭ����",d.get�ʲ�ԭ����()==null?"":d.get�ʲ�ԭ����());
		map.put("�ʲ�ԭ�ܽ��",d.get�ʲ�ԭ�ܽ��()==null?"":d.get�ʲ�ԭ�ܽ��());
		map.put("����ԭ�ܶ�",d.get����ԭ�ܶ�()==null?"":d.get����ԭ�ܶ�());
		map.put("����ԭ������",d.get����ԭ������()==null?"":d.get����ԭ������());
		map.put("¼����",d.get¼����()==null?"":d.get¼����());
		map.put("¼��ʱ��",d.get¼��ʱ��()==null?"":d.get¼��ʱ��());
		map.put("������",d.get������()==null?"":d.get������());
		map.put("��ŵص�",d.get��ŵص�()==null?"":d.get��ŵص�());
		map.put("ʹ������",d.getʹ������()==null?"":d.getʹ������());
		map.put("�۾�״̬",d.get�۾�״̬()==null?"":d.get�۾�״̬());
		map.put("�۾ɷ���",d.get�۾ɷ���()==null?"":d.get�۾ɷ���());
		map.put("�۾�ʱ��",d.get�۾�ʱ��()==null?"":d.get�۾�ʱ��());
		map.put("�����۾�����",d.get�����۾�����()==null?"":d.get�����۾�����());
		map.put("���۾���",d.get���۾���()==null?"":d.get���۾���());
		map.put("���۾ɶ�",d.get���۾ɶ�()==null?"":d.get���۾ɶ�());
		map.put("��ֵ׼��",d.get��ֵ׼��()==null?"":d.get��ֵ׼��());
		map.put("��ֵ��",d.get��ֵ��()==null?"":d.get��ֵ��());
		map.put("�ۼ��۾�",d.get�ۼ��۾�()==null?"":d.get�ۼ��۾�());
		map.put("��ֵ",d.get��ֵ()==null?"":d.get��ֵ());
		map.put("��Ʊ��",d.get��Ʊ��()==null?"":d.get��Ʊ��());
		map.put("������",d.get������()==null?"":d.get������());
		map.put("��ע",d.get��ע()==null?"":d.get��ע());
		map.put("�����",d.get�����()==null?"":d.get�����());
		map.put("���ʱ��",d.get���ʱ��()==null?"":d.get���ʱ��());
		map.put("��������",d.get��������()==null?"":d.get��������());
		map.put("������",d.get������()==null?"":d.get������());
		map.put("����ƾ����",d.get����ƾ����()==null?"":d.get����ƾ����());
		map.put("�������",d.get�������()==null?"":d.get�������());
		map.put("ͼƬ1",d.getͼƬ1()==null?"":d.getͼƬ1());
		map.put("ͼƬ2",d.getͼƬ2()==null?"":d.getͼƬ2());
		map.put("ͼƬ3",d.getͼƬ3()==null?"":d.getͼƬ3());
		map.put("���״̬",d.get���״̬()==null?"":d.get���״̬());
		map.put("�������",d.get�������()==null?"":d.get�������());
		map.put("�̿���ӯԭ��",d.get�̿���ӯԭ��()==null?"":d.get�̿���ӯԭ��());
		return map;
	}

	

}
