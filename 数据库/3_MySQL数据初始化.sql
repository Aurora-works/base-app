use base_app_db;
-- delete
set foreign_key_checks = 0;
delete from t_system_user;
delete from t_system_role;
delete from t_system_user_role;
delete from t_system_menu;
delete from t_system_role_menu;
delete from t_system_request_log;
delete from t_system_error_log;
delete from t_system_dict;
delete from t_system_param;
set foreign_key_checks = 1;

-- insert
set @sysdate = sysdate(3);
-- 系统用户表
insert into t_system_user(id, username, password, salt, nickname, sex, email, mobile, status, user_type, is_deleted, description, create_time, create_user_id, version) values
(1, 'admin', 'f045f7394e091b30b35ca79c9d8ff8a6efcd0666c541b819e92c242139d2c7bc', '944f7a6293ac19da1fdf54a3e60c3b41', '超级管理员', '1', 'admin@abc.com', '10000000000', '1', 'ADMIN', '0', '超级管理员', @sysdate, 1, 0),
(2, 'todo', null, null, '未登录用户', null, null, null, '1', 'TODO', '0', '未登录用户标识', @sysdate, 1, 0);

-- 系统角色表
insert into t_system_role(id, role_code, role_name, parent_id, order_by, status, create_time, create_user_id, version) values
(101, 'ADMIN', '超级管理员', null, '001', '1', @sysdate, 1, 0),
(102, 'SYS_MGR', '系统管理员', 101, '001001', '1', @sysdate, 1, 0),
(103, 'SYS_MGR_A', '系统管理员A', 101, '001002', '1', @sysdate, 1, 0),
(104, 'SYS_MGR_B', '系统管理员B', 101, '001003', '1', @sysdate, 1, 0),
(105, 'MGR', '管理员', 104, '001003001', '1', @sysdate, 1, 0);

-- 系统用户和系统角色的关系表
insert into t_system_user_role(id, user_id, role_id, create_time, create_user_id, version) values
(201, 1, 101, @sysdate, 1, 0);

-- 系统功能菜单表
insert into t_system_menu(id, menu_code, menu_name, href, parent_id, order_by, is_open, status, description, create_time, create_user_id, version) values
(301, 'sys', '系统管理', null, null, '991', '1', '1', '父级', @sysdate, 1, 0),
(302, 'sys_user', '人员管理', 'sys/user', 301, '991001', '1', '1', '子菜单', @sysdate, 1, 0),
(303, 'sys_role', '角色管理', 'sys/role', 301, '991002', '1', '1', '子菜单', @sysdate, 1, 0),
(304, 'sys_menu', '菜单管理', 'sys/menu', 301, '991003', '1', '1', '子菜单', @sysdate, 1, 0),
(305, 'sys_auth', '权限管理', 'sys/auth', 301, '991004', '1', '1', '子菜单', @sysdate, 1, 0),
(306, 'log', '系统日志', null, null, '992', '1', '1', '父级', @sysdate, 1, 0),
(307, 'sys_request', '操作日志', 'log/request', 306, '992001', '1', '1', '子菜单', @sysdate, 1, 0),
(308, 'sys_error', '错误记录', 'log/error', 306, '992002', '1', '1', '子菜单', @sysdate, 1, 0),
(309, 'dev', '开发中心', null, null, '993', '1', '1', '父级', @sysdate, 1, 0),
(310, 'sys_dict', '数据字典', 'dev/dict', 309, '993001', '1', '1', '子菜单', @sysdate, 1, 0),
(311, 'sys_param', '系统参数', 'dev/param', 309, '993002', '1', '1', '子菜单', @sysdate, 1, 0);

-- 系统角色对系统功能菜单的操作权限表
insert into t_system_role_menu(id, create_op, update_op, delete_op, read_op, role_id, menu_id, create_time, create_user_id, version) values
(401, '1', '1', '1', '1', 101, 302, @sysdate, 1, 0),
(402, '1', '1', '1', '1', 101, 303, @sysdate, 1, 0),
(403, '1', '1', '1', '1', 101, 304, @sysdate, 1, 0),
(404, '1', '1', '1', '1', 101, 305, @sysdate, 1, 0),
(405, '1', '1', '1', '1', 101, 307, @sysdate, 1, 0),
(406, '1', '1', '1', '1', 101, 308, @sysdate, 1, 0),
(407, '1', '1', '1', '1', 101, 310, @sysdate, 1, 0),
(408, '1', '1', '1', '1', 101, 311, @sysdate, 1, 0);

-- 系统数据字典表
insert into t_system_dict(id, dict_code, dict_key, dict_value, order_by, status, description, create_time, create_user_id, version) values
(501, 'STATUS', '1', '启用', '1', '1', '系统数据状态', @sysdate, 1, 0),
(502, 'STATUS', '0', '停用', '2', '1', '系统数据状态', @sysdate, 1, 0),
(503, 'YES_OR_NO', '1', '是', '1', '1', '是否', @sysdate, 1, 0),
(504, 'YES_OR_NO', '0', '否', '2', '1', '是否', @sysdate, 1, 0),
(505, 'SEX', '1', '男', '1', '1', '性别', @sysdate, 1, 0),
(506, 'SEX', '0', '女', '2', '1', '性别', @sysdate, 1, 0),
(516, 'USER_TYPE', 'ADMIN', '系统管理员', '1', '1', '用户类型', @sysdate, 1, 0),
(517, 'USER_TYPE', 'USER', '普通用户', '2', '1', '用户类型', @sysdate, 1, 0),
(518, 'USER_TYPE', 'TODO', '系统留用', '3', '1', '用户类型', @sysdate, 1, 0);

-- 系统参数表
insert into t_system_param(id, param_code, param_desc, param_value, description, create_time, create_user_id, version) values
(601, 'SYS_DEFAULT_PASSWORD', '系统默认密码', '123456', '新增、重置系统用户时使用的默认密码', @sysdate, 1, 0);