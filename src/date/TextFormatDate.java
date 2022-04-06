package date;

import java.util.Calendar;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class TextFormatDate {

	private Text text;
	final Calendar calendar = Calendar.getInstance();

	public TextFormatDate() {
	}

	// gán text cần điểu chỉnh chỉ cho nhập ngày tháng năm DD/MM/YYYY
	public void setTextDDMMYYYY(Text te) {
		text = te;
		text.setText("DD/MM/YYYY");
	}

	public Listener setDDMMYYYYListener = new Listener() {
		boolean ignore;

		@Override
		public void handleEvent(Event e) {
			if (ignore)
				return;
			e.doit = false;
			StringBuffer buffer = new StringBuffer(e.text);
			char[] chars = new char[buffer.length()];
			buffer.getChars(0, chars.length, chars, 0);
			if (e.character == '\b') {
				for (int i = e.start; i < e.end; i++) {
					switch (i) {
					case 0: /* [D]D */
					case 1: /* D[D] */ {
						buffer.append('D');
						break;

					}
					case 3: /* [M]M */
					case 4: /* M[M] */ {
						buffer.append('M');
						break;
					}
					case 6: /* [Y]YYY */
					case 7: /* Y[Y]YY */
					case 8: /* YY[Y]Y */
					case 9: /* YYY[Y] */ {
						buffer.append('Y');
						break;
					}
					case 2: /* YYYY[/]MM */
					case 5: /* MM[/]DD */ {
						buffer.append('/');
						break;
					}
					default:
						return;
					}

				}
				text.setSelection(e.start, e.start + buffer.length());
				ignore = true;
				text.insert(buffer.toString());
				ignore = false;
				text.setSelection(e.start, e.start);
				return;
			}

			int start = e.start;
			if (start > 9)
				return;
			int index = 0;
			for (int i = 0; i < chars.length; i++) {
				if (start + index == 2 || start + index == 5) {
					if (chars[i] == '/') {
						index++;
						continue;
					}
					buffer.insert(index++, '/');
				}
				if (chars[i] < '0' || '9' < chars[i])
					return;
				if (start + index == 3 && '1' < chars[i])
					return; /* [M]M */
				if (start + index == 0 && '3' < chars[i])
					return; /* [D]D */
				index++;
			}
			String newText = buffer.toString();
			int length = newText.length();
			StringBuffer date = new StringBuffer(text.getText());
			date.replace(e.start, e.start + length, newText);
			calendar.set(Calendar.YEAR, 1901);
			calendar.set(Calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DATE, 1);
			String yyyy = date.substring(6, 10);
			if (yyyy.indexOf('Y') == -1) {
				int year = Integer.parseInt(yyyy);
				calendar.set(Calendar.YEAR, year);
			}
			String mm = date.substring(3, 5);
			if (mm.indexOf('M') == -1) {
				int month = Integer.parseInt(mm) - 1;
				int maxMonth = calendar.getActualMaximum(Calendar.MONTH);
				if (0 > month || month > maxMonth)
					return;
				calendar.set(Calendar.MONTH, month);
			}
			String dd = date.substring(0, 2);
			if (dd.indexOf('D') == -1) {
				int day = Integer.parseInt(dd);
				int maxDay = calendar.getActualMaximum(Calendar.DATE);
				if (1 > day || day > maxDay)
					return;
				calendar.set(Calendar.DATE, day);
			} else {
				if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY) {
					char firstChar = date.charAt(8);
					if (firstChar != 'D' && '2' < firstChar)
						return;
				}
			}
			text.setSelection(e.start, e.start + length);
			ignore = true;
			text.insert(newText);
			ignore = false;
		}

	};

}
