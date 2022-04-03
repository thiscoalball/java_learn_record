
CREATE TABLE bookList(
	id INT PRIMARY KEY AUTO_INCREMENT, #书籍序号
	NAME VARCHAR(50) NOT NULL DEFAULT '',  #书籍名
	numbers INT NOT NULL DEFAULT 5,  #书本数量
	author VARCHAR(32) NOT NULL DEFAULT ''#作者
)CHARSET=utf8;

CREATE TABLE personList(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #人名
	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
	job VARCHAR(50) NOT NULL DEFAULT '', #岗位
	maxBook INT NOT NULL DEFAULT 3
)CHARSET=utf8;

CREATE TABLE recordList(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增
	personNAME VARCHAR(50) NOT NULL DEFAULT '',  #人名
	bookNAME VARCHAR(50) NOT NULL DEFAULT '',  #书籍名
	borrowDate DATETIME,#借阅日期
	ReturnDate DATETIME, #在什么时候归还 没还的话传null
	state VARCHAR(10) NOT NULL DEFAULT '未归还'
)CHARSET=utf8;

CREATE TABLE recordList(
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	bookId INT NOT NULL DEFAULT 0, #书籍序号
	borrowDate DATETIME,
	returnDate DATETIME
)CHARSET=utf8;

INSERT INTO personList VALUES(NULL,'123','小陈',MD5('123123'),'馆长',3);
INSERT INTO personList VALUES(NULL,'321','小王',MD5('123123'),'读者',3);
INSERT INTO bookList VALUES
	(NULL,'西游记',5,'吴承恩'),(NULL,'三国演义',5,'罗贯中'),(NULL,'水浒传',5,'施耐庵'),(NULL,'红楼梦',5,'曹雪芹');


#从这开始是测试 不用添加---------------------------------------------------------------------
SELECT * FROM booklist WHERE author = '吴承恩' OR NAME = '';

UPDATE booklist SET numbers=numbers-1 WHERE author='罗贯中' AND NAME='三国演义';
UPDATE personlist SET maxBook = maxBook - 1 WHERE  personId = 123;
SELECT * FROM personList;
SELECT * FROM bookList;
SELECT * FROM borrowlist;
SELECT * FROM recordList;
INSERT INTO borrowlist VALUES('123','1',NOW(),DATE_ADD(NOW(),INTERVAL + 30 DAY ))

SELECT * FROM borrowlist WHERE personId = '123' AND bookId = '1'

DELETE FROM borrowlist WHERE personId  = '321' AND bookId = '3'

UPDATE borrowlist SET returndate = returndate+ INTERVAL + 30 DAY 
	WHERE personid = '123' AND bookId = 4
	
(SELECT borrowDate,returndate,booklist.name AS bookName,personlist.name AS personName
	FROM borrowlist,booklist,personlist 
		WHERE BookId=booklist.id 
			AND borrowlist.personid=personlist.personid 
			AND personList.personid = '123' AND booklist.Name = '西游记')

SELECT DATEDIFF(returndate,borrowdate) AS DAY FROM borrowlist 
	WHERE personid = '123' AND bookid = 1
	SELECT borrowdate FROM borrowlist WHERE bookId=1 AND personid = '123'
	
SELECT DATEDIFF(NOW(),returndate) AS DAY FROM borrowlist
	WHERE DATE_ADD(returndate,INTERVAL 1 SECOND) <= NOW() AND bookId = 4 AND personId = '123'
	
INSERT INTO recordList VALUES(NULL,'222','333',NOW(),NULL,'未归还')
DELETE FROM recordList WHERE personName = '222'

UPDATE recordList SET state = '已归还',returnDate = NOW() WHERE bookName = '西游记' AND personName = '小陈'
TRUNCATE TABLE recordlist