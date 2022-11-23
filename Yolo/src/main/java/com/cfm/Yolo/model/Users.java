package com.cfm.Yolo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne(optional = true)
    @JoinColumn(name = "id")
    @MapsId
    private Person person;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "created_date")
    private Instant createdDate;

    @OneToMany(mappedBy = "user")
    private Set<PostLike> postLikes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "friend")
    private Set<Friend> friends = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Message> messages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<StoriesLike> storiesLikes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Story> stories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<MessageLike> messageLikes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Post> posts = new LinkedHashSet<>();

}