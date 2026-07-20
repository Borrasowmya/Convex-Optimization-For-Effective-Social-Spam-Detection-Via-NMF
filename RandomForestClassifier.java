package com;
import java.io.FileReader;
import weka.core.Instances;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.classifiers.Evaluation;
import java.util.Random;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.Filter;

public class RandomForestClassifier {
	static double accuracy;
	static RandomForest nb;
	static Evaluation eval;
	static int lastIndex;
	static Instances train;
	static String classify;
	static double acc;
public static void train(String input) throws Exception {
	
	nb = new RandomForest();
	FileReader reader = new FileReader(input); 
    train = new Instances(reader);
    train.setClassIndex(train.numAttributes() - 1);
	lastIndex = train.numAttributes() - 1;

	NumericToNominal convert = new NumericToNominal();
	String[] options= new String[2];
    options[0]="-R";
    options[1]="first-last";  //range of variables to make numeric
    convert.setOptions(options);
	convert.setInputFormat(train);
    train = Filter.useFilter(train, convert);
	train.setClassIndex(train.numAttributes() - 1);
	lastIndex = train.numAttributes() - 1;


    nb.buildClassifier(train);
	eval = new Evaluation(train);
	eval.crossValidateModel(nb, train,5,new Random(5));
	String results = nb.toString().trim();
	classify = eval.toMatrixString();
    acc = eval.pctCorrect();
}
public static void main(String args[])throws Exception{
	train("cluster1.arff");
}
}