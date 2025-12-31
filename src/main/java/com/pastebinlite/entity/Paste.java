package com.pastebinlite.entity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
    name = "paste",
    indexes = @Index(name = "idx_paste_key", columnList = "paste_key")
)
public class Paste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paste_key", unique = true, nullable = false)
    private String pasteKey;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(name = "max_views")
    private Integer maxViews;

    @Column(name = "view_count")
    private int viewCount;
}
