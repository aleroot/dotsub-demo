DROP TABLE IF EXISTS File_Details;
create table File_Details (id integer not null auto_increment,
 title varchar(255),
 descrption varchar(255),
 creationDate datetime,
 file_name varchar(255), primary key (id)
);