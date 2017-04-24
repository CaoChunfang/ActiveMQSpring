package com.tgb.SpringActivemq.mq.producer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liang
 * @description   Topic生产者发送消息到Topic
 * 
 */

@Component("topicSender")
public class TopicSender {
	
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("destination2")
	private ActiveMQTopic mqTopic;
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName 队列名称
	 * @param message 消息内容
	 */
	public void send(String topicName,final String message){
		jmsTemplate.send(mqTopic, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

}
