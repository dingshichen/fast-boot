package cn.dsc.mapstruct.entity;

import java.util.List;

/**
 * @author dingshichen
 */
public class School {

    private String name;

    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
