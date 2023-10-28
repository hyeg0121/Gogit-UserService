package com.gogit.gogitserver.repository;

import com.gogit.gogitserver.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getPostByWriterId(Long memberId);

}
