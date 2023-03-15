package com.cfm.Yolo.dto;

import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.cfm.Yolo.model.Person} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto implements Serializable {
    private Long code;
    private String name;
    private String gender;
    private Instant createdDate;
    private Long userId;
    private byte[] avatar;
    private byte[] background;
    private String username;
    private String salt;
    private String password;
    private Boolean online = true;
    private Instant userCreatedDate;

    public PersonDto(Person person) {
        this.code = person.getId();
        this.name = person.getName();
        this.gender = person.getGender();
        this.avatar = person.getUser().getAvatar();
        this.background = person.getUser().getBackground();
        this.username = person.getUser().getUsername();
        this.salt = person.getUser().getSalt();
        this.password = person.getUser().getPassword();
        this.online = person.getUser().getOnline();
        this.userCreatedDate = person.getUser().getCreatedDate();
        // if (person.getUser() != null) {
        // }
    }

    public Person toModel() {
        var person = new Person();
        person.setId(this.getCode());
        person.setName(this.getName());
        person.setGender(this.getGender());
        person.setUser(new User());
        person.getUser().setPerson(person);
        person.getUser().setId(this.getUserId());
        person.getUser().setAvatar(this.getAvatar());
        person.getUser().setBackground(this.getBackground());
        person.getUser().setUsername(this.getUsername());
        person.getUser().setSalt(this.getSalt());
        person.getUser().setPassword(this.getPassword());
        person.getUser().setOnline(this.getOnline());
        person.getUser().setCreatedDate(this.getUserCreatedDate());
        return person;
    }
}