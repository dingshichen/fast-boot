package cn.dsc.mapstruct.dto;

/**
 * @author dingshichen
 */
public class UserDTO {

    private Integer id;

    private String userName;

    private String balance;

    private String gender;

    private String gmtCreated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", balance='" + balance + '\'' +
                ", gender='" + gender + '\'' +
                ", gmtCreated='" + gmtCreated + '\'' +
                '}';
    }
}
