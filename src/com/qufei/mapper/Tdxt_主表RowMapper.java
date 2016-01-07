package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.qufei.model.Tdxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Tdxt_����RowMapper implements IRowMapper{

	

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Tdxt_����Model m=null;
		try {
			m=new Tdxt_����Model(
					rs.getString("���յ����"),
					rs.getString("�ʲ����"),
					rs.getString("�ʲ�����"),
					rs.getString("�����"),
					rs.getString("���ʷ����"),
					rs.getString("ƾ֤��"),
					rs.getString("���ص�ַ"),
					rs.getString("������;"),
					rs.getString("�滮��;"),
					rs.getString("ʹ��Ȩ״��"),
					rs.getString("ʹ��Ȩ����"),
					rs.getString("����֤�������"),
					rs.getString("����֤���"),
					rs.getString("����ʹ����"),
					rs.getString("����֤�ֺ�"),
					rs.getString("�غ�"),
					rs.getString("����֤������;"),
					rs.getString("ʹ�õȼ�"),
					rs.getString("����֤ʹ��Ȩ����"),
					rs.getString("�����"),
					rs.getString("����֤��֤����"),
					rs.getString("������Դ"),
					rs.getString("��������"),
					rs.getString("ʹ��Ȩ���"),
					rs.getString("����"),
					rs.getString("����"),
					rs.getString("�����ܶ�"),
					rs.getString("����������"),
					rs.getString("�ܽ��"),
					rs.getString("��״"),
					rs.getString("ʹ�÷���"),
					rs.getString("ʹ�õ�λ"),
					rs.getString("�ʲ�ԭֵ"),
					rs.getString("�ʲ�ԭ����"),
					rs.getString("�ʲ�ԭ�ܽ��"),
					rs.getString("����ԭ�ܶ�"),
					rs.getString("����ԭ������"),
					rs.getString("¼����"),
					rs.getString("¼��ʱ��"),
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
		Tdxt_����Model mdd=(Tdxt_����Model)md;
		try {
			j.put("���յ����",mdd.get���յ����());
			j.put("�ʲ����",mdd.get�ʲ����());
			j.put("�ʲ�����",mdd.get�ʲ�����());
			j.put("�����",mdd.get�����());
			j.put("���ʷ����",mdd.get���ʷ����());
			j.put("ƾ֤��",mdd.getƾ֤��());
			j.put("���ص�ַ",mdd.get���ص�ַ());
			j.put("������;",mdd.get������;());
			j.put("�滮��;",mdd.get�滮��;());
			j.put("ʹ��Ȩ״��",mdd.getʹ��Ȩ״��());
			j.put("ʹ��Ȩ����",mdd.getʹ��Ȩ����());
			j.put("����֤�������",mdd.get����֤�������());
			j.put("����֤���",mdd.get����֤���());
			j.put("����ʹ����",mdd.get����ʹ����());
			j.put("����֤�ֺ�",mdd.get����֤�ֺ�());
			j.put("�غ�",mdd.get�غ�());
			j.put("����֤������;",mdd.get����֤������;());
			j.put("ʹ�õȼ�",mdd.getʹ�õȼ�());
			j.put("����֤ʹ��Ȩ����",mdd.get����֤ʹ��Ȩ����());
			j.put("�����",mdd.get�����());
			j.put("����֤��֤����",mdd.get����֤��֤����());
			j.put("������Դ",mdd.get������Դ());
			j.put("��������",mdd.get��������());
			j.put("ʹ��Ȩ���",mdd.getʹ��Ȩ���());
			j.put("����",mdd.get����());
			j.put("����",mdd.get����());
			j.put("�����ܶ�",mdd.get�����ܶ�());
			j.put("����������",mdd.get����������());
			j.put("�ܽ��",mdd.get�ܽ��());
			j.put("��״",mdd.get��״());
			j.put("ʹ�÷���",mdd.getʹ�÷���());
			j.put("ʹ�õ�λ",mdd.getʹ�õ�λ());
			j.put("�ʲ�ԭֵ",mdd.get�ʲ�ԭֵ());
			j.put("�ʲ�ԭ����",mdd.get�ʲ�ԭ����());
			j.put("�ʲ�ԭ�ܽ��",mdd.get�ʲ�ԭ�ܽ��());
			j.put("����ԭ�ܶ�",mdd.get����ԭ�ܶ�());
			j.put("����ԭ������",mdd.get����ԭ������());
			j.put("¼����",mdd.get¼����());
			j.put("¼��ʱ��",mdd.get¼��ʱ��());
			j.put("ʹ������",mdd.getʹ������());
			j.put("�۾�״̬",mdd.get�۾�״̬());
			j.put("�۾ɷ���",mdd.get�۾ɷ���());
			j.put("�۾�ʱ��",mdd.get�۾�ʱ��());
			j.put("�����۾�����",mdd.get�����۾�����());
			j.put("���۾���",mdd.get���۾���());
			j.put("���۾ɶ�",mdd.get���۾ɶ�());
			j.put("��ֵ׼��",mdd.get��ֵ׼��());
			j.put("��ֵ��",mdd.get��ֵ��());
			j.put("�ۼ��۾�",mdd.get�ۼ��۾�());
			j.put("��ֵ",mdd.get��ֵ());
			j.put("��ע",mdd.get��ע());
			j.put("�����",mdd.get�����());
			j.put("���ʱ��",mdd.get���ʱ��());
			j.put("��������",mdd.get��������());
			j.put("������",mdd.get������());
			j.put("����ƾ����",mdd.get����ƾ����());
			j.put("�������",mdd.get�������());
			j.put("ͼƬ1",mdd.getͼƬ1());
			j.put("ͼƬ2",mdd.getͼƬ2());
			j.put("ͼƬ3",mdd.getͼƬ3());
			j.put("���״̬",mdd.get���״̬());
			j.put("�������",mdd.get�������());
			j.put("�̿���ӯԭ��",mdd.get�̿���ӯԭ��());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return j;
	}

}
