# FM-FlyMessage 

一个高效的消息推送中间件，旨在提供简洁易学习扩展的IM即时通讯系统骨架

基于Spring Boot + Netty开发，使用Eureka作为注册中心来支持消息推送服务器的集群部署，使用Protobuf高效序列化方式来提高传输效率。

## 功能列表

- 群聊
- 私聊
- 命令操作
- 可靠连接 （客户端重连、服务端检测并剔除离线客户端）
- 可靠传输（消息重发）
- 消息幂等性（唯一key + 布隆过滤器）
- 消息有序性（会话时序ID，局部顺序）
- 代码分层解耦，扩展性强

## 技术栈

| 技术栈                            | 版本              | 使用                                                  |
|--------------------------------|-----------------|-----------------------------------------------------|
| Java                           | 17              | 基于Java开发                                            |
| Spring Boot                    | 3.3.5           | 所有模块皆基于Spring Boot搭建                                |
| Spring Cloud Netflix Eureka    | 2023.0.3        | 注册中心，服务发现组件，用于注册和发现push-server实例                    |
| Spring Boot Starter Data Redis | 3.3.5           | 提供 Redis 数据访问支持                                     |
| Jedis                          | Spring Boot自动配置 | Redis 客户端，用于与 Redis 服务器进行交互                         |
| MySQL                          | 8.2.0           | 持久化用户及消息数据                                          |
| MyBatis Plus                   | 3.5.7           | 基于 MyBatis 的增强工具，简化 MyBatis 的使用，提供更强大的 CRUD 操作和分页功能 |
| Netty                          | 4.1.114.Final   | 服务器和客户端底层使用Netty构建事件驱动型的异步高性能通信                     |
| Protobuf                       | 4.28.2          | Google 开源的高性能序列化协议，用于消息数据的序列化                       |
| OkHttp                         | 4.12.0          | 远程调用模块使用其作为HTTP客户端，构建和发送请求                          |
| Caffeine                       | 3.1.8           | 使用Caffeine作为高性能底层缓存，现用于router中缓存push-server和IP的映射   |
| Knife4j                        | 4.4.0           | 基于 Swagger 的增强版 API 文档工具，提供更丰富的文档展示和交互功能            |






