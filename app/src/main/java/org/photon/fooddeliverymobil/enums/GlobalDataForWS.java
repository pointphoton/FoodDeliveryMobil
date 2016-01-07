package org.photon.fooddeliverymobil.enums;

public enum GlobalDataForWS {

	USER_ID("id"), PAGE("Page"), REC_PER_PAGE("RecPerPage"), FOLLOWER_ID("FollowerId"),	FOLLOWING_ID("FollowingId"), SEARCHER_ID("SearcherId"),
	USERNAME("UserName"), PASSWORD("password"),EMAIL("email"), NAME("Name"), LOCATION("Location"), OLD_PASSWORD("OldPassword"), NEW_PASSWORD("NewPassword"),
	WEBSITE("Website"), CONFIRM_PASSWORD("ConfirmPassword"), PASSWORD_CONFIRM("PasswordConfirm"), PHOTO_BASE64_STRING("PhotoBase64String"), 
	OLD_PHOTO_URL("OldPhotoUrl"), NEW_PHOTO_BYTES("NewPhotoBytes"), IS_NOTIFICATION_ACTIVE("IsNotificationActive"), USER_BIO("UserBio"), DATA("Data"), 
	TOKEN("token"), POST_ID("PostId"), POST_SPEECH_TEXT("PostSpeechText"), POST_SENDER_USER_ID("PostSenderUserId"), POST_SENDER_IP("PostSenderIp"),
	VIDEO_BYTES("VideoBase64String"), SHARED_TEXT("SharedText"), SHARE_ID("ShareId"), SEARCH_TEXT("SearchText"),
	REPLY_ID("ReplyId"), POST_SPEECH_TO_TEXT("PostSpeechToText"), POST_TEXT("PostText"),POST_REPLY_BYTE("PostReplyByte"),
	POST_ADDED_TIME_ZONE_ID("PostAddedTimeZoneId"),EMAIL_CODE("EmailCode");
	private final String link;

	private GlobalDataForWS(String value) {
	this.link = value;
	}

	@Override
	public String toString() {

	return link;
	}

}
