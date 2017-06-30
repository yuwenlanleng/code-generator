package com.coltd.platform.generator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.coltd.platform.generator.bean.AutoGenInf;
import com.coltd.platform.generator.bean.Table;
import com.coltd.platform.generator.utils.MyExcelHelper;

public class MainWindow extends java.awt.Frame {
	private static final long serialVersionUID = -4259339680151285634L;
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"MM-dd hh:mm:ss");// 时间格式

	FileDialog fileDialog = null;// 文件选择器
	List<Map<String, Object>> excelTables = new ArrayList<Map<String, Object>>();
	private String[] columnStrs = new String[] { "", "表名", "中文名", "包名", "" };// 列名
	// private ConConfig con;//连接信息

	public MainWindow() {
		initComponents();
		initWindow();
	}

	/**
	 * 初始化窗口
	 * */
	private void initWindow() {
		this.setResizable(false);// 不可修改大小
		this.setTitle("代码自动生成工具");
		this.setSize(640, 480);

		// 初始化文件选择器
		fileDialog = new FileDialog(this);
		fileDialog.setMultipleMode(true);// 多选

		// 初始化窗口位置
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		this.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		// 初始化表格
		initTabTable(null);

		print("根目录:" + getProjectPaht());
	}

	/**
	 * 点击生成实体
	 * */
	@SuppressWarnings("unchecked")
	private void btnBuildEntityActionPerformed(java.awt.event.ActionEvent evt) {
		Map<String, Object> excelTemps = new LinkedHashMap<String, Object>();
		for (int i = 0; i < tabTables.getRowCount(); i++) {
			Boolean isSelect = (Boolean) tabTables.getModel().getValueAt(i, 0);
			if (isSelect) {
				excelTemps = ((Map<String, Object>) tabTables.getModel()
						.getValueAt(i, 4));
				String tableName = (String) tabTables.getModel().getValueAt(i,
						1);
				String tableName_cn = (String) tabTables.getModel().getValueAt(
						i, 2);
				print("生成[" + tableName + "]-" + tableName_cn + "开始...\r\n");
				CodeAutoGenerator.generator(excelTemps, true, "", "v2");
				print("生成[OK]\r\n");
			} else {
				print("未选择需要生成的表\r\n");
			}
		}
	}

	/**
	 * 选择文件
	 * */
	private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {
		fileDialog.setVisible(true);// 显示文件选择器
		File[] fs = fileDialog.getFiles();
		if (fs.length > 0) {
			excelTables.clear();// 清空所有
			for (int i = 0; i < fs.length; i++) {
				try {
					List<Map<String, Object>> ts = MyExcelHelper.getAllTables(
							fs[i], true);
					excelTables.addAll(ts);
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
			initTabTable(null);// 将excelTables中的数据，添加到tabTabes控件中
		}
	}

	/**
	 * 得到项目根目录
	 * */
	public static String getProjectPaht() {
		return System.getProperty("user.dir");
	}

	/**
	 * 将excelTables中的数据，添加到tabTabes控件中
	 * 
	 * @param str
	 *            过滤字符
	 * */
	private void initTabTable(String str) {
		tabTables.removeAll();
		ArrayList<Object[]> arrTemp = new ArrayList<Object[]>();
		for (Map<String, Object> key : excelTables) {
			// if (str != null) {//需要过滤
			// if (key.indexOf(str) < 0) {
			// continue;
			// }
			// }
			AutoGenInf af = (AutoGenInf) key.get(AutoGenInf.AUTOGENINF);
			Table tbinf = (Table) key
					.get(Table.TABLEINFENTITY);
			arrTemp.add(new Object[] { true, tbinf.getTableName(),
					tbinf.getTableDescription(), af.getAuthGenPath(), key });
		}

		// 构建数据数组
		Object[][] tarr = new Object[arrTemp.size()][5];
		for (int i = 0; i < tarr.length; i++) {
			for (int j = 0; j < 5; j++) {
				tarr[i][j] = arrTemp.get(i)[j];
			}
		}
		tabTables.setModel(new DefaultTableModel(tarr, columnStrs));

		// 第一列为选择列
		JCheckBox c = new JCheckBox();
		tabTables.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox se = new JCheckBox();
						if ("true".equals(value.toString())) {
							se.setSelected(true);
						}
						return se;
					}
				});
		tabTables.getColumnModel().getColumn(0)
				.setCellEditor(new DefaultCellEditor(c));
		setCW(0, 20);
		setCW(1, 100);
		setCW(2, 100);
		setCW(3, 200);
		setCW(4, 10);
	}

	/**
	 * 设置列宽
	 * */
	private void setCW(int index, int width) {
		TableColumn c = tabTables.getColumnModel().getColumn(index);
		if (c != null) {
			c.setPreferredWidth(width);
			c.setMinWidth(width);
		}
	}

	/**
	 * 判断tabTables是否有选中的行
	 * */
	@SuppressWarnings("unused")
	private boolean isSelect() {
		for (int i = 0; i < tabTables.getRowCount(); i++) {
			Boolean isSelect = (Boolean) tabTables.getModel().getValueAt(i, 0);
			if (isSelect) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将信息输出到控制台
	 * */
	public void print(String str) {
		txtConsole.append(sdf.format(new Date()) + "-->" + str + "\r\n");
	}

	// ==================================以下是编辑器生成代码============================

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnSelectFile = new java.awt.Button();
		scrTables = new javax.swing.JScrollPane();
		tabTables = new javax.swing.JTable();
		txtConsole = new java.awt.TextArea();
		btnBuildEntity = new java.awt.Button();

		setBackground(java.awt.SystemColor.control);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});
		setLayout(null);

		btnSelectFile.setLabel("选择文件");
		btnSelectFile.setName("");
		btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSelectFileActionPerformed(evt);
			}
		});
		add(btnSelectFile);
		btnSelectFile.setBounds(10, 30, 70, 26);

		tabTables.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "", "表名", "中文名", "包名", "" }));
		scrTables.setViewportView(tabTables);

		add(scrTables);
		scrTables.setBounds(10, 60, 620, 180);
		add(txtConsole);
		txtConsole.setBounds(10, 250, 620, 220);

		btnBuildEntity.setActionCommand("生成实体");
		btnBuildEntity.setLabel("生成实体");
		btnBuildEntity.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBuildEntityActionPerformed(evt);
			}
		});
		add(btnBuildEntity);
		btnBuildEntity.setBounds(90, 30, 70, 26);

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_exitForm
		System.exit(0);
	}// GEN-LAST:event_exitForm

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private java.awt.Button btnBuildEntity;
	private java.awt.Button btnSelectFile;
	private javax.swing.JScrollPane scrTables;
	private javax.swing.JTable tabTables;
	private java.awt.TextArea txtConsole;
	// End of variables declaration//GEN-END:variables

}