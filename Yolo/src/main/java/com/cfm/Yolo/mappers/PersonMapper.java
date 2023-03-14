package com.cfm.Yolo.mappers;

import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonMapper {
    @Mapping(source = "person.id", target = "code")
    @Mapping(source = "person.user.username", target = "username")
    @Mapping(source = "person.user.password", target = "password")
    @Mapping(source = "person.user.online", target = "online")
    @Mapping(source = "person.user.salt", target = "salt")
    PersonDto toDto(Person person);

    @InheritInverseConfiguration
    Person toEntity(PersonDto personDto);
}
