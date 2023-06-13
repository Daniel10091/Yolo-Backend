package com.cfm.Yolo.domain.model;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @Column(name = "ddd", length = 10)
  private String ddd;

  @Column(name = "phone_number", length = 20)
  private String phoneNumber;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDate createdDate;

}