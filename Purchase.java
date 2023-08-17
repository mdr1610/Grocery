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
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class Purchase extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTable table1;
	private JTextField tcost;
	private JTextField pay;
	private JTextField bal;
	private JComboBox txtv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase frame = new Purchase();
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
public Purchase() 
{
		setTitle("Purchase ");
		setBackground(Color.WHITE);
		intt();
		connect();
	//	load();
		tn.setEditable(false);
		
		
		
		JTextArea textArea = new JTextArea();
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
		btnNewButton_1.setBounds(397, 435, 109, 35);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
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
		btnRecipet.setBounds(516, 432, 113, 35);
		contentPane.add(btnRecipet);
		
		tnn = new JTextField();
		tnn.setEditable(false);
		tnn.setColumns(10);
		tnn.setBounds(409, 40, 112, 27);
		contentPane.add(tnn);
		
		
}
	
	Connection con;
	PreparedStatement pstmt;
	PreparedStatement pstmt1;
	PreparedStatement pstmt2;

	ResultSet rs;
	DefaultTableModel df;
	private JTextField tn;
	private JTextField tnn;
	private JTextField tqt;
	
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
		
	public void barcode()
	{
		String pcode=t1.getText();
		
		try {
			pstmt=con.prepareStatement("select * from product where ProductID=?");
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			
			if(rs.next()==false)
			{
				JOptionPane.showMessageDialog(null, "Barcode not found");
				t1.setText("");
			}
			else
			{
				String vname=(rs.getString("vname"));
				String pname=(rs.getString("Name"));
				String price=(rs.getString("Cprice"));
				String qty=rs.getString("Qty");
						
				t2.setText(pname.trim());
				t3.setText(price.trim());
				tnn.setText(vname.trim());
				tqt.setText(qty.trim());

				
				//vnmae();
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
		
		String cost=tcost.getText();
		String payy=pay.getText();
		String b=bal.getText();
		String ven=tn.getText();
		
		int lastid=0;
		try 
		{
			String query1="insert into purchase(date,vendor,subtotal,pay,bal) values(?,?,?,?,?)";
			pstmt=con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, date);
			pstmt.setString(2, ven);
			pstmt.setString(3, cost);
			pstmt.setString(4, payy);
			pstmt.setString(5, b);
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				lastid=rs.getInt(1);
			}

			String query2="insert into purchaseitem(PurchaseId,ProductId,Pname,Rprice,Qty,Total) values(?,?,?,?,?,?)";
			pstmt1=con.prepareStatement(query2);
			String prid;
			String price;
			String pname;
			String qty;
			int total=0;
			
			for(int i=0;i<table1.getRowCount();i++)
			{
				prid=(String)table1.getValueAt(i,0);
				pname=(String)table1.getValueAt(i, 1);
				price=(String)table1.getValueAt(i, 2);
				qty=(String)table1.getValueAt(i, 3);
				total=(int)table1.getValueAt(i, 4);

				pstmt1.setInt(1, lastid);
				pstmt1.setString(2, prid);
				pstmt1.setString(3, pname);
				pstmt1.setString(4, price);
				pstmt1.setString(5, qty);
				pstmt1.setInt(6, total);
				pstmt1.executeUpdate();

			}
			
			String query3="update product set Qty=Qty+? where ProductID=?";
			pstmt2=con.prepareStatement(query3);
			
			for(int i=0;i<table1.getRowCount();i++)
			{
				prid=(String)table1.getValueAt(i, 0);
				qty=(String)table1.getValueAt(i, 3);

					pstmt2.setString(1, qty);
					pstmt2.setString(2, prid);
					pstmt2.executeUpdate();

			}
			
			JOptionPane.showMessageDialog(null, "Purchase Sucesfullly...!!!!!");
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void intt()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1293, 526);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Purchase Details");
		lblNewLabel.setFont(new Font("Vani", Font.BOLD, 18));
		lblNewLabel.setBounds(43, 36, 164, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Vendor");
		lblNewLabel_1.setFont(new Font("Vani", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(352, 41, 58, 19);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 111, 719, 300);
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
		
		JLabel lblNewLabel_2_3 = new JLabel("Quty");
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Product Details", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.RED));
		panel_3.setBounds(14, 146, 684, 144);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 15, 672, 123);
		panel_3.add(scrollPane_1);
		
		
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
		table1.getColumnModel().getColumn(0).setPreferredWidth(52);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(40);
		table1.getColumnModel().getColumn(4).setPreferredWidth(40);
		
	
	
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(610, 11, 88, 40);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				purchase();
				//t1.setText("");
				//t2.setText("");
				//t3.setText("");
				//t4.setText("");
				
			}
		});
		btnNewButton.setFont(new Font("Vani", Font.PLAIN, 14));
		
		JLabel lblQtyIs = new JLabel("Qty is");
		lblQtyIs.setBounds(457, 86, 51, 28);
		panel.add(lblQtyIs);
		lblQtyIs.setForeground(Color.BLACK);
		lblQtyIs.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		tqt = new JTextField();
		tqt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tqt.setForeground(Color.RED);
		tqt.setEditable(false);
		tqt.setBounds(506, 86, 47, 27);
		panel.add(tqt);
		tqt.setColumns(10);
		
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
		btnNewButton_1.setBounds(260, 435, 112, 35);
		contentPane.add(btnNewButton_1);
		
		tn = new JTextField();
		tn.setColumns(10);
		tn.setBounds(898, 37, 0, 27);
		contentPane.add(tn);
				
			try {
				connect();
				pstmt=con.prepareStatement("select Distinct Name from vendor");
				rs=pstmt.executeQuery();
				//txtv.removeAllItems();
				while(rs.next())
				{
					//txtv.addItem(rs.getString("Name"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	
			JButton btnPrint = new JButton("Print");
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});
			btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 15));
			btnPrint.setBounds(649, 436, 113, 35);
			contentPane.add(btnPrint);
			btnPrint.setVisible(true);
			
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
					" Product Id : "+t1.getText()+"\n\n"+
					" Vender Name : "+tnn.getText()+"\n\n"+
					" Total : "+tcost.getText()+"\n\n"+
					" Payment : "+pay.getText()+"\n\n"+
					" Balance : "+bal.getText()+"\n\n"+
								
					"***********************************************\n"				
			);
		
			 textArea.print();
			 
			 table1.print();
		
	}
	
	public void vnmae()
	{
		connect();

		
		JLabel tqt = new JLabel("New label");
		tqt.setBounds(941, 432, 45, 13);
		contentPane.add(tqt);
		
		String nm=tnn.getText();
		
		try {
			pstmt=con.prepareStatement("select * from product where vname=?");
			pstmt.setString(1, nm);
			rs=pstmt.executeQuery();
			
			if(rs.next()==false)
			{
				//JOptionPane.showMessageDialog(null, "Barcode not found");
				JOptionPane.showMessageDialog(null, "error");

				t1.setText("");
			}
			else
			{
				String name=(rs.getString("Name"));

				String qt=(rs.getString("Qty"));
						
				
				
			t2.setText(name.trim());
				tqt.setText(qt.trim());
				
			
				
				JOptionPane.showMessageDialog(null, "ok");
				
				
				

			}
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
								
		
	}
}