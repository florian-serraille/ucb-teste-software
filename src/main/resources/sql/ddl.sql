CREATE TABLE  user (
  id int(11) NOT NULL auto_increment,
  firstname varchar(255) NOT NULL,
  lastname varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  active int(11) default NULL,
  PRIMARY KEY  (id)
);