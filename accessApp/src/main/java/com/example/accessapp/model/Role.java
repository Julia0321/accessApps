package com.example.accessapp.model;

import com.example.accessapp.enums.EnumRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    protected Set<User> roleUser = new HashSet();
    @Id
    @SequenceGenerator(name = "role_SEQUENCE", sequenceName = "role_id_seq")
    @GeneratedValue(generator = "role_SEQUENCE")
    @Column(name = "roleId", unique = true)
    private Long id;
    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumRole type;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }

}
