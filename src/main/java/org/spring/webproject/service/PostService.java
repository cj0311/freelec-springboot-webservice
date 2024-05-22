package org.spring.webproject.service;


import lombok.RequiredArgsConstructor;
import org.spring.webproject.domain.posts.Posts;
import org.spring.webproject.domain.posts.PostsRepository;
import org.spring.webproject.web.dto.PostsListResponseDto;
import org.spring.webproject.web.dto.PostsSaveRequestDto;
import org.spring.webproject.web.dto.PostsUpdateRequestDto;
import org.spring.webproject.web.dto.PostsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {


    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto) {

        return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new  )
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {

        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("헤당 게시글이 없어여. id = " + id));

        postsRepository.delete(posts);
    }
}
