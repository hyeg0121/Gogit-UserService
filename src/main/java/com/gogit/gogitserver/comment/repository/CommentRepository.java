package com.gogit.gogitserver.comment.repository;

import com.gogit.gogitserver.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
