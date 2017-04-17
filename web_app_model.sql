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
`end_date` datetime(6) NULL COMMENT '订单归还时间',
`status` int(5) NULL,
`address` varchar(50) NULL,
`work_account_id` int(20) NULL,
PRIMARY KEY (`header_id`) 
);

CREATE TABLE `header_item` (
`header_item_id` int(20) NOT NULL AUTO_INCREMENT,
`header_id` int(20) NOT NULL,
`item_id` int(20) NOT NULL,
`attrition` int(5) NULL COMMENT '一次使用损坏率;与商品损耗率无关',
`compens` int(10) NULL COMMENT '因损坏而修补的赔偿费用',
PRIMARY KEY (`header_item_id`) 
);

CREATE TABLE `items` (
`items_id` int(20) NOT NULL AUTO_INCREMENT,
`items_name` varchar(30) NULL,
`items_price` int(10) NULL,
`items_img` varchar(20) NULL,
`items_type` int(20) NULL COMMENT '生活类的热水壶类和行李箱类',
`status` int(5) NULL,
PRIMARY KEY (`items_id`) 
);

CREATE TABLE `item` (
`item_id` int(20) NOT NULL AUTO_INCREMENT,
`item_name` varchar(20) NULL,
`unit_cost` int(10) NULL,
`item_img` varchar(255) NULL,
`items_id` int(20) NULL COMMENT '是区分同一种产品的ID',
`status` int(5) NULL COMMENT '0待租1已租2待出租',
`used_time` varchar(15) NULL DEFAULT 0 COMMENT '已使用时间,以天为默认单位',
`damage` int(5) NULL,
`description` varchar(255) NULL,
PRIMARY KEY (`item_id`) 
);

CREATE TABLE `address` (
`address_id` int(20) NOT NULL AUTO_INCREMENT,
`account_id` int(20) NOT NULL,
`address` varchar(255) NOT NULL,
`status` int(5) NULL DEFAULT 0 COMMENT '0未启用1已启用',
PRIMARY KEY (`address_id`) 
);

CREATE TABLE `work_account` (
`work_account_id` int(20) NOT NULL AUTO_INCREMENT,
`account_name` varchar(10) NOT NULL,
`password` varchar(20) NOT NULL,
`email` varchar(20) NULL,
`status` int(5) NULL DEFAULT 0 COMMENT '0激活1冻结2删除',
`show_img` varchar(50) NULL,
`sex` varchar(3) NULL,
`age` int(3) NULL,
PRIMARY KEY (`work_account_id`) 
);

CREATE TABLE `rule` (
`rule_id` int(20) NOT NULL AUTO_INCREMENT,
`items_id` int(20) NULL,
`damage_unit` int(5) NULL DEFAULT 0,
`damage_cut` int(15) NULL DEFAULT 0 COMMENT '每损耗多少就减多少元',
`used_time_unit` int(10) NULL DEFAULT 0 COMMENT '使用时间，以天为默认单位',
`used_time_cut` int(15) NULL DEFAULT 0,
PRIMARY KEY (`rule_id`) ,
UNIQUE INDEX `u_items_id` (`items_id`)
);

CREATE TABLE `comment` (
`comment_id` int(20) NOT NULL AUTO_INCREMENT,
`header_item_id` int(20) NULL,
`item_id` int(20) NULL,
`account_id` int(20) NULL,
`score` int(5) NULL,
`comment` varchar(500) NULL,
PRIMARY KEY (`comment_id`) 
);

CREATE TABLE `items_type` (
`items_type_id` int(20) NOT NULL AUTO_INCREMENT,
`items_type_name` varchar(20) NULL,
PRIMARY KEY (`items_type_id`) 
);


ALTER TABLE `header` ADD CONSTRAINT `header_account_mto` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
ALTER TABLE `header_item` ADD CONSTRAINT `header_item_header_mto` FOREIGN KEY (`header_id`) REFERENCES `header` (`header_id`);
ALTER TABLE `header_item` ADD CONSTRAINT `header_item_items_mto` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);
ALTER TABLE `item` ADD CONSTRAINT `items_all_items_otm` FOREIGN KEY (`items_id`) REFERENCES `items` (`items_id`);
ALTER TABLE `address` ADD CONSTRAINT `account_address_otm` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
ALTER TABLE `header` ADD CONSTRAINT `header_work_account_mto` FOREIGN KEY (`work_account_id`) REFERENCES `work_account` (`work_account_id`);
ALTER TABLE `rule` ADD CONSTRAINT `items_rule_oto` FOREIGN KEY (`items_id`) REFERENCES `items` (`items_id`);
ALTER TABLE `comment` ADD CONSTRAINT `header_item_comment_oto` FOREIGN KEY (`header_item_id`) REFERENCES `header_item` (`header_item_id`);
ALTER TABLE `comment` ADD CONSTRAINT `item_comment_otm` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);
ALTER TABLE `comment` ADD CONSTRAINT `account_comment_otm` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
ALTER TABLE `items` ADD CONSTRAINT `items_items_type_name` FOREIGN KEY (`items_type`) REFERENCES `items_type` (`items_type_id`);

