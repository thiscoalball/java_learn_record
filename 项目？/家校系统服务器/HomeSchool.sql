CREATE TABLE `user`(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #人名
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
	state VARCHAR(10) NOT NULL DEFAULT '' #岗位
);


CREATE TABLE admin(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #人名
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
	state VARCHAR(50) NOT NULL DEFAULT '' #岗位
);

CREATE TABLE teacher(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #教师名
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
	classId VARCHAR(12) NOT NULL DEFAULT '',#班主任的班级
	job VARCHAR(50) NOT NULL DEFAULT '' #教师类型
);

CREATE TABLE student(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #学生名
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
	classId VARCHAR(12) NOT NULL DEFAULT '',#所属的班级
	`guardianName` VARCHAR(50) NOT NULL DEFAULT '',  #监护人名
	guardianId VARCHAR(50) NOT NULL DEFAULT ''#亲属号(相当于电话号码)
);

CREATE TABLE guardian(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #监护人名
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
	`childName` VARCHAR(50) NOT NULL DEFAULT '',  #孩子名
	childId VARCHAR(50) NOT NULL DEFAULT '',#孩子账号(相当于电话号码)
	classId VARCHAR(12) NOT NULL DEFAULT ''#孩子的班级
);

CREATE TABLE class(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
	classId VARCHAR(50) NOT NULL DEFAULT '',#班级Id
	highestEnglish FLOAT NOT NULL DEFAULT 0, #英语最高分
	highestMath FLOAT NOT NULL DEFAULT 0,#数学最高分
	highestChinese FLOAT NOT NULL DEFAULT 0,#语文最高分
	average FLOAT NOT NULL DEFAULT 0,#班级平均分
	highestEnglishName VARCHAR(50) NOT NULL DEFAULT '',#英语最高分的人
	highestMathName VARCHAR(50) NOT NULL DEFAULT '',#数学最高分的人
	highestChineseName VARCHAR(50) NOT NULL DEFAULT '',#语文最高分的人
	highestAverageName VARCHAR(50) NOT NULL DEFAULT '',#平均分最高的人
	chargeTeacher VARCHAR(32) NOT NULL DEFAULT ''#班主任
);


CREATE TABLE offlineMessage(
	senderId VARCHAR(50) NOT NULL DEFAULT '',#发送人Id
	senderName VARCHAR(50) NOT NULL DEFAULT '',#发送人姓名
	receiverId VARCHAR(50) NOT NULL DEFAULT '',#接收人Id
	receiverofName VARCHAR(50) NOT NULL DEFAULT '',#接收人姓名
	`time` DATETIME ,     #通信的时间
	`type` VARCHAR(50) NOT NULL DEFAULT '',#通信类型
	`content` VARCHAR(50) NOT NULL DEFAULT ''#通信内容
);

CREATE TABLE allGrades(
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #姓名
	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
	classId VARCHAR(8) NOT NULL DEFAULT '',#班级Id
	English FLOAT NOT NULL DEFAULT 0, #英语分
	Math FLOAT NOT NULL DEFAULT 0,#数学分
	Chinese FLOAT NOT NULL DEFAULT 0#语文分
)

SELECT * FROM `user`
SELECT * FROM admin;
SELECT * FROM guardian	
SELECT * FROM teacher;
SELECT * FROM student
SELECT * FROM allgrades
#往用户表和管理员表插入管理员
INSERT INTO `user` VALUES(NULL,'管理员大哥','123123',MD5('123123'),'管理员');
INSERT INTO `admin` VALUES(NULL,'管理员大哥','123123',MD5('123123'),'管理员');
INSERT INTO teacher 
	VALUES(NULL,'陈老师','111111',MD5('123123'),'101','班主任'),
	      (NULL,'许老师','122222',MD5('123123'),'101','科任'),
              (NULL,'骆老师','133333',MD5('123123'),'102','班主任'),
              (NULL,'黄老师','144444',MD5('123123'),'102','科任'),
              (NULL,'蔡老师','155555',MD5('123123'),'101','段长')
INSERT INTO `user`
	VALUES(NULL,'陈老师','111111',MD5('123123'),'教师'),
	      (NULL,'许老师','122222',MD5('123123'),'教师'),
              (NULL,'骆老师','133333',MD5('123123'),'教师'),
              (NULL,'黄老师','144444',MD5('123123'),'教师'),
              (NULL,'蔡老师','155555',MD5('123123'),'教师')
INSERT INTO `student`
	VALUES(NULL,'小红','211111',MD5('123123'),'101','老红','311111'),
	      (NULL,'小绿','222222',MD5('123123'),'101','老绿','322222'),
	      (NULL,'小青','233333',MD5('123123'),'102','老青','333333'),
	      (NULL,'小蓝','244444',MD5('123123'),'102','老蓝','344444'),
	      (NULL,'小橙','255555',MD5('123123'),'103','老橙','355555')
INSERT INTO `user`
	VALUES(NULL,'小红','211111',MD5('123123'),'学生'),
	      (NULL,'小绿','222222',MD5('123123'),'学生'),
	      (NULL,'小青','233333',MD5('123123'),'学生'),
	      (NULL,'小蓝','244444',MD5('123123'),'学生'),
	      (NULL,'小橙','255555',MD5('123123'),'学生')
INSERT INTO allgrades
	VALUES(NULL,'小红','211111','101',123.5,150,127),
		(NULL,'小绿','222222','101',100.5,121,67),
		(NULL,'小青','233333','102',115,125,87),
		(NULL,'小黄','244444','102',150,10,12),
		(NULL,'小橙','255555','103',76,111,137)
INSERT INTO guardian
	VALUES(NULL,'老红','311111',MD5('123123'),'小红','211111','101'),
		(NULL,'老绿','322222',MD5('123123'),'小绿','222222','101'),
		(NULL,'老青','333333',MD5('123123'),'小青','233333','102'),
		(NULL,'老蓝','344444',MD5('123123'),'小蓝','244444','102'),
		(NULL,'老橙','355555',MD5('123123'),'小橙','255555','103')
INSERT INTO `user`
		VALUES(NULL,'老红','311111',MD5('123123'),'监护人'),
			(NULL,'老绿','322222',MD5('123123'),'监护人'),
			(NULL,'老青','333333',MD5('123123'),'监护人'),
			(NULL,'老蓝','344444',MD5('123123'),'监护人'),
			(NULL,'老橙','355555',MD5('123123'),'监护人')
SELECT personId,`name`,state FROM USER WHERE personId='123123' AND pwd=MD5('123123')
SELECT * FROM allGrades WHERE classid = '101' ORDER BY (english + math + chinese) DESC;
SELECT * FROM student WHERE classId='101'

SELECT guardianId,guardianName FROM student WHERE classId = '101'
SELECT `Name` FROM guardian WHERE personId='311111'
SELECT guardianId ,guardianName FROM student WHERE personId = '211111'

#获取班级数学排名
SELECT personId, `name`, math, @curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q WHERE classId = '101' ORDER BY math DESC 

#获取班级语文排名
SELECT personId, `name`, chinese, @curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q WHERE classId = '101' ORDER BY chinese DESC 

#获取班级英语排名
SELECT personId, `name`, english, @curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q WHERE classId = '101' ORDER BY english DESC 


#获取班级总分排名
SELECT personId, `name`, chinese,math,english,@curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q WHERE classId = '101' ORDER BY (english+math+chinese) DESC 

#获取年段数学排名
SELECT personId, `name`, math, @curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q  ORDER BY math DESC 

#获取年段语文排名
SELECT personId, `name`, chinese, @curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q  ORDER BY chinese DESC 

#获取年段英语排名
SELECT personId, `name`, english, @curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q  ORDER BY english DESC 

#获取年段总分排名
SELECT personId, `name`, chinese,math,english,@curRank := @curRank + 1 AS rank
FROM allgrades p, (
SELECT @curRank := 0
) q  ORDER BY (english+math+chinese) DESC 


select * from allgrades where name='小红' and personId = '211111'
select name,personId,guardianName,guardianId from student where classId = '101';

select personId from user where personid = '111111'

select Name , personId from `user` where Name='小红' and personId = '211111'
update guardian set childName='小司马',childId='266666',classId='103' where name='老司马' and personId = '366666';
UPDATE student SET guardianName='老司马',guardianId='366666' WHERE NAME='司马懿' AND personId = '266666'