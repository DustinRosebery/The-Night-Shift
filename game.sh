
DIR=$(dirname $(readlink -f "$0"))
cd $DIR

if type -p java >/dev/null 2>&1; then
    JAVA=java
else
    echo "Java was not found"
fi

if [[ "$JAVA" ]]; then
    VERSION=$("$JAVA" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ "$VERSION" < "1.8" ]]; then
	echo "This program requires at least Java 8"
    else
	rm -r $DIR/out/
	mkdir $DIR/out/
	
	javac -d $DIR/out/ $DIR/src/*.java
	
	cp -r $DIR/assets/fxml/ $DIR/out/fxml/
	cp -r $DIR/assets/img/ $DIR/out/img/
	
	java -cp $DIR/out/ Game
    fi
fi
