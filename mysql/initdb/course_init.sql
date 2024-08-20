CREATE DATABASE IF NOT EXISTS course;

USE course;


CREATE TABLE IF NOT EXISTS `assignment`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `course_id`   int(11)      DEFAULT NULL,
    `due_time`    datetime(6)  DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `question`    varchar(255) DEFAULT NULL,
    `order`       int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `assignment` (`id`, `course_id`, `due_time`, `name`, `question`, `order`)
VALUES (1, 1, '2024-07-30 14:52:49.000000', 'Assignment 1: Config Python', 'Please achieve a calculator.', 1);
INSERT INTO `assignment` (`id`, `course_id`, `due_time`, `name`, `question`, `order`)
VALUES (2, 1, '2024-07-31 17:13:28.000000', 'Assignment 2: Learn Web', 'Please explain what is HTTP.', 2);
INSERT INTO `assignment` (`id`, `course_id`, `due_time`, `name`, `question`, `order`)
VALUES (3, 1, '2024-08-07 20:18:22.000000', 'Assignment 3: Learn Topic Modeling', 'Please explain what is the topic.',
        3);
INSERT INTO `assignment` (`id`, `course_id`, `due_time`, `name`, `question`, `order`)
VALUES (4, 1, '2024-08-20 11:14:42.000000', 'Assignment 4: Learn sentiment analysis',
        'Please explain what is the sentiment.', 4);
INSERT INTO `assignment` (`id`, `course_id`, `due_time`, `name`, `question`, `order`)
VALUES (5, 1, '2024-08-21 11:16:15.000000', 'Assignment 5: Learn emotion analysis',
        'Please explain what is the Please explain what is the emotion.', 5);
INSERT INTO `assignment` (`id`, `course_id`, `due_time`, `name`, `question`, `order`)
VALUES (6, 1, '2024-08-24 11:18:43.000000', 'Assignment 6: Learn user modeling',
        'Please explain what is the Please explain what is the user modeling.', 6);
INSERT INTO `assignment` (`id`, `course_id`, `due_time`, `name`, `question`, `order`)
VALUES (7, 1, '2024-08-24 13:43:14.000000', 'Assignment 7: Learn hate detection',
        'Please explain what is the Please explain what is the hate.', 7);


CREATE TABLE IF NOT EXISTS `course`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL,
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `course` (`id`, `name`)
VALUES (1, 'Web Science');

CREATE TABLE IF NOT EXISTS `student`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `create_time` datetime(6)  DEFAULT NULL,
    `update_time` datetime(6)  DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `user_id`     int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `student` (`id`, `name`, `user_id`)
VALUES (1, 'Chris', 1);

CREATE TABLE IF NOT EXISTS `submission`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `create_time`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time`   datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `assignment_id` int(11)      DEFAULT NULL,
    `content`       varchar(255) DEFAULT NULL,
    `grade`         double       DEFAULT NULL,
    `student_id`    int(11)      DEFAULT NULL,
    `submit_time`   datetime(6)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;
