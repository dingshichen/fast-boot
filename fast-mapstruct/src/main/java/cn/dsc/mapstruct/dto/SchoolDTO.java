package cn.dsc.mapstruct.dto;

import java.util.List;

/**
 * @author dingshichen
 */
public class SchoolDTO {

    private String name;

    private List<UserDTO> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SchoolDTO{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
