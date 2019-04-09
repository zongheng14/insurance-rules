package com.drools.controller;

import com.alibaba.fastjson.JSONObject;
import com.drools.bean.ApiResponse;
import com.drools.dao.InsuranceRulesDao;
import com.drools.utils.KieSessionUtils;
import com.drools.utils.Params2rulesBean;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shaonan.hu
 * @version V1.0
 * @Time 2019/3/27
 */
@RestController
public class InsuranceRulesController {
    private static Logger logger = LoggerFactory.getLogger(InsuranceRulesController.class);


    /**
     * @param params ： 预校验的数据 （必填）
     * @return
     */
    @RequestMapping("/resolverRules")
    public ApiResponse resolverRules(@RequestBody String params) {
        logger.info("规则引擎，解析入参：{}" + params);
        ApiResponse<String> response = new ApiResponse<>();

        try {
            if (StringUtils.isBlank(params)) {
                response.setCode(500);
                response.setFail();
                response.setMsg("预校验的投保参数获取为空");
                return response;
            }
            JSONObject paramsJson = JSONObject.parseObject(params);
            String templateUrl = paramsJson.getString("templateUrl");
            if (StringUtils.isBlank(templateUrl)) {
                response.setCode(500);
                response.setFail();
                response.setMsg("缺少规则模板地址：templateUrl");
                return response;
            }

            //全局的接收对象
            List<String> listRules = new ArrayList<>();
            //参数转化为bean非动态  有局限性
            // InsuranceRules insuranceRules = param2bean(paramsJson);
            InsuranceRulesDao insuranceRulesDao = Params2rulesBean.covertRulesBean(paramsJson);

            String drl = null;
            try {
                drl = KieSessionUtils.getDRL2(templateUrl);
                logger.info("解析规则文件结果{}", drl);
            } catch (FileNotFoundException e) {
                logger.info("文件解析异常{}", e);
                response.setCode(500);
                response.setFail();
                response.setMsg("解析文件规则异常");
                return response;
            }

            KieSession kieSession = null;
            try {
                kieSession = KieSessionUtils.createKieSessionFromDRL(drl);
                kieSession.getAgenda().getAgendaGroup("sign").setFocus();
                kieSession.insert(insuranceRulesDao);
                kieSession.setGlobal("listRules", listRules);
            } catch (Exception e) {
                logger.info("模板编写异常，请仔细核对！{}", e);
                response.setCode(500);
                response.setFail();
                response.setMsg("模板编写错误，请仔细核对！错误信息如下：" + e);
                return response;
            }
            int rules_count = kieSession.fireAllRules();

            logger.info("规则评估OK,触发了" + rules_count + "条规则!");
            if (rules_count == 0 && listRules.size() == 0) {
                response.setCode(200);
                response.setSuccess();
                response.setMsg("触发了" + rules_count + "条规则,校验结果：校验通过！");
                logger.info("触发了" + rules_count + "条规则,校验结果：校验通过！");
            } else {
                //规范返回校验结果
                List<String> listRules_standard = new ArrayList<>();
                if (rules_count > 0) {
                    for (int i = 0; i < listRules.size(); i++) {
                        listRules_standard.add(i + 1 + "." + listRules.get(i));
                    }
                }
                response.setCode(500);
                response.setFail();
                response.setMsg("触发了" + rules_count + "条规则,校验结果集合：" + listRules_standard);
                logger.info("触发了" + rules_count + "条规则,校验结果集合：" + listRules_standard);
            }
        } catch (Exception e) {
            logger.info("校验结果异常{}", e);
            response.setFail();
            response.setCode(500);
            response.setMsg("校验结果异常，请查看模板是否有误" + e);
            return response;
        }
        return response;
    }


}
