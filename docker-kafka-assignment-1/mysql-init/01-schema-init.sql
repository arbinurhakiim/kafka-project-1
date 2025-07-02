CREATE TABLE IF NOT EXISTS `transaction` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `order_id` varchar(255) NOT NULL,
    `product` varchar(255) NOT NULL,
    `quantity` int unsigned NOT NULL,
    `price` double unsigned NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `hourly_transaction` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `product_name` varchar(255) NOT NULL,
    `event_timestamp` timestamp NOT NULL,
    `measurement` int NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `product_time_idx` (`product_name`,`event_timestamp`)
    ) ENGINE=InnoDB;