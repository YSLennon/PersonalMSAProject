package dev.yslennon.shop.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookListReqDto {
    private String queryType;
    @Builder.Default
    private String searchTarget = "Book";
    private int start;
    @Builder.Default
    private String output = "JS";
    @Builder.Default
    private int version = 20131101;
}
