package cn.dsc.mapstruct.mapper;

import cn.dsc.mapstruct.dto.UserDTO;
import cn.dsc.mapstruct.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author dingshichen
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "userName"),
            @Mapping(target = "gmtCreated", expression = "java(cn.hutool.core.date.DateUtil.format(user.getGmtCreated(), \"yyyy-MM-dd\"))"),
            @Mapping(target = "gender", expression = "java(user.getGender().getDesc())")
    })
    UserDTO userToUserDTO(User user);

    @Mappings({
            @Mapping(source = "name", target = "userName"),
            @Mapping(target = "gmtCreated", expression = "java(cn.hutool.core.date.DateUtil.format(user.getGmtCreated(), \"yyyy-MM-dd\"))"),
            @Mapping(target = "gender", expression = "java(user.getGender().getDesc())")
    })
    List<UserDTO> userToUserDTO(List<User> user);


    static List<UserDTO> userToUserDTOs(List<User> user) {
        return INSTANCE.userToUserDTO(user);
    }
}
