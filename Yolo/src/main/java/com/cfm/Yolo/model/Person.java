package com.cfm.Yolo.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false, length = 20)
    private String gender;

    @Column(name = "avatar", nullable = false)
    private byte[] avatar;

    @Column(name = "background", nullable = false)
    private byte[] background;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @OneToMany(mappedBy = "person")
    private Set<Contact> contacts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Phone> phones = new LinkedHashSet<>();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "person_id")
    private User user;

    @OneToMany(mappedBy = "person")
    private Set<Email> emails = new LinkedHashSet<>();

}