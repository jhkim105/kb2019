package com.example.demo.book;

import com.example.demo.config.NaverProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
public class NaverBookSearchClient implements BookSearchClient {

  @Autowired
  private NaverProperties naverProperties;

  @Autowired
  private RestTemplate restTemplate;

  @Cacheable(value = "naverSearch")
  public Page<Book> search(Pageable pageable, String keyword) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.add("X-Naver-Client-Id", naverProperties.getClientId());
    headers.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
    UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(naverProperties.getApiUrl())
        .queryParam("query", keyword)
        .queryParam("start", pageable.getPageNumber() + 1)
        .queryParam("display", pageable.getPageSize())
        .build(false);

    log.debug(uriComponents.toUriString());
    ResponseEntity<NaverBookSearchResponse> responseEntity =
        restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, NaverBookSearchResponse.class);

    NaverBookSearchResponse naverBookSearchResponse = responseEntity.getBody();
    log.debug("naverBookSearchResponse->{}", naverBookSearchResponse);
    return naverBookSearchResponse.toBookPage(pageable);
  }
}
