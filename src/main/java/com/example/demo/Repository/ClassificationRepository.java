package com.example.demo.Repository;

import com.example.demo.Model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Integer> {
}
