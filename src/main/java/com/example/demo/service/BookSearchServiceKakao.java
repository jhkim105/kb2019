package com.example.demo.service;

import com.example.demo.config.KakaoProperties;
import com.example.demo.vo.Book;
import com.example.demo.vo.KakaoBookSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
@Slf4j
public class BookSearchServiceKakao implements BookSearchService {

  @Autowired
  private KakaoProperties kakaoProperties;

  @Override
  public Page<Book> search(Pageable pageable, String keyword) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.add("Authorization", String.format("KakaoAK %s", kakaoProperties.getAppKey()));

    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
    UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(kakaoProperties.getApiUrl())
        .queryParam("query", keyword)
        .queryParam("page", pageable.getPageNumber() + 1)
        .queryParam("size", pageable.getPageSize())
        .build(false);
    RestTemplate restTemplate = new RestTemplate();
    log.debug(uriComponents.toUriString());
    ResponseEntity<KakaoBookSearchResponse> responseEntity =
        restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, KakaoBookSearchResponse.class);

    KakaoBookSearchResponse kakaoBookSearchResponse = responseEntity.getBody();
    return kakaoBookSearchResponse.toBookPage(pageable);
  }



}
