CREATE TABLE c_user (
  `UID` varchar(36) NOT NULL,
  `NICKNAME` varchar(36) DEFAULT NULL COMMENT '昵称',
  `PASSWORD` varchar(36) DEFAULT NULL COMMENT '密码',
  `REALNAME` varchar(36) DEFAULT NULL COMMENT '真名',
  `STAR` varchar(128) DEFAULT NULL COMMENT '头像地址',
  `ONLINE` varchar(4) DEFAULT NULL COMMENT '是否在线',
  `SEX` varchar(4) DEFAULT NULL COMMENT '性别',
  `PHONE` varchar(36) DEFAULT NULL COMMENT '手机',
  `MAILADDRESS` varchar(36) DEFAULT NULL COMMENT '邮箱',
  `ROLE` varchar(36) DEFAULT NULL COMMENT '角色',
  `LEV` varchar(36) DEFAULT NULL COMMENT '等级',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';


CREATE TABLE c_message (
  `MID` int(11) NOT NULL AUTO_INCREMENT,
  `FROMTARGET` varchar(36) DEFAULT NULL,
  `TOTARGET` varchar(36) DEFAULT NULL,
  `CONTENT` blob,
  `TYPE` varchar(4) DEFAULT NULL COMMENT '1点对点消息  2群内消息 3上线通知 4下线通知 5群消息通知 6系统消息通知',
  `CREATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`MID`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8 COMMENT='信息表';


CREATE TABLE uu_relation (
  `RID` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `UID` varchar(36) DEFAULT NULL COMMENT '用户id',
  `FID` varchar(36) DEFAULT NULL COMMENT '好友id （用户id）',
  `ACTIVE` varchar(4) DEFAULT NULL COMMENT '是否有效',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户关系表';


CREATE TABLE c_group (
  `GID` varchar(36) NOT NULL COMMENT '组id',
  `GNAME` varchar(36) DEFAULT NULL COMMENT '组名(唯一)',
  `INTRODUCE` varchar(1000) DEFAULT NULL COMMENT '简介',
  `ICO` varchar(128) DEFAULT NULL COMMENT '图标',
  `MAXCOUNT` int(11) DEFAULT NULL COMMENT '最大成员数',
  `CURRENTCOUNT` int(11) DEFAULT NULL COMMENT '当前成员数',
  `MAXMANAGERCOUNT` int(11) DEFAULT NULL COMMENT '最大管理员个数',
  `MANAGERCOUNT` int(11) DEFAULT NULL COMMENT '管理员个数',
  `ACTIVE` varchar(4) DEFAULT NULL COMMENT '是否有效',
  `CREATEUSER` varchar(36) DEFAULT NULL COMMENT '创始人',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`GID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组';

CREATE TABLE ug_relation (
  `RID` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `GID` varchar(36) DEFAULT NULL COMMENT '组id',
  `UID` varchar(36) DEFAULT NULL COMMENT '用户id',
  `ACITVE` varchar(4) DEFAULT NULL COMMENT '是否有效',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `ROLE` varchar(8) DEFAULT NULL COMMENT '角色 群主owner  管理员manager  普通成员common',
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组关系表';
