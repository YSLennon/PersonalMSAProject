package dev.yslennon.shop.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookSearchReqDto {
    private String query;
    private String queryType;
    private int start;
    private String sort;
    private int version = 20131101;
    private String output = "js";
}
