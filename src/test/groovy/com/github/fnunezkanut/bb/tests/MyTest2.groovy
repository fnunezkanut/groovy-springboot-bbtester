package com.github.fnunezkanut.bb.tests

import com.github.fnunezkanut.bb.BBTest
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import static org.junit.Assert.assertEquals

@Component
@BBTest //<-- custom annotation, any junit classes with this annotation will be executed in command line running jar
class MyTest2 {

	final Logger logger = LoggerFactory.getLogger(MyTest2.class)


	@Test
	void checkMessage(){

		def message = 'world'
		assertEquals( true, message == 'world')
	}

	@Test
	void shouldFail(){

		logger.warn('test')

		def message = 'world'
		assertEquals( true, message == 'WORLD')
	}
}