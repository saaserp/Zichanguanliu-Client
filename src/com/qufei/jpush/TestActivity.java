package com.qufei.jpush;

import com.qufei.androidapp.R;

import cn.jpush.android.api.JPushInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class TestActivity extends Activity {
	String msg=null;
	TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("�û��Զ���򿪵�Activity");
        Intent intent = getIntent();
        String content = null;
        if (null != intent) {
	        Bundle bundle = getIntent().getExtras();
	        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
	        content = bundle.getString(JPushInterface.EXTRA_ALERT);
	        tv.setText("Title : " + title + "  " + "Content : " + content);
        }
        setContentView(R.layout.push_activity_my);
        tv=(TextView) this.findViewById(R.id.str);
		tv.setText(content);
       // addContentView(tv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

}
