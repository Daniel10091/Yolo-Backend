package com.cfm.Yolo.intern.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Table(name = "person")
@SecondaryTable(name = "user", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "gender", length = 1)
  private String gender = "N";

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDate createdDate;

  @OneToMany(mappedBy = "person")
  private Set<Friend> friends = new LinkedHashSet<>();

  @OneToMany(mappedBy = "person")
  private Set<Address> addresses = new LinkedHashSet<>();

  @OneToMany(mappedBy = "person")
  private Set<Phone> phones = new LinkedHashSet<>();

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, optional = true)
  private User user;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
  // @JoinColumn(name = "id", referencedColumnName = "person_id")
  private Set<Email> emails = new LinkedHashSet<>();

}