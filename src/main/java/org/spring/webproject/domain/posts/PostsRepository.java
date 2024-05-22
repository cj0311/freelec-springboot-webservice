package org.spring.webproject.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p from Posts p Order by p.id desc")
    List<Posts> findAllDesc();
}
