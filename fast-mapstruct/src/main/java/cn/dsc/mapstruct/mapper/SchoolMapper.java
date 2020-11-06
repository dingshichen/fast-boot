package cn.dsc.mapstruct.mapper;

import cn.dsc.mapstruct.dto.SchoolDTO;
import cn.dsc.mapstruct.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author dingshichen
 */
@Mapper
public interface SchoolMapper {

    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    @Mapping(target = "users", expression = "java(cn.dsc.mapstruct.mapper.UserMapper.userToUserDTOs(school.getUsers()))")
    SchoolDTO schoolToSchoolDTO(School school);
}
