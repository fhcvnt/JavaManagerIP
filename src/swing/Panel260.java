package swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import QRCode.QR;


public class Panel260 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel260 window = new Panel260();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Panel260() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 887, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelSubLabel = new JPanel();
		panelSubLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSubLabel.setBounds(123, 91, 260, 135);
		panelSubLabel.setLayout(null);
		frame.getContentPane().add(panelSubLabel);

		JLabel lbc1TenThietBi = new JLabel();
		lbc1TenThietBi.setBackground(Color.WHITE);
		lbc1TenThietBi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1TenThietBi.setBounds(10, 10, 64, 25);
		lbc1TenThietBi.setText("Tên thiết bị:");
		panelSubLabel.add(lbc1TenThietBi);

		JLabel lbc1TenThietBi2 = new JLabel();
		lbc1TenThietBi2.setBackground(Color.WHITE);
		lbc1TenThietBi2.setText("CPU");
		lbc1TenThietBi2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1TenThietBi2.setBounds(80, 10, 104, 25);
		panelSubLabel.add(lbc1TenThietBi2);

		JLabel lbc1DonVi = new JLabel();
		lbc1DonVi.setBackground(Color.WHITE);
		lbc1DonVi.setText("Đơn vị:");
		lbc1DonVi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1DonVi.setBounds(10, 40, 41, 25);
		panelSubLabel.add(lbc1DonVi);

		JLabel lbc1DonVi2 = new JLabel();
		lbc1DonVi2.setBackground(Color.WHITE);
		lbc1DonVi2.setText("IT");
		lbc1DonVi2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1DonVi2.setBounds(57, 40, 177, 25);
		panelSubLabel.add(lbc1DonVi2);

		JLabel lbc1NgaySuDung = new JLabel();
		lbc1NgaySuDung.setBackground(Color.WHITE);
		lbc1NgaySuDung.setText("Ngày sử dụng:");
		lbc1NgaySuDung.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1NgaySuDung.setBounds(10, 70, 80, 25);
		panelSubLabel.add(lbc1NgaySuDung);

		JLabel lbc1NgaySuDung2 = new JLabel();
		lbc1NgaySuDung2.setBackground(Color.WHITE);
		lbc1NgaySuDung2.setText("01/01/1990--------");
		lbc1NgaySuDung2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1NgaySuDung2.setBounds(95, 70, 100, 25);
		panelSubLabel.add(lbc1NgaySuDung2);

		JLabel lbc1MaSoThietBi = new JLabel();
		lbc1MaSoThietBi.setBackground(Color.WHITE);
		lbc1MaSoThietBi.setText("Mã số thiết bị:");
		lbc1MaSoThietBi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1MaSoThietBi.setBounds(10, 100, 80, 25);
		panelSubLabel.add(lbc1MaSoThietBi);

		JLabel lbc1MaSoThietBi2 = new JLabel();
		lbc1MaSoThietBi2.setBackground(Color.WHITE);
		lbc1MaSoThietBi2.setText("CPU01");
		lbc1MaSoThietBi2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1MaSoThietBi2.setBounds(95, 100, 94, 25);

		JLabel lbc1LogoLHG = new JLabel();
		lbc1LogoLHG.setBackground(Color.WHITE);
		lbc1LogoLHG.setIcon(new ImageIcon(PrintComputerProfile.class.getResource("/QRCode/image/lacty70x50.png")));
		lbc1LogoLHG.setBounds(185, 5, 70, 50);
		panelSubLabel.add(lbc1LogoLHG);

		// tạo ảnh QR code
		// BufferedImage image = QRCode.QRCodeGenerator(contentQRCode(tenthietbi,
		// hoten,sothe, donvi, ngaysudung, masothietbi), 70, 70);
		BufferedImage image = null;
		String data = "Họ tên: Tô Ngọc Trí\nĐơn vị: IT\nTên thiết bị: CPU\nMã số thiết bị: CPUIT01";
		try {
			image = QR.createQR(data, 90, 90);
		} catch (Exception e) {
		}

		JLabel lbc1Qrcode = new JLabel();
		lbc1Qrcode.setBounds(169, 44, 90, 90);

		lbc1Qrcode.setIcon(new ImageIcon(resizeImage(image,70,70)));
		panelSubLabel.add(lbc1Qrcode);

		panelSubLabel.add(lbc1MaSoThietBi2);
	}

	public static BufferedImage resizeImage(final Image image, int width, int height) {
		final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.setComposite(AlphaComposite.Src);
		// below three lines are for RenderingHints for better image quality at cost of
		// higher processing time
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawImage(image, 0, 0, width, height, null);
		graphics2D.dispose();
		return bufferedImage;
	}
}
