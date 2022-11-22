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
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "district", length = 50)
    private String district;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "cep", nullable = false, length = 20)
    private String cep;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

}