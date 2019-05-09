all: clean
	javac --module-path $$PATH_TO_FX --add-modules  javafx.controls *.java

clean:
	rm *.class -f

run:
	java --module-path $$PATH_TO_FX --add-modules  javafx.controls Game
