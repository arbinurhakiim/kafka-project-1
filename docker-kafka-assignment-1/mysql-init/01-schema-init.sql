CREATE TABLE IF NOT EXISTS `transaction` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `order_id` varchar(255) NOT NULL,
    `product` varchar(255) NOT NULL,
    `quantity` int unsigned NOT NULL,
    `price` double unsigned NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `hourly_transaction` (
    `product_key` VARCHAR(255) NOT NULL,
    `transaction_count` BIGINT NOT NULL,
    PRIMARY KEY (`product_key`)
    ) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;