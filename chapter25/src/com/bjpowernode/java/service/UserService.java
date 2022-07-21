package com.bjpowernode.java.service;

public class UserService {

    /**
     *
     * @param name 用户名
     * @param password 密码
     * @return true 登录成功  false 登录失败
     */
    public boolean login(String name,String password){
        if(name.equals("admin") && password.equals("123")){
            return true;
        }
        return false;
    }

    public void logout(){
        System.out.println("退出登录成功");
    }
}
