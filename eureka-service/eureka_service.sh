function runScript(){
echo "Use Java 11"
sdk u java 11.0.12.7.1-amzn
echo "Kill all processes use port 8761"
npx kill-port 8761
echo "Starting eureka server"
java -jar eureka-service-0.0.1-SNAPSHOT.jar
}
runScript