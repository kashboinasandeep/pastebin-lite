package com.pastebinlite.controller;

import org.springframework.web.bind.annotation.*;

import com.pastebinlite.entity.Paste;
import com.pastebinlite.service.PasteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PasteViewController {

	private final PasteService service;

	public PasteViewController(PasteService service) {
		this.service = service;
	}

	@GetMapping("/p/{id}")
	public String view(@PathVariable String id, HttpServletRequest request) {
		Paste paste = service.fetch(id, request);
		return "<pre>" + escapeHtml(paste.getContent()) + "</pre>";
	}

	private String escapeHtml(String text) {
		return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}
}
