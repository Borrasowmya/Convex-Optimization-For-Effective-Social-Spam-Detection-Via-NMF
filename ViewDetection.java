package com;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
public class ViewDetection extends JFrame{
	JTable tab;
	DefaultTableModel dtm;
	JScrollPane jsp;
	
public ViewDetection(){
	super("View Spam Detection Screen");
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int row_no,int column_no){
			return false;
		}
	};
	tab = new JTable(dtm);
	tab.getTableHeader().setFont(new Font("Courier New",Font.BOLD,14));
	tab.setFont(new Font("Courier New",Font.BOLD,13));
	tab.setRowHeight(30);
	jsp = new JScrollPane(tab);
	getContentPane().add(jsp,BorderLayout.CENTER);
	dtm.addColumn("Favourites");
	dtm.addColumn("Tweets");
	dtm.addColumn("Retweets");
	dtm.addColumn("Hashtags");
	dtm.addColumn("Tweet Content");
	dtm.addColumn("Following");
	dtm.addColumn("Followers");
	dtm.addColumn("Account_Age");
	dtm.addColumn("Detection Type");
}
}