package com.example.demo.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tu_user")
@Getter
@ToString(exclude = {"password"})
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  private String password;

  @ElementCollection(targetClass = Role.class)
  @JoinTable(name = "tu_user_roles", joinColumns = {@JoinColumn(name = "user_id")})
  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private Set<Role> roles = new HashSet<>();

}
