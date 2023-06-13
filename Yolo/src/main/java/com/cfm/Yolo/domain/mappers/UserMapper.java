package com.cfm.Yolo.domain.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cfm.Yolo.domain.dto.UserDto;
import com.cfm.Yolo.domain.model.User;

@Mapper
public interface UserMapper {
  
  @Mapping(source = "user.id", target = "code")
  UserDto toDto(User user);

  @InheritInverseConfiguration
  User toEntiry(UserDto userDto);

}
