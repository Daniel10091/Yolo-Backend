package com.cfm.Yolo.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "emails")
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  // @Column(name = "person_id", nullable = false)
  // private Long personId;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "created_date", nullable = false)
  private Instant createdDate;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

}