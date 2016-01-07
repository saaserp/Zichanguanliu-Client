package com.qufei.login;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.qufei.androidapp.R;
import com.qufei.factory.LoginJsonResultFactory;
import com.qufei.jpush.ExampleUtil;
import com.qufei.jpush.PushSetActivity;
import com.qufei.jpush.MainActivity.MessageReceiver;
import com.qufei.main.QufeiAppMain;
import com.qufei.model.UserModel;
import com.qufei.setting.AppSetting;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.MD5Util;
import com.qufei.tools.QufeiSocketClient;

/**
 * Activity which displays a login screen to the user, offering registration as well.
 */
public class LoginActivity extends InstrumentedActivity {
	public static boolean isLogin = false;
	private CheckBox isSavePassword;


	private void initJpush(){//init the jpush 
		JPushInterface.init(getApplicationContext());
	}
	public boolean isLogin() {
		return this.isLogin == true ? true : false;// 判断当前是否登陆

	}

	public static UserModel rs;
	public String errorMessageString="网络连接失败";
	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private static String mUserID = "";

	public static String getmUserID() {
		return mUserID;
	}

	public static void setmUserID(String mUserID) {
		LoginActivity.mUserID = mUserID;
	}

	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	public void initJpushView(){

		String udid =  ExampleUtil.getImei(getApplicationContext(), "");



		String appKey = ExampleUtil.getAppKey(getApplicationContext());
		if (null == appKey) appKey = "AppKey异常";
		String packageName =  getPackageName();
		String versionName =  ExampleUtil.GetVersion(getApplicationContext());



	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		 




		//dell with the jpush essue



		// Set up the login form.
		mUserID = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.userID);
		isSavePassword=(CheckBox)findViewById(R.id.savePassword);
		SharedPreferences sp  =getSharedPreferences("user_info", Context.MODE_PRIVATE);
		String userID=sp.getString("user_id", "");
		String password=sp.getString("password","");
		isSavePassword.setChecked(sp.getBoolean("isSavePassword", false));
		mEmailView.setText(userID);


		mPasswordView = (EditText) findViewById(R.id.password);

		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					
					attemptLogin();
					return true;
				}
				return false;
			}
		});
		this.findViewById(R.id.msetting).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(LoginActivity.this,AppSetting.class);
				startActivity(i);
			}
		});
		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		this.findViewById(R.id.title_left).setVisibility(View.GONE);
		//		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
		//
		//			@Override
		//			public void onClick(View arg0) {
		//				// TODO Auto-generated method stub
		//				LoginActivity.this.finish();
		//			}
		//		});
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);


		findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				attemptLogin();
			}
		});
		this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(LoginActivity.this,AppSetting.class);
				startActivity(i);

			}
		});
		if(isSavePassword.isChecked()==true){
			mPasswordView.setText(password);
			attemptLogin();
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form. If there are form errors (invalid email, missing fields, etc.), the errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		
		if (mAuthTask != null) {
			
			return;
		}
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
		//得到InputMethodManager的实例
		if (imm.isActive()) {
		//如果开启
		imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
		//关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
		}
		// Reset errors.
		// mEmailView.setError(null);
		// mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mUserID = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

	
		// Check for a valid email address.
		if (TextUtils.isEmpty(mUserID)) {
			// mEmailView.setError(getString(R.string.error_field_required));
			errorMessageString = getString(R.string.error_idfield_required);
			Toast.makeText(LoginActivity.this, errorMessageString, Toast.LENGTH_SHORT).show();
			// focusView = mEmailView;

			// cancel = true;
			return;
		}

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {

			errorMessageString = getString(R.string.error_pwdfield_required);
			Toast.makeText(LoginActivity.this, errorMessageString, Toast.LENGTH_SHORT).show();
			
			return;
		}
	
		mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
		showProgress(true);
		mAuthTask = new UserLoginTask();
		mAuthTask.execute((Void) null);
	
	}
	private void setAlias(String alias){


		if (TextUtils.isEmpty(alias)) {
			Toast.makeText(LoginActivity.this,R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
			return;
		}
		if (!ExampleUtil.isValidTagAndAlias(alias)) {
			Toast.makeText(LoginActivity.this,R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
			return;
		}

		//调用JPush API设置Alias
		mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
	}
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
	
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
				}
			});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
				}
			});
			
		}
		else {

			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
		
		
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			JsonResultFactory jsf = new LoginJsonResultFactory();

	
			JSONArray mjsa = new JSONArray();
			JSONObject mjs = new JSONObject();
			try {
				mjs.put("userID", mUserID);
				mjs.put("userPwd", MD5Util.MD5(mPassword));
			}
			catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mjsa.put(mjs);



			try {
				jsf.setJsonStr(QufeiSocketClient.getInstance().send("login", mjsa));
	
				JsonResult jr = jsf.createResult();
				rs = (UserModel) jr.getModels().get(0);
				AppConfig.LoginUser = rs;
			}
			catch (Exception e) {
				return false;
			}

			if (!rs.getIsok().equals("true")) {
				// 登陆失败
				errorMessageString = rs.getIsok();
				return false;
			}
			else {
				// 登陆成功
				SharedPreferences mSettings;
				mSettings =getSharedPreferences("user_info", Context.MODE_PRIVATE);

				Editor mEditor = mSettings.edit();
				mEditor.putString("user_id", mUserID);
				mEditor.putBoolean("isSavePassword", isSavePassword.isChecked());
				mEditor.putString("password", mPassword);
				mEditor.commit();
				setAlias(rs.getSchoolID()+mUserID);


				return true;
			}

			// TODO: register the new account here.

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				Uri data = Uri.parse(rs.getUsername());

				Intent result = new Intent(null, data);
				result.putExtra("username", rs.getUsername());
				result.putExtra("userid", rs.getUserid());
				setResult(RESULT_OK, result);

				finish();
			}
			else {
				// CharSequence charSequence = new String("<font color='blue'>" + errorMessageString + "</font>");
				mPasswordView.requestFocus();
				// mPasswordView.setError(charSequence);
				Toast.makeText(LoginActivity.this, errorMessageString, Toast.LENGTH_SHORT).show();

			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}

	private static final String TAG = "qufei";
	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

		@Override
		public void gotResult(int code, String alias, Set<String> tags) {
			String logs ;
			switch (code) {
			case 0:
				logs = "Set tag and alias success";
				Log.i(TAG, logs);
				break;

			case 6002:
				logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
				Log.i(TAG, logs);
				if (ExampleUtil.isConnected(getApplicationContext())) {
					mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
				} else {
					Log.i(TAG, "No network");
				}
				break;

			default:
				logs = "Failed with errorCode = " + code;
				Log.e(TAG, logs);
			}

			//ExampleUtil.showToast(logs, getApplicationContext());
		}

	};

	private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

		@Override
		public void gotResult(int code, String alias, Set<String> tags) {
			String logs ;
			switch (code) {
			case 0:
				logs = "Set tag and alias success";
				Log.i(TAG, logs);
				break;

			case 6002:
				logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
				Log.i(TAG, logs);
				if (ExampleUtil.isConnected(getApplicationContext())) {
					mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
				} else {
					Log.i(TAG, "No network");
				}
				break;

			default:
				logs = "Failed with errorCode = " + code;
				Log.e(TAG, logs);
			}

			// ExampleUtil.showToast(logs, getApplicationContext());
		}

	};
	private static final int MSG_SET_ALIAS = 1001;
	private static final int MSG_SET_TAGS = 1002;
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_SET_ALIAS:
				Log.d(TAG, "Set alias in handler.");
				JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
				break;

			case MSG_SET_TAGS:
				Log.d(TAG, "Set tags in handler.");
				JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
				break;

			default:
				Log.i(TAG, "Unhandled msg - " + msg.what);
			}
		}
	};
	@Override
	public boolean dispatchKeyEvent(KeyEvent event){
		// TODO Auto-generated method stub
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK&&event.getAction() == KeyEvent.ACTION_DOWN)
		{

			Message msg=QufeiAppMain.handExit.obtainMessage();
			msg.sendToTarget();
			this.finish();
			return true;
		}
		 
		return  super.dispatchKeyEvent(event);

		//end onKeyDown
	}
	 
}

