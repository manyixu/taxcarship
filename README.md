# taxcarship

-----------------------------------

2016-02-15	更新说明

新能源系统自动判定

开关-NevMatchFlag

依赖脚本：

update syjk_ccs_code set codevalue = '[1&2:20120101-20150910],[3:20150911-00000000]' where code = 'NevMatchRule';

insert into syjk_ccs_code (CODE, CODETYPE, CODENAME, CODEALIA, VALIDATEFLAG, ISHOTPARA, CODEVALUE, REMARK, MAKDATE)
values ('NevMatchFlag', 'SysSwitch', '新能源系统判定开关', null, '0', '0', '1', '0-关闭, 1-打开', null);

-----------------------------------

version 2.2.40 based.

jdk1.6.0 recommended or named 1.6.0