package com.qufei.tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.JSONArray;

import android.app.Activity;
import android.util.Log;

import com.qufei.androidapp.R;

public class QufeiSocketClient {

		Socket socket =null;
		PrintWriter printWriter;
		BufferedReader bufferedReader;
		static QufeiSocketClient qs=null;
			public static QufeiSocketClient getInstance(){
//				if(qs==null){
//					qs=new QufeiSocketClient();
//				}
				qs=new QufeiSocketClient();
				return qs;
		
			}
			private QufeiSocketClient(){
				try {
					AppConfig app=AppConfig.getInstance();
					String ip=app.getIp();
					Log.i("ipp",ip);
					socket =new Socket(ip,Integer.parseInt(AppConfig.getInstance().getSocketPort()));
					 
					socket.setSoTimeout(AppConfig.TIMEOUT);
					
					
		
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public String send(String cmd,String str){{
				return send(">>"+cmd+"<<"+str);
				}
			}
			public String send(String cmd,JSONArray str){{
				return send(">>"+cmd+"<<"+str.toString());
				}
			}
		public String send(String str){
			String result ="";
			try {
				
				printWriter =new PrintWriter(socket.getOutputStream(),true);
				//str+="\n";
				
				bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
				printWriter.println(str.toCharArray());
				printWriter.flush();
				result = bufferedReader.readLine();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//				System.out.println("Server say : " + result);

			
			return result;

		}
		public void close(){
			try {

				bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
				printWriter.println("bye".toCharArray());
				printWriter.flush();
				printWriter.close();
				bufferedReader.close();
				socket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
}
