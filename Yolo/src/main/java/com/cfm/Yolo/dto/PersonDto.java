package com.cfm.Yolo.dto;

import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.model.Users;
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
        this.username = person.getUser().getUsername();
        this.salt = person.getUser().getSalt();
        this.hash = person.getUser().getHash();
        this.userCreatedDate = person.getUser().getCreatedDate();
//        if (person.getUser() != null) {
//        }
    }

    public Person toModel() {
        var person =  new Person();
        person.setId(this.getCode());
        person.setName(this.getName());
        person.setGender(this.getGender());
        person.setAvatar(this.getAvatar());
        person.setBackground(this.getBackground());
        person.setUser(new Users());
        person.getUser().setPerson(person);
        person.getUser().setUsername(this.getUsername());
        person.getUser().setSalt(this.getSalt());
        person.getUser().setHash(this.getHash());
        return person;
    }
}