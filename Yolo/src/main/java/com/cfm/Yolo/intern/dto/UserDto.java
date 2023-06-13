package com.cfm.Yolo.intern.dto;


import java.time.LocalDate;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
  
  private Long code;
  private byte[] avatar;
  private byte[] background;
  private String username;
  private String salt;
  private String password;
  private Boolean online;
  private LocalDate createdDate;

}
