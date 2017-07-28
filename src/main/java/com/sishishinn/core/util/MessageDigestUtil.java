package com.sishishinn.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MessageDigestUtil {

	private static Logger log = Logger.getLogger(MessageDigestUtil.class);
	
	public static String encodeSHAString(String content){
		return encode(content, "SHA");
	}
	
	public static String encodeMD5String(String content) {
		return encode(content, "MD5");
	}
	
	private static String encode(String content,String method){
		String result = null;
		try {
			MessageDigest md = null;
			md = MessageDigest.getInstance(method);
			md.update(content.getBytes());
			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
			result = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			log.info(method+"加密出错;原文："+content);
			e.printStackTrace();
		}
		return result;
	}
}
