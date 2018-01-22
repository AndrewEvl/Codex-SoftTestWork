CREATE TABLE project (id INT AUTO_INCREMENT, name VARCHAR(150),user_creator_id INT, PRIMARY KEY (id), FOREIGN KEY (user_creator_id) REFERENCES user(id));
CREATE TABLE user(id INT AUTO_INCREMENT, first_name VARCHAR(100), last_name VARCHAR(100), role VARCHAR(30), PRIMARY KEY (id));
CREATE TABLE task (id INT AUTO_INCREMENT,text VARCHAR(500), status VARCHAR(50), PRIMARY KEY (id));
CREATE TABLE comments (id INT AUTO_INCREMENT,text VARCHAR(5000), user_id INT, task_id INT, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES user(id), FOREIGN KEY (task_id) REFERENCES task(id)
);

CREATE TABLE project_user (project_id INT, user_id INT, FOREIGN KEY (project_id) REFERENCES project(id), FOREIGN KEY (user_id) REFERENCES user(id));
CREATE TABLE task_user (task_id INT, user_id INT, FOREIGN KEY (task_id) REFERENCES task(id), FOREIGN KEY (user_id) REFERENCES user(id));
CREATE TABLE comments_task_user(comments_id INT, task_id INT, user_id INT, FOREIGN KEY (comments_id) REFERENCES comments(id), FOREIGN KEY (task_id) REFERENCES task(id), FOREIGN KEY (user_id) REFERENCES user(id));

DROP TABLE task_user;
DROP TABLE comments;
DROP TABLE task;
DROP TABLE project;
DROP TABLE user;
DROP TABLE project_user;
DROP TABLE comments_task_user;