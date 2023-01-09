package org.example.pojo;

import org.example.annotation.BankAPIField;
import org.example.pojo.base.AbstractAPI;

import java.math.BigDecimal;

public class PayAPI extends AbstractAPI {

    @BankAPIField(order = 1,type = "N",length = 20)
    private long userId;

    @BankAPIField(order = 2,type = "M",length = 10)
    private BigDecimal amount;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
