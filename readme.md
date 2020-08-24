**1. springcloud-api-commons**: 公共模块api

**2. springcloud-eureka-server7001**: eureka服务注册中心（通过指定配置文件来启动集群）

_下面2个子模块均是注册到eureka server：_

**3. springcloud-consumer-order80**: 模拟服务消费者

**4. springcloud-provider-payment8001**: 模拟服务生产者（通过指定配置文件来启动集群），使用在启动类使用
@EnableDiscoveryClient，以及在controller中注入DiscoveryClient可以获取注册中心的所有注册信息以及每一个服务具体有的实例信息

_下面2个子模块均是注册到zookeeper server：_

**5. springcloud-consumer-order80-zookeeper**: 模拟消费者，通过RestTemplate调用生产者

**6. springcloud-provider-payment8100-zookeeper**: 模拟生产者

下面2个子模块均是注册到consul server:

**7. springcloud-consumer-order80-consul**: 模拟消费者，通过RestTemplate调用生产者

**8. springcloud-provider-payment8100-consul**: 模拟生产者

**9. springcloud-consumer-order80-openfeign**: 使用openfeign进行服务调用，模拟消费者

**10. springcloud-provider-payment8001-eureka-hystrix**: 服务提供方自身使用hystrix进行服务降级（一般使用在消费方）

**11. springcloud-consumer-order80-eureka-hystrix**: 服务消费方使用hystrix进行服务降级（使用@HystrixCommand（不推荐）
或者整合OpenFeign的FeignClient的fallback属性使用（推荐）），注意@RequestMapping如果要用在FeignClient上的配置

**12. springcloud-consumer-hystrix-dashboard9001**: hystrix仪表盘进行服务监控，本案例通过访问：
先访问http://localhost:9001/hystrix  再粘贴http://localhost:8001/actuator/hystrix.stream监控单个节点（或者8002节点）

**13. springcloud-consumer-hystrix-turbine9500**: turbine收集eureka上的集群信息，通过dashboard展示出来，本案例中：
先访问http://localhost:9001/hystrix  再粘贴 http://192.168.159.1:9500/turbine.stream?cluster=PROVIDER-PAYMENT-SERVICE-HYSTRIX 集群监控这个微服务

**14. springcloud-gateway9600**: gateway用作网关：包括路由（route）、断言（predicate）、过滤器（filter）

以下两个是springcloud-config的服务端和客户端，其中客户端使用手动刷新配置：

**15. springcloud-config-server3001**：config server

**16. springcloud-config-client4001**: config client

以下两个是springcloud-config的服务端和客户端，其中服务端/客户端使用手动刷新一个配置，其它通过mq进行广播通知修改（一处修改处处修改，或者只修改指定的某几个）：

**17. springcloud-config-server5555-bus** ： config server

**18. springcloud-config-client5001-bus** : config client   模拟客户端1

**19. springcloud-config-client5002-bus** : config client   模拟客户端2

**20. springcloud-seata-order2001**: 模拟消费者，整合seata处理分布式事务问题，在TM的service方法上加@GlobalTransactional

**21. springcloud-seata-storage2002**: 模拟生产者，整合seata处理分布式事务问题


**注意：**
1. 在idea上运行集群（通过指定不同配置文件的集群），需要在配置启动类中，allow parallel run
2. 在微服务里一个service可以有1个或者多个instance（通过集群构建），就比如这里的eureka-server和payment分别有3个和2个instance，
要区分serviceId和instanceId，微服务之间，可以使用RestTemplate通过http://serviceId（不用加端口号，不是主机名，不要混淆）去访问微服务（集群）
3. eureka: eureka客户端往注册中心进行注册服务，对外暴露serviceId，eureka服务端保留serviceId与真实的服务的地址的映射关系，
消费者使用serviceId进行服务消费的时候，去eureka服务端查找对应的实例地址，然后消费服务。其中，每隔30s客户端往服务端发送心跳包，
服务端没有接收到，则把改服务从服务注册表移除。但是，同一端时间，很多来自客户端的心跳包都没有收到，则启动自我保护模式，
会认为是因为网络等原因导致没有接受到心跳包，则会保留那些服务，不会立马移除，但是90s后还没有收到，则移除。属于CAP的AP，A高可用。
自我保护可以在 eureka service端通过配置进行关闭：
4. eureka与zookeeper对比：zk建立的节点是临时的，服务一旦宕机，里面移除节点，而eureka有自我保护机制，不会立马移除
5. eureka/zookeeper/consul对比：
（1）eureka、zookeeper使用java编写的，consul使用go语言
（2）服务健康检查：eureka配置可支持，zookeeper、consul支持
（3）均可集成SpringCloud使用
（4）eureka设计思想为AP，而zookeeper、consul为CP。
CAP: C为一致性（Consistency），A为可用性（Availability），P为分区容忍（Partition tolerance），C和A存在矛盾，
C运行网络延迟但是结果一定要一致，A不允许超时，但要保证可用，运行返回旧数据或者假数据。P运行一个节点宕机后其他节点能继续正常使用
6. hystrix: 1.服务降级；2.服务熔断；3.服务限流
