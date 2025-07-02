## Use the official FULL image, which includes all standard transforms
#FROM confluentinc/cp-kafka-connect:7.6.0
#
## Switch to root user to install components
#USER root
#
## Install the JDBC connector from Confluent Hub
#RUN confluent-hub install --no-prompt confluentinc/kafka-connect-jdbc:10.7.6
#
## Install the required transforms package from Confluent Hub using the correct name
#RUN confluent-hub install --no-prompt jcustenborder/kafka-connect-transform-common:latest
#
## Download the MySQL JDBC driver directly into the JDBC connector's 'lib' directory.
#RUN curl -o /usr/share/confluent-hub-components/confluentinc-kafka-connect-jdbc/lib/mysql-connector-j-8.4.0.jar \
#    https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar
#
## Switch back to the non-root user for security
#
#USER 1001
# Use the official FULL image, which includes all standard transforms
FROM confluentinc/cp-kafka-connect:7.6.0

# Switch to root user to install components
USER root

RUN confluent-hub install --no-prompt debezium/debezium-connector-mysql:3.0.8
RUN confluent-hub install --no-prompt confluentinc/kafka-connect-jdbc:10.7.3
RUN #confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:14.0.6

# Download the MySQL JDBC driver directly into the JDBC connector's 'lib' directory.
RUN curl -o /usr/share/confluent-hub-components/confluentinc-kafka-connect-jdbc/lib/mysql-connector-j-8.4.0.jar \
    https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar

# Switch back to the non-root user for security
USER 1001

