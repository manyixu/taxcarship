package com.derun.controller.paraCfg.util;

import java.util.Date;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if ("".equals(str) || str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str.trim());

	}

	public static java.sql.Date getSqlDate(Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	public static StringBuilder paramSQL(StringBuilder paramSQL) {
		if (isNotEmpty(paramSQL.toString())) {
			paramSQL.append(" and ");
		} else {
			paramSQL.append(" and ");
		}
		return paramSQL;
	}

	public static StringBuilder paramSQLC(StringBuilder paramSQL) {
		if (isNotEmpty(paramSQL.toString())) {
			paramSQL.append(" and ");
		} else {
			paramSQL.append(" where ");
		}
		return paramSQL;
	}
}
