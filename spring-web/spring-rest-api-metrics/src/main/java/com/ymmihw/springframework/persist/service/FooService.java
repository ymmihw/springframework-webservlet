package com.ymmihw.springframework.persist.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ymmihw.springframework.persist.model.Foo;

@Component
public class FooService implements IFooService {

  private static final Map<Long, Foo> map = new HashMap<Long, Foo>() {
    private static final long serialVersionUID = 1L;

    {
      {
        Foo foo = new Foo("1");
        foo.setId(1L);
        put(foo.getId(), foo);
      }
      {
        Foo foo = new Foo("2");
        foo.setId(2L);
        put(foo.getId(), foo);
      }
      {
        Foo foo = new Foo("3");
        foo.setId(3L);
        put(foo.getId(), foo);
      }
      {
        Foo foo = new Foo("4");
        foo.setId(4L);
        put(foo.getId(), foo);
      }
    }
  };

  @Override
  public List<Foo> findAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public Foo create(Foo resource) {
    map.put(resource.getId(), resource);
    return resource;
  }

  @Override
  public Foo findOne(long id) {
    return map.get(id);
  }

  @Override
  public void update(Foo resource) {
    map.put(resource.getId(), resource);
  }

  @Override
  public void deleteById(Long id) {
    map.remove(id);
  }

}
