package card;

public class Card {

	public static String getCard(String cardid) {
		String result = "";
		try {
			// chuyen doi thap phan sang thap luc phan
			result = Long.toHexString(Long.parseLong(cardid)) + "";
			// cắt đi 4 ký tự đầu sau khi chuyển đổi sang thập lục phân
			result = result.substring(4);
			// chuyen thap luc phan sang thap phan
			result = Long.parseLong(result, 16) + "";
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return result;
	}
	
}
