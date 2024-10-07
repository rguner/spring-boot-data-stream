package com.guner.repository;

import com.guner.entity.Product;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.awt.print.Book;
import java.util.stream.Stream;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;
import static org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "10"),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = READ_ONLY, value = "true")
    })
    @Query("select p from Product p")
    Stream<Product> getAllWithStream();
}
