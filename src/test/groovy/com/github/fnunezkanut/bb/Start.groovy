package com.github.fnunezkanut.bb

import org.junit.runner.JUnitCore
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Configuration
@SpringBootApplication(scanBasePackages = 'com.github.fnunezkanut.bb')
@EnableAutoConfiguration
class Start {

	static void main(String... args) {

		final ctx = SpringApplication.run(Start.class, args)

		//we need junit core which will run each test class
		JUnitCore junit = new JUnitCore()

		final Logger logger = LoggerFactory.getLogger(this)
		logger.info("Starting blackbox tests...")

		//get classes annotated with @BBTest
		final testClasses = ctx.getBeansWithAnnotation(BBTest)

		//kick off each junit test
		testClasses.each { name, bean ->

			logger.info("###BEGIN BBTEST: ${name}###")
			final Result result = junit.run(bean.class)
			if (result.wasSuccessful()) {
				logger.info("###OK BBTEST: ${name}###")
			}
			else{

				logger.error("~~~ERR BBTEST: ${name}~~~")
				final List<Failure> failures = result.getFailures()
				failures.each {
					println( it.toString() )
					println( it.getTrace() )
				}
			}
		}
	}
}
