package excel;

import java.util.Random;

public class EncodeDecode {
	public static void main(String[] args) {
		String a = "@#!$%21608Killua";
		String b = enCode(a);
		System.out.println(b);
		String c = deCode(b);
		System.out.println(c);
	}

	// Encode
	public static String enCode(String code) {
		String encode = "";
		int len = code.length();
		String kytu = "";
		for (int i = 0; i < len; i++) {
			kytu = kytu + (code.charAt(i) + 19) + ".";
		}
		Random rand = new Random();
		for (int j = 0; j < 49; j++) {
			encode = encode + rand.nextInt(254) + ".";
		}
		encode = encode + kytu;
		for (int j = 0; j < 32; j++) {
			encode = encode + rand.nextInt(254) + ".";
		}
		encode = encode + rand.nextInt(106);
		return encode;
	}

	// Decode
	public static String deCode(String encode) {
		String decode = "";
		try {
			String[] chuoi = encode.split("\\.");
			for (int i = 49; i < chuoi.length - 33; i++) {
				char kytuchuoi = (char) (Integer.parseInt(chuoi[i]) - 19);
				decode = decode + kytuchuoi;
			}

		} catch (Exception e) {

		}
		return decode;
	}
}
