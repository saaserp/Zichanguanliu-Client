package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.util.Log;

import com.qufei.model.Jjxt_����Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Jjxt_����RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject rs) {
		// TODO Auto-generated method stub
		Jjxt_����Model m=null;
		try {
			m=new Jjxt_����Model(
					rs.getString("���յ����"),
					rs.getString("�ʲ����"),
					rs.getString("�ʲ�����"),
					rs.getString("�����"),
					rs.getString("���ʷ����"),
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
		Jjxt_����Model md=(Jjxt_����Model)mdd;
		Map<String,String> j=new HashMap<String, String>();
		try {
		j.put("���յ����", md.get���յ����());
		j.put("�ʲ����", md.get�ʲ����() );
		j.put("�ʲ�����", md.get�ʲ�����() );
		j.put("�����", md.get�����() );
		j.put("���ʷ����", md.get���ʷ����() );
		j.put("�ɹ����", md.get�ɹ����() );
		j.put("�ͺ�", md.get�ͺ�() );
		j.put("���", md.get���() );
		j.put("�ʲ���Դ", md.get�ʲ���Դ() );
		j.put("������Դ", md.get������Դ() );
		j.put("��������", md.get��������() );
		j.put("����", md.get����() );
		j.put("����", md.get����() );
		j.put("�����ܶ�", md.get�����ܶ�() );
		j.put("����������", md.get����������() );
		j.put("�ܽ��", md.get�ܽ��() );
		j.put("����", md.get����() );
		j.put("�������", md.get�������() );
		j.put("��������", md.get��������() );
		j.put("��������", md.get��������() );
		j.put("������", md.get������() );
		j.put("��������", md.get��������() );
		j.put("��״", md.get��״() );
		j.put("ʹ�÷���", md.getʹ�÷���() );
		j.put("����λ", md.get����λ() );
		j.put("ʹ�õ�λ", md.getʹ�õ�λ() );
		j.put("����", md.get����() );
		j.put("�ʲ�ԭֵ", md.get�ʲ�ԭֵ() );
		j.put("�ʲ�ԭ����", md.get�ʲ�ԭ����() );
		j.put("�ʲ�ԭ�ܽ��", md.get�ʲ�ԭ�ܽ��() );
		j.put("����ԭ�ܶ�", md.get����ԭ�ܶ�() );
		j.put("����ԭ������", md.get����ԭ������() );
		j.put("¼����", md.get¼����() );
		j.put("¼��ʱ��", md.get¼��ʱ��() );
		j.put("������", md.get������() );
		j.put("��ŵص�", md.get��ŵص�() );
		j.put("ʹ������", md.getʹ������() );
		j.put("�۾�״̬", md.get�۾�״̬() );
		j.put("�۾ɷ���", md.get�۾ɷ���() );
		j.put("�۾�ʱ��", md.get�۾�ʱ��() );
		j.put("�����۾�����", md.get�����۾�����() );
		j.put("���۾���", md.get���۾���() );
		j.put("���۾ɶ�", md.get���۾ɶ�() );
		j.put("��ֵ׼��", md.get��ֵ׼��() );
		j.put("��ֵ��", md.get��ֵ��() );
		j.put("�ۼ��۾�", md.get�ۼ��۾�() );
		j.put("��ֵ", md.get��ֵ() );
		j.put("������", md.get������() );
		j.put("��ע", md.get��ע() );
		j.put("�����", md.get�����() );
		j.put("���ʱ��", md.get���ʱ��() );
		j.put("��������", md.get��������() );
		j.put("������", md.get������() );
		j.put("����ƾ����", md.get����ƾ����() );
		j.put("�������", md.get�������() );
		j.put("ͼƬ1", md.getͼƬ1() );
		j.put("ͼƬ2", md.getͼƬ2() );
		j.put("ͼƬ3", md.getͼƬ3() );
		j.put("���״̬", md.get���״̬() );
		j.put("�������", md.get�������() );
	
			j.put("�̿���ӯԭ��", md.get�̿���ӯԭ��() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return j;
	}

	
}
