package com.cfm.Yolo.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cfm.Yolo.domain.model.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.cfm.Yolo.domain.model.Person} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto implements Serializable {
    
    private Long code;
    private String name;
    private LocalDate birthday;
    private String gender;
    private LocalDateTime createdDate;
    // private Long userCode;
    private byte[] avatar;
    private byte[] background;
    private String username;
    // private String salt;
    private String password;
    private Boolean online = true;
    // private LocalDate userCreatedDate;

    public PersonDto(Person person) {
        this.code = person.getId();
        this.birthday = person.getBirthday();
        this.name = person.getName();
        this.gender = person.getGender();
        this.avatar = person.getUser().getAvatar();
        this.background = person.getUser().getBackground();
        this.username = person.getUser().getUsername();
        // this.salt = person.getUser().getSalt();
        this.password = person.getUser().getPassword();
        this.online = person.getUser().getOnline();
        // this.userCreatedDate = person.getUser().getCreatedDate();
        // if (person.getUser() != null) {
        // }
    }
    
}