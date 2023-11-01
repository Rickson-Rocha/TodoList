create table task (
  task_id int auto_increment primary key,
  task_title varchar(100),
  task_startAt datetime,
  task_endAt datetime,
  task_done boolean,
  task_priority varchar(50),
  user_id int not null,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);