package com.codinger.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

public class AES {
	
	private static String ivParameter="1234567890123456";
	
	public static void main(String[] args) {
		String key = getKey();
		System.out.println("secretKey:\n" + key);
		String originalText = "qwer@123sadsa";
		System.out.println("originalText:\n" + originalText);
		try {
			String encryptText = encrypt(originalText, key);
			System.out.println("encryptText:\n" + encryptText);
			String decryptText = decrypt(encryptText,key);
			System.out.println("decryptText:\n" + decryptText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//生成16位随机加密字符串
	public static String getKey() {
		String sKey = RandomStringUtils.randomAlphanumeric(16);
		return sKey;
	}
	// 加密
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        
//        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");//"算法/模式/补码方式"
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        
//        return new String(encrypted);
        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            
//            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
//            byte[] encrypted1 = sSrc.getBytes();
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            return null;
        }
    }
    }

