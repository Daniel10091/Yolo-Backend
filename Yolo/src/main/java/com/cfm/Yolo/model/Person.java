package com.cfm.Yolo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

//    @OneToOne(mappedBy = "person")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Users user;

    @OneToMany(mappedBy = "person")
    private Set<Email> emails = new LinkedHashSet<>();

}