CREATE TABLE `oauth_client_details` (
    `client_id` varchar(64) NOT NULL              COMMENT '客户端ID',
    `client_secret` varchar(255) NOT NULL         COMMENT '客户端秘钥',
    `resource_ids` varchar(128) DEFAULT ''        COMMENT '令牌可访问的资源服务器ID（按 "," 分隔)',
    `grant_types` varchar(128) DEFAULT  ''        COMMENT '授权模式 （按 "," 分隔)',
    `scope` varchar(64) DEFAULT ''                COMMENT '授权作用域 （按 "," 分隔)',

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