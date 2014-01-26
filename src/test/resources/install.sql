/*Table structure for table `User` */
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `activity` tinyint(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

insert  into `demo_user`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`activity`,`password`) values (1,now(),'initial',null,null,'admin',1,'password');