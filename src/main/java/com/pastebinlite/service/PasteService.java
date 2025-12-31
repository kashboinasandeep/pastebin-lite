package com.pastebinlite.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pastebinlite.dto.PasteRequest;
import com.pastebinlite.entity.Paste;
import com.pastebinlite.repository.PasteRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PasteService {

    private final PasteRepository repository;

    public PasteService(PasteRepository repository) {
        this.repository = repository;
    }

    private Instant now(HttpServletRequest request) {
        if ("1".equals(System.getenv("TEST_MODE"))) {
            String header = request.getHeader("x-test-now-ms");
            if (header != null) {
                return Instant.ofEpochMilli(Long.parseLong(header));
            }
        }
        return Instant.now();
    }

    public Paste create(PasteRequest req, HttpServletRequest request) {
        if (req.getContent() == null || req.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("content must be non-empty");
        }
        if (req.getTtl_seconds() != null && req.getTtl_seconds() < 1) {
            throw new IllegalArgumentException("ttl_seconds must be >= 1");
        }
        if (req.getMax_views() != null && req.getMax_views() < 1) {
            throw new IllegalArgumentException("max_views must be >= 1");
        }

        Paste paste = new Paste();
        paste.setPasteKey(UUID.randomUUID().toString().substring(0, 8));
        paste.setContent(req.getContent());
        paste.setMaxViews(req.getMax_views());
        paste.setViewCount(0);

        if (req.getTtl_seconds() != null) {
            paste.setExpiresAt(now(request).plusSeconds(req.getTtl_seconds()));
        }

        return repository.save(paste);
    }

    public Paste fetch(String key, HttpServletRequest request) {
        Paste paste = repository.findByPasteKey(key)
                .orElseThrow(() -> new RuntimeException("Paste not found"));

        Instant now = now(request);

        if (paste.getExpiresAt() != null && now.isAfter(paste.getExpiresAt())) {
            throw new RuntimeException("Paste expired");
        }

        if (paste.getMaxViews() != null &&
            paste.getViewCount() >= paste.getMaxViews()) {
            throw new RuntimeException("View limit exceeded");
        }

        paste.setViewCount(paste.getViewCount() + 1);
        repository.save(paste);
        return paste;
    }
}
