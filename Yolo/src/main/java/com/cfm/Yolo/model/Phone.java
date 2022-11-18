package com.cfm.Yolo.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "ddd", nullable = false, length = 10)
    private String ddd;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "phone_extension", nullable = false, length = 10)
    private String phoneExtension;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

}