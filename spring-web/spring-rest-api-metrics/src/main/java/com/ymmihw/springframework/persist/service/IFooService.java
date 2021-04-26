package com.ymmihw.springframework.persist.service;

import java.util.List;
import com.ymmihw.springframework.persist.model.Foo;

public interface IFooService {

  List<Foo> findAll();


  Foo create(Foo resource);


  Foo findOne(long id);


  void update(Foo resource);


  void deleteById(Long id);

}
