package com.cfm.Yolo.intern.mappers;

import com.cfm.Yolo.intern.dto.PersonDto;
import com.cfm.Yolo.intern.model.Person;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper 
public interface PersonMapper {
  @Mapping(source = "person.id", target = "code")
  // @Mapping(source = "person.user.id", target = "userCode")
  @Mapping(source = "person.user.avatar", target = "avatar")
  @Mapping(source = "person.user.background", target = "background")
  @Mapping(source = "person.user.username", target = "username")
  @Mapping(source = "person.user.online", target = "online")
  // @Mapping(source = "person.user.salt", target = "salt")
  @Mapping(source = "person.user.password", target = "password")
  // @Mapping(source = "person.user.createdDate", target = "userCreatedDate")
  PersonDto toDto(Person person);

  @InheritInverseConfiguration
  Person toEntity(PersonDto personDto);
}
