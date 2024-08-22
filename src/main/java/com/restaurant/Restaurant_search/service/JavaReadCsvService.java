package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.repository.CsvRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JavaReadCsvService { //공공데이터 포털에서 제공하는 데이터를 데이터베이스에 저장하는 코드 , 관리자 기능에 들어가야 함.
    @Autowired
    private CsvRepository csvRepository;

    public void readCSV() {
        Restaurant restaurant;

        try {
            String csvPath = System.getProperty("user.dir") + "\\src\\main\\resources\\csv\\restaurantData.csv"; //파일 경로
            File file = new File(csvPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                aLine = Arrays.asList(lineArr);
                restaurant = new Restaurant(lineArr[1], lineArr[6], lineArr[3], lineArr[7]);
                csvRepository.save(restaurant);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
