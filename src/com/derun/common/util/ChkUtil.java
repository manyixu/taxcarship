
package com.derun.common.util;

import com.derun.common.init.CfgLoader;



/**
 * 校验公共类
 * @author 赵文斌
 */
public class ChkUtil {
	public static final String CodeType = "SysCode_Code" ; 
	public static final String CHK_0000 = CfgLoader.getConfigValue(CodeType, "CHK_0000");   // 校验正确
//	public static final String CHK_9999 = CfgLoader.getConfigValue(CodeType, "CHK_9999");   // 未知
	public static final String CHK_8000 = CfgLoader.getConfigValue(CodeType, "CHK_8000");   // 数据库异常
	public static final String CHK_8008 = CfgLoader.getConfigValue(CodeType, "CHK_8008");   // 用户校验失败 
	
	//   81...    投保查询 
	public static final String CHK_8111 = CfgLoader.getConfigValue(CodeType, "CHK_8111");	// 交强险车辆类型不合法
	public static final String CHK_8112 = CfgLoader.getConfigValue(CodeType, "CHK_8112");	// 车架号不可为空
	public static final String CHK_8113 = CfgLoader.getConfigValue(CodeType, "CHK_8113");   // 按排量计算的车辆，排量值必须大于0
	public static final String CHK_8114 = CfgLoader.getConfigValue(CodeType, "CHK_8114");   // 按吨位算税的车辆，整备质量必须大于0
	public static final String CHK_8115 = CfgLoader.getConfigValue(CodeType, "CHK_8115");   // 特殊车标志只能是1、2、3、4、5或空
	public static final String CHK_8116 = CfgLoader.getConfigValue(CodeType, "CHK_8116");   // 黑名单最后记录所属止期不能大于系统日期
	public static final String CHK_8117 = CfgLoader.getConfigValue(CodeType, "CHK_8117");   // 找不到对应税种类型
	public static final String CHK_8118 = CfgLoader.getConfigValue(CodeType, "CHK_8118");   // 纳税类型为T时，不可以传减免税信息
	public static final String CHK_8119 = CfgLoader.getConfigValue(CodeType, "CHK_8119");   // 纳税类型为T时，不可以传完税信息
	public static final String CHK_8120 = CfgLoader.getConfigValue(CodeType, "CHK_8120");   // 纳税类型为C时，减免税信息不能为空
	public static final String CHK_8121 = CfgLoader.getConfigValue(CodeType, "CHK_8121");   // 纳税类型为C时，减免税凭证号不能为空
	public static final String CHK_8122 = CfgLoader.getConfigValue(CodeType, "CHK_8122");   // 纳税类型为C时，减免税原因代码不能为空
	public static final String CHK_8123 = CfgLoader.getConfigValue(CodeType, "CHK_8123");   // 纳税类型为C时，减免税方案代码不能为空
	public static final String CHK_8124 = CfgLoader.getConfigValue(CodeType, "CHK_8124");   // 纳税类型为C时，开具减免税凭证的税务机关名称不能为空
	public static final String CHK_8125 = CfgLoader.getConfigValue(CodeType, "CHK_8125");   // 纳税类型为C时，开具减免税凭证的税务机关代码不能为空
	public static final String CHK_8126 = CfgLoader.getConfigValue(CodeType, "CHK_8126");   // 纳税类型为C时，完税信息对象不能有值
	public static final String CHK_8127 = CfgLoader.getConfigValue(CodeType, "CHK_8127");   // 纳税类型为P时，完税信息对象不能为空
	public static final String CHK_8128 = CfgLoader.getConfigValue(CodeType, "CHK_8128");   // 纳税类型为P时，开具完税凭证的税务机关名称不能为空
	public static final String CHK_8130 = CfgLoader.getConfigValue(CodeType, "CHK_8130");   // 纳税类型为P时，完税凭证号码不能为空
	public static final String CHK_8131 = CfgLoader.getConfigValue(CodeType, "CHK_8131");   // 纳税类型为P时，减免税信息对象不能有值
	public static final String CHK_8132 = CfgLoader.getConfigValue(CodeType, "CHK_8132");   // 纳税类型为E时，减免税信息对象不能为空
	public static final String CHK_8133 = CfgLoader.getConfigValue(CodeType, "CHK_8133");   // 纳税类型为E时，减免税凭证号不能为空
	public static final String CHK_8134 = CfgLoader.getConfigValue(CodeType, "CHK_8134");   // 纳税类型为E时，减免税原因代码不能为空
	public static final String CHK_8135 = CfgLoader.getConfigValue(CodeType, "CHK_8135");   // 纳税类型为E时，减免税方案代码不能为空
	public static final String CHK_8136 = CfgLoader.getConfigValue(CodeType, "CHK_8136");   // 纳税类型为E时，开具减免税凭证的税务机关名称不能为空
	public static final String CHK_8138 = CfgLoader.getConfigValue(CodeType, "CHK_8138");   // 纳税类型为E时，完税信息对象不能有值
	public static final String CHK_8139 = CfgLoader.getConfigValue(CodeType, "CHK_8139");   // 国际区域代码不合法
	public static final String CHK_8140 = CfgLoader.getConfigValue(CodeType, "CHK_8140");   // 代收公司代码不合法
	public static final String CHK_8141 = CfgLoader.getConfigValue(CodeType, "CHK_8141");   // 纳税类型为E时,减免方案必须是免税
	public static final String CHK_8142 = CfgLoader.getConfigValue(CodeType, "CHK_8142");   // 投保查询纳税类型不能为拒缴
	public static final String CHK_8143 = CfgLoader.getConfigValue(CodeType, "CHK_8143");   // 初始登记日期不能晚于投保查询日期
//	public static final String CHK_8144 = CfgLoader.getConfigValue(CodeType, "CHK_8144");   // 税种类型代码(必须为”08”)
//	public static final String CHK_8448 = CfgLoader.getConfigValue(CodeType, "CHK_8448");   // 纳税信息为免税时，减免税原因不能为空
//	public static final String CHK_8449 = CfgLoader.getConfigValue(CodeType, "CHK_8449");   // 纳税信息为免税时，减免税方案不能为空
	public static final String CHK_8150 = CfgLoader.getConfigValue(CodeType, "CHK_8150");   // 整备质量超出规定范围

//  82...    确认服务 
	public static final String CHK_8211 = CfgLoader.getConfigValue(CodeType, "CHK_8211");   // 查询码无效
	public static final String CHK_8212 = CfgLoader.getConfigValue(CodeType, "CHK_8212");   // 不允许跨年确认，需重新投保查询
	public static final String CHK_8213 = CfgLoader.getConfigValue(CodeType, "CHK_8213");   // 合计金额不一致
	public static final String CHK_8214 = CfgLoader.getConfigValue(CodeType, "CHK_8214");   // 纳税类型不一致
	public static final String CHK_8215 = CfgLoader.getConfigValue(CodeType, "CHK_8215");   // 合计金额和纳税类型都不一致
	public static final String CHK_8216 = CfgLoader.getConfigValue(CodeType, "CHK_8216");   // 补传失败
	public static final String CHK_8217 = CfgLoader.getConfigValue(CodeType, "CHK_8217");   // 单位计税金额不一致
	public static final String CHK_8218 = CfgLoader.getConfigValue(CodeType, "CHK_8218");   // 当期年单位税额与查询信息不一致
	public static final String CHK_8219 = CfgLoader.getConfigValue(CodeType, "CHK_8219");   // 开具减免税务机关名称不一致
	public static final String CHK_8220 = CfgLoader.getConfigValue(CodeType, "CHK_8220");   // 开具减免税务机关代码不一致
	public static final String CHK_8221 = CfgLoader.getConfigValue(CodeType, "CHK_8221");   // 减免凭证号不一致
	public static final String CHK_8222 = CfgLoader.getConfigValue(CodeType, "CHK_8222");   // 算税标志不一样
	public static final String CHK_8223 = CfgLoader.getConfigValue(CodeType, "CHK_8223");   // 合计金额数据类型不能为空
	public static final String CHK_8224 = CfgLoader.getConfigValue(CodeType, "CHK_8224");   // 减免税原因代码不一致
	public static final String CHK_8225 = CfgLoader.getConfigValue(CodeType, "CHK_8225");   // 减免税方案代码不一致
	public static final String CHK_8226 = CfgLoader.getConfigValue(CodeType, "CHK_8226");   // 减免比例不一致
	public static final String CHK_8227 = CfgLoader.getConfigValue(CodeType, "CHK_8227");   // 减免金额不一致
	public static final String CHK_8228 = CfgLoader.getConfigValue(CodeType, "CHK_8228");   // 税款所属始期不一致
	public static final String CHK_8229 = CfgLoader.getConfigValue(CodeType, "CHK_8229");   // 税款所属止期不一致
	public static final String CHK_8230 = CfgLoader.getConfigValue(CodeType, "CHK_8230");	// 计税单位代码不一致
	public static final String CHK_8231 = CfgLoader.getConfigValue(CodeType, "CHK_8231");   // 本年应缴额度与查询信息不一致
	public static final String CHK_8232 = CfgLoader.getConfigValue(CodeType, "CHK_8232");   // 当期应纳税额与查询信息不一致
	public static final String CHK_8233 = CfgLoader.getConfigValue(CodeType, "CHK_8233");   // 本年合计金额不一致
	public static final String CHK_8234 = CfgLoader.getConfigValue(CodeType, "CHK_8234");   // 合计欠税金额不一致
	public static final String CHK_8235 = CfgLoader.getConfigValue(CodeType, "CHK_8235");   // 合计滞纳金不一致
	public static final String CHK_8236 = CfgLoader.getConfigValue(CodeType, "CHK_8236");   // 开具完税凭证的税务机关代码不一致
	public static final String CHK_8237 = CfgLoader.getConfigValue(CodeType, "CHK_8237");   // 开具完税凭证的税务机关名称不一致
	public static final String CHK_8238 = CfgLoader.getConfigValue(CodeType, "CHK_8238");   // 完税凭证号码不一致
	public static final String CHK_8239 = CfgLoader.getConfigValue(CodeType, "CHK_8239");   // 税种类型代码不一致
	public static final String CHK_8240 = CfgLoader.getConfigValue(CodeType, "CHK_8240");   // 税务登记证号不一致
	public static final String CHK_8241 = CfgLoader.getConfigValue(CodeType, "CHK_8241");   // 纳税人名称不一致
	public static final String CHK_8242 = CfgLoader.getConfigValue(CodeType, "CHK_8242");   // 纳税人识别号不一致
	public static final String CHK_8243 = CfgLoader.getConfigValue(CodeType, "CHK_8243");   // 当确认拒缴时，本年车船税金额不能大于0
	public static final String CHK_8244 = CfgLoader.getConfigValue(CodeType, "CHK_8244");   // 当确认拒缴时，合计滞纳金不能大于0
	public static final String CHK_8245 = CfgLoader.getConfigValue(CodeType, "CHK_8245");   // 当确认拒缴时，合计金额不能大于0
	public static final String CHK_8246 = CfgLoader.getConfigValue(CodeType, "CHK_8246");   // 投保确认时不允许拒缴
	
//	mili 2015-5-7 17:25:36   因欠税信息没有一致性校验  导致 退保长期出错	
	public static final String CHK_8247 = CfgLoader.getConfigValue(CodeType, "CHK_8247");   // 逾期日期与查询不一致
	public static final String CHK_8248 = CfgLoader.getConfigValue(CodeType, "CHK_8248");	// 逾期天数与查询不一致
	public static final String CHK_8249 = CfgLoader.getConfigValue(CodeType, "CHK_8249");   // 滞纳金与查询不一致
	public static final String CHK_8250 = CfgLoader.getConfigValue(CodeType, "CHK_8250");	// 欠税单位计税金额不一致
	public static final String CHK_8251 = CfgLoader.getConfigValue(CodeType, "CHK_8251");   // 欠税开具减免税务机关名称不一致
	public static final String CHK_8252 = CfgLoader.getConfigValue(CodeType, "CHK_8252");   // 欠税减免税原因代码不一致
	public static final String CHK_8253 = CfgLoader.getConfigValue(CodeType, "CHK_8253");   // 欠税减免税方案代码不一致
	public static final String CHK_8254 = CfgLoader.getConfigValue(CodeType, "CHK_8254");   // 欠税减免比例不一致
	public static final String CHK_8255 = CfgLoader.getConfigValue(CodeType, "CHK_8255");   // 欠税减免金额不一致
	public static final String CHK_8256 = CfgLoader.getConfigValue(CodeType, "CHK_8256");   // 欠税税款所属始期不一致
	public static final String CHK_8257 = CfgLoader.getConfigValue(CodeType, "CHK_8257");   // 欠税税款所属止期不一致
	public static final String CHK_8258 = CfgLoader.getConfigValue(CodeType, "CHK_8258");   // 欠税计税单位代码不一致
	public static final String CHK_8259 = CfgLoader.getConfigValue(CodeType, "CHK_8259");   // 欠税当期年单位税额与查询信息不一致
	public static final String CHK_8260 = CfgLoader.getConfigValue(CodeType, "CHK_8260");   // 欠税当期应纳税额与查询信息不一致
	public static final String CHK_8261 = CfgLoader.getConfigValue(CodeType, "CHK_8261");   // 欠税开具完税凭证的税务机关名称不一致
	public static final String CHK_8262 = CfgLoader.getConfigValue(CodeType, "CHK_8262");   // 欠税完税凭证号码不一致
	public static final String CHK_8263 = CfgLoader.getConfigValue(CodeType, "CHK_8263");   // 欠税合计金额不一致
	public static final String CHK_8264 = CfgLoader.getConfigValue(CodeType, "CHK_8264");   // 欠税条数与查询信息不一致
	public static final String CHK_8265 = CfgLoader.getConfigValue(CodeType, "CHK_8265");   // 欠税纳税地区代码不一致
	public static final String CHK_8266 = CfgLoader.getConfigValue(CodeType, "CHK_8266");   // 欠税开具减免税凭证的税务机关代码不一致
	public static final String CHK_8267 = CfgLoader.getConfigValue(CodeType, "CHK_8267");   // 欠税开具减免税凭证号码不一致
	public static final String CHK_8268 = CfgLoader.getConfigValue(CodeType, "CHK_8268");   // 欠税开具税凭证的税务机关代码不一致
//	mili 2015-5-7 17:25:36   因欠税信息没有一致性校验  导致 退保长期出错		
//	public static final String CHK_8269 = CfgLoader.getConfigValue(CodeType, "CHK_8269");   // 车船税确认时，车船税合计金额标志应为  ：2==确认实收
//	public static final String CHK_8270 = CfgLoader.getConfigValue(CodeType, "CHK_8270");   // 确认时选择拒缴不能填写申报状态字段
//	public static final String CHK_8271 = CfgLoader.getConfigValue(CodeType, "CHK_8271");   // 确认纳税类型为完税时，申报状态字段必须送“1”
//	public static final String CHK_8272 = CfgLoader.getConfigValue(CodeType, "CHK_8272");   // 确认纳税类型不是拒缴和完税时，申报状态字段必须送“0”


//  83...    变更查询服务
	public static final String CHK_8311 = CfgLoader.getConfigValue(CodeType, "CHK_8311");   // 无效确认码
	public static final String CHK_8312 = CfgLoader.getConfigValue(CodeType, "CHK_8312");   // 无缴税记录，此时是不可以做变更的
	public static final String CHK_8313 = CfgLoader.getConfigValue(CodeType, "CHK_8313");   // 变更查询计税，入参车辆类型无法找到对应税目
	public static final String CHK_8314 = CfgLoader.getConfigValue(CodeType, "CHK_8314");   // 变更查询入参有错误
	public static final String CHK_8315 = CfgLoader.getConfigValue(CodeType, "CHK_8315");   // 无效变更类型
	public static final String CHK_8316 = CfgLoader.getConfigValue(CodeType, "CHK_8316");   // 投保时纳税类型为R，此时是不能退保的
	public static final String CHK_8317 = CfgLoader.getConfigValue(CodeType, "CHK_8317");   // 返回车船税信息为空
	public static final String CHK_8318 = CfgLoader.getConfigValue(CodeType, "CHK_8318");   // 确认时拒缴，变更确认不能再次拒缴
	public static final String CHK_8319 = CfgLoader.getConfigValue(CodeType, "CHK_8319");   // 对应的投保确认已申报，批改未申报
	public static final String CHK_8320 = CfgLoader.getConfigValue(CodeType, "CHK_8320");   // 车辆信息不能为空
	public static final String CHK_8321 = CfgLoader.getConfigValue(CodeType, "CHK_8321");   // 初始登记日期不能为空
	public static final String CHK_8322 = CfgLoader.getConfigValue(CodeType, "CHK_8322");   // 整备质量不能为空
	public static final String CHK_8323 = CfgLoader.getConfigValue(CodeType, "CHK_8323");   // 排量不能为空
	public static final String CHK_8324 = CfgLoader.getConfigValue(CodeType, "CHK_8324");   // 交管类型不能为空
	public static final String CHK_8325 = CfgLoader.getConfigValue(CodeType, "CHK_8325");   // 纳税类型为T时，不可以传减免税信息
	public static final String CHK_8326 = CfgLoader.getConfigValue(CodeType, "CHK_8326");   // 纳税类型为T时，不可以传完税信息
	public static final String CHK_8327 = CfgLoader.getConfigValue(CodeType, "CHK_8327");   // 纳税类型为C时，减免税信息不能为空
	public static final String CHK_8328 = CfgLoader.getConfigValue(CodeType, "CHK_8328");   // 纳税类型为C时，减免税凭证号不能为空
	public static final String CHK_8329 = CfgLoader.getConfigValue(CodeType, "CHK_8329");   // 纳税类型为C时，减免税原因代码不能为空
	public static final String CHK_8330 = CfgLoader.getConfigValue(CodeType, "CHK_8330");   // 纳税类型为C时，减免税方案代码不能为空
	public static final String CHK_8331 = CfgLoader.getConfigValue(CodeType, "CHK_8331");   // 纳税类型为C时，开具减免税凭证的税务机关名称不能为空
	public static final String CHK_8332 = CfgLoader.getConfigValue(CodeType, "CHK_8332");   // 纳税类型为C时，完税信息对象不能有值
	public static final String CHK_8333 = CfgLoader.getConfigValue(CodeType, "CHK_8333");   // 纳税类型为P时，完税信息对象不能为空
	public static final String CHK_8334 = CfgLoader.getConfigValue(CodeType, "CHK_8334");   // 纳税类型为P时，开具完税凭证的税务机关名称不能为空
	public static final String CHK_8335 = CfgLoader.getConfigValue(CodeType, "CHK_8335");   // 纳税类型为P时，完税凭证号码不能为空
	public static final String CHK_8336 = CfgLoader.getConfigValue(CodeType, "CHK_8336");   // 纳税类型为P时，减免税信息对象不能有值
	public static final String CHK_8337 = CfgLoader.getConfigValue(CodeType, "CHK_8337");   // 纳税类型为E时，减免税信息对象不能为空
	public static final String CHK_8338 = CfgLoader.getConfigValue(CodeType, "CHK_8338");   // 纳税类型为E时，减免税凭证号不能为空
	public static final String CHK_8339 = CfgLoader.getConfigValue(CodeType, "CHK_8339");   // 纳税类型为E时，减免税原因代码不能为空
	public static final String CHK_8340 = CfgLoader.getConfigValue(CodeType, "CHK_8340");   // 纳税类型为E时，减免税方案代码不能为空
	public static final String CHK_8341 = CfgLoader.getConfigValue(CodeType, "CHK_8341");   // 纳税类型为E时，开具减免税凭证的税务机关名称不能为空
	public static final String CHK_8342 = CfgLoader.getConfigValue(CodeType, "CHK_8342");   // 纳税类型为E时，完税信息对象不能有值
	public static final String CHK_8343 = CfgLoader.getConfigValue(CodeType, "CHK_8343");   // 纳税类型为E并且是法定免税时，减免比例不能有值
	public static final String CHK_8344 = CfgLoader.getConfigValue(CodeType, "CHK_8344");   // 纳税类型为E并且是法定免税时，减免金额不能有值
	public static final String CHK_8345 = CfgLoader.getConfigValue(CodeType, "CHK_8345");   // 车辆类型不能为空
	public static final String CHK_8346 = CfgLoader.getConfigValue(CodeType, "CHK_8346");   // 特殊车标志只能是1、2、3、4、5或空
	public static final String CHK_8347 = CfgLoader.getConfigValue(CodeType, "CHK_8347");   // 国际区域代码不合法
	public static final String CHK_8348 = CfgLoader.getConfigValue(CodeType, "CHK_8348");   // 代收公司代码不合法
	public static final String CHK_8349 = CfgLoader.getConfigValue(CodeType, "CHK_8349");   // 申报日期不能有值
	public static final String CHK_8350 = CfgLoader.getConfigValue(CodeType, "CHK_8350");   // 投保为拒缴，车船税信息不能为空
	public static final String CHK_8351 = CfgLoader.getConfigValue(CodeType, "CHK_8351");   // 纳税类型为C时，减免税务机关代码不能为空
	public static final String CHK_8352 = CfgLoader.getConfigValue(CodeType, "CHK_8352");   // 纳税类型为P时，开具完税凭证的税务机关代码不能为空
	public static final String CHK_8353 = CfgLoader.getConfigValue(CodeType, "CHK_8353");   // 已做过退保，不能在批改
	public static final String CHK_8354 = CfgLoader.getConfigValue(CodeType, "CHK_8354");   // 初登日期不能大于系统当前日期
	
//	public static final String CHK_8355 = CfgLoader.getConfigValue(CodeType, "CHK_8355");   // 地区代码不能为空
//	public static final String CHK_8356 = CfgLoader.getConfigValue(CodeType, "CHK_8356");   // 纳税类型为拒缴时不能填写减免比例和减免金额
//	public static final String CHK_8357 = CfgLoader.getConfigValue(CodeType, "CHK_8357");   // 纳税类型为拒缴时不能输入减免税凭证号
//	public static final String CHK_8358 = CfgLoader.getConfigValue(CodeType, "CHK_8358");   // 纳税类型为拒缴时不能输入减免税原因代码
//	public static final String CHK_8359 = CfgLoader.getConfigValue(CodeType, "CHK_8359");   // 纳税类型为拒缴时不能输入减免税方案代码
//	public static final String CHK_8360 = CfgLoader.getConfigValue(CodeType, "CHK_8360");   // 纳税类型为拒缴时不能输入开具减免税凭证的税务机关名称
//  public static final String CHK_8361 = CfgLoader.getConfigValue(CodeType, "CHK_8361");   // 纳税类型为拒缴时不能输入开具减免税凭证的税务机关代码
//	public static final String CHK_8362 = CfgLoader.getConfigValue(CodeType, "CHK_8362");   // 纳税类型为拒缴时不能输入完税凭证号码
//	public static final String CHK_8363 = CfgLoader.getConfigValue(CodeType, "CHK_8363");   // 纳税类型为拒缴时不能输入开具完税凭证的税务机关名称
//	public static final String CHK_8364 = CfgLoader.getConfigValue(CodeType, "CHK_8364");   // 纳税类型为拒缴时不能输入开具完税凭证的税务机关代码

	
//  84...    变更确认服务	
	public static final String CHK_8411 = CfgLoader.getConfigValue(CodeType, "CHK_8411");   // 变更确认入参，必传信息不可为空
	public static final String CHK_8412 = CfgLoader.getConfigValue(CodeType, "CHK_8412");   // 变更不传失败
	public static final String CHK_8413 = CfgLoader.getConfigValue(CodeType, "CHK_8413");   // 合计金额不一致
	public static final String CHK_8414 = CfgLoader.getConfigValue(CodeType, "CHK_8414");   // 纳税类型不一致
	public static final String CHK_8415 = CfgLoader.getConfigValue(CodeType, "CHK_8415");   // 合计金额和纳税类型都不一致
	public static final String CHK_8416 = CfgLoader.getConfigValue(CodeType, "CHK_8416");   // 不允许跨年批改确认  批改查询与批改确认不在同一年内,请重新查询
	public static final String CHK_8417 = CfgLoader.getConfigValue(CodeType, "CHK_8417");   // 无效变更类型
	public static final String CHK_8418 = CfgLoader.getConfigValue(CodeType, "CHK_8418");   // 投保注销失败，可能是重复注销或者无效确认码
	public static final String CHK_8419 = CfgLoader.getConfigValue(CodeType, "CHK_8419");   // 确认时不是拒缴的，变更确认时不能是拒缴
	public static final String CHK_8420 = CfgLoader.getConfigValue(CodeType, "CHK_8420");   // 对应的投保确认已申报，批改未申报
	public static final String CHK_8421 = CfgLoader.getConfigValue(CodeType, "CHK_8421");   // 单位计税金额不一致
	public static final String CHK_8422 = CfgLoader.getConfigValue(CodeType, "CHK_8422");   // 当期年单位税额不一致
	public static final String CHK_8423 = CfgLoader.getConfigValue(CodeType, "CHK_8423");   // 变更补传失败
	public static final String CHK_8424 = CfgLoader.getConfigValue(CodeType, "CHK_8424");   // 无效变更查询码
	public static final String CHK_8425 = CfgLoader.getConfigValue(CodeType, "CHK_8425");   // 减免税务机关名称不一致
	public static final String CHK_8426 = CfgLoader.getConfigValue(CodeType, "CHK_8426");   // 减免税务机关代码不一致
	public static final String CHK_8427 = CfgLoader.getConfigValue(CodeType, "CHK_8427");   // 减免税凭证号不一致
	public static final String CHK_8428 = CfgLoader.getConfigValue(CodeType, "CHK_8428");   // 减免税原因代码不一致
	public static final String CHK_8429 = CfgLoader.getConfigValue(CodeType, "CHK_8429");   // 减免税方案代码不一致
	public static final String CHK_8430 = CfgLoader.getConfigValue(CodeType, "CHK_8430");   // 减免比例不一致
	public static final String CHK_8431 = CfgLoader.getConfigValue(CodeType, "CHK_8431");   // 减免金额不一致
	public static final String CHK_8432 = CfgLoader.getConfigValue(CodeType, "CHK_8432");   // 完税税务机关名称不一致
	public static final String CHK_8433 = CfgLoader.getConfigValue(CodeType, "CHK_8433");   // 完税税务机关代码不一致
	public static final String CHK_8434 = CfgLoader.getConfigValue(CodeType, "CHK_8434");   // 完税凭证号不一致
	public static final String CHK_8435 = CfgLoader.getConfigValue(CodeType, "CHK_8435");   // 无效确认标志代码 
	public static final String CHK_8436 = CfgLoader.getConfigValue(CodeType, "CHK_8436");   // 本年车船税金额不一致
	public static final String CHK_8437 = CfgLoader.getConfigValue(CodeType, "CHK_8437");   // 合计欠税金额不一致
	public static final String CHK_8438 = CfgLoader.getConfigValue(CodeType, "CHK_8438");   // 合计滞纳金不一致
	public static final String CHK_8439 = CfgLoader.getConfigValue(CodeType, "CHK_8439");   // 税款所属始期不一致
	public static final String CHK_8440 = CfgLoader.getConfigValue(CodeType, "CHK_8440");   // 税款所属止期不一致
	public static final String CHK_8441 = CfgLoader.getConfigValue(CodeType, "CHK_8441");   // 当期年单位税额不一致
	public static final String CHK_8442 = CfgLoader.getConfigValue(CodeType, "CHK_8442");   // 当期应纳税额不一致
	public static final String CHK_8443 = CfgLoader.getConfigValue(CodeType, "CHK_8443");   // 纳税信息为正常时，减免税信息不可以填
	public static final String CHK_8444 = CfgLoader.getConfigValue(CodeType, "CHK_8444");   // 纳税信息为正常时，完税信息不可以填
	public static final String CHK_8445 = CfgLoader.getConfigValue(CodeType, "CHK_8445");   // 纳税信息为减免税时，减免税信息对象必填
	public static final String CHK_8446 = CfgLoader.getConfigValue(CodeType, "CHK_8446");   // 纳税信息为减免税时，减免税比例和减税金额不能都为空
	public static final String CHK_8447 = CfgLoader.getConfigValue(CodeType, "CHK_8447");   // 纳税信息为减免税时，减免税凭证号不能为空
	public static final String CHK_8448 = CfgLoader.getConfigValue(CodeType, "CHK_8448");   // 纳税信息为减免税时，减免税原因不能为空
	public static final String CHK_8449 = CfgLoader.getConfigValue(CodeType, "CHK_8449");   // 纳税信息为减免税时，减免税方案不能为空
	public static final String CHK_8450 = CfgLoader.getConfigValue(CodeType, "CHK_8450");   // 纳税信息为减免税时，开具减免税凭证的税务机关代码不能为空
	public static final String CHK_8451 = CfgLoader.getConfigValue(CodeType, "CHK_8451");   // 纳税信息为减免税时，开具减免税凭证的税务机关名称不能为空
	public static final String CHK_8452 = CfgLoader.getConfigValue(CodeType, "CHK_8452");   // 确认时，本年纳税信息不可以为空
	public static final String CHK_8453 = CfgLoader.getConfigValue(CodeType, "CHK_8453");   // 纳税信息为减免税时，完税信息对象不能有值
	public static final String CHK_8454 = CfgLoader.getConfigValue(CodeType, "CHK_8454");   // 纳税信息为免税时，减税比例和减税金额不能都大于零
	public static final String CHK_8455 = CfgLoader.getConfigValue(CodeType, "CHK_8455");   // 纳税信息为完税时，完信息对象必填
	public static final String CHK_8456 = CfgLoader.getConfigValue(CodeType, "CHK_8456");   // 纳税信息为完税时，开具完税凭证的税务机关名称不可以为空
	public static final String CHK_8457 = CfgLoader.getConfigValue(CodeType, "CHK_8457");   // 纳税信息为完税时，开具完税凭证的税务机关代码不可以为空
	public static final String CHK_8458 = CfgLoader.getConfigValue(CodeType, "CHK_8458");   // 纳税信息为完税时，完税凭证号码不可以为空
	public static final String CHK_8459 = CfgLoader.getConfigValue(CodeType, "CHK_8459");   // 纳税信息为完税时，减免税信息对象不可以有值
	public static final String CHK_8460 = CfgLoader.getConfigValue(CodeType, "CHK_8460");   // 当期应纳税额不一致
	public static final String CHK_8461 = CfgLoader.getConfigValue(CodeType, "CHK_8461");   // 计税单位代码不一致
	public static final String CHK_8462 = CfgLoader.getConfigValue(CodeType, "CHK_8462");   // 合计金额不一致 
	public static final String CHK_8463 = CfgLoader.getConfigValue(CodeType, "CHK_8463");   // 税种类型代码不一致
	public static final String CHK_8464 = CfgLoader.getConfigValue(CodeType, "CHK_8464");   // 税务登记证号不一致
	public static final String CHK_8465 = CfgLoader.getConfigValue(CodeType, "CHK_8465");   // 纳税人名称不一致
	public static final String CHK_8466 = CfgLoader.getConfigValue(CodeType, "CHK_8466");   // 纳税人识别号不一致
	public static final String CHK_8467 = CfgLoader.getConfigValue(CodeType, "CHK_8467");   // 逾期日期不一致
	public static final String CHK_8468 = CfgLoader.getConfigValue(CodeType, "CHK_8468");   // 逾期天数不一致
	public static final String CHK_8469 = CfgLoader.getConfigValue(CodeType, "CHK_8469");   // 申报日期不一致
	public static final String CHK_8470 = CfgLoader.getConfigValue(CodeType, "CHK_8470");   // 纳税地区不一致
	public static final String CHK_8471 = CfgLoader.getConfigValue(CodeType, "CHK_8471");   // 减免方案为比例减免时，减免金额不能大于0，且比例取值范围必须是[0,1]
	public static final String CHK_8472 = CfgLoader.getConfigValue(CodeType, "CHK_8472");   // 减免方案为金额减免时，减免比例不能大于0，且减免金额不能小于0
	public static final String CHK_8473 = CfgLoader.getConfigValue(CodeType, "CHK_8473");   // 车船税已申报，不能注销
	public static final String CHK_8474 = CfgLoader.getConfigValue(CodeType, "CHK_8474");   // 车船税已完税，不能注销
	public static final String CHK_8475 = CfgLoader.getConfigValue(CodeType, "CHK_8475");   // 当确认拒缴时，本年车船税金额应该为0
	public static final String CHK_8476 = CfgLoader.getConfigValue(CodeType, "CHK_8476");   // 当确认拒缴时，合计滞纳金应该为0
	public static final String CHK_8477 = CfgLoader.getConfigValue(CodeType, "CHK_8477");   // 当确认拒缴时，合计金额应该为0
	public static final String CHK_8478 = CfgLoader.getConfigValue(CodeType, "CHK_8478");   // 当确认拒缴时，合计欠税金额应该为0
	public static final String CHK_8479 = CfgLoader.getConfigValue(CodeType, "CHK_8479");   // 纳税类型不在有效范围内
	public static final String CHK_8480 = CfgLoader.getConfigValue(CodeType, "CHK_8480");   // 纳税类型仅允许由投保询价时的正常缴税、减税变更为拒缴
	public static final String CHK_8490 = CfgLoader.getConfigValue(CodeType, "CHK_8490");   // 批改确认时 不允许是 R 拒缴
	
	public static final String CHK_8481 = CfgLoader.getConfigValue(CodeType, "CHK_8481");   // 滞纳金与查询不一致
//	public static final String CHK_8482 = CfgLoader.getConfigValue(CodeType, "CHK_8482");   // 纳税类型为免税或完税时不允许拒缴
//	public static final String CHK_8702 = CfgLoader.getConfigValue(CodeType, "CHK_8702");   // 找不到车船税信息
//	public static final String CHK_8703 = CfgLoader.getConfigValue(CodeType, "CHK_8703");   // 本年滞纳金不一致
//	public static final String CHK_8704 = CfgLoader.getConfigValue(CodeType, "CHK_8704");   // 登记日期不能大于系统当前日期
//	public static final String CHK_8705 = CfgLoader.getConfigValue(CodeType, "CHK_8705");   // 登记日期格式不正确
//	public static final String CHK_8706 = CfgLoader.getConfigValue(CodeType, "CHK_8706");   // 纳税类型为R时，减免税信息不能有值
//	public static final String CHK_8707 = CfgLoader.getConfigValue(CodeType, "CHK_8707");   // 纳税类型为R时，完税信息不能有值
//	public static final String CHK_8708 = CfgLoader.getConfigValue(CodeType, "CHK_8708");   // 已交过税，不能确认为拒缴
//	public static final String CHK_8488 = CfgLoader.getConfigValue(CodeType, "CHK_8488");   // 申报状态和查询不一致; （待定，需查看程序是否需新增错误代码）
//	public static final String CHK_8487 = CfgLoader.getConfigValue(CodeType, "CHK_8487");   // 算税标志不能为空; （需新增错误代码）
//	public static final String CHK_8483 = CfgLoader.getConfigValue(CodeType, "CHK_8483");   // 拒缴时申报状态必须为空
//	public static final String CHK_8484 = CfgLoader.getConfigValue(CodeType, "CHK_8484");   // 完税请将申报状态设为1-申报
//	public static final String CHK_8485 = CfgLoader.getConfigValue(CodeType, "CHK_8485");   // 补缴请将申报状态设为0-代收
//	public static final String CHK_8486 = CfgLoader.getConfigValue(CodeType, "CHK_8486");   // 确认时合计金额标识代码只能为4-变更确认实际退补 
	
	
	
//  84...    纳税信息查询	
	public static final String CHK_8702 = CfgLoader.getConfigValue(CodeType, "CHK_8702");	// 纳税信息不存在
	public static final String CHK_8703 = CfgLoader.getConfigValue(CodeType, "CHK_8703");	// 本年滞纳金不一致
	public static final String CHK_8704 = CfgLoader.getConfigValue(CodeType, "CHK_8704");	// 登记日期不能大于系统当前日期
	public static final String CHK_8705 = CfgLoader.getConfigValue(CodeType, "CHK_8705");	// 登记日期格式不正确
	public static final String CHK_8706 = CfgLoader.getConfigValue(CodeType, "CHK_8706");	// 纳税类型为R时，减免税信息不能有值
	public static final String CHK_8707 = CfgLoader.getConfigValue(CodeType, "CHK_8707");	// 纳税类型为R时，完税信息不能有值
	public static final String CHK_8708 = CfgLoader.getConfigValue(CodeType, "CHK_8708");	// 已交过税，不能确认为拒缴
	
	
	/**
	 * 服务访问权限校验
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean userChk(String username,String password){
		boolean chkFlag = false;
		return chkFlag;
		
	}
	
	public boolean chk_8000(){
		boolean chkFlag = false;
		return chkFlag;
	}
	/**
	 * 校验对象是否为空，字符串是否为""
	 * @param o
	 * @return
	 */
	public static boolean isNotEmpty(Object o){
		boolean result = false;
		if(o instanceof String){
			result = null != o && !"".equals(o.toString());
		}else{
			result = null != o;
		}
		return result;
	}
	
	public static void main(String[] args){
		//System.out.println(CHK_8484);
	}
}
