package com.drools.controller;


import com.drools.dao.InsuranceRules;
import com.drools.utils.KieSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RulesController {

    @RequestMapping(value = "/excute-rules")
    public String rulesEngine(Integer age) throws FileNotFoundException {

        Map<String, String> amountMap = new HashMap<>();
        InsuranceRules insuranceRules = new InsuranceRules();

        insuranceRules.setCount(1);
        insuranceRules.setInsurancePeriod("25年");//保险期间 25年
        insuranceRules.setSaleArea("北京");
        insuranceRules.setInsuredAge(age);

        String drl = KieSessionUtils.getDRL("/Users/shaonan_hu/Desktop/sign.xls");

        System.out.println("解析规则文件：" + drl);

        KieSession kieSession = KieSessionUtils.createKieSessionFromDRL(drl);
        kieSession.getAgenda().getAgendaGroup("sign").setFocus();
        kieSession.insert(insuranceRules);
        kieSession.setGlobal("amountMap", amountMap);
        kieSession.fireAllRules();

        System.out.println("评估规则ok");
        String score = amountMap.get("score");
        if (StringUtils.isNotBlank(score)) {
            return "打印结果：" + score;
        } else {
            return "校验通过";
        }

    }
}
