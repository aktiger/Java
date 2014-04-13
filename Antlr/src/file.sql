CREATE TABLE `mon_flow` (
  `flow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据流中的计算的id',
  `flow_type` int(11) NOT NULL COMMENT '计算类型。0：udw-etl，1：udw-userjob，2：数据同步，3：datamart-etl，4：others',
  `flow_name` varchar(255) NOT NULL,
  `flow_desc` text NOT NULL COMMENT '计算在本系统的描述，是一个json_array。jobid:$jobid,jobname:$jobname',
  `flow_link` varchar(255) NOT NULL COMMENT '从哪个外部系统的url可以链接过去',
  `flow_pattern` varchar(255) NOT NULL COMMENT '用于描述一个flow的pattern，用于链接到对应的系统中去',
  `monitor_version_id` int(11) NOT NULL COMMENT '关联的数据流version id',
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`flow_id`),
  KEY `FK_mon_flow_monitor_version_id` (`monitor_version_id`),
  CONSTRAINT `FK_mon_flow_monitor_version_id` FOREIGN KEY (`monitor_version_id`) REFERENCES `mon_monitor_version` (`monitor_version_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据流中的计算的定义';