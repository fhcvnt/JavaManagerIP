package itmanagerip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import accessories.DanhMucThietBi;
import accessories.MuaThietBiChoDonViKhac;
import accessories.NhanSuaThietBi;
import accessories.PhatThietBi;
import accessories.ThayTheLinhKien;
import accessories.ThietBiTrongKhoIT;
import broken.BaoPhe;
import broken.DanhSachThietBiHu;
import it.LyLichMayTinh;
import it.MuaThietBi;
import it.MuonCamera;
import it.MuonLaptop;
import it.MuonThietBiLinhKien;
import it.MuonThietBiPhongHop;
import it.NhanVienMuonCameraYeezy;
import it.PasswordError;
import it.PhanCongCongViec;
import it.ThietBiChoMuon;
import notification.XuLyDeHienThiThongBao;
import swing.PrintComputerProfile;

public class MainWindowManagerIP {

	protected Shell shell;
	// language=0: Tieng Viet, language=1: Tieng Anh
	private int language = 1;
	private String version = "V1.3.7.20201016";
	private String groupname = "Admin";
	private String personname = "";
	private String userlogin = "21608"; // mã Người dùng đăng nhập, sẽ dùng ghi vào cột Người cập nhật

	public static void main(String[] args) {
		try {
			MainWindowManagerIP window = new MainWindowManagerIP();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ***********************************************************************************************************
	// gan ngon ngu
	public void setLanguage(int ngonnu) {
		language = ngonnu;
	}

	// ***********************************************************************************************************
	// gan ngon ngu
	public void setGroupName(String group, String user, String tennguoidung, String phienban) {
		groupname = group;
		userlogin = user;
		personname = tennguoidung;
		version = phienban;
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell();
		shell.setSize(809, 431);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setMaximized(true);
		shell.setText("IT Manager - " + version + " - " + personname + " (" + userlogin + ")" + " - " + groupname);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/IP64.png"));

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("&File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmUser = new MenuItem(menu_1, SWT.NONE);
		mntmUser.setText("Users");
		mntmUser.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/user25.png"));

		MenuItem mntmPersoninfo = new MenuItem(menu_1, SWT.NONE);
		mntmPersoninfo.setText("Person info");
		mntmPersoninfo.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/personinfo.png"));

		MenuItem mntmStatistic = new MenuItem(menu_1, SWT.NONE);
		mntmStatistic.setText("Statistic");
		mntmStatistic
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/char25.png"));

		MenuItem mntmUpdate = new MenuItem(menu_1, SWT.NONE);
		mntmUpdate.setText("Update");
		mntmUpdate
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/update.png"));

		MenuItem mntmClose = new MenuItem(menu_1, SWT.NONE);
		mntmClose.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/exit.png"));

		mntmClose.setText("Exit\tCtrl + Q");
		mntmClose.setAccelerator(SWT.MOD1 + 'Q');

		MenuItem mntmIp = new MenuItem(menu, SWT.CASCADE);
		mntmIp.setText("&IP");

		Menu menu_2 = new Menu(mntmIp);
		mntmIp.setMenu(menu_2);

		MenuItem mntmIpList = new MenuItem(menu_2, SWT.NONE);
		mntmIpList.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/list.png"));
		mntmIpList.setText("IP List\tCtrl + L");
		mntmIpList.setAccelerator(SWT.MOD1 + 'L');

		MenuItem mntmGetIP = new MenuItem(menu_2, SWT.NONE);
		mntmGetIP.setText("Get IP");
		mntmGetIP.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/button/get ip25.png"));
		mntmGetIP.setAccelerator(262220);

		MenuItem mntmDepartment = new MenuItem(menu, SWT.CASCADE);
		mntmDepartment.setText("&Department");

		Menu menu_4 = new Menu(mntmDepartment);
		mntmDepartment.setMenu(menu_4);

		MenuItem mntmDepartmentList = new MenuItem(menu_4, SWT.NONE);
		mntmDepartmentList
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/dept.png"));
		mntmDepartmentList.setText("Department List\t Ctrl + D");
		mntmDepartmentList.setAccelerator(SWT.MOD1 + 'D');

		MenuItem mntmBuilding = new MenuItem(menu, SWT.CASCADE);
		mntmBuilding.setText("&Building");

		Menu menu_5 = new Menu(mntmBuilding);
		mntmBuilding.setMenu(menu_5);

		MenuItem mntmListBuilding = new MenuItem(menu_5, SWT.NONE);
		mntmListBuilding.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/building.png"));
		mntmListBuilding.setText("Building List\tCtrl + B");
		mntmListBuilding.setAccelerator(SWT.MOD1 + 'B');

		MenuItem mntmMachineType = new MenuItem(menu, SWT.CASCADE);
		mntmMachineType.setText("&Machine Type");

		Menu menu_9 = new Menu(mntmMachineType);
		mntmMachineType.setMenu(menu_9);

		MenuItem mntmMachineTypeList = new MenuItem(menu_9, SWT.NONE);
		mntmMachineTypeList.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/robot vang25.png"));

		mntmMachineTypeList.setText("Machine Type List\tCtrl + M");
		mntmMachineTypeList.setAccelerator(SWT.MOD1 + 'M');

		MenuItem mntmWifi = new MenuItem(menu_9, SWT.NONE);
		mntmWifi.setText("Wifi");
		mntmWifi.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/wifi25.png"));
		mntmWifi.setAccelerator(262221);

		MenuItem mntmOperatorSystem = new MenuItem(menu, SWT.CASCADE);
		mntmOperatorSystem.setText("Operator &System");

		Menu menu_10 = new Menu(mntmOperatorSystem);
		mntmOperatorSystem.setMenu(menu_10);

		MenuItem mntmOperatorSystemList = new MenuItem(menu_10, SWT.NONE);
		mntmOperatorSystemList
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/os.png"));

		mntmOperatorSystemList.setText("Operator System List\tCtrl + O");
		mntmOperatorSystemList.setAccelerator(SWT.MOD1 + 'O');

		MenuItem mntmOffice = new MenuItem(menu, SWT.CASCADE);
		mntmOffice.setText("O&ffice");

		Menu menu_11 = new Menu(mntmOffice);
		mntmOffice.setMenu(menu_11);

		MenuItem mntmOfficeList = new MenuItem(menu_11, SWT.NONE);
		mntmOfficeList
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/office.png"));

		mntmOfficeList.setText("Office List\tAlt + O");
		mntmOfficeList.setAccelerator(SWT.MOD3 + 'O');

		MenuItem mntmItSuggestion = new MenuItem(menu, SWT.CASCADE);
		mntmItSuggestion.setText("Suggestion form");

		Menu menu_7 = new Menu(mntmItSuggestion);
		mntmItSuggestion.setMenu(menu_7);

		MenuItem mntmInternet = new MenuItem(menu_7, SWT.NONE);
		mntmInternet.setText("Internet");
		mntmInternet.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/internet25.png"));
		mntmInternet.setAccelerator(262226);

		MenuItem mntmSoftware = new MenuItem(menu_7, SWT.NONE);
		mntmSoftware.setText("Software");
		mntmSoftware
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/soft25.png"));
		mntmSoftware.setAccelerator(262226);

		MenuItem mntmDataShare = new MenuItem(menu_7, SWT.NONE);
		mntmDataShare.setText("Data Share");
		mntmDataShare.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/datashare25.png"));
		mntmDataShare.setAccelerator(262226);

		MenuItem mntmMail = new MenuItem(menu_7, SWT.NONE);
		mntmMail.setText("Mail");
		mntmMail.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/gmail25.png"));
		mntmMail.setAccelerator(262226);

		MenuItem mntmUsb = new MenuItem(menu_7, SWT.NONE);
		mntmUsb.setText("USB");
		mntmUsb.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/usb25.png"));
		mntmUsb.setAccelerator(262226);

		MenuItem mntmIT = new MenuItem(menu, SWT.CASCADE);
		mntmIT.setText("IT");

		Menu menuIT = new Menu(mntmIT);
		mntmIT.setMenu(menuIT);

		MenuItem mntmWorkAssignment = new MenuItem(menuIT, SWT.NONE);
		mntmWorkAssignment.setText("Work assignment");
		mntmWorkAssignment.setAccelerator(65605);
		mntmWorkAssignment.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/workassignment.png"));

		MenuItem mntmComputerProfile = new MenuItem(menuIT, SWT.NONE);
		mntmComputerProfile.setText("Computer profile");
		mntmComputerProfile.setAccelerator(65605);
		mntmComputerProfile.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/computerprofile.png"));

		MenuItem mntmPrintLabelProfile = new MenuItem(menuIT, SWT.NONE);
		mntmPrintLabelProfile.setText("Print label profile");
		mntmPrintLabelProfile.setAccelerator(65605);
		mntmPrintLabelProfile.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/printlabel.png"));

		MenuItem mntmDeviceForBorrow = new MenuItem(menuIT, SWT.NONE);
		mntmDeviceForBorrow.setText("Devices for borrow");
		mntmDeviceForBorrow.setAccelerator(65605);
		mntmDeviceForBorrow.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/devicefoborrow.png"));

		MenuItem mntmStaffBorrowCamera = new MenuItem(menuIT, SWT.NONE);
		mntmStaffBorrowCamera.setText("Staff borrow camera Yeezy");
		mntmStaffBorrowCamera.setAccelerator(65605);
		mntmStaffBorrowCamera
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/staff.png"));

		MenuItem mntmBorrowCamera = new MenuItem(menuIT, SWT.NONE);
		mntmBorrowCamera.setText("Borrow camera");
		mntmBorrowCamera.setAccelerator(65605);
		mntmBorrowCamera
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/camera.png"));

		MenuItem mntmBorrowMeetingRoom = new MenuItem(menuIT, SWT.NONE);
		mntmBorrowMeetingRoom.setText("Borrow meeting room equipment");
		mntmBorrowMeetingRoom.setAccelerator(65605);
		mntmBorrowMeetingRoom
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/meeting.png"));

		MenuItem mntmBorrowAccessories = new MenuItem(menuIT, SWT.NONE);
		mntmBorrowAccessories.setText("Borrow accessories");
		mntmBorrowAccessories.setAccelerator(65605);
		mntmBorrowAccessories.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/accessories.png"));

		MenuItem mntmBorrowLaptop = new MenuItem(menuIT, SWT.NONE);
		mntmBorrowLaptop.setText("Borrow laptop");
		mntmBorrowLaptop
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/laptop.png"));
		mntmBorrowLaptop.setAccelerator(65605);

		MenuItem mntmBuyDevices = new MenuItem(menuIT, SWT.NONE);
		mntmBuyDevices.setText("Buy devices");
		mntmBuyDevices
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/buy.png"));

		MenuItem mntmPasswordError = new MenuItem(menuIT, SWT.NONE);
		mntmPasswordError.setText("Password error");
		mntmPasswordError
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/passworderror.png"));

		MenuItem mntmReportBroken = new MenuItem(menu, SWT.CASCADE);
		mntmReportBroken.setText("Broken report");

		Menu menuBrokenReport = new Menu(mntmReportBroken);
		mntmReportBroken.setMenu(menuBrokenReport);

		MenuItem mntmBrokenDevice = new MenuItem(menuBrokenReport, SWT.NONE);
		mntmBrokenDevice.setText("Broken device");
		mntmBrokenDevice.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/broken device.png"));
		mntmBrokenDevice.setAccelerator(65605);

		MenuItem mntmDeviceDestructionList = new MenuItem(menuBrokenReport, SWT.NONE);
		mntmDeviceDestructionList.setText("Device destruction list");
		mntmDeviceDestructionList.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class,
				"/itmanagerip/Icon/menu/Device destruction.png"));
		mntmDeviceDestructionList.setAccelerator(65605);

		MenuItem mntmAccessories = new MenuItem(menu, SWT.CASCADE);
		mntmAccessories.setText("Accessories");

		Menu menuAccessories = new Menu(mntmAccessories);
		mntmAccessories.setMenu(menuAccessories);

		MenuItem mntmReplacementAccessories = new MenuItem(menuAccessories, SWT.NONE);
		mntmReplacementAccessories.setText("Replacement accessories");
		mntmReplacementAccessories.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class,
				"/itmanagerip/Icon/menu/ReplacementAccessories.png"));
		mntmReplacementAccessories.setAccelerator(65605);

		MenuItem mntmBuyDevice = new MenuItem(menuAccessories, SWT.NONE);
		mntmBuyDevice.setText("Buy device for other department");
		mntmBuyDevice.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/buy device.png"));
		mntmBuyDevice.setAccelerator(65605);

		MenuItem mntmRepairDevice = new MenuItem(menuAccessories, SWT.NONE);
		mntmRepairDevice.setText("Repair device");
		mntmRepairDevice.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/repair device.png"));
		mntmRepairDevice.setAccelerator(65605);

		MenuItem mntmCatalogOfDevice = new MenuItem(menuAccessories, SWT.NONE);
		mntmCatalogOfDevice.setText("Catalog of device");
		mntmCatalogOfDevice
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/catalog.png"));
		mntmCatalogOfDevice.setAccelerator(65605);

		MenuItem mntmDeviceInITWarehouse = new MenuItem(menuAccessories, SWT.NONE);
		mntmDeviceInITWarehouse.setText("Device in IT warehouse");
		mntmDeviceInITWarehouse.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class,
				"/itmanagerip/Icon/menu/device in IT warehouse.png"));
		mntmDeviceInITWarehouse.setAccelerator(65605);

		MenuItem mntmGiveTheDevice = new MenuItem(menuAccessories, SWT.NONE);
		mntmGiveTheDevice.setText("Give the device");
		mntmGiveTheDevice.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/give the device.png"));
		mntmGiveTheDevice.setAccelerator(65605);

		MenuItem mntmLanguage = new MenuItem(menu, SWT.CASCADE);
		mntmLanguage.setText("&Language");

		Menu menuLanguage = new Menu(mntmLanguage);
		mntmLanguage.setMenu(menuLanguage);

		MenuItem mntmEnglish = new MenuItem(menuLanguage, SWT.NONE);
		mntmEnglish.setImage(
				SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/englisht.png"));
		mntmEnglish.setText("English\tAlt + E");
		mntmEnglish.setAccelerator(SWT.MOD3 + 'E');

		MenuItem mntmVietnamese = new MenuItem(menuLanguage, SWT.NONE);
		mntmVietnamese
				.setImage(SWTResourceManager.getImage(MainWindowManagerIP.class, "/itmanagerip/Icon/menu/Vietnam.png"));
		mntmVietnamese.setText("Việt Nam\tAlt + V");
		mntmVietnamese.setAccelerator(SWT.MOD3 + 'V');

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		CTabFolder tabFolder = new CTabFolder(composite, SWT.TOP);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		tabFolder.setSelectionBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		// ngon ngu
		if (language == 0) {
			shell.setText("IT Quản lý - " + version + " - " + personname + " (" + userlogin + ")" + " - " + groupname);
			mntmFile.setText("Tệp");
			mntmUser.setText("Người dùng");
			mntmPersoninfo.setText("Thông tin nhân viên");
			mntmStatistic.setText("Thống kê");
			mntmUpdate.setText("Cập nhật");
			mntmClose.setText("Thoát\tCtrl + Q");
			mntmIp.setText("IP");
			mntmIpList.setText("Danh sách IP\tCtrl + L");
			mntmGetIP.setText("Lấy IP");
			mntmDepartment.setText("Đơn vị");
			mntmDepartmentList.setText("Danh sách đơn vị\t Ctrl + D");
			mntmBuilding.setText("Tòa nhà");
			mntmListBuilding.setText("Danh sách tòa nhà\tCtrl + B");
			mntmMachineType.setText("Loại máy");
			mntmMachineTypeList.setText("Danh sách loại máy\tCtrl + M");
			mntmOperatorSystem.setText("Hệ điều hành");
			mntmOperatorSystemList.setText("Danh sách hệ điều hành\tCtrl + O");
			mntmOffice.setText("Office");
			mntmOfficeList.setText("Danh sách Office\tAlt + O");
			mntmItSuggestion.setText("Phiếu đề nghị");
			mntmInternet.setText("Mạng");
			mntmSoftware.setText("Phần mềm");
			mntmDataShare.setText("Ổ đĩa dùng chung");
			mntmMail.setText("Mail");
			mntmWorkAssignment.setText("Phân công công việc");
			mntmComputerProfile.setText("Lý lịch máy tính");
			mntmPrintLabelProfile.setText("In nhãn lý lịch");
			mntmDeviceForBorrow.setText("Thiết bị cho mượn");
			mntmStaffBorrowCamera.setText("Nhân viên mượn máy ảnh Yeezy");
			mntmBorrowCamera.setText("Mượn máy ảnh");
			mntmBorrowMeetingRoom.setText("Mượn thiết bị phòng họp");
			mntmBorrowAccessories.setText("Mượn linh kiện");
			mntmBorrowLaptop.setText("Mượn laptop");
			mntmBuyDevices.setText("Mua thiết bị");
			mntmPasswordError.setText("Sai mật khẩu");
			mntmReportBroken.setText("Báo phế");
			mntmBrokenDevice.setText("Thiết bị hư");
			mntmDeviceDestructionList.setText("Danh sách thiết bị báo phế");
			mntmAccessories.setText("Linh kiện");
			mntmReplacementAccessories.setText("Thay thế linh kiện");
			mntmBuyDevice.setText("Mua thiết bị cho đơn vị khác");
			mntmRepairDevice.setText("Sửa thiết bị");
			mntmCatalogOfDevice.setText("Danh mục thiết bị");
			mntmDeviceInITWarehouse.setText("Thiết bị trong kho IT");
			mntmGiveTheDevice.setText("Phát thiết bị");
			mntmLanguage.setText("Ngôn ngữ");
		} else {
			shell.setText("IT Manager - " + version + " - " + personname + " (" + userlogin + ")" + " - " + groupname);
			mntmFile.setText("&File");
			mntmUser.setText("Users");
			mntmPersoninfo.setText("Person info");
			mntmStatistic.setText("Statistic");
			mntmUpdate.setText("Update");
			mntmClose.setText("Exit\tCtrl + Q");
			mntmIp.setText("&IP");
			mntmIpList.setText("IP List\tCtrl + L");
			mntmGetIP.setText("Get IP");
			mntmDepartment.setText("&Department");
			mntmDepartmentList.setText("Department List\t Ctrl + D");
			mntmBuilding.setText("&Building");
			mntmListBuilding.setText("Building List\tCtrl + B");
			mntmMachineType.setText("&Machine Type");
			mntmMachineTypeList.setText("Machine Type List\tCtrl + M");
			mntmOperatorSystem.setText("Operator &System");
			mntmOperatorSystemList.setText("Operator System List\tCtrl + O");
			mntmOffice.setText("O&ffice");
			mntmOfficeList.setText("Office List\tAlt + O");
			mntmItSuggestion.setText("Suggestion form");
			mntmInternet.setText("Inernet");
			mntmSoftware.setText("Software");
			mntmDataShare.setText("Data Share");
			mntmMail.setText("Mail");

			mntmWorkAssignment.setText("Work assignment");
			mntmComputerProfile.setText("Computer profile");
			mntmPrintLabelProfile.setText("Print label profile");
			mntmDeviceForBorrow.setText("Devices for borrow");
			mntmStaffBorrowCamera.setText("Staff borrow camera Yeezy");
			mntmBorrowCamera.setText("Borrow camera");
			mntmBorrowMeetingRoom.setText("Borrow meeting room equipment");
			mntmBorrowAccessories.setText("Borrow accessories");
			mntmBorrowLaptop.setText("Borrow laptop");
			mntmBuyDevices.setText("Buy devices");
			mntmPasswordError.setText("Password error");
			mntmReportBroken.setText("Broken report");
			mntmBrokenDevice.setText("Broken device");
			mntmDeviceDestructionList.setText("Device destruction list");
			mntmAccessories.setText("Accessories");
			mntmReplacementAccessories.setText("Replacement accessories");
			mntmBuyDevice.setText("Buy device for other department");
			mntmRepairDevice.setText("Repair device");
			mntmCatalogOfDevice.setText("Catalog of device");
			mntmDeviceInITWarehouse.setText("Device in IT warehouse");
			mntmGiveTheDevice.setText("Give the device");
			mntmLanguage.setText("&Language");
		}
		mntmUpdate.setEnabled(false);

		// xử lý hiện thông báo phân công xử lý công việc
		XuLyDeHienThiThongBao xulythongbao = new XuLyDeHienThiThongBao();
		xulythongbao.createContents(userlogin);

		// Event phím tắt (ctrl + U)
		tabFolder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (groupname.equals("Admin") || groupname.equals("Manager")) {
					if (e.keyCode == 'u' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.ALT) {
						mntmUpdate.setEnabled(true);
					} else if (e.keyCode == 'u' && (e.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) {
						mntmUpdate.setEnabled(false);
					}
				}
			}
		});

		// kiem tra neu la user thi khong cho chinh sua mot vai chuc nang
		if (!(groupname.equals("Admin") || groupname.equals("Manager") || groupname.equals("Translate"))) {
			mntmWifi.setEnabled(false);
			mntmOperatorSystemList.setEnabled(false);
			mntmOfficeList.setEnabled(false);
			mntmItSuggestion.setEnabled(false);
			mntmComputerProfile.setEnabled(false);
			mntmPrintLabelProfile.setEnabled(false);
			mntmDeviceForBorrow.setEnabled(false);
			mntmStaffBorrowCamera.setEnabled(false);
			mntmBorrowCamera.setEnabled(false);
			mntmBorrowMeetingRoom.setEnabled(false);
			mntmBorrowAccessories.setEnabled(false);
			mntmBorrowLaptop.setEnabled(false);
		}

		// Mở cửa sổ Người dùng
		// ===================================================================================
		mntmUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Người dùng")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("User")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					Users user = new Users();
					user.showTabfolder(tabFolder, shell, groupname, userlogin, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);
				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Thông tin nhân viên
		// ===================================================================================
		mntmPersoninfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Thông tin nhân viên")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Person info")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PersonInfo person = new PersonInfo();
					person.showTabFolder(tabFolder, shell, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);
				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Thống kê
		// ===================================================================================
		mntmStatistic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Thống kê")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Statistic")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					Statistic statistic = new Statistic();
					statistic.showTabFolder(tabFolder, shell, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);
				} catch (Exception ex) {
				}
			}
		});

		// Cập nhật
		// -----------------------------------------------------------------------------
		mntmUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Cập nhật")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Update")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					UpdateSoftware update = new UpdateSoftware();
					update.createContentsTabfolder(tabFolder, shell, groupname, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Đóng ứng dụng Exit Window
		// -----------------------------------------------------------------------------
		mntmClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// System.exit(0);
				Display.getDefault().dispose();
			}
		});

		// Mở cửa sổ IP List
		// ===================================================================================
		mntmIpList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (itemrun.getText().equals("IP")) {
						tabFolder.setSelection(i);
						return;
					}
					i++;
				}
				try {

					if (Display.getDefault().getBounds().width > 1900) {
						IPList iplist = new IPList();
						iplist.showTabFolder(tabFolder, shell, groupname, userlogin, language);
						tabFolder.setSelection(tabFolder.getItemCount() - 1);
					} else {
						IPList1366x768 iplist = new IPList1366x768();
						iplist.showTabFolder(tabFolder, shell, groupname, userlogin, language);
						tabFolder.setSelection(tabFolder.getItemCount() - 1);
					}

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Get IP
		// ===================================================================================
		mntmGetIP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GetIP getip = new GetIP();
				getip.setLanguage(language);
				getip.open();
			}
		});

		// Mở cửa sổ Đơn vị
		// ==================================================================================
		mntmDepartmentList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Đơn vị")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Department")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					Department department = new Department();
					department.showTabFolder(tabFolder, shell, groupname, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Building
		// ----------------------------------------------------------------------------------
		mntmListBuilding.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Tòa nhà")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Building")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					Building building = new Building();
					building.showTabFolder(tabFolder, shell, groupname, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Loại Máy - Machine Type
		// ------------------------------------------------------------------
		mntmMachineTypeList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Loại máy")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Machine type")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					MachineType machine = new MachineType();
					machine.showTabFolder(tabFolder, shell, groupname, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Wifi
		// ------------------------------------------------------------------
		mntmWifi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Wifi")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Wifi")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					Wifi wifi = new Wifi();
					wifi.showTabFolder(tabFolder, shell, groupname, userlogin, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);
				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Hệ điều hành
		// ---------------------------------------------------------------------
		mntmOperatorSystemList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Hệ điều hành")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Operator system")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					OperatorSystem os = new OperatorSystem();
					os.showTabFolder(tabFolder, shell, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Office
		// ----------------------------------------------------------------------------
		mntmOfficeList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (itemrun.getText().equals("Office")) {
						tabFolder.setSelection(i);
						return;
					}
					i++;
				}
				try {
					Office office = new Office();
					office.showTabFolder(tabFolder, shell, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ phiếu đề nghị Internet
		// ----------------------------------------------------------------------------
		mntmInternet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mạng")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Internet")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PDNInternet internet = new PDNInternet();
					internet.showTabFolder(tabFolder, shell, userlogin, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ phiếu đề nghị Software
		// ----------------------------------------------------------------------------
		mntmSoftware.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Phần mềm")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Software")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PDNSoftware soft = new PDNSoftware();
					soft.showTabFolder(tabFolder, shell, userlogin, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ phiếu đề nghị Data Share
		// ----------------------------------------------------------------------------
		mntmDataShare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Ổ đĩa dùng chung")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Data share")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PDNDataShare datashare = new PDNDataShare();
					datashare.showTabFolder(tabFolder, shell, userlogin, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ phiếu đề nghị Mail
		// ----------------------------------------------------------------------------
		mntmMail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mail")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Mail")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PDNMail mail = new PDNMail();
					mail.showTabFolder(tabFolder, shell, userlogin, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);
				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ phiếu đề nghị USB
		// ----------------------------------------------------------------------------
		mntmUsb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (itemrun.getText().equals("USB")) {
						tabFolder.setSelection(i);
						return;
					}
					i++;
				}
				try {
					PDNUSB usb = new PDNUSB();
					usb.showTabFolder(tabFolder, shell, userlogin, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT phân công công việc
		// ----------------------------------------------------------------------------
		mntmWorkAssignment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Phân công công việc")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Work assignment")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PhanCongCongViec congviec = new PhanCongCongViec();
					congviec.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT Lý lịch máy tính
		// ----------------------------------------------------------------------------
		mntmComputerProfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Lý lịch máy tính")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Computer profile")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					LyLichMayTinh lylichmaytinh = new LyLichMayTinh();
					lylichmaytinh.setData(language, userlogin);
					lylichmaytinh.showTabFolder(tabFolder, shell, language, userlogin);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT in nhãn lý lịch
		// ----------------------------------------------------------------------------
		mntmPrintLabelProfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PrintComputerProfile inlylich = new PrintComputerProfile();
					inlylich.setVisibleFrame();
				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT thiết bị cho mượn
		// ----------------------------------------------------------------------------
		mntmDeviceForBorrow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Thiết bị cho mượn")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Device for borrow")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					ThietBiChoMuon lylichmaytinh = new ThietBiChoMuon();
					lylichmaytinh.showTabFolder(tabFolder, shell, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT nhân viên mượn máy ảnh yeezy
		// ----------------------------------------------------------------------------
		mntmStaffBorrowCamera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Nhân viên mượn máy ảnh Yeezy")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Staff borrow camera Yeezy")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					NhanVienMuonCameraYeezy nhanvien = new NhanVienMuonCameraYeezy();
					nhanvien.showTabFolder(tabFolder, shell, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT mượn camera
		// ----------------------------------------------------------------------------
		mntmBorrowCamera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mượn máy ảnh")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Borrow camera")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					MuonCamera camera = new MuonCamera();
					camera.showTabFolder(tabFolder, shell, language, userlogin);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT mượn thiết bị phòng họp
		// ----------------------------------------------------------------------------
		mntmBorrowMeetingRoom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mượn thiết bị phòng họp")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Borrow meeting room equipment")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					MuonThietBiPhongHop device = new MuonThietBiPhongHop();
					device.showTabFolder(tabFolder, shell, language, userlogin);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT mượn linh kiện
		// ----------------------------------------------------------------------------
		mntmBorrowAccessories.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mượn linh kiện")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Borrow accessories")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					MuonThietBiLinhKien device = new MuonThietBiLinhKien();
					device.showTabFolder(tabFolder, shell, language, userlogin);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT mượn laptop
		// ----------------------------------------------------------------------------
		mntmBorrowLaptop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mượn laptop")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Borrow laptop")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					MuonLaptop device = new MuonLaptop();
					device.showTabFolder(tabFolder, shell, language, userlogin);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT mua thiết bị
		// ----------------------------------------------------------------------------
		mntmBuyDevices.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mua thiết bị")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Buy device")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					MuaThietBi thietbi = new MuaThietBi();
					thietbi.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ IT sai mật khẩu
		// ----------------------------------------------------------------------------
		mntmPasswordError.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Sai mật khẩu")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Password error")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PasswordError password = new PasswordError();
					password.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Báo phế - thiết bị hư
		// ----------------------------------------------------------------------------
		mntmBrokenDevice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Thiết bị hư")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Broken device")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					DanhSachThietBiHu thietbihu = new DanhSachThietBiHu();
					thietbihu.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Báo phế - Báo phế
		// ----------------------------------------------------------------------------
		mntmDeviceDestructionList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Báo phế")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Destruction")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					BaoPhe baophe = new BaoPhe();
					baophe.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ linh kiện - Thay thế linh kiện
		// ----------------------------------------------------------------------------
		mntmReplacementAccessories.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Thay thế linh kiện")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Replacement accessories")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					ThayTheLinhKien thaythelinhkien = new ThayTheLinhKien();
					thaythelinhkien.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ linh kiện - mua linh kiện cho các đơn vị
		// ----------------------------------------------------------------------------
		mntmBuyDevice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Mua thiết bị cho các đơn vị")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Buy device for departments")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					MuaThietBiChoDonViKhac muathietbi = new MuaThietBiChoDonViKhac();
					muathietbi.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ sửa thiết bị
		// ----------------------------------------------------------------------------
		mntmRepairDevice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Sửa thiết bị")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Repair device")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					NhanSuaThietBi suathietbi = new NhanSuaThietBi();
					suathietbi.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ sửa danh mục thiết bị
		// ----------------------------------------------------------------------------
		mntmCatalogOfDevice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Danh mục thiết bị")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Catalog of device")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					DanhMucThietBi danhmuc = new DanhMucThietBi();
					danhmuc.showTabFolder(tabFolder, shell, language);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ Thiết bị trong kho IT
		// ----------------------------------------------------------------------------
		mntmDeviceInITWarehouse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Thiết bị trong kho IT")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Device in IT warehouse")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					ThietBiTrongKhoIT thietbi = new ThietBiTrongKhoIT();
					thietbi.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Mở cửa sổ phát thiết bị
		// ----------------------------------------------------------------------------
		mntmGiveTheDevice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = 0;
				for (CTabItem itemrun : tabFolder.getItems()) {
					if (language == 0) {
						if (itemrun.getText().equals("Phát thiết bị")) {
							tabFolder.setSelection(i);
							return;
						}
					} else {
						if (itemrun.getText().equals("Give the device")) {
							tabFolder.setSelection(i);
							return;
						}
					}
					i++;
				}
				try {
					PhatThietBi thietbi = new PhatThietBi();
					thietbi.showTabFolder(tabFolder, shell, language, userlogin, groupname);
					tabFolder.setSelection(tabFolder.getItemCount() - 1);

				} catch (Exception ex) {
				}
			}
		});

		// Thay đổi ngôn ngữ thành Tiếng Anh
		// -------------------------------------------------------------------------------------------------
		mntmEnglish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				shell.setText("IP Manager");
				mntmFile.setText("&File");
				mntmUser.setText("Users");
				mntmPersoninfo.setText("Person info");
				mntmStatistic.setText("Statistic");
				mntmUpdate.setText("Update");
				mntmClose.setText("Exit\tCtrl + Q");
				mntmIp.setText("&IP");
				mntmIpList.setText("IP List\tCtrl + L");
				mntmGetIP.setText("Get IP");
				mntmDepartment.setText("&Department");
				mntmDepartmentList.setText("Department List\t Ctrl + D");
				mntmBuilding.setText("&Building");
				mntmListBuilding.setText("Building List\tCtrl + B");
				mntmMachineType.setText("&Machine Type");
				mntmMachineTypeList.setText("Machine Type List\tCtrl + M");
				mntmOperatorSystem.setText("Operator &System");
				mntmOperatorSystemList.setText("Operator System List\tCtrl + O");
				mntmOffice.setText("O&ffice");
				mntmOfficeList.setText("Office List\tAlt + O");
				mntmItSuggestion.setText("Suggestion form");
				mntmInternet.setText("Inernet");
				mntmSoftware.setText("Software");
				mntmDataShare.setText("Data Share");
				mntmMail.setText("Mail");

				mntmWorkAssignment.setText("Work assignment");
				mntmComputerProfile.setText("Computer profile");
				mntmPrintLabelProfile.setText("Print label profile");
				mntmDeviceForBorrow.setText("Devices for borrow");
				mntmStaffBorrowCamera.setText("Staff borrow camera Yeezy");
				mntmBorrowCamera.setText("Borrow camera");
				mntmBorrowMeetingRoom.setText("Borrow meeting room equipment");
				mntmBorrowAccessories.setText("Borrow accessories");
				mntmBorrowLaptop.setText("Borrow laptop");
				mntmBuyDevices.setText("Buy devices");
				mntmPasswordError.setText("Password error");
				mntmReportBroken.setText("Broken report");
				mntmBrokenDevice.setText("Broken device");
				mntmDeviceDestructionList.setText("Device destruction list");
				mntmAccessories.setText("Accessories");
				mntmReplacementAccessories.setText("Replacement accessories");
				mntmBuyDevice.setText("Buy device for other department");
				mntmRepairDevice.setText("Repair device");
				mntmCatalogOfDevice.setText("Catalog of device");
				mntmDeviceInITWarehouse.setText("Device in IT warehouse");
				mntmGiveTheDevice.setText("Give the device");
				mntmLanguage.setText("&Language");

				if (language == 0) {
					for (CTabItem itemrun : tabFolder.getItems()) {
						itemrun.dispose();
					}
				}

				language = 1;
				Display.getDefault().update();
			}
		});

		// Thay đổi ngôn ngữ thành Tiếng Việt
		// -------------------------------------------------------------------------------------------------
		mntmVietnamese.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				shell.setText("Quản lý IP");
				mntmFile.setText("Tệp");
				mntmUser.setText("Người dùng");
				mntmPersoninfo.setText("Thông tin nhân viên");
				mntmStatistic.setText("Thống kê");
				mntmUpdate.setText("Cập nhật");
				mntmClose.setText("Thoát\tCtrl + Q");
				mntmIp.setText("IP");
				mntmIpList.setText("Danh sách IP\tCtrl + L");
				mntmGetIP.setText("Lấy IP");
				mntmDepartment.setText("Đơn vị");
				mntmDepartmentList.setText("Danh sách đơn vị\t Ctrl + D");
				mntmBuilding.setText("Tòa nhà");
				mntmListBuilding.setText("Danh sách tòa nhà\tCtrl + B");
				mntmMachineType.setText("Loại máy");
				mntmMachineTypeList.setText("Danh sách loại máy\tCtrl + M");
				mntmOperatorSystem.setText("Hệ điều hành");
				mntmOperatorSystemList.setText("Danh sách hệ điều hành\tCtrl + O");
				mntmOffice.setText("Office");
				mntmOfficeList.setText("Danh sách Office\tAlt + O");
				mntmItSuggestion.setText("Phiếu đề nghị");
				mntmInternet.setText("Mạng");
				mntmSoftware.setText("Phần mềm");
				mntmDataShare.setText("Ổ đĩa dùng chung");
				mntmMail.setText("Mail");
				mntmWorkAssignment.setText("Phân công công việc");
				mntmComputerProfile.setText("Lý lịch máy tính");
				mntmPrintLabelProfile.setText("In nhãn lý lịch");
				mntmDeviceForBorrow.setText("Thiết bị cho mượn");
				mntmStaffBorrowCamera.setText("Nhân viên mượn máy ảnh Yeezy");
				mntmBorrowCamera.setText("Mượn máy ảnh");
				mntmBorrowMeetingRoom.setText("Mượn thiết bị phòng họp");
				mntmBorrowAccessories.setText("Mượn linh kiện");
				mntmBorrowLaptop.setText("Mượn laptop");
				mntmBuyDevices.setText("Mua thiết bị");
				mntmPasswordError.setText("Sai mật khẩu");
				mntmReportBroken.setText("Báo phế");
				mntmBrokenDevice.setText("Thiết bị hư");
				mntmDeviceDestructionList.setText("Danh sách thiết bị báo phế");
				mntmAccessories.setText("Linh kiện");
				mntmReplacementAccessories.setText("Thay thế linh kiện");
				mntmBuyDevice.setText("Mua thiết bị cho đơn vị khác");
				mntmRepairDevice.setText("Sửa thiết bị");
				mntmCatalogOfDevice.setText("Danh mục thiết bị");
				mntmDeviceInITWarehouse.setText("Thiết bị trong kho IT");
				mntmGiveTheDevice.setText("Phát thiết bị");
				mntmLanguage.setText("Ngôn ngữ");

				if (language == 1) {
					for (CTabItem itemrun : tabFolder.getItems()) {
						itemrun.dispose();
					}
				}

				language = 0;
				Display.getDefault().update();
			}
		});

	}

	// ***********************************************************************************************************
	// Resize hinh anh
	public Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		image.dispose(); // don't forget about me!
		return scaled;
	}
}
