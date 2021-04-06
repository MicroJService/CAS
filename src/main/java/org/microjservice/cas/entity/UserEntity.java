package org.microjservice.cas.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * User Entity
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Entity
@Table(name = "user", schema = "cas")
@Data
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    private String password;

    private Integer countryCode;

    private Long phone;
}
