package com.example.accessapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "app_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "app_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Builder.Default
    @ToString.Exclude
    protected Set<Role> roles = new HashSet<Role>();
    @Id
    @SequenceGenerator(name = "user_SEQUENCE", sequenceName = "user_id_seq")
    @GeneratedValue(generator = "user_SEQUENCE")
    @Column(name = "userId", unique = true)
    private Long id;
    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @Column
    private String password;
    @JsonIgnore
    @Column
    private String verificationCode;
    @Column
    private Date passwordModificationDateByUser;
    @Column
    private Date passwordModificationDateByAdmin;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = true)
    private boolean active;
    @Column(nullable = false)
    private Date modificationDate;
    @Column(nullable = false)
    private Date creationDate;
    @Column(nullable = false)
    private String createdBy;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            creationDate = new Date();
            modificationDate = new Date();
            createdBy = "app";
        }
    }

    public void addRole(Role theRole) {

        this.roles.add(theRole);
        theRole.getRoleUser().add(this);
    }

    public void removeRole(Role role) {

        this.roles.remove(role);
        role.getRoleUser().remove(this);
    }
}
