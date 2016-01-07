package org.photon.fooddeliverymobil.link;


public class HttpLink {

	private static final String HTTP_LINK ;
	private static final String PRODUCT_LINK ;
	private static final String BRANCH_LINK ;
	private static final String LOGIN_LINK ;
	private static final String ADDRESS_LINK ;
	private static final String ORDER_LINK ;
	private static final String AUTH_HEADER;


	static {
		HTTP_LINK ="http://10.0.2.2:8087/FoodDelivery-web/webapi";
		PRODUCT_LINK = "products";
		BRANCH_LINK  ="branches";
		LOGIN_LINK ="login";
		ADDRESS_LINK ="addresses";
		ORDER_LINK ="orders";
		AUTH_HEADER="AuthHeader";
	}

	public static String getProductLink() {
		return PRODUCT_LINK;
	}

	public static String getHttpLink() {
		return HTTP_LINK;
	}

	public static String getBranchLink() {
		return BRANCH_LINK;
	}

	public static String getLoginLink() {
		return LOGIN_LINK;
	}

	public static String getAddressLink() {
		return ADDRESS_LINK;
	}

	public static String getAuthHeader() {
		return AUTH_HEADER;
	}

	public static String getOrderLink() {
		return ORDER_LINK;
	}
}
