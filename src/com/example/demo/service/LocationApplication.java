package com.example.demo.service;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import android.app.Application;
import android.app.Service;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

/**
 * ��Application
 */
public class LocationApplication extends Application {

	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;
	
	public TextView mLocationResult,logMsg;
	public TextView trigger,exit;
	public Vibrator mVibrator;
	
	public double xCode;
	public double yCode;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(getApplicationContext());
		
		
		mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
	}

	
	/**
	 * ʵ��ʵλ�ص�����
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			//Receive Location 
			StringBuffer sb = new StringBuffer(256);
////			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("latitude : ");
			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
			sb.append("|");
			sb.append(location.getLongitude());
			sb.append("|");
//			sb.append("\nradius : ");
			sb.append(location.getRadius());
			sb.append(location.getAltitude());
//			if (location.getLocType() == BDLocation.TypeGpsLocation){
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//				sb.append("\ndirection : ");
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//				sb.append(location.getDirection());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//				//��Ӫ����Ϣ
//				sb.append("\noperationers : ");
//				sb.append(location.getOperators());
//			}
//			xCode = location.getLatitude();
			setxCode(location.getLatitude());
//			yCode = location.getLongitude();
			setyCode(location.getLongitude());
			logMsg(sb.toString());
			Log.i("BaiduLocationApiDem", sb.toString());
			Log.i("xCode :", String.valueOf(getxCode()));
			Log.i("yCode :", String.valueOf(getyCode()));
		}


	}
	
	
	/**
	 * ��ʾ�����ַ���
	 * @param str
	 */
	public void logMsg(String str) {
		try {
			if (mLocationResult != null)
				mLocationResult.setText(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �߾��ȵ���Χ���ص�
	 * @author jpren
	 *
	 */
	public double getxCode() {
		return xCode;
	}


	public void setxCode(double xCode) {
		this.xCode = xCode;
	}


	public double getyCode() {
		return yCode;
	}


	public void setyCode(double yCode) {
		this.yCode = yCode;
	}
}