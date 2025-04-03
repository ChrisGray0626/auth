CREATE DATABASE IF NOT EXISTS auth;
USE auth;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `username`    varchar(32)  NOT NULL,
    `password`    varchar(255) NOT NULL,
    `role`        varchar(32)  NOT NULL,
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `user` (`id`, `username`, `password`, `role`)
VALUES (1, 'Chris', '$2a$10$2vgi2N3T5yHqdEHUH101lug07Yd3xJ8YzWgTLJ41H1Z9r8cLfvWw6', 'student');
