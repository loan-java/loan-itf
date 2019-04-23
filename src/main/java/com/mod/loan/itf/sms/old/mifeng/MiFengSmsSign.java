//package com.mod.loan.itf.sms.old.mifeng;
//
//public enum MiFengSmsSign {
//	KELEGUAN("keleguan","100","惠金黑卡","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	MALIHUISHOU("malihuishou","101","麻利贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	HUAQIAN("huaqian","102","51花钱","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	SUIXINGHUA("suixinghua","103","随行花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	ZHAOCAIHOU("zhaocaihou","104","招财猴","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	KELEDAI("keledai","105","可乐贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	XIANYU("xianyu","106","仙遇钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	MIAOJIE("miaojie","107","秒借信用","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	FEINIUDAI("feiniudai","108","飞牛贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JINZHUBAO("jinzhubao","109","金猪宝","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YIMIAOJIE("yimiaojie","110","易秒下款","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	TUZI("tuzi","111","兔子优钱","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YIDAI("yidai","112","易贷码头","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	QIANCHENG("qiancheng","113","钱无尽","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	REMEN("remen","114","火力贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	RUYIZHU("ruyizhu","115","如意猪","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JINQIANGUI("jinqiangui","116","赢天下","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	CHAOXIANGJIE("chaoxiangjie","117","钞享借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	NUOMIJIE("nuomijie","118","诺米借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	FENDADAI("fendadai","119","芬达贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	HUOLIDAI("huolidai","120","活力贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	DAZHONG("dazhong","121","大众秒贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	BAOTAI	("baotai","122","宝泰钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JINGYU("jingyu","123","鲸鱼全天贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	FANSHEN("fanshen","124","翻身钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JINLIANHUA("jinlianhua","125","金联花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	BAOJIEDAO("baojiedao","126","包借到","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YUNDOU("yundou","127","云豆钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	HEIKA("heika","128","91黑卡","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	WOYAODAI("woyaodai","129","51我要贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	WENXIAKUAN("wenxiakuan","130","51稳下款","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	XUEBIDAI("xuebidai","131","雪碧贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	DALIANHUA("dalianhua","132","大脸花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	DALIHUA("dalihua","133","大力花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YOUJIE("youjie","134","51有借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	MALIU("maliu","135","麻溜优借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JIZHUANDAI("jizhuandai","136","51急转贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	XIANGJIE("xiangjie","137","51享借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YOUHUA("youhua","138","有借有花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	QIANGUI("qiangui","139","51钱柜","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	DUODIANDAI("duodiandai","140","51多点贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	MOD("mod","141","模德科技","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	SUIBIANHUA("suibianhua","142","91随便花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JIDAI("jidai","143","急贷钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	SHANQUHUA("shanquhua","144","闪趣花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	FENGSHENG("fengsheng","145","风生钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	QIANBILAI("qianbilai","146","钱必来","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	LVKA("lvka","147","91绿卡","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	XIAOHUI("xiaohui","148","小汇钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YINGHUA("yinghua","149","应花钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	BAOJIEKUAN("baojiekuan","150","包借款","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YOUNIDAI("younidai","151","51优你贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	SHANGTAI("shangtai","152","上台钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	ZHUANYUNZHU("zhuanyunzhu","153","转运猪","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	KUAIZHUDAI("kuaizhudai","154","快猪贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	YUNCAI("yuncai","155","运财钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	CHUANGSHI("chuangshi","156","创世花呗","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JINMEIGUI("jinmeigui","157","金玫瑰","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	PEIQIHUA("peiqihua","158","配齐花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	MIZHUDAI("mizhudai","159","米猪贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	LAIYUNBAO("laiyunbao","160","来运宝","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	SUIWOJIE("suiwojie","161","随我借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	DONGXIDAI("dongxidai","162","东西贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	DUOMILE("duomile","163","多米乐","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	HUADU("huadu","164","花都钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	SUIYIJIE("suiyijie","165","随亿借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	MIHAODUO("mihaoduo","166","米好多","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	BITONGDAI("bitongdai","167","必通贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	LAOMA("laoma","168","老马速借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	ZHUFU("zhufu","169","猪福钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	JIXIANGDAI("jixiangdai","170","吉享贷","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	DATONG("datong","171","大通钱包","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	XIONGMAO("xiongmao","172","熊猫快借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	QIANZAIZHE("qianzaizhe","173","钱在这","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	XINGXING("xingxing","174","星星速借","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	HUAQIANGUAN("huaqianguan","175","花钱罐","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	XIAOHUAHUA("xiaohuahua","176","小花花","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//	MALIFENQI("malifenqi","177","麻利分期","0005","NGUwOTU1MzFmOGJhMjM2NmY1MGMzYWFiYmYzMWRjYzQ="),
//
//
//
//
//	;
//	private String key;
//
//    private String signId;
//
//    private String signName;
//
//    private String accesskey; //account
//
//    private String secret;  //pwd
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
//	private MiFengSmsSign(String key, String signId, String signName, String accesskey, String secret) {
//		this.key = key;
//		this.signId = signId;
//		this.signName = signName;
//		this.accesskey = accesskey;
//		this.secret = secret;
//	}
//
//	public static MiFengSmsSign getSign(String key) {
//        for (MiFengSmsSign enumYunpianApikey : MiFengSmsSign.values()) {
//        	if (enumYunpianApikey.getKey().equals(key)) {
//        		 return enumYunpianApikey;
//			}
//        }
//        return null;
//    }
//}
