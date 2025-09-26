javac -d out $(find src -name "*.java")

java -cp "lib/*;out" Main