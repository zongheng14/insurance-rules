package com.drools.utils;

import com.alibaba.fastjson.JSONObject;
import com.drools.dao.InsuranceRulesDao;

/**
 * 投保规则校验
 * 将入参封装到map中
 *
 * @author shaonan.hu
 * @version V1.0
 * @Time 2019/3/27
 */
public class Params2rulesBean {

    public static InsuranceRulesDao covertRulesBean(JSONObject paramsJson) throws Exception {

        InsuranceRulesDao insuranceRulesDao = new InsuranceRulesDao();

        insuranceRulesDao.setParam1(paramsJson.getString("companycode"));//保险公司
        insuranceRulesDao.setParam2(paramsJson.getString("planCode"));//方案代码
        insuranceRulesDao.setParam3(paramsJson.getString("channelId"));//渠道号
        insuranceRulesDao.setParam4(paramsJson.getString("saleArea"));//销售区域 1：不限 2……n:具体城市名称或者code
        insuranceRulesDao.setParam5(paramsJson.getString("insuranceAge"));//投保年龄  格式：传数字 如 2岁：2
        insuranceRulesDao.setParam6(paramsJson.getString("insurancePeriod"));//保险期间 单位是年
        insuranceRulesDao.setParam7(paramsJson.getString("payPeriod"));//缴费期间 单位：年
        insuranceRulesDao.setParam8(paramsJson.getString("payType"));//缴费方式 1:趸交  2：年交
        insuranceRulesDao.setParam9(paramsJson.getString("guaranteeType"));//保障类型 1:不限  2…n:具体的类型
        insuranceRulesDao.setParam10(paramsJson.getString("waitingPeriod"));//等待期 单位：天
        insuranceRulesDao.setParam11(paramsJson.getString("pausePeriod"));//犹豫期 单位：天
        insuranceRulesDao.setParam12(paramsJson.getString("careerType"));//职业类型
        insuranceRulesDao.setParam13(paramsJson.getString("totalAmount"));//总保额 单位：万
        insuranceRulesDao.setParam14(paramsJson.getString("deductibles"));//免赔额   单位：万
        insuranceRulesDao.setParam15(paramsJson.getString("mainRiskAmount"));//主险保额 单位：万
        insuranceRulesDao.setParam16(paramsJson.getString("mainRiskPremium"));//主险保费 单位：分
        insuranceRulesDao.setParam17(paramsJson.getString("additionRiskAmount"));//附加险保额 单位：万
        insuranceRulesDao.setParam18(paramsJson.getString("additionRiskPremium"));//附加险保费 单位：分
        insuranceRulesDao.setParam19(paramsJson.getString("relationShipApplicant"));//与投保人关系  1：丈夫，2：妻子，3：父亲，4：母亲，5：儿子，6：女儿'
        insuranceRulesDao.setParam20(paramsJson.getString("relationShipInsured"));//与被保人关系  1：丈夫，2：妻子，3：父亲，4：母亲，5：儿子，6：女儿'
        insuranceRulesDao.setParam21(paramsJson.getString("sex"));//性别  1：男  2：女
        insuranceRulesDao.setParam22(paramsJson.getString("documentType"));//证件类型
        insuranceRulesDao.setParam23(paramsJson.getString("totalpremium"));//总保费  单位：分
        return insuranceRulesDao;
    }
}
