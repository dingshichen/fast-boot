package cn.dsc.mapstruct.entity;

import cn.dsc.mapstruct.consts.GenderEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dingshichen
 */
public class User {

    private Integer id;

    private String name;

    private BigDecimal balance;

    private GenderEnum gender;

    private Date gmtCreated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}
