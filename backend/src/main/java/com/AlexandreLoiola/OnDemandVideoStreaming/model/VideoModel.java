package com.AlexandreLoiola.OnDemandVideoStreaming.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_VIDEO")
public class VideoModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title", length = 100, nullable = false, unique = true)
    private String title;

    @Column(name = "description", length = 500, nullable = false, unique = true)
    private String description;

    @Column(name = "url", length = 500, nullable = false, unique = true)
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "uploaded_at", nullable = false)
    private Date uploadedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Version
    @Column(name = "version", nullable = false)
    private long version;
}
