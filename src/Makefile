all: clean
	javac *.java
	java Game

clean:
	rm *.class -f
	rm manifest.txt -f

run: jar
	java -jar Game.jar

build: clean
	javac *.java

jar: build
	echo "Main-Class: Game" > manifest.txt
	rm Game.jar -f
	jar cvfm Game.jar manifest.txt *.class
	make clean