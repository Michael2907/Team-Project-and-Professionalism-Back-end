package com.groupProject.ANPRAPI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.groupProject.ANPRAPI.resource.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ActivityControllerTest.class,
		BlacklistControllerTest.class,
		CarParkControllerTest.class,
		EventLogControllerTest.class,
		UserControllerTest.class,
		UserGroupControllerTest.class
})
public class AnprapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
