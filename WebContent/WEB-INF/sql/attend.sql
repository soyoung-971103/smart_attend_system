insert into student(depart_id, grade, class, schoolno, name, phone, sex, pwd, pic, state) values(1, 1, '1','1','1','1',1, '1','1','1');

select  * from student;

delete from student where id = 2;

create table attendcontrol ( 
   id   int     not null    auto_increment,
   subjecttime   tinyint,
   lecturetime   tinyint,
   mylecturetime   tinyint,
   attendtime   tinyint,
   primary key(id)
);

create table building ( 
   id   int     not null    auto_increment,
   name   varchar(20),
   floor   tinyint,
   primary key(id)
);

create table depart ( 
   id   int     not null    auto_increment,
   name   varchar(50),
   classnum   tinyint,
   gradesystem   tinyint,
   primary key(id)
);

create table holiday ( 
   id   int     not null    auto_increment,
   yyyy   int,
   holiday   date,
   reason   varchar(20),
   primary key(id)
);

create table lecture ( 
   id   int     not null    auto_increment,
   subject_id   int,
   teacher_id   int,
   class   varchar(5),
   primary key(id)
);

create table lectureday ( 
   id   int     not null    auto_increment,
   lecture_id   int,
   room_id   int,
   th   tinyint,
   starth   tinyint,
   normdate   date,
   normstart   tinyint,
   normhour   tinyint,
   normstate   varchar(5),
   restdate   date,
   reststart   tinyint,
   resthour   tinyint,
   reststate   varchar(5),
   state   varchar(10),
   primary key(id)
);

create table mylecture ( 
   id   int     not null    auto_increment,
   strudent_id   int,
   lecture_id   int,
   departname   varchar(30),
   grade   tinyint,
   term   tinyint,
   iattend   tinyint,
   imiddle   tinyint,
   ilast   tinyint,
   inormal   tinyint,
   ipractice   tinyint,
   itotal   tinyint,
   ipoint   float,
   igrade   varchar(2),
   retake   tinyint,
   ilate   tinyint,
   ixhour   int,
   qakind   tinyint,
   qaday   date,
   qatitle   varchar(255),
   qaask   varchar(255),
   qaanswer   text,
   h1   tinyint,
   h2   tinyint,
   h3   tinyint,
   h4   tinyint,
   h5   tinyint,
   h6   tinyint,
   h7   tinyint,
   h8   tinyint,
   h9   tinyint,
   h10   tinyint,
   h11   tinyint,
   h12   tinyint,
   h13   tinyint,
   h14   tinyint,
   h15   tinyint,
   h16   tinyint,
   h17   tinyint,
   h18   tinyint,
   h19   tinyint,
   h20   tinyint,
   h21   tinyint,
   h22   tinyint,
   h23   tinyint,
   h24   tinyint,
   h25   tinyint,
   h26   tinyint,
   h27   tinyint,
   h28   tinyint,
   h29   tinyint,
   h30   tinyint,
   h31   tinyint,
   h32   tinyint,
   h33   tinyint,
   h34   tinyint,
   h35   tinyint,
   h36   tinyint,
   h37   tinyint,
   h38   tinyint,
   h39   tinyint,
   h40   tinyint,
   h41   tinyint,
   h42   tinyint,
   h43   tinyint,
   h44   tinyint,
   h45   tinyint,
   h46   tinyint,
   h47   tinyint,
   h48   tinyint,
   h49   tinyint,
   h50   tinyint,
   h51   tinyint,
   h52   tinyint,
   h53   tinyint,
   h54   tinyint,
   h55   tinyint,
   h56   tinyint,
   h57   tinyint,
   h58   tinyint,
   h59   tinyint,
   h60   tinyint,
   h61   tinyint,
   h62   tinyint,
   h63   tinyint,
   h64   tinyint,
   primary key(id)
);

create table notice ( 
   id   int     not null    auto_increment,
   writeday   date,
   title   varchar(255),
   txt1   text,
   primary key(id)
);

create table room ( 
   id   int     not null    auto_increment,
   building_id   int,
   floor   tinyint,
   ho   varchar(10),
   depart_id   int,
   name   varchar(50),
   kind   varchar(10),
   area   int,
   primary key(id)
);

create table staff ( 
   id   int     not null    auto_increment,
   depart_id   int,
   uid   varchar(20),
   pwd   varchar(20),
   name   varchar(20),
   tel   varchar(11),
   phone   varchar(20),
   email   varchar(50),
   pic   varchar(255),
   primary key(id)
);

create table student ( 
   id   int     not null    auto_increment,
   depart_id   int,
   grade   tinyint,
   class   varchar(5),
   schoolno   varchar(9),
   name   varchar(30),
   phone   varchar(11),
   sex   tinyint,
   pwd   varchar(20),
   pic   varchar(255),
   state   varchar(5),
   primary key(id)
);

create table subject ( 
   id   int     not null    auto_increment,
   depart_id   int,
   code   varchar(20),
   yyyy   int,
   grade   tinyint,
   term   tinyint,
   ismajor   varchar(5),
   ischoice   varchar(5),
   ispractice   varchar(5),
   name   varchar(50),
   ipoint   float,
   ihour   tinyint,
   primary key(id)
);

create table teacher ( 
   id   int     not null    auto_increment,
   depart_id   int,
   kind   varchar(2),
   uid   varchar(20),
   pwd   varchar(20),
   name   varchar(20),
   tel   varchar(11),
   phone   varchar(11),
   email   varchar(50),
   pic   varchar(255),
   primary key(id)
);

create table timetable ( 
   id   int     not null    auto_increment,
   lecture_id   int,
   weekday   varchar(5),
   istart   tinyint,
   ihour   tinyint,
   room_id   int,
   primary key(id)
);

