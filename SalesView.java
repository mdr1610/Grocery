

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalesView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesView frame = new SalesView();
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
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	private JTable table_1;
	
	public SalesView() 
	{
		initt();
		
	}
	
	
	public void initt()
	{
		setTitle("Display Records");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1290, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBounds(0, 11, 1264, 543);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 1244, 155);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr No","Sales ID","Product ID","Product Name","Cost Price","Qty ","Balance"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(107);
		table.getColumnModel().getColumn(4).setPreferredWidth(88);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 44, 1244, 155);
		panel.add(scrollPane_1);
		
		
		table1 = new JTable();
		scrollPane_1.setViewportView(table1);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr No","Date","Customer ID","Customer Name","Phone No","Total","Paid ","Blanace"
			}
		));
		table1.getColumnModel().getColumn(0).setPreferredWidth(52);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(107);
		table1.getColumnModel().getColumn(4).setPreferredWidth(88); 
		
		JButton btnNewButton = new JButton("Display Sales Records");
		btnNewButton.addActionListener(new ActionListener() {
	

			public void actionPerformed(ActionEvent e) 
			{ vi();
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","");
				Statement st=con.createStatement();
				String query="select * from salesproduct ";
				rs=st.executeQuery(query);
				ResultSetMetaData rsmd=rs.getMetaData();
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String [cols];
				
				for(int i=0;i<cols;i++)
					colName[i]=rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colName);
				String SRNO,Salesid,prid,pname,Price,Qty,Total;
				while(rs.next())
				{
					SRNO=rs.getString(1);
					Salesid=rs.getString(2);
					prid=rs.getString(3);
					pname=rs.getString(4);
					Price=rs.getString(5);
					Qty=rs.getString(6);
					Total=rs.getString(7);
				
					
					
					String[] row= {SRNO,Salesid,prid,pname,Price,Qty,Total};
					model.addRow(row);

				}
				st.close();
				con.close();
			} 
			
			catch (ClassNotFoundException e1) 
			{
				
				e1.printStackTrace();
			} 
			catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(510, 431, 261, 34);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Close");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//JOptionPane.showMessageDialog(null, "Do you want to exit","Bank System",JOptionPane.QUESTION_MESSAGE);
				setVisible(false);
			}
		});
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Vani", Font.BOLD, 15));
		lblNewLabel.setBounds(611, 517, 69, 22);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sales Deatils");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(25, 19, 146, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sales Prodcut  Deatils");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 218, 202, 14);
		panel.add(lblNewLabel_1_1);
	}
	
	
	public void vi()
	{
		
		
		
		
		try 
		{
			 Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","");
			Statement st=con.createStatement();
			String query="select * from sales ";
			rs=st.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel) table1.getModel();
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String [cols];
			
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			String id,date,c_id,cname,subtotal,pay,bal;
			while(rs.next())
			{
				id=rs.getString(1);
				date=rs.getString(2);
				c_id=rs.getString(3);
				cname=rs.getString(4);
				subtotal=rs.getString(5);
				pay=rs.getString(6);
				bal=rs.getString(7);
				
				
				String[] row= { id,date,c_id,cname,subtotal,pay,bal};
				model.addRow(row);

			}
			st.close();
			con.close();
		} 
		
		catch (ClassNotFoundException e1) 
		{
			
			e1.printStackTrace();
		} 
		catch (SQLException e1) {
		
			e1.printStackTrace();
		}
		
	}
}
