package com.rizalmovic.models;

import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Data
@Table(name = "users")
@ToString(exclude = "password")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(name = "password")
    @Setter(AccessLevel.NONE)
    @NonNull
    private String password;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword(String password, boolean hashed) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String hashed, String plain) {
        return BCrypt.checkpw(plain, hashed);
    }
}