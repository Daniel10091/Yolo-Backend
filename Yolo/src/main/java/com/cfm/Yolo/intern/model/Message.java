package com.cfm.Yolo.intern.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "content")
  private byte[] content;

  @Column(name = "message")
  private String message;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDate createdDate;

}