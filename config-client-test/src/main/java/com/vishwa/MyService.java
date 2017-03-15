package com.vishwa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@Autowired
	org.springframework.core.env.Environment env;

	public String getName() {
		return env.getRequiredProperty("name");
	}

}
