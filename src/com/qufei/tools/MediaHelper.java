package com.qufei.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MediaHelper {
	Context context;
	  private UploadLogService logService; 
	  private boolean start=true;
	  private ProgressDialog uploadbar;
	MediaHelper(Context context){
		this.context=context;
		logService=new UploadLogService(context);
		uploadbar=new ProgressDialog(context);
		uploadbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
		 
	}
	
	static boolean isFolderExists(String strFolder) {
		File file = new File(strFolder);        
		if (!file.exists()) {
			if (file.mkdirs()) {                
				return true;
			} else {
				return false;

			}
		}
		return true;

	}
	
	public static String  saveBitmap(String bitName, Bitmap mBitmap) {
		String Path=AppConfig.CashPath ;
		if(!isFolderExists(Path)){
			return null;
		}
		String filePath=Path+ bitName;;
		File f = new File(filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {

			//Toast.makeText(context, "在保存图片时出错：", Toast.LENGTH_SHORT).show();
			Log.i("Savebitmap","保存失败"+filePath+".png");
			return null;
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
			Log.i("Savebitmap","保存成功"+filePath+".jpg");

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
			return f.getPath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		 
	}
	private Handler handUploadImage = new Handler(){  
        

		@Override  
        public void handleMessage(Message msg) {  
            int length = msg.getData().getInt("size");  
            uploadbar.setProgress(length);  
            float num = (float)uploadbar.getProgress()/(float)uploadbar.getMax();  
            int result = (int)(num * 100);  
          // resulView.setText(result+ "%");  
            if(uploadbar.getProgress()==uploadbar.getMax()){ 
            	uploadbar.dismiss();
                Toast.makeText(context, "成功", 1).show();  
                
            }  
        }  
    };  
      
	/** 
	 * 上传文件 
	 * @param uploadFile 
	 */  
	private void uploadFile(final File uploadFile) {  
		uploadbar.show();
		new Thread(new Runnable() {           
			@Override  
			public void run() {  
				try {  
					uploadbar.setMax((int)uploadFile.length());  
					String souceid = logService.getBindId(uploadFile);  
					String head = "Content-Length="+ uploadFile.length() + ";filename="+ uploadFile.getName() + ";sourceid="+  
							(souceid==null? "" : souceid)+"\r\n";  
					Socket socket = new Socket("192.168.191.1",6000);  
					OutputStream outStream = socket.getOutputStream();  
					outStream.write(head.getBytes());  

					PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());      
					String response = StreamTool.readLine(inStream);  
					String[] items = response.split(";");  
					String responseid = items[0].substring(items[0].indexOf("=")+1);  
					String position = items[1].substring(items[1].indexOf("=")+1);  
					if(souceid==null){//代表原来没有上传过此文件，往数据库添加一条绑定记录  
						logService.save(responseid, uploadFile);  
					}  
					RandomAccessFile fileOutStream = new RandomAccessFile(uploadFile, "r");  
					fileOutStream.seek(Integer.valueOf(position));  
					byte[] buffer = new byte[1024];  
					int len = -1;  
					int length = Integer.valueOf(position);  
					while(start&&(len = fileOutStream.read(buffer)) != -1){  
						outStream.write(buffer, 0, len);  
						length += len;  
						Message msg = new Message();  
						msg.getData().putInt("size", length);  
						handUploadImage.sendMessage(msg);  
					}  
					fileOutStream.close();  
					outStream.close();  
					inStream.close();  
					socket.close();  
					if(length==uploadFile.length()) logService.delete(uploadFile);  
				} catch (Exception e) {  
					e.printStackTrace();  
				}  
			}  
		}).start();  
	}
	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {

			myFileUrl = new URL(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setConnectTimeout(0);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			 
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
