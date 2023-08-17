import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Product extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTable table;
	private JTextField t5;
	private JTextField t6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Product() {
		setTitle("Product");
	intt();
	connect();
	load();
	}
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DefaultTableModel df;
	private JTextField vno;
	private JTextField vname;
	
	public void connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","");
		} 
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void load()
	{
		 try {
			pstmt=con.prepareStatement("select * from product");
			rs=pstmt.executeQuery();
			
			
			ResultSetMetaData rd=rs.getMetaData();
			int a=rd.getColumnCount();
			df=(DefaultTableModel) table.getModel();
			df.setRowCount(0);
			
			while(rs.next())
			{
				Vector v=new Vector();
				
				for(int i=0;i<=a;i++)
				{
					v.add(rs.getString("ID"));
					v.add(rs.getString("vid"));
					v.add(rs.getString("vname"));
					v.add(rs.getString("Name"));
					v.add(rs.getString("Description"));
					v.add(rs.getString("ProductID"));
					v.add(rs.getString("Cprice"));
					v.add(rs.getString("Rprice"));
					v.add(rs.getString("Qty"));				
				
				}
				df.addRow(v);
			}
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
 public void intt()
 {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1262, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 22, 1226, 423);
		contentPane.add(panel);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Vani", Font.PLAIN, 15));
		lblProductName.setBounds(31, 139, 99, 21);
		panel.add(lblProductName);
		
		JLabel lblDesciption = new JLabel("Desciption");
		lblDesciption.setFont(new Font("Vani", Font.PLAIN, 15));
		lblDesciption.setBounds(31, 170, 99, 21);
		panel.add(lblDesciption);
		
		JLabel lblBarcode = new JLabel("Product ID");
		lblBarcode.setFont(new Font("Vani", Font.PLAIN, 15));
		lblBarcode.setBounds(31, 216, 99, 21);
		panel.add(lblBarcode);
		
		JLabel lblPrice = new JLabel("Cost Price");
		lblPrice.setFont(new Font("Vani", Font.PLAIN, 15));
		lblPrice.setBounds(31, 259, 99, 21);
		panel.add(lblPrice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 8));
		scrollPane.setBounds(342, 57, 874, 239);
		panel.add(scrollPane);
		
		t1 = new JTextField();
		t1.setColumns(10);
		t1.setBounds(156, 135, 155, 23);
		panel.add(t1);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(156, 168, 155, 23);
		panel.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(156, 214, 155, 23);
		panel.add(t3);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(156, 257, 155, 23);
		panel.add(t4);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vid=vno.getText();
				String vn=vname.getText();
				String name=t1 .getText();
				String des=t2 .getText();
				String brcd=t3.getText();
				String cprice=t4.getText();
				String rprice=t5.getText();
				String qty=t6 .getText();

				try
				{
					PreparedStatement pstmt=con.prepareStatement("insert into product(vid,vname,Name,Description,ProductID,Cprice,Rprice,Qty) values(?,?,?,?,?,?,?,?)");
					pstmt.setString(1, vid);
					pstmt.setString(2, vn);
					pstmt.setString(3, name);
					pstmt.setString(4, des);
					pstmt.setString(5, brcd);
					pstmt.setString(6, cprice);
					pstmt.setString(7, rprice);
					pstmt.setString(8, qty);

				
					pstmt.executeUpdate();
				
					JOptionPane.showMessageDialog(null,"Product Addded Sucessfully...!!");
					load();
					
					vno.setText("");
					vname.setText("");
					t1.setText("");
					t2.setText("");
					t3.setText(""); 
					t4.setText("");
					t5.setText("");
					t6.setText("");
			
				}
			
				catch(Exception sq) 
				{
					sq.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(31, 376, 89, 36);
		panel.add(btnNewButton);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				df=(DefaultTableModel) table.getModel();
				int sel=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(sel, 0).toString());	
				
				vno.setText(df.getValueAt(sel, 1).toString());
				vname.setText(df.getValueAt(sel, 2).toString());
				t1.setText(df.getValueAt(sel, 3).toString());
				t2.setText(df.getValueAt(sel, 4).toString());
				t3.setText(df.getValueAt(sel, 5).toString());
				t4.setText(df.getValueAt(sel, 6).toString());
				t5.setText(df.getValueAt(sel, 7).toString());
				t6.setText(df.getValueAt(sel, 8).toString());
				

				btnNewButton.setEnabled(false);
			}
		});
		
		
		table.setBounds(427, 383, 225, -324);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Sr No","Vendor Id","Vendor Name"," Product Name","Description ","Product ID"," Cost Price","Retail Price","Quantity"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(20);
			table.getColumnModel().getColumn(1).setPreferredWidth(20);
			table.getColumnModel().getColumn(2).setPreferredWidth(20);
			table.getColumnModel().getColumn(3).setPreferredWidth(20);
			table.getColumnModel().getColumn(4).setPreferredWidth(20);

		
		
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				df=(DefaultTableModel)table.getModel();
				int sel=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(sel, 0).toString());
		
				String name=t1 .getText();
				String des=t2 .getText();
				String brcd=t3.getText();
				String cprice=t4.getText();
				String rprice=t5.getText();
				String qty=t6 .getText();
				
				
				try
				{
					PreparedStatement pstmt=con.prepareStatement("update product set Name=?,Description=?,ProductID=?,Cprice=?,Rprice=?,Qty=? where ID=?");
					pstmt.setString(1, name);
					pstmt.setString(2, des);
					pstmt.setString(3, brcd);
					pstmt.setString(4, cprice);
					pstmt.setString(5, rprice);
					pstmt.setString(6, qty);
					pstmt.setInt(7,id);

				
					pstmt.executeUpdate();
				
					JOptionPane.showMessageDialog(null,"Edited");
					load();
					btnNewButton.setEnabled(true);
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					t6.setText("");
					vno.setText("");
					vname.setText("");
				}
			
				catch(Exception sq) 
				{
					sq.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnEdit.setBounds(158, 376, 89, 36);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				df=(DefaultTableModel) table.getModel();
				int sel=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(sel, 0).toString());
				
		
				try
				{
					PreparedStatement pstmt=con.prepareStatement("delete from product where ID=?");
		
					pstmt.setInt(1, id);
				
					pstmt.executeUpdate();
				
					JOptionPane.showMessageDialog(null,"Deleted");
					load();
					btnNewButton.setEnabled(true);
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					t6.setText("");
					vno.setText("");
					vname.setText("");
				
				
			
				}
			
				catch(Exception sq) 
				{
					sq.printStackTrace();
				}
				
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDelete.setBounds(257, 376, 89, 36);
		panel.add(btnDelete);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setFont(new Font("Vani", Font.PLAIN, 15));
		lblQty.setBounds(31, 345, 99, 21);
		panel.add(lblQty);
		
		JLabel lblRetailPrice = new JLabel("Retail Price");
		lblRetailPrice.setFont(new Font("Vani", Font.PLAIN, 15));
		lblRetailPrice.setBounds(31, 299, 99, 21);
		panel.add(lblRetailPrice);
		
		t5 = new JTextField();
		t5.setColumns(10);
		t5.setBounds(156, 297, 155, 23);
		panel.add(t5);
		
		t6 = new JTextField();
		t6.setColumns(10);
		t6.setBounds(156, 343, 155, 23);
		panel.add(t6);
		
		JLabel lblNewLabel = new JLabel("Product Items");
		lblNewLabel.setFont(new Font("Vani", Font.BOLD, 15));
		lblNewLabel.setBounds(33, 11, 124, 27);
		panel.add(lblNewLabel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCancel.setBounds(379, 376, 89, 36);
		panel.add(btnCancel);
		
		JLabel lblVendorId = new JLabel("Vendor ID");
		lblVendorId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblVendorId.setBounds(31, 58, 99, 21);
		panel.add(lblVendorId);
		
		vname = new JTextField();
		vname.setColumns(10);
		vname.setBounds(156, 102, 155, 23);
		panel.add(vname);
		
		
		vno = new JTextField();
		vno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
		});
		vno.setColumns(10);
		vno.setBounds(156, 59, 155, 23);
		panel.add(vno);
		
		
		JLabel lblVendorName = new JLabel("Vendor Name");
		lblVendorName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblVendorName.setBounds(31, 108, 99, 21);
		panel.add(lblVendorName);
		
		JButton btnNewButton_1 = new JButton("Find");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vnn();
				
			}
		});
		btnNewButton_1.setBounds(268, 8, 85, 32);
		panel.add(btnNewButton_1);
	}
 
 public void vnn()
 {
	 String vnoo=vno.getText();
		
		try {
			pstmt=con.prepareStatement("select * from vendor where ID=?");
			pstmt.setString(1, vnoo);
			rs=pstmt.executeQuery();
			
			if(rs.next()==false)
			{
				JOptionPane.showMessageDialog(null, "Vendor not found");
				vno.setText("");
			}
			else 
			{
				String pname=(rs.getString(2));						
				vname.setText(pname.trim());
			}
		} 
		catch (SQLException e1) 
		{
			
			e1.printStackTrace();
		}
		
 }
}
