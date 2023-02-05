package com.cfm.Yolo.dto;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
  
  private Integer id;
  private byte[] avatar;
  private byte[] background;
  private String username;

}
