package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import java.util.Date;
import java.io.File;
import java.util.Calendar;
import java.util.Locale;
import java.io.FileWriter;
public class ReadDataset{

	static ArrayList<Tweet> dataset = new ArrayList<Tweet>();
	static SpamFilter filter;

public static void clear() throws Exception{
	dataset.clear();
	if(filter == null){
		filter = new SpamFilter();
		filter.trainSpam("lib/spam.txt");
		filter.trainGood("lib/good.txt");
		filter.finalizeTraining();
	}
}
public static void read(File file,DefaultTableModel dtm){
	try{
		clear();
		File list[] = file.listFiles();
		java.util.Date today = new java.util.Date();
		today = new java.sql.Date(today.getTime());
		for(int i=0;i<list.length;i++){
			Object obj = new JSONParser().parse(new FileReader(list[i])); 
			JSONObject jo = (JSONObject) obj; 
			String tweet = (String) jo.get("text"); 
			Object row[] = {(i+1),tweet};
			dtm.addRow(row);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}


public static void read(File file){
	try{
		ViewDetection vd = new ViewDetection();
		File list[] = file.listFiles();
		java.util.Date today = new java.util.Date();
		today = new java.sql.Date(today.getTime());
		StringBuilder sb = new StringBuilder();
		sb.append("Favourites,Tweets,Retweets,Hashtags,Tweet Content,Following,Followers,Account_Age,class"+System.getProperty("line.separator"));
		for(int i=0;i<list.length;i++){
			Object obj = new JSONParser().parse(new FileReader(list[i])); 
			JSONObject jo = (JSONObject) obj; 
			String tweet = (String) jo.get("text"); 
			long retweet = (long) jo.get("retweet_count");
			java.sql.Date date = new java.sql.Date(new Date((String)jo.get("created_at")).getTime());
			JSONObject ob = (JSONObject)jo.get("user");
			long followers = (long)ob.get("followers_count");
			long following = (long)ob.get("friends_count");
			long tweet_count = (long)ob.get("listed_count");
			long hashtag = (long)ob.get("statuses_count");
			long favourite = (long)ob.get("favourites_count");
			boolean spam = filter.analyze(tweet);
			String content = "No_Spam_Content";
			String msg = "No_Spam";
			if(spam) {
				content = "Spam_Content";
				msg = "Spam";
			}
			sb.append(favourite+","+tweet_count+","+retweet+","+hashtag+","+content+","+following+","+followers+","+getDiffYears(date,today)+","+msg+System.getProperty("line.separator"));
			Object row[] = {favourite,tweet_count,retweet,hashtag,content,following,followers,getDiffYears(date,today)+" "+list[i].getName(),msg};
			vd.dtm.addRow(row);
		}
		FileWriter fw = new FileWriter("lib/data.txt");
		fw.write(sb.toString());
		fw.close();
		vd.setVisible(true);
		vd.setSize(800,600);
		File temp = new File("lib/data.arff");
		if(!temp.exists())
			Convert.convert("lib/data.txt","lib/data.arff");
	}catch(Exception e){
		e.printStackTrace();
	}
}

public static int getDiffYears(Date first, Date last) {
    Calendar a = getCalendar(first);
    Calendar b = getCalendar(last);
    int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
    if (a.get(Calendar.DAY_OF_YEAR) > b.get(Calendar.DAY_OF_YEAR)) {
        diff--;
    }
    return diff;
}
public static Calendar getCalendar(Date date) {
    Calendar cal = Calendar.getInstance(Locale.US);
    cal.setTime(date);
    return cal;
}
}
