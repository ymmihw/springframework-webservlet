package com.ymmihw.springframework.web.metric;

public interface ICustomActuatorMetricService {

  void increaseCount(final int status);

  Object[][] getGraphData();
}
