//package com.mod.loan.itf.sms.old.chuangrui;
//
//public enum ChuangRuiSmsSign {
//	KELEGUAN("keleguan","12180","【惠金黑卡】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	MALIHUISHOU("malihuishou","5883","【麻利app】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	HUAQIAN("huaqian","6573","【51花钱】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	SUIXINGHUA("suixinghua","6574","【随行花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	ZHAOCAIHOU("zhaocaihou","7002","【招财猴】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	KELEDAI("keledai","7150","【可乐贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	XIANYU("xianyu","7151","【仙遇钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	MIAOJIE("miaojie","12840","【秒借信用】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	FEINIUDAI("feiniudai","7440","【飞牛贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JINZHUBAO("jinzhubao","7441","【金猪宝】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YIMIAOJIE("yimiaojie","7888","【易秒下款】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	TUZI("tuzi","8118","【兔子优钱】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YIDAI("yidai","8150","【易贷码头】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	QIANCHENG("qiancheng","12551","【钱无尽】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	REMEN("remen","9131","【火力贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	RUYIZHU("ruyizhu","9122","【如意猪】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JINQIANGUI("jinqiangui","10172","【赢天下】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	CHAOXIANGJIE("chaoxiangjie","9613","【钞享借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	NUOMIJIE("nuomijie","9696","【诺米借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	FENDADAI("fendadai","9760","【芬达贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	HUOLIDAI("huolidai","9791","【活力贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	DAZHONG("dazhong","12315","【大众秒贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	BAOTAI	("baotai","10161","【宝泰钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JINGYU("jingyu","12889","【鲸鱼app】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	FANSHEN("fanshen","10176","【翻身钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JINLIANHUA("jinlianhua","10177","【金联花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	BAOJIEDAO("baojiedao","11959","【包借到】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YUNDOU("yundou","12156","【云豆钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	HEIKA("heika","12241","【91黑卡】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	WOYAODAI("woyaodai","12630","【51我要贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	WENXIAKUAN("wenxiakuan","12631","【51稳下款】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	XUEBIDAI("xuebidai","12895","【雪碧贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	DALIANHUA("dalianhua","12897","【大脸花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	DALIHUA("dalihua","12896","【大力花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YOUJIE("youjie","12977","【51有借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	MALIU("maliu","12978","【麻溜优借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JIZHUANDAI("jizhuandai","13103","【51急转贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	XIANGJIE("xiangjie","13197","【51享借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YOUHUA("youhua","13198","【有借有花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	QIANGUI("qiangui","14730","【51钱柜】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	DUODIANDAI("duodiandai","14729","【51多点贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	MOD("mod","15877","【模德科技】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	SUIBIANHUA("suibianhua","16157","【91随便花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JIDAI("jidai","16158","【急贷钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	SHANQUHUA("shanquhua","16287","【闪趣花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	FENGSHENG("fengsheng","17304","【风生钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	QIANBILAI("qianbilai","17305","【钱必来】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	LVKA("lvka","17388","【91绿卡】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	XIAOHUI("xiaohui","17468","【小汇钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YINGHUA("yinghua","17470","【应花钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	BAOJIEKUAN("baojiekuan","17473","【包借款】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YOUNIDAI("younidai","17549","【51优你贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	SHANGTAI("shangtai","17670","【上台钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	ZHUANYUNZHU("zhuanyunzhu","17738","【转运猪】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	KUAIZHUDAI("kuaizhudai","17739","【快猪贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	YUNCAI("yuncai","17740","【运财钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	CHUANGSHI("chuangshi","17741","【创世花呗】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JINMEIGUI("jinmeigui","17742","【金玫瑰】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	PEIQIHUA("peiqihua","17743","【配齐花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	MIZHUDAI("mizhudai","17815","【米猪贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	LAIYUNBAO("laiyunbao","17816","【来运宝】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	SUIWOJIE("suiwojie","17819","【随我借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	DONGXIDAI("dongxidai","17829","【东西贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	DUOMILE("duomile","17830","【多米乐】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	HUADU("huadu","18028","【花都钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	SUIYIJIE("suiyijie","18030","【随亿借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	MIHAODUO("mihaoduo","18032","【米好多】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	BITONGDAI("bitongdai","18034","【必通贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	LAOMA("laoma","18137","【老马速借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	ZHUFU("zhufu","18139","【猪福钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	JIXIANGDAI("jixiangdai","18141","【吉享贷】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	DATONG("datong","18144","【大通钱包】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	XIONGMAO("xiongmao","18145","【熊猫快借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	QIANZAIZHE("qianzaizhe","18148","【钱在这】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	XINGXING("xingxing","18307","【星星速借】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	HUAQIANGUAN("huaqianguan","18309","【花钱罐】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	XIAOHUAHUA("xiaohuahua","18311","【小花花】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
//	MALIFENQI("malifenqi","15489","【麻利分期】","qJ3NQmvC8G7vUlP0","GWPCYDQt3VjWBne66XnBHxQZiP7lBgxT"),
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
//	private ChuangRuiSmsSign(String key, String signId, String signName, String accesskey, String secret) {
//		this.key = key;
//		this.signId = signId;
//		this.signName = signName;
//		this.accesskey = accesskey;
//		this.secret = secret;
//	}
//
//	public static ChuangRuiSmsSign getSign(String key) {
//        for (ChuangRuiSmsSign enumYunpianApikey : ChuangRuiSmsSign.values()) {
//        	if (enumYunpianApikey.getKey().equals(key)) {
//        		 return enumYunpianApikey;
//			}
//        }
//        return null;
//    }
//}
