package com.example.shop;

import com.example.shop.entity.Category;
import com.example.shop.entity.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.CategoryStat;
import com.example.shop.repository.ProductRepository;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataRunner implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataRunner(CategoryRepository categoryRepository,
                      ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

    }

    @Override
    public void run(String... args) {
        // CREATE: заполняем таблицы, только если они пусты
        if (categoryRepository.count() == 0) {
            Category electronics = categoryRepository.save(new Category("Электроника"));
            Category books = categoryRepository.save(new Category("Книги"));

            productRepository.save(new Product("Смартфон",
                    new BigDecimal("29990.00"), 10, electronics));

            productRepository.save(new Product("Наушники",
                    new BigDecimal("4990.00"), 25, electronics));

            productRepository.save(new Product("Ноутбук",
                    new BigDecimal("74990.00"), 5, electronics));

            productRepository.save(new Product("Основы Java",
                    new BigDecimal("1200.00"), 40, books));

            productRepository.save(new Product("Базы данных",
                    new BigDecimal("1500.00"), 30, books));
        }

        // CREATE: третья категория канцтовары
        if (!categoryRepository.existsByTitle("Канцтовары")) {
            Category stationery = categoryRepository.save(new Category("Канцтовары"));

            productRepository.save(new Product("Ручка шариковая",
                    new BigDecimal("50.00"), 100, stationery));
            productRepository.save(new Product("Блокнот А5",
                    new BigDecimal("250.00"), 60, stationery));

            System.out.println("=== Категория «Канцтовары» добавлена ===");
        }

        // READ: все записи
        System.out.println("=== Все товары ===");
        for (Product p : productRepository.findAll()) {
            System.out.println(p.getId() + ". " + p.getName() + " - "
                    + p.getPrice() + " руб. ["
                    + p.getCategory().getTitle() + "]");
        }

        // READ: поиск по условию
        System.out.println("=== Поиск по слову 'смарт' ===");
        productRepository.findByNameContainingIgnoreCase("смарт")
                .forEach(p -> System.out.println(p.getName()));

        // UPDATE: изменяем найденный объект и сохраняем
        productRepository.findByNameContainingIgnoreCase("смарт")
                .forEach(p -> {
                    p.setPrice(new BigDecimal("27990.00"));
                    productRepository.save(p);
                });

        // 8.2
        System.out.println("=== Товары дороже 5000 руб. ===");
        for (Product p : productRepository
                .findByPriceGreaterThanOrderByPriceDesc(new BigDecimal("5000"))) {
            System.out.println(p.getName() + " - " + p.getPrice() + " руб.");
        }

        // 8.3
        productRepository.findByNameContainingIgnoreCase("Блокнот")
                .forEach(productRepository::delete);

        // Статистика по категориям
        System.out.println("=== Товаров в категориях ===");
        for (CategoryStat s : productRepository.countByCategory()) {
            System.out.println(s.getTitle() + ": " + s.getProductCount());
        }
    }
}