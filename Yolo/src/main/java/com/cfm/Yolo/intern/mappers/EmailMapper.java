package com.cfm.Yolo.intern.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cfm.Yolo.intern.dto.EmailDto;
import com.cfm.Yolo.intern.model.Email;

@Mapper
public interface EmailMapper {
  
  @Mapping(source = "email.id", target = "code")
  EmailDto toDto(Email email);

  @InheritInverseConfiguration
  Email toEntity(EmailDto emailDto);

}
