package com.jiuxian.mossrose.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.jiuxian.mossrose.job.StreamingJob;

public class SomeStreamingJob implements StreamingJob<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SomeStreamingJob.class);
	
	@Override
	public Streamer<String> streamer() {
		return new Streamer<String>() {

			private List<String> list = Lists.newArrayList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < list.size() - 1;
			}

			@Override
			public String next() {
				return list.get(index++);
			}
		};
	}

	@Override
	public Executor<String> executor() {
		return new Executor<String>() {

			@Override
			public void execute(String item) {
				LOGGER.info(Thread.currentThread() + " StreamingJob: " + item);
			}
		};
	}

}
