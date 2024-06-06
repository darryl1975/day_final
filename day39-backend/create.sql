use mymedia;

create table employee(
	id int not null auto_increment,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(150) not null,
    profile_url varchar(2000),
    constraint employee_pk primary key (id)
);
    