package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.service.JavaReadCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 관리자 권한의 기능을 처리
@Controller
@RequestMapping("/manage")
public class AdministratorController {

    @Autowired
    private JavaReadCsvService javaReadCsvService;
    @RequestMapping("/csvToDb") //원주시 식당 csv 파일을 db에 저장
    public String csv(Model model){
        javaReadCsvService.readCSV();
        return "index";
    }
}
