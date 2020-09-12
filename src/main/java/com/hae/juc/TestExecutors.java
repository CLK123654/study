package com.hae.juc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author huanghb
 * @date 2020/7/26
 */
public class TestExecutors {

  ExecutorService  service = new ThreadPoolExecutor(5,20,0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

  void Test1() {

  }

  @Test
  public void testCompletionService() throws Exception {
    CompletionService completionService =  new ExecutorCompletionService(service);
    for (int i = 0; i < 10; i++) {
      completionService.submit(new Callable() {
        @Override
        public Object call() throws Exception {
          String name = Thread.currentThread().getName();
          System.out.println(name);
          return name;
        }
      });
    }
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Future future = completionService.take();
      String result = (String)future.get();
      list.add(result);
    }
    System.out.println(list);
  }
}
