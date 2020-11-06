package cn.dsc.mapstruct.mapper;
import cn.dsc.mapstruct.consts.GenderEnum;
import cn.dsc.mapstruct.dto.UserDTO;
import cn.dsc.mapstruct.entity.User;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author dingshichen
 */
public class UserMapperTest {

    @Test
    public void testUserToUserDTO() {
        User user = new User();
        user.setId(10);
        user.setName("dingshichen");
        user.setBalance(new BigDecimal("100"));
        user.setGender(GenderEnum.MAN);
        user.setGmtCreated(DateUtil.parse("2020-10-10"));

        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        System.out.println(userDTO);
    }
}