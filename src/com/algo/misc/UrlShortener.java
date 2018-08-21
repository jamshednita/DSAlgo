package com.algo.misc;

public class UrlShortener {
	
	private static final String ALPHABET_MAP="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final Integer BASE = 62;
	// To shorten urlId
	public static String encode(int urlId) {
		StringBuffer shortendUrl = new StringBuffer();
		while(urlId > 0) {
			shortendUrl.append(ALPHABET_MAP.charAt(urlId%BASE));
			urlId/=BASE;
		}
		
		return shortendUrl.reverse().toString();
	}

	// To decode into original urlId
	public static int decode(String shortUrl) {
		int urlId=0;
		
		for (int i = 0; i < shortUrl.length(); i++) {
			urlId = urlId*BASE + ALPHABET_MAP.indexOf(shortUrl.charAt(i));
		}
		
		return urlId;
	}
	
	public static void main(String[] args) {
		System.out.println("encode 123 : "+encode(123)); //b9
		System.out.println("decode b9 : "+decode("b9"));
	}
}
