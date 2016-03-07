del *.class
javac -d . ./src/*.java
jar cmf BotRunner.mf Runner.jar ./bot/*.class
