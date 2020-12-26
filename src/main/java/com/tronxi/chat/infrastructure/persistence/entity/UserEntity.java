package com.tronxi.chat.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "user")
public class UserEntity {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String password;
}
