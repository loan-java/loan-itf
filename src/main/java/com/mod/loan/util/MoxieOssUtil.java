//package com.mod.loan.util;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Reader;
//import java.io.StringWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.zip.GZIPInputStream;
//
//import org.apache.commons.lang.StringUtils;
//import org.jsoup.Jsoup;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.common.utils.IOUtils;
//import com.aliyun.oss.model.OSSObject;
//import com.google.gson.GsonBuilder;
//import com.mod.loan.config.Constant;
//
//public class MoxieOssUtil {
//
//	private static Logger logger = LoggerFactory.getLogger(MoxieOssUtil.class);
//
//	/**
//	 * 获取运营商聚信立报告数据
//	 *
//	 * @param taskId
//	 *            魔蝎taskid
//	 * @return
//	 */
//	public static String mobileJxlReport(String taskId) {
//		String data = null;
//		try {
//			String savePath = Constant.OSS_MOXIE_MOBILE_JXL + "/" + taskId;
//			OSSObject ossObject = getOssObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath);
//			data = uncompress(ossObject.getObjectContent());
//		} catch (IOException e) {
//			logger.error("获取运营商聚信立报告数据异常,taskId={}", taskId);
//		}
//		return StringUtils.isBlank(data) ? "" : data;
//	}
//
//	/**
//	 * 获取运营商原始数据
//	 *
//	 * @param taskId
//	 *            魔蝎taskid
//	 * @return
//	 */
//	public static String mobileMxdata(String taskId) {
//		String data = null;
//		try {
//			String savePath = Constant.OSS_MOXIE_MOBILE_MXDATA + "/" + taskId;
//			OSSObject ossObject = getOssObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath);
//			data = uncompress(ossObject.getObjectContent());
//		} catch (IOException e) {
//			logger.error("获取运营商原始数据异常,taskId={}", taskId);
//		}
//		return StringUtils.isBlank(data) ? "" : data;
//	}
//
//	/**
//	 * 获取支付宝原始数据
//	 *
//	 * @param taskId
//	 *            魔蝎taskid
//	 * @return
//	 */
//	public static String zfbData(String taskId) {
//		String data = null;
//		try {
//			String savePath = Constant.OSS_MOXIE_ZFB_DATA + "/" + taskId;
//			OSSObject ossObject = getOssObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath);
//			data = IOUtils.readStreamAsString(ossObject.getObjectContent(), "utf-8");
//		} catch (Exception e) {
//			logger.error("获取支付宝原始数据异常,taskId={}", taskId);
//		}
//		return StringUtils.isBlank(data) ? "" : data;
//	}
//
//	/**
//	 * 获取魔杖报告的token
//	 *
//	 * @return token
//	 */
//	public static String magic_wand_token() {
//		try {
//			URL url = new URL(Constant.MOXIE_MAGIC_TOKEN);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("POST");
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			connection.setUseCaches(false);
//			connection.setRequestProperty("Content-Type", "application/json");
//
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("customer_id", Constant.MOXIE_CUSTOMER_ID);
//			map.put("secret", Constant.MOXIE_MAGIC_SECRET);
//			String json = new GsonBuilder().create().toJson(map);
//			connection.connect();
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
//			writer.write(json);
//			writer.close();
//			String result = "";
//			int responseCode = connection.getResponseCode();
//			if (responseCode == 200) {
//				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
//				String inputLine;
//				while ((inputLine = in.readLine()) != null) {
//					result += inputLine;
//				}
//				JSONObject res = JSON.parseObject(result);
//				String token = res.get("token").toString();
//				connection.disconnect();
//				return token;
//			} else {
//				logger.info("获取魔杖token异常！responseCode={}", responseCode);
//				return "";
//			}
//		} catch (Exception e) {
//			logger.error("获取魔杖token异常！");
//		}
//		return "";
//	}
//
//	/**
//	 * 上传魔蝎运营商聚信立数据至阿里云，不需要解压
//	 *
//	 * @param taskId
//	 * @param phone
//	 * @param token
//	 * @throws IOException
//	 */
//	public static void uploadMobileJxlReport(String token, String taskId, String phone) {
//		OSSClient ossClient = new OSSClient(endPointUrl(Constant.ENVIROMENT), Constant.MOXIE_ACCESSKEY_ID,
//				Constant.MOXIE_ACCESS_KEY_SECRET);
//		String savePath = Constant.OSS_MOXIE_MOBILE_JXL + "/" + taskId;
//		try {
//			String linkurl = String.format(Constant.MOXIE_JXREPORT, phone, taskId);
//			InputStream inputStream = getInputStream(linkurl, token);
//			ossClient.putObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath, inputStream);
//		} catch (Exception e) {
//			logger.error("聚信立报告上传失败,taskid=" + taskId, e);
//			throw new RuntimeException(e);
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//	}
//
//	/**
//	 * 上传魔蝎运营商原始数据至阿里云，不需要解压
//	 *
//	 * @param taskId
//	 * @param phone
//	 * @param token
//	 * @throws IOException
//	 */
//	public static void uploadMobileMxdata(String token, String taskId, String phone) {
//		OSSClient ossClient = new OSSClient(endPointUrl(Constant.ENVIROMENT), Constant.MOXIE_ACCESSKEY_ID,
//				Constant.MOXIE_ACCESS_KEY_SECRET);
//		String savePath = Constant.OSS_MOXIE_MOBILE_MXDATA + "/" + taskId;
//		try {
//			String linkurl = String.format(Constant.MOXIE_MXDATA_EX, phone, taskId);
//			InputStream inputStream = getInputStream(linkurl, token);
//			ossClient.putObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath, inputStream);
//		} catch (Exception e) {
//			logger.error("运营商原始数据上传失败,taskid=" + taskId, e);
//			throw new RuntimeException(e);
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//	}
//
//	/**
//	 * 上传魔蝎支付宝数据至阿里云
//	 *
//	 * @param taskId
//	 * @param token
//	 * @throws IOException
//	 */
//	public static void uploadZfbdata(String token, String taskId) {
//		OSSClient ossClient = new OSSClient(endPointUrl(Constant.ENVIROMENT), Constant.MOXIE_ACCESSKEY_ID,
//				Constant.MOXIE_ACCESS_KEY_SECRET);
//		String savePath = Constant.OSS_MOXIE_ZFB_DATA + "/" + taskId;
//		try {
//			String linkurl = String.format(Constant.MOXIE_ALIPAY, taskId);
//			InputStream inputStream = getInputStream(linkurl, token);
//			ossClient.putObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath, inputStream);
//		} catch (Exception e) {
//			logger.error("支付宝数据上传失败,taskid=" + taskId, e);
//			throw new RuntimeException(e);
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//	}
//
//	/**
//	 * 上传魔蝎魔杖数据至阿里云(token需要单独获取)
//	 *
//	 * @param taskId
//	 * @throws IOException
//	 */
//	public static Boolean uploadMagicWandReport(String taskId) {
//		Boolean result = true;
//		OSSClient ossClient = new OSSClient(endPointUrl(Constant.ENVIROMENT), Constant.MOXIE_ACCESSKEY_ID,
//				Constant.MOXIE_ACCESS_KEY_SECRET);
//		String savePath = Constant.OSS_MOXIE_MAGIC_WAND + "/" + taskId;
//		String magic_token = magic_wand_token();
//		if (StringUtils.isEmpty(magic_token)) {
//			return false;
//		}
//		String linkurl = String.format(Constant.MOXIE_MAGIC_REPORT, taskId);
//		try {
//			InputStream inputStream = getInputStream(linkurl, magic_token);
//			ossClient.putObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath, inputStream);
//		} catch (Exception e) {
//			logger.error("魔杖数据上传失败,taskid=" + taskId, e);
//			throw new RuntimeException(e);
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//		return result;
//	}
//
//	/**
//	 * 上传通讯录数据至阿里云，不需要解压
//	 *
//	 * @param taskId
//	 * @param addressList
//	 * @throws IOException
//	 */
//	public static void uploaAddressList(String taskId, String addressList) {
//		OSSClient ossClient = new OSSClient(endPointUrl(Constant.ENVIROMENT), Constant.MOXIE_ACCESSKEY_ID,
//				Constant.MOXIE_ACCESS_KEY_SECRET);
//		String savePath = Constant.OSS_MOXIE_ADDRESS_LIST + "/" + taskId;
//		try {
//			InputStream inputStream = new ByteArrayInputStream(addressList.getBytes());
//			ossClient.putObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath, inputStream);
//		} catch (Exception e) {
//			logger.error("通讯录上传失败,taskid=" + taskId, e);
//			throw new RuntimeException(e);
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//	}
//
//	/**
//	 * 获取魔杖报告
//	 *
//	 * @param taskId
//	 *            运营商taskId
//	 * @return
//	 */
//	public static String magicWandReport(String taskId) {
//		String data = null;
//		try {
//			String savePath = Constant.OSS_MOXIE_MAGIC_WAND + "/" + taskId;
//			OSSObject ossObject = getOssObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath);
//			data = IOUtils.readStreamAsString(ossObject.getObjectContent(), "utf-8");
//		} catch (Exception e) {
//			logger.error("获取魔杖报告异常,taskId={}", taskId);
//		}
//		return StringUtils.isBlank(data) ? "" : data;
//	}
//
//	/**
//	 * 获取通讯录信息
//	 *
//	 * @param taskId
//	 *            通讯录taskId
//	 * @return
//	 */
//	public static String addressList(String taskId) {
//		String data = null;
//		try {
//			String savePath = Constant.OSS_MOXIE_ADDRESS_LIST + "/" + taskId;
//			OSSObject ossObject = getOssObject(Constant.OSS_MOXIE_BUCKET_NAME, savePath);
//			data = IOUtils.readStreamAsString(ossObject.getObjectContent(), "utf-8");
//		} catch (Exception e) {
//			logger.error("获取通讯录信息异常,taskId={}", taskId);
//		}
//		return StringUtils.isBlank(data) ? "" : data;
//	}
//
//	public static String getZmScore(String taskId, String token) throws IOException {
//		String url = String.format(Constant.MOXIE_ZM_SCORE, taskId);
//		return Jsoup.connect(url).header("Authorization", "token " + token).ignoreHttpErrors(true)
//				.ignoreContentType(true).execute().body();
//	}
//
//	private static InputStream getInputStream(String linkUrl, String token) throws IOException {
//		URL url = new URL(linkUrl);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("GET");
//		connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
//		connection.setRequestProperty("Accept", "application/json");
//		if (null != token || !"".equals(token)) {
//			connection.setRequestProperty("Authorization", "token " + token);
//		}
//		connection.connect();
//		connection.disconnect();
//		return connection.getInputStream();
//	}
//
//	private static String uncompress(InputStream gzippedResponse) throws IOException {
//		InputStream decompressedResponse = new GZIPInputStream(gzippedResponse);
//		Reader reader = new InputStreamReader(decompressedResponse, "UTF-8");
//		StringWriter writer = new StringWriter();
//		char[] buffer = new char[10240];
//		for (int length = 0; (length = reader.read(buffer)) > 0;) {
//			writer.write(buffer, 0, length);
//		}
//		writer.close();
//		reader.close();
//		decompressedResponse.close();
//		gzippedResponse.close();
//		return writer.toString();
//	}
//
//	/**
//	 * 根据路径获取oss文件对象
//	 *
//	 * @param env
//	 * @param bucketName
//	 * @param savePath
//	 * @return
//	 */
//	private static OSSObject getOssObject(String bucketName, String savePath) {
//		OSSClient ossClient = new OSSClient(endPointUrl(Constant.ENVIROMENT), Constant.MOXIE_ACCESSKEY_ID,
//				Constant.MOXIE_ACCESS_KEY_SECRET);
//		OSSObject oSSObject = ossClient.getObject(bucketName, savePath);
//		return oSSObject;
//	}
//
//	private static String endPointUrl(String env) {
//		if ("dev".equals(env)) {
//			return Constant.OSS_ENPOINT_OUT;
//		}
//		return Constant.OSS_ENPOINT_IN;
//	}
//
//}
