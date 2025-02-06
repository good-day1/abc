/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.31 : Database - text
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`text` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `text`;

/*Table structure for table `buy` */

DROP TABLE IF EXISTS `buy`;

CREATE TABLE `buy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods` int DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `buy` */

insert  into `buy`(`id`,`goods`,`userId`,`createtime`,`remark`,`count`) values 
(7,1,6,'2023-12-13 21:53:10','下次记得买李宁',2),
(8,8,6,'2023-10-18 18:54:08','天冷了',2),
(9,10,6,'2023-12-02 05:53:15','上个s',1),
(10,7,2,'2023-11-28 06:53:19','好好好',2),
(11,3,2,'2023-11-29 21:06:26','很不错',1),
(12,3,2,'2023-11-09 21:53:35','00000',1),
(13,1,27,'2023-12-11 21:53:44','233333',1),
(14,7,2,'2023-12-04 21:53:06','333333',2);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `storage` int DEFAULT NULL,
  `goodsType` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods` */

insert  into `goods`(`id`,`name`,`storage`,`goodsType`,`count`,`remark`,`price`) values 
(1,'nike aj4',1,5,104,'0999',900),
(2,'电脑',2,4,14,'123',8000),
(3,'斯伯丁篮球',1,1,464,'2',30),
(5,'vivo s18',1,4,99,'0.333',4000),
(6,'华为mate60',1,4,316,'0.222',6000),
(7,'百乐p500',5,2,60,'000000',10),
(8,'羽绒服',3,5,28,'66666',300),
(9,'亚历山大麦昆',6,6,4,'......',6000),
(10,'蝴蝶刀',7,7,8,'....',10023),
(11,'饮料',7,3,1,NULL,3),
(13,'安踏kt4',1,1,30,'liuhao嘎嘎买',300);

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goodstype` */

insert  into `goodstype`(`id`,`name`,`remark`) values 
(1,'体育','123'),
(2,'文体','0'),
(3,'其他','000'),
(4,'电子','132'),
(5,'服饰','0'),
(6,'潮流','01'),
(7,'游戏','000');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int NOT NULL,
  `menuCode` varchar(8) DEFAULT NULL,
  `menuName` varchar(16) DEFAULT NULL,
  `menuLevel` varchar(2) DEFAULT NULL,
  `menuParentCode` varchar(8) DEFAULT NULL,
  `menuClick` varchar(16) DEFAULT NULL,
  `menuRight` varchar(8) DEFAULT NULL,
  `menuComponent` varchar(200) DEFAULT NULL,
  `menuIcon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `menu` */

insert  into `menu`(`id`,`menuCode`,`menuName`,`menuLevel`,`menuParentCode`,`menuClick`,`menuRight`,`menuComponent`,`menuIcon`) values 
(1,'001','管理员管理','1',NULL,'Admin','0','admin/AdminManage.vue','\'el-icon-s-custom\''),
(2,'002','用户管理','1',NULL,'User','0,1','user/UserManage.vue','\'el-icon-user-solid\''),
(3,'003','仓库管理','1',NULL,'Storage','0,1','storage/StorageManage.vue','el-icon-office-building'),
(4,'004','物品分类','1',NULL,'Goodstype','0,1,2','goodstype/GoodstypeManage.vue','el-icon-menu'),
(5,'005','物品管理','1',NULL,'Goods','0,1,2','goods/GoodsManage.vue','el-icon-s-manage'),
(6,'006','出入库记录管理','1',NULL,'Record','0','record/RecordManage.vue','el-icon-s-order'),
(7,'007','购买记录','1',NULL,'Buy','0,1,2','buy/BuyManage.vue','el-icon-s-order');

/*Table structure for table `record` */

DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods` int DEFAULT NULL,
  `userID` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `record` */

insert  into `record`(`id`,`goods`,`userID`,`admin_id`,`count`,`createtime`,`remark`) values 
(8,1,26,1,10,NULL,'啥也不是'),
(9,2,5,1,1,NULL,'00000'),
(10,6,28,1,222,NULL,'华为才是最强的'),
(11,3,5,1,369,NULL,'你拿这个球可以扣篮'),
(12,10,4,1,1,NULL,'谁开箱谁傻瓜'),
(13,9,32,1,1,NULL,'啥也不是'),
(14,1,4,28,-3,NULL,'加三双鞋');

/*Table structure for table `storage` */

DROP TABLE IF EXISTS `storage`;

CREATE TABLE `storage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `storage` */

insert  into `storage`(`id`,`name`,`remark`) values 
(1,'tmp1','123'),
(2,'tmp2','123'),
(3,'tmp3','123'),
(5,'tmp5','123'),
(6,'tmp6','1234'),
(7,'tmp7','1'),
(8,'tmp33','00000');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `no` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `isvalid` int DEFAULT NULL,
  `money` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`no`,`name`,`password`,`age`,`sex`,`phone`,`role_id`,`isvalid`,`money`) values 
(1,'2242266376','超级管理员','aspl',18,1,'16813540047',0,1,300),
(2,'2242266377','用户','123',11,0,'4561345',2,1,170),
(3,'saf','bbbbb','123',17,0,'45645678',2,1,200),
(4,'fds123','cccc','123456',18,0,'45643534',1,1,0),
(5,'asdf','(NULLfas)','1569',18,0,'656',1,1,600),
(6,'asfsd','fsadasf','14545',18,1,'594',2,1,153033),
(7,'asfdsa','fasfasf','2595',18,0,'5965',2,1,156465),
(23,'3','2','58',20,2,'325',2,1,60515256),
(24,'123123','123','123',18,0,'111234123',1,1,800000),
(26,'12344','admi','123',12,0,'1234213',1,1,900123),
(27,'1234','asd','1234',12,1,'12342112',2,1,100),
(28,'2244266399','管理员','123',18,1,'00000555',1,1,461564),
(30,'333333333','myy','1223',18,0,'111111111',1,1,0),
(31,'3335658','ccc','123',19,1,'0000000000',1,1,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
