package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Restaurant> restaurantList(){ //index화면에 보이는 9개의 식당 정보를 반환
        List<Restaurant> list = restaurantRepository.findTop9ByRating();
        return list;
    }

    public List<Restaurant> rankRestaurantList(){ //index화면에 보이는 21개의 식당 정보를 반환
        List<Restaurant> list = restaurantRepository.findTop21ByRating();
        return list;
    }

    public Restaurant findRestaurant(long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElse(null); // 식당이 존재하지 않으면 null을 반환하거나 예외를 던질 수 있음

    }


    public void avgRestaurantRating(BigDecimal reviewRating, int id){ //리뷰로 받은 평점과 기본 평점의 평균을 구해 다시 저장
        Restaurant restaurant = findRestaurant(id);
        BigDecimal restaurantRating = restaurant.getRating();
        BigDecimal rating;

        if(restaurantRating == null){ //기존 식당 평점이 존재하지 않으면 새로받은 평점을 넣음
            restaurant.setRating(reviewRating);

        }else{ //기존 평점이 존재하면 평균을 구함
            rating = restaurantRating.add(reviewRating);
            rating = rating.divide(new BigDecimal("2"));
            restaurant.setRating(rating);
        }
        restaurantRepository.save(restaurant);
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> searchRestaurants(String searchName) { //식당 검색 기능
        List<Restaurant> restaurants = restaurantRepository.searchByRestaurantName(searchName);

        if (restaurants.isEmpty()) {
            return null;
        }
        return restaurants;
    }

    public void writeRestaurant(Restaurant restaurant, MultipartFile file)  throws IOException{
        if(!file.isEmpty()){
            String photo_path = uploadFile(file, restaurant.getRestaurantID()); //파일 업로드 경로
            restaurant.setPhotoPath(photo_path);
        }

        this.addRestaurant(restaurant);

    }

    public String uploadFile(MultipartFile file, Integer restaurantId) throws IOException {
        String restaurantPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\restaurantImg"; //파일 경로
        String fileName = "restaurant_"+ restaurantId + "_" + file.getOriginalFilename();
        File saveFile = new File(restaurantPath, fileName);

        file.transferTo(saveFile); //파일 경로에 저장

        return "\\img\\restaurantImg\\"+fileName;

    }

    public void modifyRestaurant(Restaurant restaurant, MultipartFile file) throws IOException{ //식당 수정
        if(!file.isEmpty()){
            String photo_path = uploadFile(file, restaurant.getRestaurantID()); //파일 업로드 경로
            restaurant.setPhotoPath(photo_path);
        }

        restaurantRepository.save(restaurant);
    }

    public void removeRestaurant(Long restaurantId){
        restaurantRepository.deleteById(restaurantId);
    }


    public Page<Restaurant> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 21; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<Restaurant> postsPages = restaurantRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "restaurantID")));

        return postsPages;

    }
}
