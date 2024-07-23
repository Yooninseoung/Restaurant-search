package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public void writeReview(Review review, MultipartFile file) throws IOException{

        //Integer restaurantId = review.getRestaurant().getRestaurantID();
        Integer restaurantId = review.getRestaurantId();
        String photo_path = uploadFile(file, restaurantId); //파일 업로드

        review.setPhoto_path(photo_path);
        review.setUserID("admin");

        System.out.println(review);

        reviewRepository.save(review);



    }

    public String uploadFile(MultipartFile file, Integer id) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\files"; //파일 경로
        String fileName = "review_"+ id +"_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile); //파일 경로에 저장

        return "\\img\\files\\"+fileName;

    }


    public List<Review> getReviewsByRestaurantId(int restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }
}
