package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_merchant")
public class Merchant {
	/**
	 * 商户别名与app别名一致
	 */
	@Id
	@Column(name = "merchant_alias")
	private String merchantAlias;

	/**
	 * 商户名称
	 */
	@Column(name = "merchant_name")
	private String merchantName;

	/**
	 * 商户支付宝
	 */
	@Column(name = "merchant_zfb")
	private String merchantZfb;

	/**
	 * 商户状态
	 */
	@Column(name = "merchant_status")
	private Integer merchantStatus;

	/**
	 * app别名
	 */
	@Column(name = "merchant_app")
	private String merchantApp;

	@Column(name = "merchant_channel")
	private String merchantChannel;

	/**
	 * app别名
	 */
	@Column(name = "merchant_app_ios")
	private String merchantAppIos;

	@Column(name = "create_time")
	private Date createTime;


	/**
	 * 获取商户别名与app别名一致
	 *
	 * @return merchant_alias - 商户别名与app别名一致
	 */
	public String getMerchantAlias() {
		return merchantAlias;
	}

	/**
	 * 设置商户别名与app别名一致
	 *
	 * @param merchantAlias
	 *            商户别名与app别名一致
	 */
	public void setMerchantAlias(String merchantAlias) {
		this.merchantAlias = merchantAlias == null ? null : merchantAlias.trim();
	}

	/**
	 * 获取商户名称
	 *
	 * @return merchant_name - 商户名称
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 设置商户名称
	 *
	 * @param merchantName
	 *            商户名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName == null ? null : merchantName.trim();
	}

	/**
	 * 获取商户支付宝
	 *
	 * @return merchant_zfb - 商户支付宝
	 */
	public String getMerchantZfb() {
		return merchantZfb;
	}

	/**
	 * 设置商户支付宝
	 *
	 * @param merchantZfb
	 *            商户支付宝
	 */
	public void setMerchantZfb(String merchantZfb) {
		this.merchantZfb = merchantZfb == null ? null : merchantZfb.trim();
	}

	/**
	 * 获取商户状态
	 *
	 * @return merchant_status - 商户状态
	 */
	public Integer getMerchantStatus() {
		return merchantStatus;
	}

	/**
	 * 设置商户状态
	 *
	 * @param merchantStatus
	 *            商户状态
	 */
	public void setMerchantStatus(Integer merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	/**
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMerchantApp() {
		return merchantApp;
	}

	public void setMerchantApp(String merchantApp) {
		this.merchantApp = merchantApp;
	}


}