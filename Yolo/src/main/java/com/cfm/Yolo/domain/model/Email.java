package com.cfm.Yolo.domain.model;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Entity
@Table(name = "emails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
  private LocalDate createdDate;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

}