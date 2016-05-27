package com.derun.gt3;

public class Gt3SQL {
	
	////目前提供的查询sql只有代征单位代码，没有开具完税凭证税务机关代码和税务机关名称，税务机关名称需要关联字典表查询
	StringBuffer sb = new StringBuffer("");    
	public Gt3SQL() {
		// TODO Auto-generated constructor stub
		sb.append("    WITH JBXX as (                                                            ");
		sb.append("       select w.djxh,                                                         ");
		sb.append("               w.nsrsbh,                                                      ");
		sb.append("               w.CCSBDM,                                                      ");
		sb.append("               w.CLHPHM,                                                      ");
		sb.append("               w.CLHPZL,                                                      ");
		sb.append("               w.CLLX,                                                        ");
		sb.append("               w.SYRMC,                                                       ");
		sb.append("               w.pzxh,                                                        ");
		sb.append("               w.skssqq,                                                      ");
		sb.append("               w.skssqz,                                                      ");
		sb.append("               w.cclx                                                         ");
		sb.append("         from (select                                                         ");
		sb.append("                       a.sbxxuuid,                                            ");
		sb.append("                       c.djxh,                                                ");
		sb.append("                       c.nsrsbh,                                              ");
		sb.append("                       b.clsbdh CCSBDM,                                       ");
		sb.append("                       b.clpzh CLHPHM,                                        ");
		sb.append("                       '' CLHPZL,                                             ");
		sb.append("                       '' CLLX,                                               ");
		sb.append("                       c.nsrmc SYRMC,                                         ");
		sb.append("                       a.pzxh,                                                ");
		sb.append("                       a.skssqq,                                              ");
		sb.append("                       a.skssqz,                                              ");
		sb.append("                       '0' cclx                                               ");
		sb.append("                  from hx_sb.sb_sysbmxbg@JS_CXK a,                            ");
		sb.append("                       hx_sb.sb_clqkxx@JS_CXK   b,                            ");
		sb.append("                       hx_dj.dj_nsrxx@JS_CXK    c                             ");
		sb.append("                                                                              ");
		sb.append("                 where a.djxh = b.djxh                                        ");
		sb.append("                   and a.djxh = c.djxh                                        ");
		sb.append("                   and a.sybh_1 = b.clsbdh                                    ");
		sb.append("                   and a.sybh_1='LZWACAGA9A4181610'                           ");
		sb.append("                   AND to_char(a.skssqq , 'yyyy') = '2016'                    ");
		sb.append("                   and a.zfbz_1='N'                                           ");
		sb.append("                   and a.sybz_dm_1 ='5'                                       ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("                ) w                                                           ");
		sb.append("                  group by w.djxh,                                            ");
		sb.append("                  w.nsrsbh,                                                   ");
		sb.append("                  w.ccsbdm,                                                   ");
		sb.append("                  w.clhphm,                                                   ");
		sb.append("                  w.clhpzl,                                                   ");
		sb.append("                  w.cllx,                                                     ");
		sb.append("                  w.cclx,                                                     ");
		sb.append("                  w.syrmc,                                                    ");
		sb.append("                  w.pzxh,                                                     ");
		sb.append("                  w.skssqq,                                                   ");
		sb.append("                  w.skssqz                                                    ");
		sb.append("       )                                                                      ");
		sb.append("      select                                                                  ");
		sb.append("                                                                              ");
		sb.append("       tpl.id_sequences.nextval,                                              ");
		sb.append("       jbxx.nsrsbh              NSRSBH,                                       ");
		sb.append("       jbxx.ccsbdm              CCSBDM,                                       ");
		sb.append("       jbxx.clhphm              CLHPHM,                                       ");
		sb.append("       jbxx.clhpzl              CLHPZL,                                       ");
		sb.append("       jbxx.cllx                CLLX,                                         ");
		sb.append("                                                                              ");
		sb.append("       jbxx.syrmc SYRMC,                                                      ");
		sb.append("       jbxx.skssqq SKSSQQ,                                                    ");
		sb.append("       jbxx.skssqz SKSSQZ,                                                    ");
		sb.append("       yj.ybtse JNJE,                                                         ");
		sb.append("       yj.yzclrq WSRQ,                                                        ");
		sb.append("       jks.zsswjg_dm dzdwdm,                                                  ");
		sb.append("       nvl(jks.pzhm, jks.dzsphm) WSPZH,                                       ");
		sb.append("       sysdate SJCJRQ                                                         ");
		sb.append("        from hx_zs.zs_yjsf@JS_CXK yj, jbxx, hx_zs.zs_jks@JS_CXK jks           ");
		sb.append("                                                                              ");
		sb.append("       where                                                                  ");
		sb.append("        yj.zsxm_dm='10114'                                                    ");
		sb.append("         and jks.zsxm_dm='10114'                                              ");
		sb.append("         and yj.skzl_dm='10'                                                  ");
		sb.append("         AND to_char(yj.skssqq , 'yyyy') = '2016'                             ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("         and yj.yzpzxh = jbxx.pzxh                                            ");
		sb.append("         AND yj.skssqq = jbxx.skssqq                                          ");
		sb.append("         AND yj.skssqz = jbxx.skssqz                                          ");
		sb.append("         AND jbxx.ccsbdm = yj.sybh_1                                          ");
		sb.append("         AND yj.sybh_1 = 'LZWACAGA9A4181610'                                  ");
		sb.append("         and yj.tzlx_dm in ('1', '4')                                         ");
		sb.append("         and jks.tzlx_dm in ('1', '4')                                        ");
		sb.append("         and yj.skcllx_dm='1'                                                 ");
		sb.append("         and yj.yzclrq is not null                                            ");
		sb.append("         and yj.zsuuid = jks.zsuuid (+);                                      ");
		sb.append("                                                                              ");
		sb.append("       with jbxx as                                                           ");
		sb.append("      (                                                                       ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("       select w.djxh,                                                         ");
		sb.append("               w.nsrsbh,                                                      ");
		sb.append("               w.CCSBDM,                                                      ");
		sb.append("               w.CLHPHM,                                                      ");
		sb.append("               w.CLHPZL,                                                      ");
		sb.append("               w.CLLX,                                                        ");
		sb.append("               w.SYRMC,                                                       ");
		sb.append("               w.pzxh,                                                        ");
		sb.append("               w.skssqq,                                                      ");
		sb.append("               w.skssqz,                                                      ");
		sb.append("               w.cclx                                                         ");
		sb.append("         from (select                                                         ");
		sb.append("                       a.sbxxuuid,                                            ");
		sb.append("                       c.djxh,                                                ");
		sb.append("                       c.nsrsbh,                                              ");
		sb.append("                       b.clsbdh CCSBDM,                                       ");
		sb.append("                       b.clpzh CLHPHM,                                        ");
		sb.append("                       '' CLHPZL,                                             ");
		sb.append("                       '' CLLX,                                               ");
		sb.append("                       c.nsrmc SYRMC,                                         ");
		sb.append("                       a.pzxh,                                                ");
		sb.append("                       a.skssqq,                                              ");
		sb.append("                       a.skssqz,                                              ");
		sb.append("                       '0' cclx                                               ");
		sb.append("                  from hx_sb.sb_sysbmxbg@JS_CXK a,                            ");
		sb.append("                       hx_sb.sb_clqkxx@JS_CXK   b,                            ");
		sb.append("                       hx_dj.dj_nsrxx@JS_CXK    c                             ");
		sb.append("                                                                              ");
		sb.append("                 where a.djxh = b.djxh                                        ");
		sb.append("                   and a.djxh = c.djxh                                        ");
		sb.append("                   and a.sybh_1 = b.clsbdh                                    ");
		sb.append("                   and a.sybh_1='LZWACAGA9A4181610'                           ");
		sb.append("                   AND to_char(a.skssqq , 'yyyy') = '2016'                    ");
		sb.append("                   and a.zfbz_1='N'                                           ");
		sb.append("                   and a.sybz_dm_1 ='5'                                       ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("                ) w                                                           ");
		sb.append("                  group by w.djxh,                                            ");
		sb.append("                  w.nsrsbh,                                                   ");
		sb.append("                  w.ccsbdm,                                                   ");
		sb.append("                  w.clhphm,                                                   ");
		sb.append("                  w.clhpzl,                                                   ");
		sb.append("                  w.cllx,                                                     ");
		sb.append("                  w.cclx,                                                     ");
		sb.append("                  w.syrmc,                                                    ");
		sb.append("                  w.pzxh,                                                     ");
		sb.append("                  w.skssqq,                                                   ");
		sb.append("                  w.skssqz                                                    ");
		sb.append("       )                                                                      ");
		sb.append("      select                                                                  ");
		sb.append("                                                                              ");
		sb.append("       tpl.id_sequences.nextval,                                              ");
		sb.append("       jbxx.nsrsbh              NSRSBH,                                       ");
		sb.append("       jbxx.ccsbdm              CCSBDM,                                       ");
		sb.append("       jbxx.clhphm              CLHPHM,                                       ");
		sb.append("       jbxx.clhpzl              CLHPZL,                                       ");
		sb.append("       jbxx.cllx                CLLX,                                         ");
		sb.append("                                                                              ");
		sb.append("       jbxx.syrmc SYRMC,                                                      ");
		sb.append("       jbxx.skssqq SKSSQQ,                                                    ");
		sb.append("       jbxx.skssqz SKSSQZ,                                                    ");
		sb.append("       yj.ybtse JNJE,                                                         ");
		sb.append("       yj.yzclrq WSRQ,                                                        ");
		sb.append("       jks.zsswjg_dm dzdwdm,                                                  ");
		sb.append("       nvl(jks.pzhm, jks.dzsphm) WSPZH,                                       ");
		sb.append("       sysdate SJCJRQ                                                         ");
		sb.append("        from hx_zs.zs_yjsf@JS_CXK yj, jbxx, hx_zs.zs_wsz@JS_CXK jks           ");
		sb.append("                                                                              ");
		sb.append("       where                                                                  ");
		sb.append("          yj.zsxm_dm='10114'                                                  ");
		sb.append("                                                                              ");
		sb.append("         and yj.skzl_dm='10'                                                  ");
		sb.append("         AND to_char(yj.skssqq , 'yyyy') = '2016'                             ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("         and yj.yzpzxh = jbxx.pzxh                                            ");
		sb.append("         AND yj.skssqq = jbxx.skssqq                                          ");
		sb.append("         AND yj.skssqz = jbxx.skssqz                                          ");
		sb.append("         AND jbxx.ccsbdm = yj.sybh_1                                          ");
		sb.append("                  AND yj.sybh_1 = 'LZWACAGA9A4181610'                         ");
		sb.append("         and yj.tzlx_dm in ('1', '4')                                         ");
		sb.append("         and jks.tzlx_dm in ('1', '4')                                        ");
		sb.append("         and yj.skcllx_dm='1'                                                 ");
		sb.append("         and yj.yzclrq is not null                                            ");
		sb.append("         and yj.zsuuid = jks.zsuuid (+);                                      ");
		sb.append("                                                                              ");
		sb.append("   with jbxx as                                                               ");
		sb.append("      (                                                                       ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("       select w.djxh,                                                         ");
		sb.append("               w.nsrsbh,                                                      ");
		sb.append("               w.CCSBDM,                                                      ");
		sb.append("               w.CLHPHM,                                                      ");
		sb.append("               w.CLHPZL,                                                      ");
		sb.append("               w.CLLX,                                                        ");
		sb.append("               w.SYRMC,                                                       ");
		sb.append("               w.pzxh,                                                        ");
		sb.append("               w.skssqq,                                                      ");
		sb.append("               w.skssqz,                                                      ");
		sb.append("               w.cclx                                                         ");
		sb.append("         from (select                                                         ");
		sb.append("                       a.sbxxuuid,                                            ");
		sb.append("                       c.djxh,                                                ");
		sb.append("                       c.nsrsbh,                                              ");
		sb.append("                       b.clsbdh CCSBDM,                                       ");
		sb.append("                       b.clpzh CLHPHM,                                        ");
		sb.append("                       '' CLHPZL,                                             ");
		sb.append("                       '' CLLX,                                               ");
		sb.append("                       c.XM SYRMC,                                            ");
		sb.append("                       a.pzxh,                                                ");
		sb.append("                       a.skssqq,                                              ");
		sb.append("                       a.skssqz,                                              ");
		sb.append("                       '0' cclx                                               ");
		sb.append("                  from hx_sb.sb_sysbmxbg@JS_CXK a,                            ");
		sb.append("                       hx_sb.sb_clqkxx@JS_CXK   b,                            ");
		sb.append("                       gs_cxtj.dj_zrr@JS_CXK    c                             ");
		sb.append("                                                                              ");
		sb.append("                 where a.djxh = b.djxh                                        ");
		sb.append("                   and a.djxh = c.djxh                                        ");
		sb.append("                   and a.sybh_1 = b.clsbdh                                    ");
		sb.append("                   and a.sybh_1='LZWACAGA9A4181610'                           ");
		sb.append("                   AND to_char(a.skssqq , 'yyyy') = '2016'                    ");
		sb.append("                   and a.zfbz_1='N'                                           ");
		sb.append("                   and a.sybz_dm_1 ='5'                                       ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("                ) w                                                           ");
		sb.append("                  group by w.djxh,                                            ");
		sb.append("                  w.nsrsbh,                                                   ");
		sb.append("                  w.ccsbdm,                                                   ");
		sb.append("                  w.clhphm,                                                   ");
		sb.append("                  w.clhpzl,                                                   ");
		sb.append("                  w.cllx,                                                     ");
		sb.append("                  w.cclx,                                                     ");
		sb.append("                  w.syrmc,                                                    ");
		sb.append("                  w.pzxh,                                                     ");
		sb.append("                  w.skssqq,                                                   ");
		sb.append("                  w.skssqz                                                    ");
		sb.append("       )                                                                      ");
		sb.append("      select                                                                  ");
		sb.append("                                                                              ");
		sb.append("       tpl.id_sequences.nextval,                                              ");
		sb.append("       jbxx.nsrsbh              NSRSBH,                                       ");
		sb.append("       jbxx.ccsbdm              CCSBDM,                                       ");
		sb.append("       jbxx.clhphm              CLHPHM,                                       ");
		sb.append("       jbxx.clhpzl              CLHPZL,                                       ");
		sb.append("       jbxx.cllx                CLLX,                                         ");
		sb.append("                                                                              ");
		sb.append("       jbxx.syrmc SYRMC,                                                      ");
		sb.append("       jbxx.skssqq SKSSQQ,                                                    ");
		sb.append("       jbxx.skssqz SKSSQZ,                                                    ");
		sb.append("       yj.ybtse JNJE,                                                         ");
		sb.append("       yj.yzclrq WSRQ,                                                        ");
		sb.append("       jks.zsswjg_dm dzdwdm,                                                  ");
		sb.append("       nvl(jks.pzhm, jks.dzsphm) WSPZH,                                       ");
		sb.append("       sysdate SJCJRQ                                                         ");
		sb.append("        from hx_zs.zs_yjsf@JS_CXK yj, jbxx, hx_zs.zs_jks@JS_CXK jks           ");
		sb.append("                                                                              ");
		sb.append("       where                                                                  ");
		sb.append("          yj.zsxm_dm='10114'                                                  ");
		sb.append("         and jks.zsxm_dm='10114'                                              ");
		sb.append("         and yj.skzl_dm='10'                                                  ");
		sb.append("         AND to_char(yj.skssqq , 'yyyy') = '2016'                             ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("         and yj.yzpzxh = jbxx.pzxh                                            ");
		sb.append("         AND yj.skssqq = jbxx.skssqq                                          ");
		sb.append("         AND yj.skssqz = jbxx.skssqz                                          ");
		sb.append("         AND jbxx.ccsbdm = yj.sybh_1                                          ");
		sb.append("                  AND yj.sybh_1 = 'LZWACAGA9A4181610'                         ");
		sb.append("         and yj.tzlx_dm in ('1', '4')                                         ");
		sb.append("         and jks.tzlx_dm in ('1', '4')                                        ");
		sb.append("         and yj.skcllx_dm='1'                                                 ");
		sb.append("         and yj.yzclrq is not null                                            ");
		sb.append("         and yj.zsuuid = jks.zsuuid (+);                                      ");
		sb.append("                                                                              ");
		sb.append(" with jbxx as                                                                 ");
		sb.append("      (                                                                       ");
		sb.append("                                                                              ");
		sb.append("                                                                              ");
		sb.append("       select w.djxh,                                                         ");
		sb.append("               w.nsrsbh,                                                      ");
		sb.append("               w.CCSBDM,                                                      ");
		sb.append("               w.CLHPHM,                                                      ");
		sb.append("               w.CLHPZL,                                                      ");
		sb.append("               w.CLLX,                                                        ");
		sb.append("               w.SYRMC,                                                       ");
		sb.append("               w.pzxh,                                                        ");
		sb.append("               w.skssqq,                                                      ");
		sb.append("               w.skssqz,                                                      ");
		sb.append("               w.cclx                                                         ");
		sb.append("         from (select                                                         ");
		sb.append("                       a.sbxxuuid,                                            ");
		sb.append("                       c.djxh,                                                ");
		sb.append("                       c.nsrsbh,                                              ");
		sb.append("                       b.clsbdh CCSBDM,                                       ");
		sb.append("                       b.clpzh CLHPHM,                                        ");
		sb.append("                       '' CLHPZL,                                             ");
		sb.append("                       '' CLLX,                                               ");
		sb.append("                       c.XM SYRMC,                                            ");
		sb.append("                       a.pzxh,                                                ");
		sb.append("                       a.skssqq,                                              ");
		sb.append("                       a.skssqz,                                              ");
		sb.append("                       '0' cclx                                               ");
		sb.append("                  from hx_sb.sb_sysbmxbg@JS_CXK a,                            ");
		sb.append("                       hx_sb.sb_clqkxx@JS_CXK   b,                            ");
		sb.append("                       gs_cxtj.dj_zrr@JS_CXK    c                             ");
		sb.append("                                                                              ");
		sb.append("                 where a.djxh = b.djxh                                        ");
		sb.append("                   and a.djxh = c.djxh                                        ");
		sb.append("                   and a.sybh_1 = b.clsbdh                                    ");
		sb.append("                   and a.sybh_1='LZWACAGA9A4181610'                           ");
		sb.append("                   AND to_char(a.skssqq , 'yyyy') = '2016'                    ");
		sb.append("                   and a.zfbz_1='N'                                           ");
		sb.append("                   and a.sybz_dm_1 ='5'                                       ");
		sb.append("                                                                              ");
		sb.append("                ) w                                                           ");
		sb.append("                  group by w.djxh,                                            ");
		sb.append("                  w.nsrsbh,                                                   ");
		sb.append("                  w.ccsbdm,                                                   ");
		sb.append("                  w.clhphm,                                                   ");
		sb.append("                  w.clhpzl,                                                   ");
		sb.append("                  w.cllx,                                                     ");
		sb.append("                  w.cclx,                                                     ");
		sb.append("                  w.syrmc,                                                    ");
		sb.append("                  w.pzxh,                                                     ");
		sb.append("                  w.skssqq,                                                   ");
		sb.append("                  w.skssqz                                                    ");
		sb.append("       )                                                                      ");
		sb.append("      select                                                                  ");
		sb.append("                                                                              ");
		sb.append("       tpl.id_sequences.nextval,                                              ");
		sb.append("       jbxx.nsrsbh              NSRSBH,                                       ");
		sb.append("       jbxx.ccsbdm              CCSBDM,                                       ");
		sb.append("       jbxx.clhphm              CLHPHM,                                       ");
		sb.append("       jbxx.clhpzl              CLHPZL,                                       ");
		sb.append("       jbxx.cllx                CLLX,                                         ");
		sb.append("                                                                              ");
		sb.append("       jbxx.syrmc SYRMC,                                                      ");
		sb.append("       jbxx.skssqq SKSSQQ,                                                    ");
		sb.append("       jbxx.skssqz SKSSQZ,                                                    ");
		sb.append("       yj.ybtse JNJE,                                                         ");
		sb.append("       yj.yzclrq WSRQ,                                                        ");
		sb.append("       jks.zsswjg_dm dzdwdm,                                                  ");
		sb.append("       nvl(jks.pzhm, jks.dzsphm) WSPZH,                                       ");
		sb.append("       sysdate SJCJRQ                                                         ");
		sb.append("        from hx_zs.zs_yjsf@JS_CXK yj, jbxx, hx_zs.zs_wsz@JS_CXK jks           ");
		sb.append("                                                                              ");
		sb.append("       where  yj.zsxm_dm='10114'                                              ");
		sb.append("                                                                              ");
		sb.append("         and yj.skzl_dm='10'                                                  ");
		sb.append("         AND to_char(yj.skssqq , 'yyyy') = '2016'                             ");
		sb.append("                                                                              ");
		sb.append("         and yj.yzpzxh = jbxx.pzxh                                            ");
		sb.append("         AND yj.skssqq = jbxx.skssqq                                          ");
		sb.append("         AND yj.skssqz = jbxx.skssqz                                          ");
		sb.append("         AND jbxx.ccsbdm = yj.sybh_1                                          ");
		sb.append("                  AND yj.sybh_1 = 'LZWACAGA9A4181610'                         ");
		sb.append("         and yj.tzlx_dm in ('1', '4')                                         ");
		sb.append("         and jks.tzlx_dm in ('1', '4')                                        ");
		sb.append("         and yj.skcllx_dm='1'                                                 ");
		sb.append("         and yj.yzclrq is not null                                            ");
		sb.append("         and yj.zsuuid = jks.zsuuid (+);                                      ");
		
	}
	

	public static void main(String[] args) {
		Gt3SQL gt3sql = new Gt3SQL();
		System.out.println(gt3sql.sb.toString());
	}

}
