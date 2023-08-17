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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Sales extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTable table1;
	private JTextField tcost;
	private JTextField pay;
	private JTextField bal;
	//private JComboBox txtv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales frame = new Sales();
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
public Sales() 
{
		setTitle("Sales");
		setBackground(Color.GRAY);
		intt();
		connect();
		autoID();
	//	load();
		tn.setEditable(false);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setFont(new Font("Vani", Font.PLAIN, 14));
		textArea.setBounds(939, 40, 311, 309);
		contentPane.add(textArea);
		textArea.setVisible(true);
		
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Vani", Font.PLAIN, 15));
		btnNewButton_1.setBounds(913, 507, 109, 35);
		contentPane.add(btnNewButton_1);
		
		cno = new JTextField();
		cno.setColumns(10);
		cno.setBounds(217, 97, 112, 27);
		contentPane.add(cno);
		
		cname = new JTextField();
		cname.setColumns(10);
		cname.setBounds(217, 164, 112, 27);
		contentPane.add(cname);
		
		pn = new JTextField();
		pn.setColumns(10);
		pn.setBounds(217, 224, 112, 27);
		contentPane.add(pn);
		
		JLabel lblNewLabel_2 = new JLabel("Customer ID");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(43, 97, 112, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Customer Name");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(43, 164, 123, 27);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Phone No");
		lblNewLabel_2_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(43, 231, 112, 27);
		contentPane.add(lblNewLabel_2_2);
		
		JButton btnRecipet = new JButton("Receipt");
		btnRecipet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					printt();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRecipet.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnRecipet.setBounds(863, 570, 113, 35);
		contentPane.add(btnRecipet);
			
		
}
	
	Connection con;
	PreparedStatement pstmt;
	PreparedStatement pstmt1;
	PreparedStatement pstmt2;

	ResultSet rs;
	DefaultTableModel df;
	private JTextField tn;
	private JTextField cno;
	private JTextField cname;
	private JTextField pn;
	
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
		
	
	
	public void autoID()
	{
		
		
	}

	
	public void barcode()
	{
		String pcode=t1.getText();
		
		try {
			pstmt=con.prepareStatement("select * from product where ProductID=?");
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			
			if(rs.next()==false)
			{
				JOptionPane.showMessageDialog(null, "ID not found");
				t1.setText("");
			}
			else 
			{
				String pname=(rs.getString("Name"));
				String price=(rs.getString("Rprice"));
				//String qty=rs.getString("Qty");
						
				t2.setText(pname.trim());
				t3.setText(price.trim());
				//t4.setText(qty.trim());
				

			}
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
									
	}
	
	public void purchase()
	{
		int price=Integer.parseInt(t3.getText());
		int qty=Integer.parseInt(t4.getText());
		
		int tot=price*qty;
		int cquty = 0;
		try {
			cquty=rs.getInt("Qty");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(qty>cquty)
		{
			JOptionPane.showMessageDialog(null, "Qty Not Enough.....!!!");
		}
		else
		{
		
		df=(DefaultTableModel)table1.getModel();
		df.addRow(new Object[] 
				{
					t1.getText(),
					t2.getText(),
					t3.getText(),
					t4.getText(),
					tot
					
				}		
		
	);}
		
		int sum=0;
		for(int i=0;i<table1.getRowCount();i++)
		{
			sum=sum+Integer.parseInt(table1.getValueAt(0, 4).toString());
			
		}
		tcost.setText(String.valueOf(sum));
	}
	
	
	public void addbl()
	{
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now=LocalDateTime.now();
		String date=dt.format(now);
		String cid=cno.getText();
		String cnm=cname.getText();
		String ph=pn.getText();
		String cost=tcost.getText();
		String payy=pay.getText();
		String b=bal.getText();
		String ven=tn.getText();
		
		int lastid=0;
		try 
		{
			String query1="insert into sales(date,c_id,Cname,phone,subtotal,pay,bal) values(?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, date);
			pstmt.setString(2, cid);
			pstmt.setString(3, cnm);
			pstmt.setString(4, ph);
			pstmt.setString(5, cost);
			pstmt.setString(6, payy);
			pstmt.setString(7, b);
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				lastid=rs.getInt(1);
			}

			String query2="insert into salesproduct(Salesid,prid,pname,Price,Qty,Total) values(?,?,?,?,?,?)";
			pstmt1=con.prepareStatement(query2);
			String prid;
			String name;
			String price;
			String qty;
			int total=0;
			
			for(int i=0;i<table1.getRowCount();i++)
			{
				prid=(String)table1.getValueAt(i, 0);
				name=(String)table1.getValueAt(i, 1);
				price=(String)table1.getValueAt(i, 2);
				qty=(String)table1.getValueAt(i, 3);
				total=(int)table1.getValueAt(i, 4);

				pstmt1.setInt(1, lastid);
				pstmt1.setString(2, prid);
				pstmt1.setString(3, name);
				pstmt1.setString(4, price);
				pstmt1.setString(5, qty);
				pstmt1.setInt(6, total);
				pstmt1.executeUpdate();

			}
			
			String query3="update product set Qty=Qty-? where ProductID=?";
			pstmt2=con.prepareStatement(query3);
			
			for(int i=0;i<table1.getRowCount();i++)
			{
				prid=(String)table1.getValueAt(i, 0);
				qty=(String)table1.getValueAt(i, 3);

					pstmt2.setString(1, qty);
					pstmt2.setString(2, prid);
					pstmt2.executeUpdate();

			}
			
			JOptionPane.showMessageDialog(null, "Sales Sucesfullly...!!!!!");
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void intt()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1293, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sales Details");
		lblNewLabel.setFont(new Font("Vani", Font.BOLD, 18));
		lblNewLabel.setBounds(43, 36, 164, 28);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 282, 719, 296);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Product Code");
		lblNewLabel_2.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(24, 11, 112, 27);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Product Name");
		lblNewLabel_2_1.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(178, 11, 112, 27);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Price");
		lblNewLabel_2_2.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(344, 11, 56, 27);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Qty");
		lblNewLabel_2_3.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_2_3.setBounds(476, 11, 61, 27);
		panel.add(lblNewLabel_2_3);
		
		t1 = new JTextField();
		t1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					barcode();
				}
				
			}
		});
		t1.setBounds(24, 49, 112, 27);
		panel.add(t1);
		t1.setColumns(10);
			
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(178, 49, 112, 27);
		panel.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(319, 49, 112, 27);
		panel.add(t3);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(457, 49, 112, 27);
		panel.add(t4);
		
		/*table = new JTable();
		table.setBackground(Color.RED);
		table.setBounds(51, 256, 503, -111);
		panel.add(table);
		*/
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 119, 672, 155);
		panel.add(scrollPane_1);
		
		
		table1 = new JTable();
		scrollPane_1.setViewportView(table1);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Product Code", "Product Name", "Price", "Quality", "Total"
			}
		));
		
	
	
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setIcon(new ImageIcon("F:\\HospitalManagementSystem\\HMS ICON\\save-icon--1.png"));
		btnNewButton.setBounds(610, 11, 88, 40);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				purchase();
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				
			}
		});
		btnNewButton.setFont(new Font("Vani", Font.PLAIN, 14));
		table1.getColumnModel().getColumn(0).setPreferredWidth(52);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(40);
		table1.getColumnModel().getColumn(4).setPreferredWidth(40);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Total Cost");
		lblNewLabel_2_2_1.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_2_2_1.setBounds(805, 164, 78, 27);
		contentPane.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Payment");
		lblNewLabel_2_2_2.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_2_2_2.setBounds(805, 254, 78, 27);
		contentPane.add(lblNewLabel_2_2_2);
		
		tcost = new JTextField();
		tcost.setColumns(10);
		tcost.setBounds(786, 202, 112, 27);
		contentPane.add(tcost);
		
		pay = new JTextField();
		pay.setColumns(10);
		pay.setBounds(786, 292, 112, 27);
		contentPane.add(pay);
		
		JLabel lblNewLabel_2_2_2_1 = new JLabel("Balance");
		lblNewLabel_2_2_2_1.setFont(new Font("Vani", Font.PLAIN, 16));
		lblNewLabel_2_2_2_1.setBounds(805, 330, 78, 27);
		contentPane.add(lblNewLabel_2_2_2_1);
		
		bal = new JTextField();
		bal.setColumns(10);
		bal.setBounds(786, 368, 112, 27);
		contentPane.add(bal);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setIcon(new ImageIcon("F:\\HospitalManagementSystem\\HMS ICON\\save-icon--1.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int payy=Integer.parseInt(pay.getText());
				int cost=Integer.parseInt(tcost.getText());
				int tos=cost-payy;
				
				bal.setText(String.valueOf(tos));
				addbl();
			//JOptionPane.showMessageDialog(null, "Purchase Sucesfullly...!!!!!");

			}
		});
		
		
		btnNewButton_1.setFont(new Font("Vani", Font.PLAIN, 15));
		btnNewButton_1.setBounds(786, 507, 112, 35);
		contentPane.add(btnNewButton_1);
		
		tn = new JTextField();
		tn.setColumns(10);
		tn.setBounds(898, 37, 0, 27);
		contentPane.add(tn);
						
		
		
		
	}
	
	
	public void printt() throws PrinterException
	{

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Vani", Font.PLAIN, 14));
		textArea.setBounds(937, 11, 311, 540);
		contentPane.add(textArea);
		textArea.setVisible(true);
		
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now=LocalDateTime.now();
		String date=dt.format(now);
		
	
				textArea.setText("************************************************\n");
						textArea.append("\t Maniak Store \n" +
					"***********************************************\n" +
					" Date   : "+date+"\n\n"+
					" Customer Id : "+cno.getText()+"\n\n"+
					" Customer Name : "+cname.getText()+"\n\n"+
					" Phone No : "+pn.getText()+"\n\n"+
					" Total : "+tcost.getText()+"\n\n"+
					" Payment : "+pay.getText()+"\n\n"+
					" Balance : "+bal.getText()+"\n\n"+
								
					"***********************************************\n"				
			);
		
			 textArea.print();
			 
			 table1.print();
		
	}
}