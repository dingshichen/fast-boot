package cn.dsc.mapstruct.mapper;

import cn.dsc.mapstruct.consts.GenderEnum;
import cn.dsc.mapstruct.dto.SchoolDTO;
import cn.dsc.mapstruct.entity.School;
import cn.dsc.mapstruct.entity.User;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author dingshichen
 */
public class SchoolMapperTest {

    @Test
    public void schoolToSchoolDTO() {
        User user1 = new User();
        user1.setId(10);
        user1.setName("dingshichen");
        user1.setBalance(new BigDecimal("100"));
        user1.setGender(GenderEnum.MAN);
        user1.setGmtCreated(DateUtil.parse("2020-10-10"));

        User user2 = new User();
        user2.setId(15);
        user2.setName("qiuping");
        user2.setBalance(new BigDecimal("50"));
        user2.setGender(GenderEnum.WOMAN);
        user2.setGmtCreated(DateUtil.parse("2020-11-05"));

        School school = new School();
        school.setName("zhonghua");
        school.setUsers(CollUtil.newArrayList(user1, user2));

        SchoolDTO dto = SchoolMapper.INSTANCE.schoolToSchoolDTO(school);
        System.out.println(dto);
    }
}