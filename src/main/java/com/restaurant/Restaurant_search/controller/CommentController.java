package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Comment addComment(@RequestParam Integer boardId,
                              @RequestParam String username,
                              @RequestParam String content) {
        return (Comment) commentService.addComment(boardId, username, content);
    }
}