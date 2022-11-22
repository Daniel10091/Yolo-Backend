package com.cfm.Yolo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private Person person;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "hash", nullable = false)
    private String hash;

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