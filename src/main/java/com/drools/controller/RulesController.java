package com.drools.controller;


import com.alibaba.fastjson.JSONObject;
import com.drools.dao.InsuranceRules;
import com.drools.utils.KieSessionUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RulesController {

    @RequestMapping(value = "/excute-rules")
    public String rulesEngine(@RequestBody String params) throws FileNotFoundException {

//        Map<String, String> amountMap = new HashMap<>();
        List<String> listRules = new ArrayList<>();
        InsuranceRules insuranceRules = new InsuranceRules();

        JSONObject paramsJson = JSONObject.parseObject(params);

        insuranceRules.setCompanycode(paramsJson.getString("companycode"));
        insuranceRules.setPayPeriod(paramsJson.getString("payPeriod"));
        insuranceRules.setPayType(paramsJson.getString("payType"));
        insuranceRules.setInsurancePeriod(paramsJson.getString("insurancePeriod"));//保险期间 25年
        insuranceRules.setSaleArea(paramsJson.getString("saleArea"));
        insuranceRules.setInsuredAge(paramsJson.getInteger("age"));
        insuranceRules.setAmount(paramsJson.getString("amount"));//保额

//        String drl = KieSessionUtils.getDRL("/Users/shaonan_hu/Desktop/sign1.xls");
        String drl = KieSessionUtils.getDRL("D:\\droolsExcel\\sign.xls");

        System.out.println("解析规则文件：" + drl);

        KieSession kieSession = null;
        try {
            kieSession = KieSessionUtils.createKieSessionFromDRL(drl);
        } catch (Exception e) {
            e.printStackTrace();
            return "模板编写错误，请仔细核对！错误信息如下：" + e;
        }
        kieSession.getAgenda().getAgendaGroup("sign").setFocus();
        kieSession.insert(insuranceRules);
        kieSession.setGlobal("listRules", listRules);
        int rules_count = kieSession.fireAllRules();

        System.out.println("评估规则ok");
        System.out.println("触发了" + rules_count + "条规则");
        if (rules_count == 0 && listRules.size() == 0) {
            return "触发了" + rules_count + "条规则,打印结果：true,通过校验";
        } else {
            return "触发了" + rules_count + "条规则,打印结果：" + listRules.toString();
        }
//        for(int i=0;i < listRules.size();i++){
//            String msg = listRules.get(i);
//        }
//        if (StringUtils.isNotBlank(score)) {
//            return "触发了" + rules_count + "条规则，打印结果：" + score;
//        } else {
//            return "触发了" + rules_count + "条规则，返回false，校验无法通过，请检查投保信息是否正确";
//        }
    }
}
