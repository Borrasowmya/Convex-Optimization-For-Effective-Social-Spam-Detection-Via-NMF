package com;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JFileChooser;
import java.awt.Cursor;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.awt.Cursor;
public class Main extends JFrame{
	JLabel l1;
	JPanel p1,p2,p3;
	Font f1;
	JScrollPane jsp;
	JButton b1,b2,b3,b4,b5;
	JFileChooser chooser;
	DefaultTableModel dtm;
	JTable table;
	File file;
	
public Main(){
	super("Spammer Detection");
	p1 = new JPanel();
	p1.setPreferredSize(new Dimension(600,50));
	p1.setBackground(new Color(204, 110, 155));
	l1 = new JLabel("<html><body><center>Social Spammer Detection via Convex Nonnegative Matrix Factorization<br/></center></body></html>".toUpperCase());
	l1.setFont(new Font("Castellar", 1, 18));
	l1.setForeground(Color.white);
	p1.add(l1);
	getContentPane().add(p1,BorderLayout.NORTH);

	f1 = new Font("Times New Roman",Font.BOLD,14);

	p2 = new JPanel();
	p2.setLayout(new BorderLayout());
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int r,int c){
			return false;
		}
	};
	table = new JTable(dtm);
	table.setRowHeight(30);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	table.setFont(f1);
	table.getTableHeader().setFont(f1);
	jsp = new JScrollPane(table);
	
	dtm.addColumn("Tweet ID");
	dtm.addColumn("Tweet Text");
	table.getColumnModel().getColumn(0).setPreferredWidth(100);
	table.getColumnModel().getColumn(1).setPreferredWidth(1000);
		
	p3 = new JPanel();
	p3.setPreferredSize(new Dimension(150,110));
	chooser = new JFileChooser(new File("."));
	chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);
	
	b1 = new JButton("Upload Tweets Dataset");
	b1.setFont(f1);
	b1.setPreferredSize(new Dimension(300,30));
	p3.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			int option = chooser.showOpenDialog(Main.this);
			if(option == chooser.APPROVE_OPTION){
				file = chooser.getSelectedFile();
				clearTable();
				Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
				setCursor(hourglassCursor);
				ReadDataset.read(file,dtm);
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
				JOptionPane.showMessageDialog(Main.this,"Dataset Loaded");
			}
		}
	});

	b2 = new JButton("Detect Fake Content, Spam URL, Trending Topic & Fake Account");
	b2.setPreferredSize(new Dimension(500,30));
	b2.setFont(f1);
	p3.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			ReadDataset.read(file);
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
		}
	});

	b3 = new JButton("Run Random Forest Prediction");
	b3.setFont(f1);
	b3.setPreferredSize(new Dimension(300,30));
	p3.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			try{
				RandomForestClassifier.train("lib/data.arff");
				JOptionPane.showMessageDialog(Main.this,"Random Forest Classification Accuracy : "+RandomForestClassifier.acc);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	});

	b4 = new JButton("View Prediction Results");
	b4.setFont(f1);
	b4.setPreferredSize(new Dimension(300,30));
	p3.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			JOptionPane.showMessageDialog(Main.this,"Random Forest Classification Result\n"+RandomForestClassifier.classify);
		}
	});

	b5 = new JButton("Exit");
	b5.setFont(f1);
	p3.add(b5);
	b5.setPreferredSize(new Dimension(300,30));
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			System.exit(0);
		}
	});

	
	getContentPane().add(jsp,BorderLayout.CENTER);
	getContentPane().add(p3,BorderLayout.SOUTH);
}
public void clearTable(){
	for(int i=table.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
public static void main(String a[])throws Exception{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	Main ud = new Main();
	ud.setVisible(true);
	ud.setExtendedState(JFrame.MAXIMIZED_BOTH);
}

}
