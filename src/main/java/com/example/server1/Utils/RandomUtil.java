package com.example.server1.Utils;

import java.util.Random;

public class RandomUtil {
    // 用于注册的验证码 六位
    public static String getVerifyCode(){
        Random r = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++){
            int randomNum = r.nextInt(10);
            code.append(randomNum);
        }
        return code.toString();
    }
    // 用于登录的验证码 四位
    public static String getLoginCode(){
        Random r = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++){
            int randomNum = r.nextInt(10);
            code.append(randomNum);
        }
        return code.toString();
    }
}
