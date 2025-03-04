package dev.yslennon.shop.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResDto {
    private String title;
    private String author;
    private String description;
    private String isbn;
    private int priceStandard;
    private String cover;
    private String categoryName;
    private String publisher;
    private int customerReviewRank;

    public static BookResDto from(Object obj) {
        if (obj instanceof BookResDto) {
            return (BookResDto) obj;
        } else return null;

    }
}
