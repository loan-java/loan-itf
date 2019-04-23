//package com.mod.loan.itf.sms.old.yunpian;
//
//public enum YunPianSmsSign {
//	KELEGUAN("keleguan","100","惠金黑卡","f55ff33fd81556a2441c18956e509a00",""),
//	MALIHUISHOU("malihuishou","101","麻利app","f55ff33fd81556a2441c18956e509a00",""),
//	HUAQIAN("huaqian","102","51花钱","f55ff33fd81556a2441c18956e509a00",""),
//	SUIXINGHUA("suixinghua","103","随行花","f55ff33fd81556a2441c18956e509a00",""),
//	ZHAOCAIHOU("zhaocaihou","104","招财猴","f55ff33fd81556a2441c18956e509a00",""),
//	KELEDAI("keledai","105","可乐app","f55ff33fd81556a2441c18956e509a00",""),
//	XIANYU("xianyu","106","仙遇钱包","f55ff33fd81556a2441c18956e509a00",""),
//	MIAOJIE("miaojie","107","秒借信用","f55ff33fd81556a2441c18956e509a00",""),
//	FEINIUDAI("feiniudai","108","飞牛app","f55ff33fd81556a2441c18956e509a00",""),
//	JINZHUBAO("jinzhubao","109","金猪宝","f55ff33fd81556a2441c18956e509a00",""),
//	YIMIAOJIE("yimiaojie","110","易秒下款","f55ff33fd81556a2441c18956e509a00",""),
//	TUZI("tuzi","111","兔子优钱","f55ff33fd81556a2441c18956e509a00",""),
//	YIDAI("yidai","112","易贷码头","f55ff33fd81556a2441c18956e509a00",""),
//	QIANCHENG("qiancheng","113","钱无尽","f55ff33fd81556a2441c18956e509a00",""),
//	REMEN("remen","114","火力app","f55ff33fd81556a2441c18956e509a00",""),
//	RUYIZHU("ruyizhu","115","如意猪","f55ff33fd81556a2441c18956e509a00",""),
//	JINQIANGUI("jinqiangui","116","赢天下","f55ff33fd81556a2441c18956e509a00",""),
//	CHAOXIANGJIE("chaoxiangjie","117","钞享借","f55ff33fd81556a2441c18956e509a00",""),
//	NUOMIJIE("nuomijie","118","诺米借","f55ff33fd81556a2441c18956e509a00",""),
//	FENDADAI("fendadai","119","芬达app","f55ff33fd81556a2441c18956e509a00",""),
//	HUOLIDAI("huolidai","120","活力app","f55ff33fd81556a2441c18956e509a00",""),
//	DAZHONG("dazhong","121","大众秒贷","f55ff33fd81556a2441c18956e509a00",""),
//	BAOTAI	("baotai","122","宝泰钱包","f55ff33fd81556a2441c18956e509a00",""),
//	JINGYU("jingyu","123","鲸鱼app","f55ff33fd81556a2441c18956e509a00",""),
//	FANSHEN("fanshen","124","翻身钱包","f55ff33fd81556a2441c18956e509a00",""),
//	JINLIANHUA("jinlianhua","125","金联花","f55ff33fd81556a2441c18956e509a00",""),
//	BAOJIEDAO("baojiedao","126","包借到","f55ff33fd81556a2441c18956e509a00",""),
//	YUNDOU("yundou","127","云豆钱包","f55ff33fd81556a2441c18956e509a00",""),
//	HEIKA("heika","128","91黑卡","f55ff33fd81556a2441c18956e509a00",""),
//	WOYAODAI("woyaodai","129","51我要app","f55ff33fd81556a2441c18956e509a00",""),
//	WENXIAKUAN("wenxiakuan","130","51稳下款","f55ff33fd81556a2441c18956e509a00",""),
//	XUEBIDAI("xuebidai","131","雪碧app","f55ff33fd81556a2441c18956e509a00",""),
//	DALIANHUA("dalianhua","132","大脸花","f55ff33fd81556a2441c18956e509a00",""),
//	dalihua("dalihua","133","大力花","f55ff33fd81556a2441c18956e509a00",""),
//	YOUJIE("youjie","134","51有借","f55ff33fd81556a2441c18956e509a00",""),
//	MALIU("maliu","135","麻溜优借","f55ff33fd81556a2441c18956e509a00",""),
//	JIZHUANDAI("jizhuandai","136","51急转app","f55ff33fd81556a2441c18956e509a00",""),
//	XIANGJIE("xiangjie","137","51享借","f55ff33fd81556a2441c18956e509a00",""),
//	YOUHUA("youhua","138","有借有花","f55ff33fd81556a2441c18956e509a00",""),
//	QIANGUI("qiangui","139","51钱柜","f55ff33fd81556a2441c18956e509a00",""),
//	DUODIANDAI("duodiandai","140","51多点app","f55ff33fd81556a2441c18956e509a00",""),
//	MOD("mod","142","模德科技","f55ff33fd81556a2441c18956e509a00",""),
//	SUIBIANHUA("suibianhua","143","91随便花","f55ff33fd81556a2441c18956e509a00",""),
//	JIDAI("jidai","144","急贷钱包","f55ff33fd81556a2441c18956e509a00",""),
//	SHANQUHUA("shanquhua","145","闪趣花","f55ff33fd81556a2441c18956e509a00",""),
//	FENGSHENG("fengsheng","146","风生钱包","f55ff33fd81556a2441c18956e509a00",""),
//	QIANBILAI("qianbilai","147","钱必来","f55ff33fd81556a2441c18956e509a00",""),
//	LVKA("lvka","148","91緑卡","f55ff33fd81556a2441c18956e509a00",""),
//	XIAOHUI("xiaohui","149","小汇钱包","f55ff33fd81556a2441c18956e509a00",""),
//	YINGHUA("yinghua","150","应花钱包","f55ff33fd81556a2441c18956e509a00",""),
//	BAOJIEKUAN("baojiekuan","151","包借款","f55ff33fd81556a2441c18956e509a00",""),
//	YOUNIDAI("younidai","152","51优你贷","f55ff33fd81556a2441c18956e509a00",""),
//	SHANGTAI("shangtai","153","上台钱包","f55ff33fd81556a2441c18956e509a00",""),
//	ZHUANYUNZHU("zhuanyunzhu","154","转运猪","f55ff33fd81556a2441c18956e509a00",""),
//	KUAIZHUDAI("kuaizhudai","155","快猪贷","f55ff33fd81556a2441c18956e509a00",""),
//	YUNCAI("yuncai","156","运财钱包","f55ff33fd81556a2441c18956e509a00",""),
//	CHUANGSHI("chuangshi","157","创世花呗","f55ff33fd81556a2441c18956e509a00",""),
//	JINMEIGUI("jinmeigui","158","金玫瑰","f55ff33fd81556a2441c18956e509a00",""),
//	PEIQIHUA("peiqihua","159","配齐花","f55ff33fd81556a2441c18956e509a00",""),
//	MIZHUDAI("mizhudai","160","米猪贷","f55ff33fd81556a2441c18956e509a00",""),
//	LAIYUNBAO("laiyunbao","161","来运宝","f55ff33fd81556a2441c18956e509a00",""),
//	SUIWOJIE("suiwojie","162","随我借","f55ff33fd81556a2441c18956e509a00",""),
//	DONGXIDAI("dongxidai","163","东西贷","f55ff33fd81556a2441c18956e509a00",""),
//	DUOMILE("duomile","164","多米乐","f55ff33fd81556a2441c18956e509a00",""),
//	HUADU("huadu","165","花都钱包","f55ff33fd81556a2441c18956e509a00",""),
//	SUIYIJIE("suiyijie","166","随亿借","f55ff33fd81556a2441c18956e509a00",""),
//	MIHAODUO("mihaoduo","167","米好多","f55ff33fd81556a2441c18956e509a00",""),
//	BITONGDAI("bitongdai","168","必通贷","f55ff33fd81556a2441c18956e509a00",""),
//	LAOMA("laoma","169","老马速借","f55ff33fd81556a2441c18956e509a00",""),
//	ZHUFU("zhufu","170","猪福钱包","f55ff33fd81556a2441c18956e509a00",""),
//	JIXIANGDAI("jixiangdai","171","吉享贷","f55ff33fd81556a2441c18956e509a00",""),
//	DATONG("datong","172","大通钱包","f55ff33fd81556a2441c18956e509a00",""),
//	XIONGMAO("xiongmao","173","熊猫快借","f55ff33fd81556a2441c18956e509a00",""),
//	QIANZAIZHE("qianzaizhe","174","钱在这","f55ff33fd81556a2441c18956e509a00",""),
//	XINGXING("xingxing","175","星星速借","f55ff33fd81556a2441c18956e509a00",""),
//	HUAQIANGUAN("huaqianguan","176","花钱罐","f55ff33fd81556a2441c18956e509a00",""),
//	XIAOHUAHUA("xiaohuahua","177","小花花","f55ff33fd81556a2441c18956e509a00",""),
//	MALIFENQI("malifenqi","178","麻利分期","f55ff33fd81556a2441c18956e509a00",""),
//
//	;
//	private String key;
//
//    private String signId;
//
//    private String signName;
//
//    private String accesskey;
//
//    private String secret;
//
//    public String getKey() {
//		return key;
//	}
//
//	public void setKey(String key) {
//		this.key = key;
//	}
//
//	public String getSignId() {
//		return signId;
//	}
//
//	public void setSignId(String signId) {
//		this.signId = signId;
//	}
//
//	public String getSignName() {
//		return signName;
//	}
//
//	public void setSignName(String signName) {
//		this.signName = signName;
//	}
//
//	public String getAccesskey() {
//		return accesskey;
//	}
//
//	public void setAccesskey(String accesskey) {
//		this.accesskey = accesskey;
//	}
//
//	public String getSecret() {
//		return secret;
//	}
//
//	public void setSecret(String secret) {
//		this.secret = secret;
//	}
//
//	private YunPianSmsSign(String key, String signId, String signName, String accesskey, String secret) {
//		this.key = key;
//		this.signId = signId;
//		this.signName = signName;
//		this.accesskey = accesskey;
//		this.secret = secret;
//	}
//
//	public static YunPianSmsSign getSign(String key) {
//        for (YunPianSmsSign enumYunpianApikey : YunPianSmsSign.values()) {
//        	if (enumYunpianApikey.getKey().equals(key)) {
//        		 return enumYunpianApikey;
//			}
//        }
//        return null;
//    }
//}
