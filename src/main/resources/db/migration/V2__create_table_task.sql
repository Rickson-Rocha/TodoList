
-- Create the task table
CREATE TABLE task (
  id INT AUTO_INCREMENT PRIMARY KEY,
  task_title VARCHAR(100) NOT NULL UNIQUE,
  task_description VARCHAR(100) NOT NULL,
  task_start DATE,
  task_end DATE,
  task_done TINYINT(1) NOT NULL,
  task_priority VARCHAR(50),
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);