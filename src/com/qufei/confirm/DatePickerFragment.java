package com.qufei.confirm;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerFragment extends DialogFragment  implements

 OnDateSetListener{
	Context context;
	DatePickerFragment(Context x){
		context=x;
		 
	}
	@Override  
    public Dialog onCreateDialog(Bundle savedInstanceState) {  
        final Calendar c = Calendar.getInstance();  
        int year = c.get(Calendar.YEAR);  
        int month = c.get(Calendar.MONTH);  
        int day = c.get(Calendar.DAY_OF_MONTH);  
        return new DatePickerDialog(getActivity(), this, year, month, day);  
    }
	
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// TODO Auto-generated method stub
		Message msg=((Confirm)context).confirmHandler.obtainMessage();
		msg.obj=  year+"-"+(month+1)+"-"+day;
		
		msg.sendToTarget();
	//	Toast.makeText(context, "日期设置成功", Toast.LENGTH_SHORT).show();
		
	}  
	

}
