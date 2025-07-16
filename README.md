# End-to-End Kafka Project

This project is a simple end-to-end demonstration of a Kafka-based system. It showcases various Kafka features, including the publish-subscribe pattern, Kafka Streams for real-time data processing, and Kafka Connect for moving data between Kafka and other systems like Elasticsearch and MySQL.

---

### **How to run:**

1.  **Start the environment:**
    Use `docker-compose` from the `docker-kafka-assignment-1` directory to start all the services, including Zookeeper, Kafka brokers, Kafka-UI, Elasticsearch, and Kibana.
    ```bash
    cd docker-kafka-assignment-1
    docker-compose up -d --build
    ```

2.  **Start Spring Boot Applications:**
    Open new terminals for each of the following applications and run them using the Maven wrapper.
    * **Order Producer:**
        ```bash
        cd order-producer
        ./mvnw spring-boot:run
        ```
    * **Order Consumer:**
        ```bash
        cd order-consumer
        ./mvnw spring-boot:run
        ```
    * **Kafka Streams:**
        ```bash
        cd kafka-streams
        ./mvnw spring-boot:run
        ```

3.  **Configure Kafka Connect:**
    Post the Kafka connector configurations to the Kafka Connect REST API.
    * **Elasticsearch Sink Connector:**
        ```bash
        curl -X POST -H "Content-Type: application/json" --data @docker-kafka-assignment-1/kafka-connectors/elasticsearch-sink-connector.json http://localhost:8083/connectors
        ```
    * **MySQL Sink Connector:**
        ```bash
        curl -X POST -H "Content-Type: application/json" --data @docker-kafka-assignment-1/kafka-connectors/mysql-sink-connector.json http://localhost:8083/connectors
        ```

4.  **Produce and Consume Messages:**
    * **Send a Sample Order:**
        ```bash
        curl -X POST -H "Content-Type: application/json" -d '{
          "id": "1",
          "product": "Laptop",
          "quantity": 1,
          "price": 1200.00
        }' http://localhost:8080/api/orders
        ```

5.  **Access Monitoring and Management UIs:**
    * **Kafka-UI:** Open your web browser and navigate to [http://localhost:8090](http://localhost:8090) to access the Kafka-UI.
    * **Kibana:** Open your web browser and navigate to [http://localhost:5601](http://localhost:5601) to access Kibana and visualize the data.

---

### **Cleanup**

To stop and remove all the containers and volumes created by `docker-compose`, run the following command:

```bash
cd docker-kafka-assignment-1
docker-compose down -v