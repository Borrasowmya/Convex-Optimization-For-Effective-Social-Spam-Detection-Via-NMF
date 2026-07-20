set classpath=lib/json-simple-1.1.jar;lib/weka.jar;.;
javac -d . *.java
java -Xmx1000M com.Main
pause