package com.qufei.adapter;

import java.util.List;

import com.qufei.androidapp.R;
import com.qufei.model.Dict_固定资产Model;
import com.qufei.model.Dict_国资2010Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchGuoziFelleihaoAdapter extends BaseAdapter {
	List<Dict_国资2010Model>data;
	Context context;
	public SearchGuoziFelleihaoAdapter(Context context,List<Dict_国资2010Model>data){
		this.context=context;
		this.data=data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		view=LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.item_searchfenleihao, null);
		
		TextView text=(TextView) view.findViewById(R.id.fenleihao);
		
		text.setText(data.get(arg0).get名称());
		
		
		return view;
	}

}
