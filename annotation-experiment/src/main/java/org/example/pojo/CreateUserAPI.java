package org.example.pojo;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import org.example.annotation.BankAPI;
import org.example.annotation.BankAPIField;
import org.example.pojo.base.AbstractAPI;

@BankAPI(URL = "/bank/createUser",desc = "创建用户接口")

public class CreateUserAPI extends AbstractAPI {
    @BankAPIField(order = 1,type = "S",length = 10)
    private String name;
    @BankAPIField(order = 2,type = "S",length = 18)
    private String identity;
    @BankAPIField(order = 4,type = "S",length = 11)
    private String mobile;
    @BankAPIField(order = 3,type = "N",length = 5)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
