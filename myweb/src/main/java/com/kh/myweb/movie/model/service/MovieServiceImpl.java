package com.kh.myweb.movie.model.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final String SERVICE_KEY = "75fe583eb3a66516a987b8482f69071e";
    
    @Override
    public String requestGetMovie() {
    	log.info("requestGetMovie() 호출됨");
        StringBuilder sb = new StringBuilder();
        
       
        sb.append("https://api.themoviedb.org/3/movie/popular");
        sb.append("?api_key=").append(SERVICE_KEY); // TMDb에서는 api_key 파라미터를 사용합니다.
        sb.append("&language=en-US");
        sb.append("&page=1");
        
        URI uri = null;
        try {
            uri = new URI(sb.toString());
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
        String apiResponseData = new RestTemplate().getForObject(uri, String.class);
        log.info("API 응답: {}", apiResponseData);
        return apiResponseData;
    }

    @Override
    public String requestGetMovieDetail(int pk) {
        return null;
    }
}
