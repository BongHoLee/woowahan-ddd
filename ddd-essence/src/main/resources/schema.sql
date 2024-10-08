-- 가게
DROP TABLE IF EXISTS SHOP;
CREATE TABLE SHOP(
                     ID INT PRIMARY KEY AUTO_INCREMENT,
                     VERSION INT NOT NULL DEFAULT 0          COMMENT '락 버전',
                     NAME VARCHAR(150) NOT NULL              COMMENT '가게명',
                     MIN_ORDER_AMOUNT INT NOT NULL           COMMENT '최소 주문 금액'
);

DROP TABLE IF EXISTS OPERATION_HOURS;
CREATE TABLE OPERATION_HOURS(
                                SHOP_ID INT NOT NULL                    COMMENT '가게 ID',
                                DAY_OF_WEEK VARCHAR(10) NOT NULL        COMMENT '휴무 요일',
                                START_TIME TIME NOT NULL                COMMENT '시작 시간',
                                END_TIME TIME NOT NULL                  COMMENT '종료 시간',
                                CONSTRAINT PK_SHOP_DAY_OFFS PRIMARY KEY (SHOP_ID, DAY_OF_WEEK)
);

-- 메뉴
DROP TABLE IF EXISTS MENU;
CREATE TABLE MENU(
                     ID INT PRIMARY KEY AUTO_INCREMENT,
                     VERSION INT NOT NULL DEFAULT 0          COMMENT '락 버전',
                     SHOP_ID INT NULL                        COMMENT '메뉴 ID',
                     MENU_NAME VARCHAR(200) NOT NULL         COMMENT '음식명',
                     MENU_DESCRIPTION VARCHAR(500) NOT NULL  COMMENT '메뉴 설명',
                     OPEN BIT NOT NULL                       COMMENT '메뉴 공개 여부'
);


DROP TABLE IF EXISTS OPTION_GROUP;
CREATE TABLE OPTION_GROUP(
                             ID INT PRIMARY KEY AUTO_INCREMENT       COMMENT '옵션그룹 ID',
                             NAME VARCHAR(100) NOT NULL              COMMENT '옵션그룹명',
                             MENU_ID INT NULL                        COMMENT '몌뉴 ID',
                             MANDATORY BIT NOT NULL                  COMMENT '기본 그룹 여부'
);


DROP TABLE IF EXISTS OPTION;
CREATE TABLE OPTION(
                       OPTION_GROUP_ID INT NULL                COMMENT '옵션그룹 ID',
                       NAME VARCHAR(100) NOT NULL              COMMENT '옵션명',
                       PRICE INT NOT NULL                      COMMENT '가격',
                       CONSTRAINT PK_MENU_OPTIONS PRIMARY KEY (OPTION_GROUP_ID, NAME, PRICE)
);

-- 장바구니
DROP TABLE IF EXISTS CART;
CREATE TABLE CART(
                     ID INT PRIMARY KEY AUTO_INCREMENT,
                     VERSION INT NOT NULL DEFAULT 0          COMMENT '락 버전',
                     USER_ID INT NOT NULL                    COMMENT '사용자ID',
                     SHOP_ID INT NULL                        COMMENT '가게ID'
);


DROP TABLE IF EXISTS CART_LINE_ITEM;
CREATE TABLE CART_LINE_ITEM(
                               ID INT PRIMARY KEY AUTO_INCREMENT,
                               CART_ID INT NULL                        COMMENT '카트 ID',
                               MENU_ID INT NOT NULL                    COMMENT '메뉴ID',
                               MENU_NAME VARCHAR(200) NOT NULL         COMMENT '음식명',
                               MENU_COUNT INT NOT NULL                 COMMENT '갯수'
);


DROP TABLE IF EXISTS CART_OPTION_GROUP;
CREATE TABLE CART_OPTION_GROUP(
                                  ID INT PRIMARY KEY AUTO_INCREMENT,
                                  CART_LINE_ITEM_ID INT NULL              COMMENT '장바구니항목ID',
                                  NAME  VARCHAR(100) NOT NULL             COMMENT '이름',
                                  CONSTRAINT U_CART_OPTION_GROUPS UNIQUE (CART_LINE_ITEM_ID, NAME)
);


DROP TABLE IF EXISTS CART_OPTION;
CREATE TABLE CART_OPTION(
                            CART_OPTION_GROUP_ID INT NOT NULL       COMMENT '카트옵션그룹ID',
                            NAME VARCHAR(100) NOT NULL              COMMENT '옵션스펙명',
                            PRICE INT NOT NULL                      COMMENT '가격',
                            CONSTRAINT PK_CART_OPTIONS PRIMARY KEY (CART_OPTION_GROUP_ID, NAME, PRICE)
);

-- 주문
DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS(
                       ID INT PRIMARY KEY AUTO_INCREMENT,
                       USER_ID INT NOT NULL                    COMMENT '사용자ID',
                       SHOP_ID INT NOT NULL                    COMMENT '가게ID',
                       ORDERED_TIME TIMESTAMP NOT NULL         COMMENT '주문시간'
);

DROP TABLE IF EXISTS ORDER_LINE_ITEM;
CREATE TABLE ORDER_LINE_ITEM(
                                ID INT PRIMARY KEY AUTO_INCREMENT,
                                ORDER_ID INT NULL                       COMMENT '주문 ID',
                                MENU_ID INT NOT NULL                    COMMENT '메뉴ID',
                                MENU_NAME VARCHAR(200) NOT NULL         COMMENT '메뉴명',
                                COUNT INT NOT NULL                 COMMENT '갯수'
);


DROP TABLE IF EXISTS ORDER_OPTION_GROUP;
CREATE TABLE ORDER_OPTION_GROUP(
                                   ID INT PRIMARY KEY AUTO_INCREMENT,
                                   ORDER_LINE_ITEM_ID INT NULL             COMMENT '장바구니항목ID',
                                   NAME  VARCHAR(100) NOT NULL             COMMENT '이름'
);


DROP TABLE IF EXISTS ORDER_OPTION;
CREATE TABLE ORDER_OPTION(
                             ORDER_OPTION_GROUP_ID INT NOT NULL      COMMENT '주문옵션그룹ID',
                             NAME VARCHAR(100) NOT NULL              COMMENT '옵션스펙명',
                             PRICE INT NOT NULL                      COMMENT '가격',
                             CONSTRAINT PK_ORDER_OPTIONS PRIMARY KEY (ORDER_OPTION_GROUP_ID, NAME, PRICE)
);
