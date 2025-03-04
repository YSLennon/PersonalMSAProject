package dev.yslennon.shop.shop.util;

import dev.yslennon.shop.shop.dto.BookListReqDto;
import dev.yslennon.shop.shop.dto.BookSearchReqDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientUtil {

    private WebClient aladinBookListApi;
    private WebClient aladinBookSearchApi;

    @Value("${aladin.ttbkey}")
    private String ttbkey;

    @PostConstruct
    public void init() {
        this.aladinBookListApi = WebClient.builder()
                .baseUrl("http://www.aladin.co.kr/ttb/api/ItemList.aspx")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        this.aladinBookSearchApi = WebClient.builder()
                .baseUrl("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<String> aladinBookListApi(BookListReqDto bookList) {
        return aladinBookListApi.get()
                .uri(urlBuilder -> urlBuilder
                        .queryParam("ttbkey", ttbkey)
                        .queryParam("QueryType", bookList.getQueryType())
                        .queryParam("SearchTarget", bookList.getSearchTarget())
                        .queryParam("Start", bookList.getStart())
                        .queryParam("Output", bookList.getOutput())
                        .queryParam("Version", bookList.getVersion())
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> aladinBookSearchApi(BookSearchReqDto bookList) {
        return aladinBookSearchApi.get()
                .uri(urlBuilder -> urlBuilder
                        .queryParam("ttbkey", ttbkey)
                        .queryParam("Query", bookList.getQuery())
                        .queryParam("QueryType", bookList.getQueryType())
                        .queryParam("Start", bookList.getStart())
                        .queryParam("Sort", bookList.getSort())
                        .queryParam("Output", bookList.getOutput())
                        .queryParam("Version", bookList.getVersion())
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
