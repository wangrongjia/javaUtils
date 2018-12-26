package com.codinger.utils.encrypt;

import org.apache.commons.codec.binary.Base64;

public class BASE64Utils {
	
	public static void main(String[] args) {
		String originalText = "qwer!123";
		System.out.println("originalText\n" + originalText);
		String encryptText = encodeStr(originalText);
		System.out.println("encryptText\n" + encryptText);
		System.out.println("decryptText\n" + decodeStr(encryptText));
	}
	
	/**
	 * 加密
	 * @param inputStr
	 * @return encode
	 */
	public static String encodeStr(String inputStr) {
		byte[] content = inputStr.getBytes();
		Base64 base64 = new Base64();
		content = base64.encode(content);
		String encode = new String(content);
		return encode;
	}

	/**
	 * 解密
	 * @param encodeStr
	 * @return
	 */
	public static String decodeStr(String encodeStr) {
		byte[] content = encodeStr.getBytes();
		Base64 base64 = new Base64();
		content = base64.decode(content);
		String decode = new String(content);
		return decode;
	}
}
