package org.example.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.annotation.BankAPI;
import org.example.annotation.BankAPIField;
import org.example.pojo.CreateUserAPI;
import org.example.pojo.PayAPI;
import org.example.pojo.base.AbstractAPI;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;

public class BankService {
    static Logger log = LogManager.getLogger();

    //创建用户方法
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setName(name);
        createUserAPI.setIdentity(identity);
        createUserAPI.setAge(age);
        createUserAPI.setMobile(mobile);
        return remoteCall(createUserAPI);
    }
    //支付方法
    public static String pay(long userId, BigDecimal amount) throws IOException {
        PayAPI payAPI = new PayAPI();
        payAPI.setUserId(userId);
        payAPI.setAmount(amount);
        return remoteCall(payAPI);
    }

    public static String remoteCall(AbstractAPI api) {
        BankAPI bankAPI = api.getClass().getAnnotation(BankAPI.class);
        bankAPI.URL();
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(bankAPI.URL());
        Arrays.stream(api.getClass().getDeclaredFields()).
                filter(field -> field.isAnnotationPresent(BankAPIField.class))
                .sorted(Comparator.comparingInt(field -> field.getAnnotation(BankAPIField.class).order()))
                .peek(field -> field.setAccessible(true))
                .forEach(
                        field -> {
                            BankAPIField bankAPIField = field.getAnnotation(BankAPIField.class);
                            Object value = "";
                            try {
                                value = field.get(api);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            switch(bankAPIField.type()){
                                case "S": {
                                    stringBuilder.append(String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_'));
                                    break;
                                }
                                case "N": {
                                    stringBuilder.append(String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0'));
                                    break;
                                }
                                case "M": {
                                    if (!(value instanceof BigDecimal))
                                        throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
                                    stringBuilder.append(String.format("%0" + bankAPIField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                                    break;
                                }
                                default:
                                    break;
                            }
                            });
        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
        String param = stringBuilder.toString();
        long begin = System.currentTimeMillis();
        String result = Request.Post("http://localhost:45678/reflection" + bankAPI.URL())
                .bodyString(param, Request.ContentType)
                .execute().returnContent().asString();
        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankAPI.desc(), bankAPI.URL(), param, System.currentTimeMillis() - begin);
        return result;
    }

    private static class Request {
        public static final String ContentType = "1";
        private static final Request instance = new Request();
        private String param;
        public static Request Post(String url){
            return instance;
        }
        public Request bodyString(String param,String contentType){
            instance.param = param;
            return instance;
        }
        public Request execute(){
            return instance;
        }
        public Request returnContent(){
            return instance;
        }
        public String asString(){
            return instance.param;
        }

    }
}
