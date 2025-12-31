package com.pastebinlite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pastebinlite.entity.Paste;

public interface PasteRepository extends JpaRepository<Paste, Long> {
    Optional<Paste> findByPasteKey(String pasteKey);
}
