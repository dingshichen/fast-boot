DeveloperConsumerServerA 和 DeveloperConsumerServerC 是同一个消费者组

DeveloperConsumerServerB 和 DeveloperConsumerServerD 是同一个消费者组

topic developer 创建了两个分区

测试结果

1、如果不指定分区、不指定key，则同一个消费者组里的两个消费实例“轮询”消费，每个分区只会被单个组里的一个消费实例消费，实现了负载均衡

2、如果分区数小于同一个消费者组里的消费实例数，那么超出的消费实例永远不会消费

3、如果投递消息时指定消息的分区或者key，则可以固定让每个消费者组里的一个消费实例来消费，实现了顺序消费

