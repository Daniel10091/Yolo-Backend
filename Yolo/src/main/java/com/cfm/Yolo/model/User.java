package com.cfm.Yolo.model;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne(optional = true)
    @JoinColumn(name = "id")
    @MapsId
    private Person person;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "background")
    private byte[] background;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "online", nullable = false)
    private Boolean online = true;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
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