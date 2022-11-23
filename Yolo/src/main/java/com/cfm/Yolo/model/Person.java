package com.cfm.Yolo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "person")
@SecondaryTable(name = "users", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "background")
    private byte[] background;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Instant createdDate;

    @OneToMany(mappedBy = "person")
    private Set<Friend> friends = new LinkedHashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Phones> phones = new LinkedHashSet<>();

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, optional = true)
    private Users user;

    @OneToMany(mappedBy = "person")
    private Set<Email> emails = new LinkedHashSet<>();

}