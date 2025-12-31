package com.pastebinlite.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.pastebinlite.dto.PasteRequest;
import com.pastebinlite.entity.Paste;
import com.pastebinlite.service.PasteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pastes")
public class PasteController {

    private final PasteService service;

    public PasteController(PasteService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String, String> create(@RequestBody PasteRequest req, HttpServletRequest request) {
        Paste paste = service.create(req, request);
        return Map.of(
                "id", paste.getPasteKey(),
                "url", "/p/" + paste.getPasteKey()
        );
    }

    @GetMapping("/{id}")
    public Map<String, Object> fetch(@PathVariable String id, HttpServletRequest request) {
        Paste paste = service.fetch(id, request);

        Integer remaining = paste.getMaxViews() == null
                ? null
                : paste.getMaxViews() - paste.getViewCount();

        return Map.of(
                "content", paste.getContent(),
                "remaining_views", remaining,
                "expires_at", paste.getExpiresAt()
        );
    }
}
