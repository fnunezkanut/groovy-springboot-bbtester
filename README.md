# groovy-springboot-bbtester
A minimal Spring Boot+Groovy based project structure to help create a self contained jar with black box tests. 

Using gradle a "self-contained" .jar can be generated which can be executed on the command line 
and any Junit tests annotated with @BBTest will be executed.

Any exceptions (and their traces) on any failed tests are shown to allow to quickly catch issues during smoke testing.

This sample project contains 2 test classes, one will fail in order to illustrate what happens during a test failure

## Building a "selfcontained" .jar

```shell
cd /project/dir/
gradle clean bootRepackage
```

## Running your tests

Gradle will create a self-contained SpringBoot based .jar file which contains your Black Box tests, 
which can be executed on a server that has no gradle installed.

```shell
cd /project/dir/build/libs/
java -jar groovy-springboot-bbtester-0.0.1-app.jar
```

The result (notice successful test executions and the single failure) looks like this

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.4.RELEASE)

2017-07-16 13:20:40.361  INFO 13976 --- [           main] com.github.fnunezkanut.bb.Start          : Starting Start on workstation with PID 13976 (C:\projects\groovy-springboot-bbtester\build\libs\groovy-springboot-bbtester-0.0.1-app.jar started by admin in C:\projects\groovy-springboot-bbtester\build\libs)
2017-07-16 13:20:40.365  INFO 13976 --- [           main] com.github.fnunezkanut.bb.Start          : No active profile set, falling back to default profiles: default
2017-07-16 13:20:40.432  INFO 13976 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@47f37ef1: startup date [Sun Jul 16 13:20:40 BST 2017]; root of context hierarchy
2017-07-16 13:20:41.050  INFO 13976 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-07-16 13:20:41.064  INFO 13976 --- [           main] com.github.fnunezkanut.bb.Start          : Started Start in 1.045 seconds (JVM running for 1.765)
2017-07-16 13:20:41.088  INFO 13976 --- [           main] com.github.fnunezkanut.bb.Start          : Starting blackbox tests...
2017-07-16 13:20:41.119  INFO 13976 --- [           main] com.github.fnunezkanut.bb.Start          : ###BEGIN BBTEST: myTest###
2017-07-16 13:20:41.170  INFO 13976 --- [           main] com.github.fnunezkanut.bb.Start          : ###OK BBTEST: myTest###
2017-07-16 13:20:41.170  INFO 13976 --- [           main] com.github.fnunezkanut.bb.Start          : ###BEGIN BBTEST: myTest2###
2017-07-16 13:20:41.172  WARN 13976 --- [           main] com.github.fnunezkanut.bb.tests.MyTest2  : test
2017-07-16 13:20:41.173 ERROR 13976 --- [           main] com.github.fnunezkanut.bb.Start          : ~~~ERR BBTEST: myTest2~~~
shouldFail(com.github.fnunezkanut.bb.tests.MyTest2): expected:<true> but was:<false>
java.lang.AssertionError: expected:<true> but was:<false>
        at org.junit.Assert.fail(Assert.java:88)
        <snip long trace>

2017-07-16 13:20:41.191  INFO 13976 --- [       Thread-2] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@47f37ef1: startup date [Sun Jul 16 13:20:40 BST 2017]; root of context hierarchy
2017-07-16 13:20:41.193  INFO 13976 --- [       Thread-2] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown

```

## Why would I find this project useful?

This project is a starting point :)
You might want to black box test a HTTP(S) REST API microservice on a machine that has no gradle installed, 
your tests can interact then with this microservice using a http client library (OkHTTP3 recommended) and check results.

I recommend reading [this excellent article on microservice testing from Martin Fowler](https://martinfowler.com/articles/microservice-testing/)
