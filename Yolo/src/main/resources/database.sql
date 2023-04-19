CREATE TABLE person (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(1) DEFAULT "N",
    birthday TIMESTAMP,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ck_person CHECK (gender IN ("F", "M", "N")),
    CONSTRAINT pk_person PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE emails (
    id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_email PRIMARY KEY (id),
    CONSTRAINT fk_email_person FOREIGN KEY (person_id) REFERENCES person(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE phones (
    id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL,
    ddd VARCHAR(10) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    phone_extension VARCHAR (10),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_phone PRIMARY KEY (id),
    CONSTRAINT fk_phone_person FOREIGN KEY (person_id) REFERENCES person(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE addresses (
    id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    district VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_address PRIMARY KEY (id),
    CONSTRAINT fk_address_person FOREIGN KEY (person_id) REFERENCES person(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE user (
    id INT NOT NULL,
    avatar LONGBLOB,
    background LONGBLOB,
    username VARCHAR(100) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    online BOOLEAN DEFAULT TRUE NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT fk_user_person FOREIGN KEY (id) REFERENCES person(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE friends (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    approved BOOLEAN NOT NULL DEFAULT FALSE,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_friend PRIMARY KEY (id),
    CONSTRAINT fk_friend_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_friend_friend FOREIGN KEY (friend_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE posts (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    content LONGBLOB NOT NULL,
    title VARCHAR(30),
    description VARCHAR(255),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_post PRIMARY KEY (id),
    CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE post_likes (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    liked BOOLEAN NOT NULL DEFAULT FALSE,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_post_likes PRIMARY KEY (id),
    CONSTRAINT fk_post_likes_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_post_likes_post FOREIGN KEY (post_id) REFERENCES posts(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE messages (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    content LONGBLOB,
    message VARCHAR(255),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_message PRIMARY KEY (id),
    CONSTRAINT fk_message_user FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE message_likes (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    message_id INT NOT NULL,
    liked BOOLEAN NOT NULL DEFAULT FALSE,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_message_likes PRIMARY KEY (id),
    CONSTRAINT fk_message_likes_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_message_likes_message FOREIGN KEY (message_id) REFERENCES messages(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE stories (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    content LONGBLOB NOT NULL,
    title VARCHAR(30),
    description VARCHAR(255),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_stories PRIMARY KEY (id),
    CONSTRAINT fk_stories_user FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE stories_likes (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    stories_id INT NOT NULL,
    liked BOOLEAN NOT NULL DEFAULT FALSE,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_stories_likes PRIMARY KEY (id),
    CONSTRAINT fk_stories_likes_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_stories_likes_stories FOREIGN KEY (stories_id) REFERENCES stories(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE permisions (
    id INT NOT NULL,
    route VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_route PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE user_permisions (
    id INT NOT NULL,
    user_id INT NOT NULL,
    route VARCHAR(100) NOT NULL,
    name varchar(100) NOT NULL,
    CONSTRAINT pk_route PRIMARY KEY (id),
    CONSTRAINT fk_permisions_user FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;