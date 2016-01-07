package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Dzxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Dzxt_����RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Dzxt_����Model m = null;
		try {
			m = new Dzxt_����Model(
					rs.getString("���յ����"),
					rs.getString("�ʲ����"),
					rs.getString("�ʲ�����"),
					rs.getString("�����"),
					rs.getString("���ʷ����"),
					rs.getString("���ʴ���"),
					rs.getString("�ɹ����"),
					rs.getString("�ͺ�"),
					rs.getString("���"),
					rs.getString("�ʲ���Դ"),
					rs.getString("������Դ"),
					rs.getString("��������"),
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
		Dzxt_����Model m=(Dzxt_����Model)md;
		j.put("���յ����",m.get���յ����()==null?"":m.get���յ����());
		j.put("�ʲ����",m.get�ʲ����()==null?"":m.get�ʲ����());
		j.put("�ʲ�����",m.get�ʲ�����()==null?"":m.get�ʲ�����());
		j.put("�����",m.get�����()==null?"":m.get�����());
		j.put("���ʷ����",m.get���ʷ����()==null?"":m.get���ʷ����());
		j.put("���ʴ���",m.get���ʴ���()==null?"":m.get���ʴ���());
		j.put("�ɹ����",m.get�ɹ����()==null?"":m.get�ɹ����());
		j.put("�ͺ�",m.get�ͺ�()==null?"":m.get�ͺ�());
		j.put("���",m.get���()==null?"":m.get���());
		j.put("�ʲ���Դ",m.get�ʲ���Դ()==null?"":m.get�ʲ���Դ());
		j.put("������Դ",m.get������Դ()==null?"":m.get������Դ());
		j.put("��������",m.get��������()==null?"":m.get��������());
		j.put("����",m.get����()==null?"":m.get����());
		j.put("����",m.get����()==null?"":m.get����());
		j.put("�����ܶ�",m.get�����ܶ�()==null?"":m.get�����ܶ�());
		j.put("����������",m.get����������()==null?"":m.get����������());
		j.put("�ܽ��",m.get�ܽ��()==null?"":m.get�ܽ��());
		j.put("����",m.get����()==null?"":m.get����());
		j.put("�������",m.get�������()==null?"":m.get�������());
		j.put("��������",m.get��������()==null?"":m.get��������());
		j.put("��������",m.get��������()==null?"":m.get��������());
		j.put("������",m.get������()==null?"":m.get������());
		j.put("��������",m.get��������()==null?"":m.get��������());
		j.put("��״",m.get��״()==null?"":m.get��״());
		j.put("ʹ�÷���",m.getʹ�÷���()==null?"":m.getʹ�÷���());
		j.put("����λ",m.get����λ()==null?"":m.get����λ());
		j.put("ʹ�õ�λ",m.getʹ�õ�λ()==null?"":m.getʹ�õ�λ());
		j.put("����",m.get����()==null?"":m.get����());
		j.put("�ʲ�ԭֵ",m.get�ʲ�ԭֵ()==null?"":m.get�ʲ�ԭֵ());
		j.put("�ʲ�ԭ����",m.get�ʲ�ԭ����()==null?"":m.get�ʲ�ԭ����());
		j.put("�ʲ�ԭ�ܽ��",m.get�ʲ�ԭ�ܽ��()==null?"":m.get�ʲ�ԭ�ܽ��());
		j.put("����ԭ�ܶ�",m.get����ԭ�ܶ�()==null?"":m.get����ԭ�ܶ�());
		j.put("����ԭ������",m.get����ԭ������()==null?"":m.get����ԭ������());
		j.put("¼����",m.get¼����()==null?"":m.get¼����());
		j.put("¼��ʱ��",m.get¼��ʱ��()==null?"":m.get¼��ʱ��());
		j.put("������",m.get������()==null?"":m.get������());
		j.put("��ŵص�",m.get��ŵص�()==null?"":m.get��ŵص�());
		j.put("ʹ������",m.getʹ������()==null?"":m.getʹ������());
		j.put("�۾�״̬",m.get�۾�״̬()==null?"":m.get�۾�״̬());
		j.put("�۾ɷ���",m.get�۾ɷ���()==null?"":m.get�۾ɷ���());
		j.put("�۾�ʱ��",m.get�۾�ʱ��()==null?"":m.get�۾�ʱ��());
		j.put("�����۾�����",m.get�����۾�����()==null?"":m.get�����۾�����());
		j.put("���۾���",m.get���۾���()==null?"":m.get���۾���());
		j.put("���۾ɶ�",m.get���۾ɶ�()==null?"":m.get���۾ɶ�());
		j.put("��ֵ׼��",m.get��ֵ׼��()==null?"":m.get��ֵ׼��());
		j.put("��ֵ��",m.get��ֵ��()==null?"":m.get��ֵ��());
		j.put("�ۼ��۾�",m.get�ۼ��۾�()==null?"":m.get�ۼ��۾�());
		j.put("��ֵ",m.get��ֵ()==null?"":m.get��ֵ());
		j.put("������",m.get������()==null?"":m.get������());
		j.put("��ע",m.get��ע()==null?"":m.get��ע());
		j.put("�����",m.get�����()==null?"":m.get�����());
		j.put("���ʱ��",m.get���ʱ��()==null?"":m.get���ʱ��());
		j.put("��������",m.get��������()==null?"":m.get��������());
		j.put("������",m.get������()==null?"":m.get������());
		j.put("����ƾ����",m.get����ƾ����()==null?"":m.get����ƾ����());
		j.put("�������",m.get�������()==null?"":m.get�������());
		j.put("ͼƬ1",m.getͼƬ1()==null?"":m.getͼƬ1());
		j.put("ͼƬ2",m.getͼƬ2()==null?"":m.getͼƬ2());
		j.put("ͼƬ3",m.getͼƬ3()==null?"":m.getͼƬ3());
		j.put("���״̬",m.get���״̬()==null?"":m.get���״̬());
		j.put("�������",m.get�������()==null?"":m.get�������());
		return j;
	}

}
