package com.cfm.Yolo.converts;

import java.util.List;
import java.util.stream.Collectors;

import com.cfm.Yolo.dto.EmailDto;
import com.cfm.Yolo.model.Email;

public class EmailConvert {

  // public static EmailDto convertEmailDto(Email email) {
  //   EmailDto emailDto = new EmailDto();
  //   emailDto.setCode(email.getId());
  //   emailDto.setPerson(email.getPerson());
  //   emailDto.setEmail(email.getEmail());
  //   emailDto.setCreatedDate(email.getCreatedDate());
  //   return emailDto;
  // }

  // public static List<EmailDto> convertEmailDtoToList(List<Email> email) {
  //   return email.stream().map(EmailConvert::convertEmailDto).collect(Collectors.toList());
  // }

  // public static Email convertEmailEntity(EmailDto emailDto) {
  //   Email email = new Email();
  //   email.setId(emailDto.getCode());
  //   email.setPerson(emailDto.getPerson());
  //   email.setEmail(emailDto.getEmail());
  //   email.setCreatedDate(emailDto.getCreatedDate());
  //   return email;
  // }

  // public static List<Email> convertEmailEntityToList(List<EmailDto> emailDto) {
  //   return emailDto.stream().map(EmailConvert::convertEmailEntity).collect(Collectors.toList());
  // }

}
