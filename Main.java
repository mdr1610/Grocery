import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Store Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 306, 584);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Store Management System");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 28, 286, 27);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Vendor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vendor v1=new Vendor();
				v1.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Vani", Font.PLAIN, 15));
		btnNewButton.setBounds(90, 98, 115, 42);
		panel.add(btnNewButton);
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product p=new Product();
				p.setVisible(true);
			}
		});
		btnProduct.setFont(new Font("Vani", Font.PLAIN, 15));
		btnProduct.setBounds(90, 176, 115, 42);
		panel.add(btnProduct);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Purchase p=new Purchase();
				p.setVisible(true);
			}
		});
		btnPurchase.setFont(new Font("Vani", Font.PLAIN, 15));
		btnPurchase.setBounds(90, 247, 115, 42);
		panel.add(btnPurchase);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogout.setFont(new Font("Vani", Font.PLAIN, 15));
		btnLogout.setBounds(90, 510, 115, 42);
		panel.add(btnLogout);
		
		JButton btnSales = new JButton("Sales");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sales s=new Sales();
				s.setVisible(true);
			}
		});
		btnSales.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnSales.setBounds(90, 312, 115, 42);
		panel.add(btnSales);
		
		JButton btnViewPurchaseRecoreds = new JButton("View Purchase Recoreds");
		btnViewPurchaseRecoreds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View v=new View();
				v.setVisible(true);
			}
		});
		btnViewPurchaseRecoreds.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnViewPurchaseRecoreds.setBounds(46, 374, 205, 42);
		panel.add(btnViewPurchaseRecoreds);
		
		JButton btnViewSalesRecoreds = new JButton("View Sales Records");
		btnViewSalesRecoreds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesView sv=new SalesView();
				sv.setVisible(true);
			}
		});
		btnViewSalesRecoreds.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnViewSalesRecoreds.setBounds(46, 439, 205, 42);
		panel.add(btnViewSalesRecoreds);
	}
}
