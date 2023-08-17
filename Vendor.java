import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vendor extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					Vendor frame = new Vendor();
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
	public Vendor() 
	{
		setTitle("Vendor");
		initt();
		connect();
		load();
	}
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DefaultTableModel df;
	
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
			pstmt=con.prepareStatement("select * from vendor");
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
					v.add(rs.getString("Name"));
					v.add(rs.getString("Phone"));
					v.add(rs.getString("Email"));
					v.add(rs.getString("Address"));
				}
				df.addRow(v);
			}
			
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	void initt()
	{
		setMaximumSize(new Dimension(5000, 5000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1246, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 11, 1210, 374);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vendor Name");
		lblNewLabel.setFont(new Font("Vani", Font.PLAIN, 15));
		lblNewLabel.setBounds(33, 58, 99, 21);
		panel.add(lblNewLabel);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Vani", Font.PLAIN, 15));
		lblPhone.setBounds(33, 107, 99, 21);
		panel.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Vani", Font.PLAIN, 15));
		lblEmail.setBounds(33, 153, 99, 21);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Vani", Font.PLAIN, 15));
		lblAddress.setBounds(33, 196, 99, 21);
		panel.add(lblAddress);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(new Dimension(93, 500));
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 8));
		scrollPane.setMaximumSize(new Dimension(3000, 3000));
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(341, 52, 859, 239);
		panel.add(scrollPane);
		
		t1 = new JTextField();
		t1.setBounds(158, 56, 155, 23);
		panel.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(158, 105, 155, 23);
		panel.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(158, 151, 155, 23);
		panel.add(t3);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(158, 194, 155, 23);
		panel.add(t4);
		
		table = new JTable();
		table.setBounds(427, 383, 225, -324);
		panel.add(table);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr No","Name","Phone No ","Email"," Address",
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=t1 .getText();
				String ph=t2 .getText();
				String email=t3.getText();
				String add=t4.getText();
		
				try
				{
					PreparedStatement pstmt=con.prepareStatement("insert into vendor(Name,Phone,Email,Address) values(?,?,?,?)");
					pstmt.setString(1, name);
					pstmt.setString(2, ph);
					pstmt.setString(3, email);
					pstmt.setString(4, add);

				
					pstmt.executeUpdate();
				
					JOptionPane.showMessageDialog(null,"Inserted");
					load();
					
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
			
				}
			
				catch(Exception sq) 
				{
					sq.printStackTrace();
				}
				
				
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				df=(DefaultTableModel) table.getModel();
				int sel=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(sel, 0).toString());
				t1.setText((df.getValueAt(sel, 1).toString()));
				t2.setText((df.getValueAt(sel, 2).toString()));
				t3.setText((df.getValueAt(sel, 3).toString()));
				t4.setText((df.getValueAt(sel, 4).toString()));

				btnNewButton.setEnabled(false);
			}
			
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(21, 255, 89, 36);
		panel.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				df=(DefaultTableModel) table.getModel();
				int sel=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(sel, 0).toString());
				
				String name=t1 .getText();
				String ph=t2 .getText();
				String email=t3.getText();
				String add=t4.getText();
		
				try
				{
					PreparedStatement pstmt=con.prepareStatement("update vendor set Name=?,Phone=?,Email=?,Address=? where ID=?");
					pstmt.setString(1, name);
					pstmt.setString(2, ph);
					pstmt.setString(3, email);
					pstmt.setString(4, add);
					pstmt.setInt(5, id);

				
					pstmt.executeUpdate();
				
					JOptionPane.showMessageDialog(null,"Edited");
					load();
					btnNewButton.setEnabled(true);
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
			
				}
			
				catch(Exception sq) 
				{
					sq.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnEdit.setBounds(141, 255, 89, 36);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				df=(DefaultTableModel) table.getModel();
				int sel=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(sel, 0).toString());
				
		
				try
				{
					PreparedStatement pstmt=con.prepareStatement("delete from vendor  where ID=?");
		
					pstmt.setInt(1, id);

				
					pstmt.executeUpdate();
				
					JOptionPane.showMessageDialog(null,"Deleted");
					load();
					btnNewButton.setEnabled(true);
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
			
				}
			
				catch(Exception sq) 
				{
					sq.printStackTrace();
				}
				
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDelete.setBounds(240, 255, 89, 36);
		panel.add(btnDelete);
		
		JLabel lblVendorDetails = new JLabel("Vendor Details");
		lblVendorDetails.setFont(new Font("Vani", Font.BOLD, 15));
		lblVendorDetails.setBounds(21, 11, 124, 27);
		panel.add(lblVendorDetails);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCancel.setBounds(141, 315, 89, 36);
		panel.add(btnCancel);
	}
}
