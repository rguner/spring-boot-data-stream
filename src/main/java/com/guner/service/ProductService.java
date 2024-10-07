package com.guner.service;

import com.guner.entity.Product;
import com.guner.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private final EntityManager entityManager;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    //org.springframework.dao.InvalidDataAccessApiUsageException: You're trying to execute a streaming query method
    // without a surrounding transaction that keeps the connection open so that the Stream can actually be consumed;
    // Make sure the code consuming the stream uses @Transactional or any other way of declaring a (read-only) transaction
    public List<Product> getAllBooksWithStream() {
        List<Product> productList = new ArrayList<>();
        Stream<Product> productStream = productRepository.getAllWithStream();

        productStream.forEach(product -> {
            productList.add(product);
            entityManager.detach(product);
            /**
             * Using the repository above we are ready to consume and process each Product via a Stream.
             * we will eventually run into an OutOfMemoryError.
             *
             * The reason for this is that even though we are streaming Product entities from the database
             * and have marked the query and transaction as read only,
             * Hibernate keeps track of all entities in it’s persistence context.
             * After going through a few thousand records, the heap will be full of these entities.
             *
             * To resolve this we have to handle each entity in the stream in a different manner and
             * most importantly tell Hibernate that it shouldn’t keep track of the entity
             * in the persistence context once we are done with it.
             */
        });
        productStream.close();
        return productList;
    }

    @Transactional
    public List<Product> getAllBooksWithAutoClosableStream() {
        List<Product> productList = new ArrayList<>();

        try (Stream<Product> productStream = productRepository.getAllWithStream()) {
            productStream.forEach(product -> {
                productList.add(product);
                entityManager.detach(product);
            });
        }
        return productList;
    }



}
