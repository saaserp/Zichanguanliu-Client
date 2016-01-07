package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Clxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Clxt_����RowMapper implements IRowMapper{


	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Clxt_����Model m = null;
		try {
			m=new Clxt_����Model(
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
					rs.getString("���ܺ�"),
					rs.getString("������ɫ"),
					rs.getString("������ɫ"),
					rs.getString("��������"),
					rs.getString("��������"),
					rs.getString("��������"),
					rs.getString("Ʒ���ͺ�"),
					rs.getString("��������"),
					rs.getString("������"),
					rs.getString("��λ��"),
					rs.getString("������;"),
					rs.getString("�ɹ���ʽ"),
					rs.getString("���ƺ�"),
					rs.getString("ȼ������"),
					rs.getString("������"),
					rs.getString("��������"),
					rs.getString("��״"),
					rs.getString("ʹ�÷���"),
					rs.getString("����λ"),
					rs.getString("ʹ�õ�λ"),
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
		return m;
	}


	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>j=new HashMap<String, String>();
		Clxt_����Model d=(Clxt_����Model) md;
		j.put("���յ����",d.get���յ����()==null?"":d.get���յ����());
		j.put("�ʲ����",d.get�ʲ����()==null?"":d.get�ʲ����());
		j.put("�ʲ�����",d.get�ʲ�����()==null?"":d.get�ʲ�����());
		j.put("�����",d.get�����()==null?"":d.get�����());
		j.put("�ͺ�",d.get�ͺ�()==null?"":d.get�ͺ�());
		j.put("���",d.get���()==null?"":d.get���());
		j.put("��������",d.get��������()==null?"":d.get��������());
		j.put("�ʲ���Դ",d.get�ʲ���Դ()==null?"":d.get�ʲ���Դ());
		j.put("������Դ",d.get������Դ()==null?"":d.get������Դ());
		j.put("���ʷ����",d.get���ʷ����()==null?"":d.get���ʷ����());
		j.put("����",d.get����()==null?"":d.get����());
		j.put("����",d.get����()==null?"":d.get����());
		j.put("�����ܶ�",d.get�����ܶ�()==null?"":d.get�����ܶ�());
		j.put("����������",d.get����������()==null?"":d.get����������());
		j.put("�ܽ��",d.get�ܽ��()==null?"":d.get�ܽ��());
		j.put("����",d.get����()==null?"":d.get����());
		j.put("���ܺ�",d.get���ܺ�()==null?"":d.get���ܺ�());
		j.put("������ɫ",d.get������ɫ()==null?"":d.get������ɫ());
		j.put("������ɫ",d.get������ɫ()==null?"":d.get������ɫ());
		j.put("��������",d.get��������()==null?"":d.get��������());
		j.put("��������",d.get��������()==null?"":d.get��������());
		j.put("��������",d.get��������()==null?"":d.get��������());
		j.put("Ʒ���ͺ�",d.getƷ���ͺ�()==null?"":d.getƷ���ͺ�());
		j.put("��������",d.get��������()==null?"":d.get��������());
		j.put("������",d.get������()==null?"":d.get������());
		j.put("��λ��",d.get��λ��()==null?"":d.get��λ��());
		j.put("������;",d.get������;()==null?"":d.get������;());
		j.put("�ɹ���ʽ",d.get�ɹ���ʽ()==null?"":d.get�ɹ���ʽ());
		j.put("���ƺ�",d.get���ƺ�()==null?"":d.get���ƺ�());
		j.put("ȼ������",d.getȼ������()==null?"":d.getȼ������());
		j.put("������",d.get������()==null?"":d.get������());
		j.put("��������",d.get��������()==null?"":d.get��������());
		j.put("��״",d.get��״()==null?"":d.get��״());
		j.put("ʹ�÷���",d.getʹ�÷���()==null?"":d.getʹ�÷���());
		j.put("����λ",d.get����λ()==null?"":d.get����λ());
		j.put("ʹ�õ�λ",d.getʹ�õ�λ()==null?"":d.getʹ�õ�λ());
		j.put("�ʲ�ԭֵ",d.get�ʲ�ԭֵ()==null?"":d.get�ʲ�ԭֵ());
		j.put("�ʲ�ԭ����",d.get�ʲ�ԭ����()==null?"":d.get�ʲ�ԭ����());
		j.put("�ʲ�ԭ�ܽ��",d.get�ʲ�ԭ�ܽ��()==null?"":d.get�ʲ�ԭ�ܽ��());
		j.put("����ԭ�ܶ�",d.get����ԭ�ܶ�()==null?"":d.get����ԭ�ܶ�());
		j.put("����ԭ������",d.get����ԭ������()==null?"":d.get����ԭ������());
		j.put("¼����",d.get¼����()==null?"":d.get¼����());
		j.put("¼��ʱ��",d.get¼��ʱ��()==null?"":d.get¼��ʱ��());
		j.put("������",d.get������()==null?"":d.get������());
		j.put("��ŵص�",d.get��ŵص�()==null?"":d.get��ŵص�());
		j.put("ʹ������",d.getʹ������()==null?"":d.getʹ������());
		j.put("�۾�״̬",d.get�۾�״̬()==null?"":d.get�۾�״̬());
		j.put("�۾ɷ���",d.get�۾ɷ���()==null?"":d.get�۾ɷ���());
		j.put("�۾�ʱ��",d.get�۾�ʱ��()==null?"":d.get�۾�ʱ��());
		j.put("�����۾�����",d.get�����۾�����()==null?"":d.get�����۾�����());
		j.put("���۾���",d.get���۾���()==null?"":d.get���۾���());
		j.put("���۾ɶ�",d.get���۾ɶ�()==null?"":d.get���۾ɶ�());
		j.put("��ֵ׼��",d.get��ֵ׼��()==null?"":d.get��ֵ׼��());
		j.put("��ֵ��",d.get��ֵ��()==null?"":d.get��ֵ��());
		j.put("�ۼ��۾�",d.get�ۼ��۾�()==null?"":d.get�ۼ��۾�());
		j.put("��ֵ",d.get��ֵ()==null?"":d.get��ֵ());
		j.put("��ע",d.get��ע()==null?"":d.get��ע());
		j.put("�����",d.get�����()==null?"":d.get�����());
		j.put("���ʱ��",d.get���ʱ��()==null?"":d.get���ʱ��());
		j.put("��������",d.get��������()==null?"":d.get��������());
		j.put("������",d.get������()==null?"":d.get������());
		j.put("����ƾ����",d.get����ƾ����()==null?"":d.get����ƾ����());
		j.put("�������",d.get�������()==null?"":d.get�������());
		j.put("ͼƬ1",d.getͼƬ1()==null?"":d.getͼƬ1());
		j.put("ͼƬ2",d.getͼƬ2()==null?"":d.getͼƬ2());
		j.put("ͼƬ3",d.getͼƬ3()==null?"":d.getͼƬ3());
		j.put("���״̬",d.get���״̬()==null?"":d.get���״̬());
		j.put("�������",d.get�������()==null?"":d.get�������());
		j.put("�̿���ӯԭ��",d.get�̿���ӯԭ��()==null?"":d.get�̿���ӯԭ��());
		return j;
	}

}
