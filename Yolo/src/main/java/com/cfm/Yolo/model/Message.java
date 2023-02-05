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
@Table(name = "messages")
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
    private Instant createdDate;

}