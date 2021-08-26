package com.tronxi.chat.infrastructure.persistence.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "tokennotification")
public class NotificationTokenEntity {
    @Id
    private String id;
    @OneToOne(targetEntity = UserEntity.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private UserEntity userEntity;
    private String token;
}
