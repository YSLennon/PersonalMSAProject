package dev.yslennon.shop.shop.controller;

import dev.yslennon.shop.core.dto.ApiResponse;
import dev.yslennon.shop.shop.dto.BookListReqDto;
import dev.yslennon.shop.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/new/{page}")
    public ResponseEntity<ApiResponse<List<Object>>> searchNewBookList(@PathVariable("page") int page) {
        BookListReqDto bookListReqDto = BookListReqDto.builder()
                .queryType("ItemNewAll")
                .start(page)
                .build();
        return shopService.searchBookList(bookListReqDto);
    }

    @GetMapping("/best/{page}")
    public ResponseEntity<ApiResponse<List<Object>>> searchBestBookList(@PathVariable("page") int page) {
        BookListReqDto bookListReqDto = BookListReqDto.builder()
                .queryType("Bestseller")
                .start(page)
                .build();
        return shopService.searchBookList(bookListReqDto);
    }
}
