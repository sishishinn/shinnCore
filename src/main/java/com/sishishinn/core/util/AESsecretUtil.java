package com.sishishinn.core.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class AESsecretUtil {

	private static Logger log = Logger.getLogger(AESsecretUtil.class);
	
	/**
	 * AES解密
	 * @param password 初始密钥
	 * @param content  解密内容
	 * @return
	 */
	public static String decrypt(String password,String content){
		String decodeString = null;
		try {
			SecretKeySpec key = genKey(password);
			
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			byte[] bytecontent = parseHexStr2Byte(content);
			byte[] result = cipher.doFinal(bytecontent);
			
			decodeString = new String(result,"UTF-8");
		} catch (Exception e) {
			log.info("解密出错；原文："+content);
			e.printStackTrace();
		}
		return decodeString;
	}
	
	/**
	 * AES加密
	 * @param password 初始密钥
	 * @param content  加密内容
	 * @return
	 */
	public static String encrypt(String password,String content){
		String encodeString = null;
		try {
			SecretKeySpec key = genKey(password);
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			byte[] byteContent = content.getBytes("utf-8");
			byte[] result = cipher.doFinal(byteContent);
			
			encodeString = parseByte2HesStr(result);
		} catch (Exception e) {
			log.info("加密出错；原文："+content);
			e.printStackTrace();
		}
		return encodeString;
	}
	
	/**
	 * 生成密钥
	 * @param password 初始密钥
	 * @return
	 */
	public static SecretKeySpec genKey(String password)throws Exception{
		KeyGenerator kgen =  KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(password.getBytes());
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), "AES");
		return key;
	}
	
	/**
	 * 二进制转十六进制
	 */
	public static String parseByte2HesStr(byte buf[]){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length()==1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	/**
	 * 十六进制转二进制
	 */
	public static byte[] parseHexStr2Byte(String hexStr){
		
		if (hexStr.length()<1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0; i < hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high*16 + low);
		}
		return result;
	}
	
	
}
