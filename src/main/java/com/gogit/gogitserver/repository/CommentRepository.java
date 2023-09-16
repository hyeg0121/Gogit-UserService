package com.gogit.gogitserver.repository;

import com.gogit.gogitserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
