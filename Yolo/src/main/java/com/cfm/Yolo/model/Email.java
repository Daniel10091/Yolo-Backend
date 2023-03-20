package com.cfm.Yolo.model;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "emails")
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  // @Column(name = "person_id", nullable = false)
  // private Long personId;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private Instant createdDate;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

}