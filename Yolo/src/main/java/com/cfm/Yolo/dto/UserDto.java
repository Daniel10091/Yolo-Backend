package com.cfm.Yolo.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
  
  private Long id;
  private byte[] avatar;
  private byte[] background;
  private String username;

}
