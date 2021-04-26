package com.ymmihw.springframework.web.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ymmihw.springframework.web.metric.IActuatorMetricService;
import com.ymmihw.springframework.web.metric.IMetricService;

@Controller
@RequestMapping(value = "/admin")
public class MetricController {

  @Autowired
  private IMetricService metricService;

  @Autowired
  private IActuatorMetricService actuatorMetricService;

  @RequestMapping(value = "/metric", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, ConcurrentHashMap<Integer, Integer>> getMetric() {
    return metricService.getFullMetric();
  }

  @RequestMapping(value = "/status-metric", method = RequestMethod.GET)
  @ResponseBody
  public Map<Integer, Integer> getStatusMetric() {
    return metricService.getStatusMetric();
  }

  @RequestMapping(value = "/metric-graph", method = RequestMethod.GET)
  @ResponseBody
  public Object[][] drawMetric() {
    final Object[][] result = metricService.getGraphData();
    for (int i = 1; i < result[0].length; i++) {
      result[0][i] = result[0][i].toString();
    }
    return result;
  }

  @RequestMapping(value = "/metric-graph2", method = RequestMethod.GET)
  @ResponseBody
  public Object[][] drawMetric2() {
    final Object[][] result = actuatorMetricService.getGraphData();
    for (int i = 1; i < result[0].length; i++) {
      result[0][i] = result[0][i].toString();
    }
    return result;
  }

}
