CREATE TABLE `account` (
`account_id` int(20) NOT NULL AUTO_INCREMENT,
`account_name` varchar(10) NULL,
`password` varchar(20) NULL,
`email` varchar(20) NULL,
`status` int(3) NULL COMMENT '用数字表示',
`credit` int(3) NULL,
`name` varchar(10) NULL,
`show_img` varchar(50) NULL,
`sex` varchar(3) NULL,
`age` int(3) NULL,
PRIMARY KEY (`account_id`) ,
UNIQUE INDEX `account_name_u` (`account_name`)
);

CREATE TABLE `header` (
`header_id` int(20) NOT NULL AUTO_INCREMENT,
`account_id` int(20) NOT NULL,
`create_date` datetime(6) NULL,
`status` int(5) NULL,
`address` varchar(50) NULL,
`worker_id` int(20) NULL,
PRIMARY KEY (`header_id`) 
);

CREATE TABLE `header_item` (
`header_item_id` int(20) NOT NULL AUTO_INCREMENT,
`header_id` int(20) NOT NULL,
`item_id` int(20) NOT NULL,
`quality` int(10) NULL,
`unit` varchar(5) NULL,
PRIMARY KEY (`header_item_id`) 
);

CREATE TABLE `all_items` (
`all_items_id` int(20) NOT NULL AUTO_INCREMENT,
`all_items_name` varchar(30) NULL,
`all_items_img` varchar(20) NULL,
`type_id` int(20) NULL,
PRIMARY KEY (`all_items_id`) 
);

CREATE TABLE `items` (
`item_id` int(20) NOT NULL AUTO_INCREMENT,
`unit_cost` int(10) NULL,
`item_img` varchar(255) NULL,
`all_items_id` int(20) NULL,
`damage` int(5) NULL,
`description` varchar(255) NULL,
PRIMARY KEY (`item_id`) 
);


ALTER TABLE `header` ADD CONSTRAINT `worker_account_oto` FOREIGN KEY (`worker_id`) REFERENCES `account` (`account_id`);
ALTER TABLE `header` ADD CONSTRAINT `header_account_mto` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
ALTER TABLE `header_item` ADD CONSTRAINT `header_item_header_mto` FOREIGN KEY (`header_id`) REFERENCES `header` (`header_id`);
ALTER TABLE `header_item` ADD CONSTRAINT `header_item_items_mto` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);
ALTER TABLE `items` ADD CONSTRAINT `items_all_items_otm` FOREIGN KEY (`all_items_id`) REFERENCES `all_items` (`all_items_id`);

