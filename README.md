# test-spring-boot-redis

[![TravisCI Status][travis-image]][travis-url]
[![Codecov Status][codecov-image]][codecov-url]

Proyecto base para micro servicio de redis

## Build project with maven

```
$ mvn clean verify spring-boot:run
```

## Build project with gradle

```
$ gradle clean build bootRun
```

## Swagger

```
http://localhost:9994/swagger-ui.html
```

## Rest services

```
POST
http://localhost:9994/service/redis/message

{
   "messageId": "messageId",
   "description": "description",
   "groupId": "groupId",
}
```

```
GET
http://localhost:9994/service/redis/message/{messageId}
```

```
GET
http://localhost:9994/service/redis/messages
```

[travis-image]: https://travis-ci.org/yadickson/test-spring-boot-redis.svg?branch=master
[travis-url]: https://travis-ci.org/yadickson/test-spring-boot-redis

[codecov-image]: https://codecov.io/gh/yadickson/test-spring-boot-redis/branch/master/graph/badge.svg?branch=master
[codecov-url]: https://codecov.io/gh/yadickson/test-spring-boot-redis

