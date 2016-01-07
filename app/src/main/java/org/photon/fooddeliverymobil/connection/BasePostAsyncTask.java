package org.photon.fooddeliverymobil.connection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.photon.fooddeliverymobil.application.FoodDeliveryApp;
import org.photon.fooddeliverymobil.link.HttpLink;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BasePostAsyncTask<T>  extends AsyncTask<String, Void, String> {

	private List<PostListener<T>> getListeners;

	private Context context;
	private String serviceName;
	private String requestBody;
	private String errorMessage;
	private String methodName;
	private String sendData;
	private InputStream inputStream;
	boolean isArrived;
	boolean serviceStatus;

	{
		getListeners = new ArrayList<>();

		serviceName = "";
		methodName = "";
		sendData = "";

	}

	public BasePostAsyncTask(Context contextInstance, String serviceName, String requestBody) {

		this.context = contextInstance;
		this.serviceName = serviceName;
		this.requestBody = requestBody;

	}

	@Override protected String doInBackground(String... params) {
		String result = "";
		try {
			result = readGetFeed(params[0], params[1]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override protected void onPostExecute(String result) {
		this.fireResponseEvent(result);
	}

	public String readGetFeed(String URL, String uriParam) throws Exception {

		String uri = URL;
		String responseData = "";
		if (uriParam != null && !uriParam.equalsIgnoreCase("")) {

			uri += "/" + uriParam;
			this.methodName = uriParam;
		}

		String sendJson = this.requestBody;
		Log.d("requestbody ", requestBody);
		java.net.URL url = new URL(uri);
		Log.d("url ", url.toString());
		HttpURLConnection connection = null;
		int code = -1;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			Log.d("connection ", "starting");
			connection.connect();
			OutputStream os = connection.getOutputStream();
			os.write(sendJson.getBytes("UTF-8"));
				os.close();
			code = connection.getResponseCode();
			Log.d("connection ", "code " + code);
			if (code == 200 || code == 401 || code==202) {
				isArrived = true;

				try {
					inputStream = new BufferedInputStream(connection.getInputStream());
					responseData = org.apache.commons.io.IOUtils.toString(inputStream, "UTF-8");
					if (HttpLink.getLoginLink()==methodName) {
						String headerValue = (null != connection.getHeaderField(HttpLink.getAuthHeader())) ? connection.getHeaderField(HttpLink.getAuthHeader()) : "no header value";
						//	Log.d("header value :",headerValue);
						FoodDeliveryApp.setToken(headerValue);
					}
					//responseData = IOUtils.toString(inputStream, "UTF-8");
					//responseData = ServiceUtil.EncodeUTF8InputStream(inputStream);
					serviceStatus = true;
				} catch (Exception e) {
					Log.e("status code", code + " " + e.getMessage() + " service status : " + serviceStatus);
					errorMessage += code + " " + e.getMessage() + " service status : " + serviceStatus;

				}
			}

		} catch (Exception e) {
			Log.e("GetAsyncTask", code + " " + e.getMessage() + " service status : " + serviceStatus);
			errorMessage += code + " " + e.getMessage() + " service status : " + serviceStatus;
		}
		if (null != inputStream) inputStream.close();
		if (null != connection) connection.disconnect();
		Log.d(" response", responseData);
		return responseData;

	}

	private synchronized void fireResponseEvent(String responseData) {

		ResponseEventModel<T> responseEvent = new ResponseEventModel<T>(this);
		responseEvent.setServiceName(this.serviceName);
		responseEvent.setSendData(this.sendData);
		responseEvent.setErrorMessage(this.errorMessage);
		responseEvent.setStream(this.inputStream);
		responseEvent.setIsArrived(this.isArrived);
		responseEvent.setServiceStatus(this.serviceStatus);
		responseEvent.setMethodName(this.methodName);
		responseEvent.setResponseData(responseData);

		for (PostListener<T> listener : getListeners) {
			Log.d("POST response", "responseevent gidiyor");
			listener.onPostCommit(responseEvent);
		}

	}




	public synchronized void addServiceClientListener(PostListener<T> postListener) {
		this.getListeners.add(postListener);
	}

}