package com.tronxi.chat.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "message")
public class MessageEntity {

    @Id
    private String id;
    private String message;
    private Boolean readed;
    private String senderId;
    private Date date;
}
