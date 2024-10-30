-- drop user
drop user if exists 'base_app_root'@'%';
-- create user
create user 'base_app_root'@'%' identified by '';
-- grants privileges
grant all on `base\_app\_db`.* to 'base_app_root'@'%';