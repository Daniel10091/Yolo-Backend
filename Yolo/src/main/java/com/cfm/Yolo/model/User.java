package com.cfm.Yolo.model;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.utility.nullability.NeverNull.ByDefault;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @Column(name = "status", length = 7)
    private Boolean status;

    @Column(name = "created_date")
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