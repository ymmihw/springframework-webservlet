package com.ymmihw.springframework.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.google.common.base.Preconditions;
import com.ymmihw.springframework.persist.model.Foo;
import com.ymmihw.springframework.persist.service.IFooService;
import com.ymmihw.springframework.web.util.RestPreconditions;

@Controller
@RequestMapping(value = "/foos")
public class FooController {
  @Autowired
  private IFooService service;

  public FooController() {
    super();
  }

  // API

  @RequestMapping(method = RequestMethod.GET, value = "/count")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public long count() {
    return 2l;
  }

  // read - one

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Foo findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
    final Foo resourceById = RestPreconditions.checkFound(service.findOne(id));

    return resourceById;
  }

  // read - all

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<Foo> findAll() {
    return service.findAll();
  }


  // write

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Foo create(@RequestBody final Foo resource, final HttpServletResponse response) {
    Preconditions.checkNotNull(resource);
    final Foo foo = service.create(resource);
    return foo;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public void update(@PathVariable("id") final Long id, @RequestBody final Foo resource) {
    Preconditions.checkNotNull(resource);
    RestPreconditions.checkFound(service.findOne(resource.getId()));
    service.update(resource);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") final Long id) {
    service.deleteById(id);
  }


}
