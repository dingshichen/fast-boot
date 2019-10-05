package cn.dsc.rocketmqproducer.listen;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dingShiChen
 * @since 2019/7/28
 */
@Slf4j
@Service
public class FastTransactionListener implements TransactionListener {


	private static final Map<String, Integer> LOCAL_TRANS = new ConcurrentHashMap<>();

	/**
	 * 处理本地事务
	 * @param msg
	 * @param arg
	 * @return
	 */
	@Override
	public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
		String transactionId = msg.getTransactionId();
		log.info("FastTransactionListener executeLocalTransaction transactionId : {}", transactionId);
		//0:执行中，状态未知；1：本地事务执行成功；2：本地事务执行失败
		LOCAL_TRANS.put(transactionId, 0);
		try {
			//todo 处理本地事务，service
			Thread.sleep(10000);
			LOCAL_TRANS.put(transactionId, 1);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
			LOCAL_TRANS.put(transactionId, 2);
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}
		//提交事务
		return LocalTransactionState.COMMIT_MESSAGE;
	}

	/**
	 * 消息回查
	 * @param msg
	 * @return
	 */
	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt msg) {
		Integer state = LOCAL_TRANS.get(msg.getTransactionId());
		switch (state){
			case 0:
				return LocalTransactionState.UNKNOW;
			case 1:
				return LocalTransactionState.COMMIT_MESSAGE;
			case 2:
				return LocalTransactionState.ROLLBACK_MESSAGE;
		}
		return LocalTransactionState.UNKNOW;
	}
}
