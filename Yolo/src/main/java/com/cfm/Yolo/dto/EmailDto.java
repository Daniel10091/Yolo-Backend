package com.cfm.Yolo.dto;

import java.time.Instant;

import com.cfm.Yolo.model.Email;
import com.cfm.Yolo.model.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDto {

  private Integer code;
  private String email;
  private Instant createdDate;

  public Email toModel() {
    Email email = new Email();
    email.setId(this.getCode());
    email.setPerson(new Person());
    email.setEmail(this.getEmail());
    email.setCreatedDate(this.getCreatedDate());
    return email;
  }

}
