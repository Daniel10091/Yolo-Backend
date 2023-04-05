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

  private Long code;
  // private Long personId;
  private String address;
  private Instant createdDate;
  private Person person;

  public Email toModel(Person person) {
    Email email = new Email();
    email.setId(this.getCode());
    this.setPerson(person);
    email.setPerson(this.getPerson());
    email.setAddress(this.getAddress());
    email.setCreatedDate(this.getCreatedDate());
    return email;
  }

}
