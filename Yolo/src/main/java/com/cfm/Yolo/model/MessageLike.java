package com.cfm.Yolo.model;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Entity
@Table(name = "message_likes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MessageLike {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "liked", nullable = false)
  private Boolean liked = false;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDate createdDate;

}