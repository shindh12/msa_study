package com.msa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(MQEventListener2.FeedMsgProcessor.class)
public class MQEventListener2 {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FeedService feedService;
	
	@StreamListener(MQEventListener2.FeedMsgProcessor.INPUT)
	public void processEvent(String msg) {
		logger.info("Feed Info Consumed : " + msg);
		String[] msgArr = msg.split(":");
		
		logger.info("Feed Info userId : " + msgArr[0]);
		logger.info("Feed Info postId : " + msgArr[1]);
		feedService.addFeeds(Long.valueOf(msgArr[0]), Long.valueOf(msgArr[1]));
	}
	
	public interface FeedMsgProcessor {
		public static final String INPUT = "input2";
		@Input(INPUT) SubscribableChannel aInput();
	}
}
