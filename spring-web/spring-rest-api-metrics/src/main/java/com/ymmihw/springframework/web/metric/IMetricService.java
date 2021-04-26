package com.ymmihw.springframework.web.metric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IMetricService {

  void increaseCount(final String request, final int status);

  Map<String, ConcurrentHashMap<Integer, Integer>> getFullMetric();

  Map<Integer, Integer> getStatusMetric();

  Object[][] getGraphData();
}
