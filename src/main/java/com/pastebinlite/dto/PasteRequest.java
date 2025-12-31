package com.pastebinlite.dto;

import lombok.Data;

@Data
public class PasteRequest {
    private String content;
    private Integer ttl_seconds;
    private Integer max_views;
}
