###官方文档：
https://docs.jboss.org/drools/release/6.5.0.Final/drools-docs/html_single/index.html#d0e5713

###参考
https://blog.csdn.net/ujsleo/article/details/78716842

###访问路径
post请求  

http://localhost:8081/resolverRules

####入参json

{
  "templateUrl": "D:\\droolsExcel\\personal-api\\templateRules.xls",
  "companycode": "picc",
  "planCode": "",
  "channelId": "",
  "saleArea": "哈尔滨",
  "insuranceAge": "3",
  "insurancePeriod": "30",
  "payPeriod": "3",
  "payType": "2",
  "guaranteeType": "",
  "waitingPeriod": "",
  "pausePeriod": "",
  "careerType": "",
  "totalAmount": "62",
  "deductibles": "",
  "mainRiskAmount": "",
  "mainRiskPremium": "",
  "additionRiskAmount": "",
  "additionRiskPremium": "",
  "relationShipApplicant": "",
  "relationShipInsured": "",
  "sex": "",
  "documentType": "",
  "totalpremium": ""
}


###对应模板字段

保险公司	param1
方案代码	param2
渠道号	param3
销售区域	param4
投保年龄	param5
保险期间	param6
缴费期间	param7
缴费方式	param8
保障类型	param9
等待期	param10
犹豫期	param11
职业类型	param12
总保额	param13
免赔额	param14
主险保额	param15
主险保费	param16
附加险保额	param17
附加险保费	param18
与投保人关系	param19
与被保人关系	param20
性别	param21
证件	param22
总保费 	param23
	


