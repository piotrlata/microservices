package com.shop.notification.model.dao;

import com.shop.notification.model.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String body;
    private String receiver;
    private String subject;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
}
