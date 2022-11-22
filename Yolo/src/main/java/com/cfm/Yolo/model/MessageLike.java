package com.cfm.Yolo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "message_likes")
public class MessageLike {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "liked", nullable = false)
    private Boolean liked = false;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Instant createdDate;

}