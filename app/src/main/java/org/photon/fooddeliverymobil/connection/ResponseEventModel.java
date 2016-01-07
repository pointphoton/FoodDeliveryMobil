package org.photon.fooddeliverymobil.connection;

import android.util.Pair;

import java.io.InputStream;
import java.util.EventObject;
import java.util.List;

public class ResponseEventModel<T> extends EventObject {
	private static final long serialVersionUID = 1L;



	private String serviceName;
	private String responseData;
	private String errorMessage;
	private String sendData;
	private boolean serviceStatus;
	private T model;
	private List<T> listModel;
	private String methodName;
	private boolean isArrived;
	private InputStream stream;
	List<Pair<String,String>> sentParameters;

	public ResponseEventModel(Object source){
		super(source);
	}




	public InputStream getStream() {
		return stream;
	}

	public ResponseEventModel setStream(InputStream stream) {
		this.stream = stream;
		return this;
	}

	public boolean isArrived() {
		return isArrived;
	}

	public ResponseEventModel setIsArrived(boolean isArrived) {
		this.isArrived = isArrived;
		return this;
	}

	public List<Pair<String, String>> getSentParameters() {
		return sentParameters;
	}

	public ResponseEventModel setSentParameters(List<Pair<String, String>> sentParameters) {
		this.sentParameters = sentParameters;
		return this;
	}

	public boolean getServiceStatus() {
		return serviceStatus;
	}

	public ResponseEventModel setServiceStatus(boolean serviceStatus) {
		this.serviceStatus = serviceStatus;
		return this;
	}

	public String getMethodName() {
		return methodName;
	}

	public ResponseEventModel setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}

	public List<T> getListModel() {
		return listModel;
	}

	public ResponseEventModel setListModel(List<T> listModel) {
		this.listModel = listModel;
		return this;
	}

	public T getModel() {
		return model;
	}

	public ResponseEventModel setModel(T model) {
		this.model = model;
		return this;
	}

	public String getSendData() {
		return sendData;
	}

	public ResponseEventModel setSendData(String sendData) {
		this.sendData = sendData;
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ResponseEventModel setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}


	public String getResponseData() {
		return responseData;
	}

	public ResponseEventModel setResponseData(String responseData) {
		this.responseData = responseData;
		return this;
	}

	public String getServiceName() {
		return serviceName;
	}

	public ResponseEventModel setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}