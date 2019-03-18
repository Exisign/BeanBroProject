package com.example.demo.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TestDomain;
import com.example.demo.repository.TestRepository;

@Service("TestService")
public class TestServiceImpl implements TestService {

	@Autowired
	TestRepository testrepository;

	@Override
	public List<TestDomain> allList() {
		return testrepository.findAll();
	}

	@Override
	public void dataSave(TestDomain testDomain) {
		testrepository.save(testDomain);
	}
	
}
