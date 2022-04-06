package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import QRCode.QR;
import date.ConvertDate;
import printerworks.J2PanelPrinter;
import printerworks.J2Printer;
import sql.ConnectSQL;

public class PrintComputerProfile {

	private JFrame frame;
	private JTextField textProfileID;
	private ConnectSQL connect;
	private int ngonngu = 1;
	private JComboBox<Object> comboBoxDepartment;
	private JComboBox<Object> comboBoxType;
	private ArrayList<JPanel> listpanelContainLabel;
	private JPanel panel;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintComputerProfile window = new PrintComputerProfile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setVisibleFrame() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public PrintComputerProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PrintComputerProfile.class.getResource("/itmanagerip/Icon/IP64.png")));
		frame.setTitle("In tem lý lịch máy tính");
		frame.setBounds(100, 100, 850, 880);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelSearch = new JPanel();
		frame.getContentPane().add(panelSearch, BorderLayout.NORTH);

		JLabel lbProfileID = new JLabel("Mã lý lịch");
		panelSearch.add(lbProfileID);

		textProfileID = new JTextField();
		textProfileID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSearch.add(textProfileID);
		textProfileID.setColumns(12);

		JLabel lbDepartment = new JLabel("Đơn vị");
		panelSearch.add(lbDepartment);

		comboBoxDepartment = new JComboBox<Object>();
		comboBoxDepartment.setEditable(true);
		panelSearch.add(comboBoxDepartment);

		JLabel lbType = new JLabel("Loại");
		panelSearch.add(lbType);

		comboBoxType = new JComboBox<Object>();
		comboBoxType.setModel(new DefaultComboBoxModel<Object>(new String[] { "CPU", "Monitor", "UPS" }));
		comboBoxType.setEditable(true);
		panelSearch.add(comboBoxType);

		JButton btnSearch = new JButton("Tìm kiếm");
		panelSearch.add(btnSearch);

		JButton btnPrint = new JButton("In");
		panelSearch.add(btnPrint);
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane.setAutoscrolls(true);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		listpanelContainLabel = new ArrayList<>();

		scrollPane.setViewportView(panel);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// căn giữa màn hình
		frame.setLocationRelativeTo(null);

		// lấy dữ liệu cho combo đơn vị
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			String select = "SELECT DISTINCT [DonVi] FROM [dbo].[IT_LyLichMayTinh] ORDER BY [DonVi] ASC";
			ResultSet result = connect.getStatement().executeQuery(select);
			ArrayList<String> datadepartment = new ArrayList<>();

			while (result.next()) {
				datadepartment.add(result.getString(1));
			}
			comboBoxDepartment.setModel(new DefaultComboBoxModel<Object>(datadepartment.toArray()));
			result.close();
			connect.closeStatement();
		} catch (SQLException se) {

			if (ngonngu == 0) {
				JOptionPane.showMessageDialog(null, "Lỗi! \n" + se.toString(), "Thông báo lỗi",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error! \n" + se.toString(), "Notice error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		comboBoxDepartment.setSelectedIndex(-1);
		comboBoxType.setSelectedIndex(-1);

		// ----------------------------------------------------------------------
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});

		// ----------------------------------------------------------------------
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				J2Printer printer = new J2Printer();
				printer.setSeparatePrintThread(false);

				for (int i = 0; i < listpanelContainLabel.size(); i++) {
					J2PanelPrinter panelPrinter = new J2PanelPrinter(listpanelContainLabel.get(i));
					printer.addPageable(panelPrinter);
				}

				printer.clearFooters();
				// printer.setLeftMargin(0.1f);
				printer.setPrintJobName("In danh sách nhãn máy tính");
				printer.setTopMargin(0.1f);
				printer.setBottomMargin(0.1f);
				printer.print();
			}
		});
	}

	// search
	// --------------------------------------------------------------------------
	public void search() {
		try {
			if (connect == null) {
				connect = new ConnectSQL();
				connect.setConnection();
			}
			connect.setStatement();
			Statement state = connect.getStatement();

			String malylich = "";
			if (!textProfileID.getText().isEmpty()) {
				malylich = " AND [MaLyLich]='" + textProfileID.getText() + "'";
			}

			String loai = "";
			if (comboBoxType.getSelectedIndex() == -1) {
				loai = "";
			} else if (comboBoxType.getSelectedItem().toString().equals("CPU")) {
				loai = " AND [CPU_Loai]<>'' AND [CPU_Loai] IS NOT NULL";
			} else if (comboBoxType.getSelectedItem().toString().equals("Monitor")) {
				loai = " AND [ManHinh_Loai]<>'' AND [ManHinh_Loai] IS NOT NULL";
			} else if (comboBoxType.getSelectedItem().toString().equals("UPS")) {
				loai = " AND [UPS_Loai]<>'' AND [UPS_Loai] IS NOT NULL";
			}

			String donvi = "";
			if (comboBoxDepartment.getSelectedIndex() == -1) {
				donvi = "";
			} else {
				donvi = " AND [DonVi] LIKE N'%" + comboBoxDepartment.getSelectedItem().toString() + "%'";
			}

			String select = "SELECT [MaLyLich],[DonVi],[MaSoDonVi],[CPU_Loai],[NgaySuDung],[ManHinh_Loai],[ManHinh_NgaySuDung],[UPS_Loai],[UPS_NgaySuDung],[NguoiSuDung],[SoThe] FROM [dbo].[IT_LyLichMayTinh] WHERE 1=1"
					+ malylich + donvi + loai + " ORDER BY [MaSoDonVi] ASC";

			ResultSet result = state.executeQuery(select);
			int stt = 0;
			int vitrix = 0;
			int vitriy = 1;
			int vitridong = 0;
			int countlabelprint = 0;
			listpanelContainLabel.clear();
			panel.removeAll();
			panel.repaint();

			JPanel panelContainLabel0 = new JPanel();
			panelContainLabel0.setBackground(Color.LIGHT_GRAY);
			panelContainLabel0.setBounds(36, 69, 796, 20);

			listpanelContainLabel.add(panelContainLabel0);

			panel.add(listpanelContainLabel.get(0));
			scrollPane.setViewportView(panel);

			// frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
			panelContainLabel0.setAutoscrolls(true);
			panelContainLabel0.setPreferredSize(new Dimension(796, 1000));
			panelContainLabel0.setLayout(null);

			int vitripanely = 1191;

			while (result.next()) {
				String cpungaysudung = result.getString(4).isEmpty() ? ""
						: ConvertDate.convertDate(result.getString(5));
				String monitorngaysudung = result.getString(6).isEmpty() ? ""
						: ConvertDate.convertDate(result.getString(7));
				String upsngaysudung = result.getString(8).isEmpty() ? ""
						: ConvertDate.convertDate(result.getString(9));

				// điền thông tin cho panel sub label
				if (comboBoxType.getSelectedIndex() == -1 || comboBoxType.getSelectedItem().toString().equals("CPU")) {
					if (!result.getString(4).isEmpty()&&!result.getString(5).equals("1990-01-01")) {
						if (vitriy == 1) {
							vitrix = 0;
							vitriy++;
						} else if (vitriy == 2) {
							vitrix = 264;
							vitriy++;
						} else if (vitriy == 3) {
							vitrix = 528;
							vitriy = 1;
						}
						countlabelprint++;

						if (countlabelprint > 1 && countlabelprint % 21 == 1) {
							JPanel panelContainLabel = new JPanel();
							panelContainLabel.setBackground(Color.LIGHT_GRAY);
							panelContainLabel.setBounds(36, vitripanely, 796, 20);

							listpanelContainLabel.add(panelContainLabel);
							stt++;
							panel.add(panelContainLabel);
							vitripanely = vitripanely + 1000;

							panelContainLabel.setAutoscrolls(true);
							panelContainLabel.setPreferredSize(new Dimension(796, 1000));
							panelContainLabel.setLayout(null);

							vitrix = 0;
							vitriy = 2;
							vitridong = 0;
						}

						createPanelSubLabel(listpanelContainLabel.get(stt), vitrix + 4, vitridong + 4, "CPU",
								result.getString(10), result.getString(11), result.getString(2), cpungaysudung,
								"C" + result.getString(3));

						if (vitriy == 1) {
							vitridong = vitridong + 139;

						}
					}
				}

				if (comboBoxType.getSelectedIndex() == -1
						|| comboBoxType.getSelectedItem().toString().equals("Monitor")) {
					if (!result.getString(6).isEmpty()&&!result.getString(7).equals("1990-01-01")) {
						if (vitriy == 1) {
							vitrix = 0;
							vitriy++;
						} else if (vitriy == 2) {
							vitrix = 264;
							vitriy++;
						} else if (vitriy == 3) {
							vitrix = 528;
							vitriy = 1;
						}
						countlabelprint++;

						if (countlabelprint > 1 && countlabelprint % 21 == 1) {
							JPanel panelContainLabel = new JPanel();
							panelContainLabel.setBackground(Color.LIGHT_GRAY);
							panelContainLabel.setBounds(36, vitripanely, 796, 20);
							panelContainLabel.setLayout(null);
							listpanelContainLabel.add(panelContainLabel);
							stt++;
							panel.add(panelContainLabel);
							vitripanely = vitripanely + 1000;

							panelContainLabel.setAutoscrolls(true);
							panelContainLabel.setPreferredSize(new Dimension(796, 1000));
							panelContainLabel.setLayout(null);

							vitrix = 0;
							vitriy = 2;
							vitridong = 0;
						}

						createPanelSubLabel(listpanelContainLabel.get(stt), vitrix + 4, vitridong + 4, "Monitor",
								result.getString(10), result.getString(11), result.getString(2), monitorngaysudung,
								"M" + result.getString(3));

						if (vitriy == 1) {
							vitridong = vitridong + 139;
						}
					}
				}

				if (comboBoxType.getSelectedIndex() == -1 || comboBoxType.getSelectedItem().toString().equals("UPS")) {
					if (!result.getString(8).isEmpty()&&!result.getString(9).equals("1990-01-01")) {
						if (vitriy == 1) {
							vitrix = 0;
							vitriy++;
						} else if (vitriy == 2) {
							vitrix = 264;
							vitriy++;
						} else if (vitriy == 3) {
							vitrix = 528;
							vitriy = 1;
						}
						countlabelprint++;

						if (countlabelprint > 1 && countlabelprint % 21 == 1) {
							JPanel panelContainLabel = new JPanel();
							panelContainLabel.setBackground(Color.LIGHT_GRAY);
							panelContainLabel.setBounds(36, vitripanely, 796, 20);

							listpanelContainLabel.add(panelContainLabel);
							stt++;
							panel.add(panelContainLabel);
							vitripanely = vitripanely + 1000;

							panelContainLabel.setAutoscrolls(true);
							panelContainLabel.setPreferredSize(new Dimension(796, 1000));
							panelContainLabel.setLayout(null);

							vitrix = 0;
							vitriy = 2;
							vitridong = 0;
						}

						createPanelSubLabel(listpanelContainLabel.get(stt), vitrix + 4, vitridong + 4, "UPS",
								result.getString(10), result.getString(11), result.getString(2), upsngaysudung,
								"U" + result.getString(3));

						if (vitriy == 1) {
							vitridong = vitridong + 139;

						}
					}

				}
			}

			frame.repaint();
			result.close();
			state.close();
			connect.closeStatement();

		} catch (SQLException se) {

		}
	}

	// -------------------------------------------------------------------------------------------
	public void setLanguage(int language) {
		ngonngu = language;
	}

	// hàm tạo composite sub label
	// -------------------------------------------------------------------------------------------
	public void createPanelSubLabel(JPanel panelPrintQRCode, int x, int y, String tenthietbi, String hoten,
			String sothe, String donvi, String ngaysudung, String masothietbi) {
		panelPrintQRCode.setLayout(null);
		// x,y là vị trí của compositeSubLabel trong compositePrintQRCode
		JPanel panelSubLabel = new JPanel();
		panelSubLabel.setBackground(Color.WHITE);
		panelSubLabel.setBounds(x, y, 260, 135);
		panelSubLabel.setLayout(null);
		panelSubLabel.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lbc1TenThietBi = new JLabel();
		lbc1TenThietBi.setBackground(Color.WHITE);
		lbc1TenThietBi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1TenThietBi.setBounds(10, 10, 64, 25);
		lbc1TenThietBi.setText("Tên thiết bị:");
		panelSubLabel.add(lbc1TenThietBi);

		JLabel lbc1TenThietBi2 = new JLabel();
		lbc1TenThietBi2.setBackground(Color.WHITE);
		lbc1TenThietBi2.setText(tenthietbi);
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
		lbc1DonVi2.setText(donvi);
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
		lbc1NgaySuDung2.setText(ngaysudung);
		lbc1NgaySuDung2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1NgaySuDung2.setBounds(95, 70, 100, 25);
		panelSubLabel.add(lbc1NgaySuDung2);

		JLabel lbc1MaSoThietBi = new JLabel();
		lbc1MaSoThietBi.setBackground(Color.WHITE);
		lbc1MaSoThietBi.setText("Mã số thiết bị:");
		lbc1MaSoThietBi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1MaSoThietBi.setBounds(10, 100, 78, 25);
		panelSubLabel.add(lbc1MaSoThietBi);

		JLabel lbc1MaSoThietBi2 = new JLabel();
		lbc1MaSoThietBi2.setBackground(Color.WHITE);
		lbc1MaSoThietBi2.setText(masothietbi);
		lbc1MaSoThietBi2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbc1MaSoThietBi2.setBounds(90, 100, 105, 25);
		panelSubLabel.add(lbc1MaSoThietBi2);

		JLabel lbc1LogoLHG = new JLabel();
		lbc1LogoLHG.setBackground(Color.WHITE);
		lbc1LogoLHG.setIcon(new ImageIcon(PrintComputerProfile.class.getResource("/QRCode/image/lacty70x50.png")));
		lbc1LogoLHG.setBounds(185, 5, 70, 50);
		panelSubLabel.add(lbc1LogoLHG);

		JLabel lbc1Qrcode = new JLabel();
		lbc1Qrcode.setBounds(185, 60, 70, 70);
		// tạo ảnh QR code
		// BufferedImage image = QRCode.QRCodeGenerator(contentQRCode(tenthietbi,
		// hoten,sothe, donvi, ngaysudung, masothietbi), 70, 70);
		BufferedImage image = null;
		try {
			image = QR.createQR(contentQRCode(tenthietbi, hoten, sothe, donvi, ngaysudung, masothietbi), 100, 100);
		} catch (Exception e) {
		}

		lbc1Qrcode.setIcon(new ImageIcon(ResizeImage.resizeImageQuality(image, 70, 70)));
		panelSubLabel.add(lbc1Qrcode);

		panelPrintQRCode.add(panelSubLabel);
	}

	// điền nội dung ảnh QR Code
	// ---------------------------------------------------------------------------------------
	public String contentQRCode(String tenthietbi, String hoten, String sothe, String donvi, String ngaysudung,
			String masothietbi) {
		String result = "Tên thiết bị: ";
		result = result + tenthietbi + "\n" + "Tên-ST: " + hoten + " - " + sothe + "\n" + "Đơn vị: " + donvi + "\n"
				+ "Ngày sử dụng: " + ngaysudung + "\n" + "Mã số thiết bị: " + masothietbi;
		return result;
	}
}
