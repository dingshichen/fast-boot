package cn.dsc.kafka.springconsumer.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author dingshichen
 */
@Component
public class DefaultConsumerAwareRebalanceListener implements ConsumerAwareRebalanceListener {

    @Override
    public void onPartitionsRevokedBeforeCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        System.out.println("消费者平衡操作之前、消费者停止拉取之后被回调");
        //提交偏移量避免重复消费
        consumer.commitAsync();
    }

    @Override
    public void onPartitionsRevokedAfterCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        System.out.println("平衡之后，消费者拉取消息之前被回调");
        long committedOffset = -1;
        for (TopicPartition partition : partitions) {
            //获取该分区已消费的偏移量
            committedOffset = consumer.committed(partition).offset();
            //重置偏移量到上一次提交的偏移量的下一个
            consumer.seek(partition, committedOffset + 1);
        }
    }

    @Override
    public void onPartitionsAssigned(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        System.out.println("onPartitionsAssigned");
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("onPartitionsRevoked");
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("onPartitionsAssigned");
    }
}
