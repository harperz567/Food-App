package com.micro.userinfo.mapper;

import com.micro.userinfo.dto.UserDTO;
import com.micro.userinfo.entity.User;
import org.mapstruct.Mapper;  // 确保是从 mapstruct 包导入
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User mapUserDTOToUser(UserDTO userDTO);
    UserDTO mapUserToUserDTO(User user);
}
