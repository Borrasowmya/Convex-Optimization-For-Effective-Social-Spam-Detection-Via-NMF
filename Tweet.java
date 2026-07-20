package com;
public class Tweet{
	String tweet;
	long retweet; //dependent
	String sentiment;//attitude
	double uncertain;
public void setTweet(String tweet){
	this.tweet = tweet;
}
public String getTweet(){
	return tweet;
}
public void setRetweet(long retweet){
	this.retweet = retweet;
}
public long getRetweet(){
	return retweet;
}
public void setSentiment(String sentiment){
	this.sentiment = sentiment;
}
public String getSentiment(){
	return sentiment;
}
public void setUncertain(double uncertain){
	this.uncertain = uncertain;
}
public double getUncertain(){
	return uncertain;
}
}