package com.cfm.Yolo.domain.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cfm.Yolo.domain.dto.EmailDto;
import com.cfm.Yolo.domain.model.Email;

@Mapper
public interface EmailMapper {
  
  @Mapping(source = "email.id", target = "code")
  EmailDto toDto(Email email);

  @InheritInverseConfiguration
  Email toEntity(EmailDto emailDto);

}
