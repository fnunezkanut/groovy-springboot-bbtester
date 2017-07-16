package com.github.fnunezkanut.bb.tests

import com.github.fnunezkanut.bb.BBTest
import org.junit.Test
import org.springframework.stereotype.Component

import static org.junit.Assert.assertEquals

@Component
@BBTest //<-- custom annotation, any junit classes with this annotation will be executed in command line running jar
class MyTest{


	@Test
	void checkMessage(){

		def message = 'hello'
		assertEquals( true, message == 'hello')
	}
}