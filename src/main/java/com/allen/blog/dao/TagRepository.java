package com.allen.blog.dao;

import com.allen.blog.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
