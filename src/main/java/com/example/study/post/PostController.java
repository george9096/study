package com.example.study.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostMapper postMapper;

    public PostController(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @GetMapping
    public List<PostDto> list() {
        return postMapper.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> detail(@PathVariable long id) {
        PostDto post = postMapper.findById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public String create(@RequestBody PostCreateRequest request) {
        postMapper.insert(request);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id, @RequestBody PostUpdateRequest request) {
        postMapper.update(new PostUpdateRequest(id, request.title(), request.content()));
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        postMapper.delete(id);
        return "OK";
    }
}
