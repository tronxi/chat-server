package com.tronxi.chat.infrastructure.persistence.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "conversation")
public class ConversationEntity {

    @Id
    private String id;

    private String name;

    @ManyToMany(targetEntity = UserEntity.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserEntity> userEntityList;

    @ManyToMany(targetEntity = MessageEntity.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MessageEntity> messageEntityList;
}
