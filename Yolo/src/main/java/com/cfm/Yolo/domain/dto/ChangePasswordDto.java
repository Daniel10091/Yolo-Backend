package com.cfm.Yolo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePasswordDto {
  
  private String oldPassword;
  private String newPassword;
  
}
