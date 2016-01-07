package com.qufei.addproduct;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qufei.androidapp.R;

public class ImageSelectDialog extends AlertDialog {
	private Context context;
	private ListView lv_imageselect_dialog;

	public ImageSelectDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_imageselect);
		setTitle("上传图片");
		 setIcon(R.drawable.upload);
		lv_imageselect_dialog = (ListView) findViewById(R.id.lv_imageselect_dialog);
		 
		String[] strs = { "拍照上传", "本地上传" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				R.layout.lv_item_textview1, R.id.tv_item_text1, strs);
		lv_imageselect_dialog.setAdapter(adapter);
	}
}
