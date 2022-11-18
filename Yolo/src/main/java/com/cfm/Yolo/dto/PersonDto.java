package com.cfm.Yolo.dto;

import com.cfm.Yolo.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.cfm.Yolo.model.Person} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {
    private Integer code;
    private String name;
    private String gender;
    private byte[] avatar;
    private byte[] background;
    private Instant createdDate;
    private String username;
    private String salt;
    private String hash;
    private Instant userCreatedDate;

    public PersonDto(Person person) {
        this.code = person.getId();
        this.name = person.getName();
        this.gender = person.getGender();
        this.avatar = person.getAvatar();
        this.background = person.getBackground();
        this.createdDate = person.getCreatedDate();
        if (person.getUser() != null) {
            this.username = person.getUser().getUsername();
            this.salt = person.getUser().getSalt();
            this.hash = person.getUser().getHash();
            this.userCreatedDate = person.getUser().getCreatedDate();
        }
    }
}