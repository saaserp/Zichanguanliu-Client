package com.qufei.addproduct;

import java.util.Calendar;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

public class MDatePicker extends DialogFragment  implements

 OnDateSetListener{
	Map<String,String> data;
	public MDatePicker(Map<String,String> data){
		this.data=data;
		 
	}
	@Override  
    public Dialog onCreateDialog(Bundle savedInstanceState) {  
        final Calendar c = Calendar.getInstance();  
        int year = c.get(Calendar.YEAR);  
        int month = c.get(Calendar.MONTH);  
        int day = c.get(Calendar.DAY_OF_MONTH);  
        return new DatePickerDialog(getActivity(), this, year, month, day);  
    }
	
//	@Override
//	public void onDateSet(DatePicker view, int year, int month, int day) {
//		// TODO Auto-generated method stub
// 
//		 // year+"-"+(month+1)+"-"+day;
//		
//
//	}
	@Override
	public void onDateSet(android.widget.DatePicker arg0, int year, int month,
			int day) {
		// TODO Auto-generated method stub
		String time=year+"-"+(month+1)+"-"+day;
		Message msg=AddProductActivity.handModyfi.obtainMessage();
		data.remove("default");
		msg.what= AddProductActivity.postion;
		 
		data.put("default", time);
		msg.obj=data;
		msg.sendToTarget();
		
		
		
	}  
	

}
