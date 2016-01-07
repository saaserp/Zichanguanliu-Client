package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Fwxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Fwxt_����RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Fwxt_����Model m=null;
		try {
			m=new Fwxt_����Model(
					jsobj.getString("���յ����"),
					jsobj.getString("�ʲ����"),
					jsobj.getString("�ʲ�����"),
					jsobj.getString("�����"),
					jsobj.getString("���ʷ����"),
					jsobj.getString("ƾ֤��"),
					jsobj.getString("���ݵ�ַ"),
					jsobj.getString("���ϲ���"),
					jsobj.getString("���²���"),
					jsobj.getString("ʹ�����"),
					jsobj.getString("�������"),
					jsobj.getString("ռ�����"),
					jsobj.getString("������;"),
					jsobj.getString("��Ȩ״��"),
					jsobj.getString("���ݽṹ"),
					jsobj.getString("�滮��;"),
					jsobj.getString("��Ƶ�λ"),
					jsobj.getString("ʩ����λ"),
					jsobj.getString("��������"),
					jsobj.getString("�˷�����"),
					jsobj.getString("��Ȩ֤�������"),
					jsobj.getString("��Ȩ֤���"),
					jsobj.getString("��Ȩ֤�ֺ�"),
					jsobj.getString("��Ȩ֤��λ����"),
					jsobj.getString("��Ȩ֤�������"),
					jsobj.getString("��Ȩ֤������;"),
					jsobj.getString("������Դ"),
					jsobj.getString("����"),
					jsobj.getString("����"),
					jsobj.getString("�����ܶ�"),
					jsobj.getString("����������"),
					jsobj.getString("�ܽ��"),
					jsobj.getString("��״"),
					jsobj.getString("ʹ�÷���"),
					jsobj.getString("ʹ�õ�λ"),
					jsobj.getString("�ʲ�ԭֵ"),
					jsobj.getString("�ʲ�ԭ����"),
					jsobj.getString("�ʲ�ԭ�ܽ��"),
					jsobj.getString("����ԭ�ܶ�"),
					jsobj.getString("����ԭ������"),
					jsobj.getString("¼����"),
					jsobj.getString("¼��ʱ��"),
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
		Fwxt_����Model m=(Fwxt_����Model)md;
		map.put("���յ����",m.get���յ����());
		map.put("�ʲ����",m.get�ʲ����());
		map.put("�ʲ�����",m.get�ʲ�����());
		map.put("�����",m.get�����());
		map.put("���ʷ����",m.get���ʷ����());
		map.put("ƾ֤��",m.getƾ֤��());
		map.put("���ݵ�ַ",m.get���ݵ�ַ());
		map.put("���ϲ���",m.get���ϲ���());
		map.put("���²���",m.get���²���());
		map.put("ʹ�����",m.getʹ�����());
		map.put("�������",m.get�������());
		map.put("ռ�����",m.getռ�����());
		map.put("������;",m.get������;());
		map.put("��Ȩ״��",m.get��Ȩ״��());
		map.put("���ݽṹ",m.get���ݽṹ());
		map.put("�滮��;",m.get�滮��;());
		map.put("��Ƶ�λ",m.get��Ƶ�λ());
		map.put("ʩ����λ",m.getʩ����λ());
		map.put("��������",m.get��������());
		map.put("�˷�����",m.get�˷�����());
		map.put("��Ȩ֤�������",m.get��Ȩ֤�������());
		map.put("��Ȩ֤���",m.get��Ȩ֤���());
		map.put("��Ȩ֤�ֺ�",m.get��Ȩ֤�ֺ�());
		map.put("��Ȩ֤��λ����",m.get��Ȩ֤��λ����());
		map.put("��Ȩ֤�������",m.get��Ȩ֤�������());
		map.put("��Ȩ֤������;",m.get��Ȩ֤������;());
		map.put("������Դ",m.get������Դ());
		map.put("����",m.get����());
		map.put("����",m.get����());
		map.put("�����ܶ�",m.get�����ܶ�());
		map.put("����������",m.get����������());
		map.put("�ܽ��",m.get�ܽ��());
		map.put("��״",m.get��״());
		map.put("ʹ�÷���",m.getʹ�÷���());
		map.put("ʹ�õ�λ",m.getʹ�õ�λ());
		map.put("�ʲ�ԭֵ",m.get�ʲ�ԭֵ());
		map.put("�ʲ�ԭ����",m.get�ʲ�ԭ����());
		map.put("�ʲ�ԭ�ܽ��",m.get�ʲ�ԭ�ܽ��());
		map.put("����ԭ�ܶ�",m.get����ԭ�ܶ�());
		map.put("����ԭ������",m.get����ԭ������());
		map.put("¼����",m.get¼����());
		map.put("¼��ʱ��",m.get¼��ʱ��());
		map.put("ʹ������",m.getʹ������());
		map.put("�۾�״̬",m.get�۾�״̬());
		map.put("�۾ɷ���",m.get�۾ɷ���());
		map.put("�۾�ʱ��",m.get�۾�ʱ��());
		map.put("�����۾�����",m.get�����۾�����());
		map.put("���۾���",m.get���۾���());
		map.put("���۾ɶ�",m.get���۾ɶ�());
		map.put("��ֵ׼��",m.get��ֵ׼��());
		map.put("��ֵ��",m.get��ֵ��());
		map.put("�ۼ��۾�",m.get�ۼ��۾�());
		map.put("��ֵ",m.get��ֵ());
		map.put("��ע",m.get��ע());
		map.put("�����",m.get�����());
		map.put("���ʱ��",m.get���ʱ��());
		map.put("��������",m.get��������());
		map.put("������",m.get������());
		map.put("����ƾ����",m.get����ƾ����());
		map.put("�������",m.get�������());
		map.put("ͼƬ1",m.getͼƬ1());
		map.put("ͼƬ2",m.getͼƬ2());
		map.put("ͼƬ3",m.getͼƬ3());
		map.put("���״̬",m.get���״̬());
		map.put("�������",m.get�������());
		map.put("�̿���ӯԭ��",m.get�̿���ӯԭ��());
		return map;
	}



}
