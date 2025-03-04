package dev.yslennon.shop.shop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yslennon.shop.core.dto.ApiResponse;
import dev.yslennon.shop.shop.dto.BookListReqDto;
import dev.yslennon.shop.shop.dto.BookResDto;
import dev.yslennon.shop.shop.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final WebClientUtil webClientUtil;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public ResponseEntity<ApiResponse<List<Object>>> searchBookList(BookListReqDto bookList) {

        List<Mono<String>> pageMonos = new ArrayList<>();
        List<String> jsonList;
        List<Object> pageList = null;
        try {
            if (bookList.getStart() < 10) {
                pageList = getBookInRedis(bookList.getQueryType());
                if (pageList != null) {
                    System.out.println("Redis PageList: " + pageList);
                    return ApiResponse.success(pageList);
                }
                for (int i = 1; i <= 10; i++) {
                    bookList.setStart(i);
                    Mono<String> pageMono = webClientUtil.aladinBookListApi(bookList);
                    pageMonos.add(pageMono);
                }
            } else {
                Mono<String> pageMono = webClientUtil.aladinBookListApi(bookList);
                pageMonos.add(pageMono);
            }

            jsonList = Mono.zip(pageMonos, responses -> Arrays.stream(responses)
                    .map(Object::toString).toList()).block();
            if (jsonList != null) {
                pageList = getBookListFrom(jsonList);

                setBookInRedis(bookList.getQueryType(), pageList);
            }

            return ApiResponse.success(pageList);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private List<Object> getBookListFrom(List<String> pageList) throws JsonProcessingException {
        List<Object> responseValues = new ArrayList<>();
        for (String page : pageList) {
            JsonNode nodeList = objectMapper.readTree(page).path("item");
            List<BookResDto> bookResDtoList = new ArrayList<>();

            for (JsonNode item : nodeList) {
                BookResDto value = BookResDto.builder()
                        .title(item.path("title").asText())
                        .author(item.path("author").asText())
                        .description(item.path("description").asText())
                        .isbn(item.path("isbn").asText())
                        .priceStandard(item.path("priceStandard").asInt())
                        .cover(item.path("cover").asText())
                        .categoryName(item.path("categoryName").asText())
                        .publisher(item.path("publisher").asText())
                        .customerReviewRank(item.path("customerReviewRank").asInt())
                        .build();
                bookResDtoList.add(value);
            }
            responseValues.add(bookResDtoList);
        }
        return responseValues;

    }

    private List<Object> getBookInRedis(String queryType) {
        Object pageList = redisTemplate.opsForValue().get(queryType);
        if (pageList instanceof List) {
            return (List<Object>) pageList;
        }
        return null;
    }

    private void setBookInRedis(String queryType, List<Object> pageList) {
        redisTemplate.opsForValue().set(queryType, pageList, Duration.ofHours(1));
    }
}
