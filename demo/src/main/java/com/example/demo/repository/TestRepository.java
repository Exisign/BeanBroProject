package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.TestDomain;

public interface TestRepository extends JpaRepository<TestDomain, Long> {


}
