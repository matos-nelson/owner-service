CREATE TABLE IF NOT EXISTS owner (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  address_id bigint NOT NULL,
  user_id varchar(255) NOT NULL,
  first_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  last_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  middle_name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  suffix varchar(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY address_id_idx (address_id),
  UNIQUE KEY user_id_idx (user_id),
  UNIQUE KEY (email)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;