package com.example.demo.repository.service;

import java.util.List;

import com.example.demo.domain.TestDomain;

public interface TestService {

	public List<TestDomain> allList();
	
	public void dataSave(TestDomain testDomain);
}
