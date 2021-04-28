package com.ymmihw.spring.mvc;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PassParametersController {
  @GetMapping("/showViewPage")
  public String passParametersWithModel(Model model) {
    Map<String, String> map = new HashMap<>();
    map.put("spring", "mvc");
    model.addAttribute("message", "Baeldung");
    model.mergeAttributes(map);
    return "viewPage";
  }

  @GetMapping("/printViewPage")
  public String passParametersWithModelMap(ModelMap modelMap) {
    modelMap.addAttribute("welcomeMessage", "welcome");
    modelMap.addAttribute("message", "Baeldung");
    
    Map<String, String> map = new HashMap<>();
    map.put("spring", "mvc");
    modelMap.addAllAttributes(map);
    
    return "viewPage";
  }

  @GetMapping("/goToViewPage")
  public ModelAndView passParametersWithModelAndView() {
    ModelAndView modelAndView = new ModelAndView("viewPage");
    modelAndView.addObject("message", "Baeldung");
    return modelAndView;
  }
}
