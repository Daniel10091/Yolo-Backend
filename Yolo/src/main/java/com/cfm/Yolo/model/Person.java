package com.cfm.Yolo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
@SecondaryTable(name = "user", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", length = 20)
    private String gender;

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
    private User user;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "id", referencedColumnName = "person_id")
    private Set<Email> emails = new LinkedHashSet<>();

}