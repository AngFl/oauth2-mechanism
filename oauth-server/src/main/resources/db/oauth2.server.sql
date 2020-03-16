CREATE TABLE `oauth_client_details` (
    `client_id` varchar(64) NOT NULL              COMMENT '客户端ID',
    `client_secret` varchar(255) NOT NULL         COMMENT '客户端秘钥',
    `resource_ids` varchar(128) DEFAULT ''        COMMENT '令牌可访问的资源服务器ID（按 "," 分隔)',
    `grant_types` varchar(128) DEFAULT  ''        COMMENT '授权模式 （按 "," 分隔)',
    `scopes` varchar(64) DEFAULT ''                COMMENT '授权作用域 （按 "," 分隔)',

    `server_redirect_uri` varchar(255) DEFAULT '' COMMENT  '客户端服务器注册的授权码回调地址',
    `access_token_validity` int(11) DEFAULT 0     COMMENT  '访问令牌的有效期，s为单位',
    `refresh_token_validity` int(11) DEFAULT 0    COMMENT  '刷新令牌的有效期，s为单位',
    `additional_information` varchar(128) DEFAULT '' COMMENT '令牌附属的额外信息',
    `auto_approve` enum('0', '1') NOT NULL              COMMENT '默认0,适用于authorization_code模式,设置用户是否自动approval操作,设置 1 跳过用户确认授权操作页面，直接跳到redirect_uri',
    `created_at` datetime NOT NULL                COMMENT '创建时间',
    `updated_at` datetime NOT NULL                COMMENT '修改时间',
    `meta_flag`  int(11) DEFAULT 0                COMMENT '数据删除标注 0 正常',
    PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户信息表
CREATE TABLE `oauth_user_details` (
    `user_id` BIGINT(20) NOT NULL  AUTO_INCREMENT            COMMENT '用户ID',
    `username` varchar(128) NOT NULL                         COMMENT '客户端秘钥',
    `password` varchar(255) NOT NULL                         COMMENT '令牌可访问的资源服务器ID（按 "," 分隔)',
    `additional_info` varchar(128) DEFAULT  ''               COMMENT '附属的额外信息',
    `account_expired` enum('0', '1') NOT NULL                COMMENT '用户账户过期, 默认0, 0-未过期,1-过期',
    `account_locked` enum('0', '1') NOT NULL                 COMMENT '用户账户锁定, 默认0, 0-未锁定,1-锁定',
    `account_enable` enum('0', '1') NOT NULL                 COMMENT '账户是否启用, 默认0, 0-未启用,1-启用',
    `credential_expired` enum('0', '1') NOT NULL             COMMENT '凭证过期, 默认0, 0-未过期,1-过期',

    `authorities_roles` varchar(128)                           COMMENT          '用户授权角色',
    `created_at` datetime NOT NULL                COMMENT '创建时间',
    `updated_at` datetime NOT NULL                COMMENT '修改时间',
    `meta_flag`  int(11) DEFAULT 0                COMMENT '数据删除标注 0 正常',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 权限表
CREATE TABLE `oauth_permission` (
  `permission_id` BIGINT(20)                    NOT NULL  AUTO_INCREMENT  COMMENT '权限ID',
  `permission_group_id` BIGINT(20)              NOT NULL COMMENT '权限组ID',
  `permission_group_name` varchar(64)           NOT NULL COMMENT '权限组',
  `permission_description` varchar(128)         NOT NULL                 COMMENT '权限描述',
  `permission_mask` int(11) NOT NULL            COMMENT '权限标记位, 10进制存储, 标识为2进制',
  `created_at` DATETIME NOT NULL                COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL                COMMENT '修改时间',
  `meta_flag`  INT(11) DEFAULT 0                COMMENT '数据删除标注 0 正常',
      PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 权限角色
CREATE TABLE `oauth_permission_roles` (
    `permission_role_id`      BIGINT(20) NOT NULL              AUTO_INCREMENT            COMMENT '权限角色ID',
    `permission_description`  VARCHAR(128)                     NOT NULL                  COMMENT '权限描述',
    `permission_mask`         INT(11) NOT NULL                 COMMENT '权限标记位, 10进制存储, 标识为2进制',
    `created_at`              DATETIME NOT NULL                COMMENT '创建时间',
    `updated_at`              DATETIME NOT NULL                COMMENT '修改时间',
    `meta_flag`               INT(11) DEFAULT 0                COMMENT '数据删除标注 0 正常',
    PRIMARY KEY (`permission_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 用户权限角色
CREATE TABLE `oauth_user_permission_role` (
      `oauth_user_permission_id`      BIGINT(20)   NOT NULL      AUTO_INCREMENT            COMMENT '权限角色用户ID',
      `oauth_user_id`                 BIGINT(20)   NOT NULL      COMMENT '权限描述',
      `oauth_permission_role_id`      BIGINT(20)   NOT NULL      COMMENT '权限标记位, 10进制存储, 标识为2进制',
      `created_at`              DATETIME NOT NULL                COMMENT '创建时间',
      `updated_at`              DATETIME NOT NULL                COMMENT '修改时间',
      `meta_flag`               INT(11) DEFAULT 0                COMMENT '数据删除标注 0 正常',
      PRIMARY KEY (`oauth_user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;