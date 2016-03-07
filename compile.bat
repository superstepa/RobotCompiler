del *.class
javac -d . *.java
jar cmf BotRunner.mf Runner.jar ./bot/*.class
