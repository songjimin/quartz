package com.example.quartz_test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TargetServerInfo {

	private long connectionTargetId;

	private String gameCode;

	private String interval;

	private String targetServerAddress;

	private int targetServerPort;

	private Object jobClass;

}
