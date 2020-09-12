package com.hae.basis;


import org.junit.Test;

import java.util.*;

/**
 * 比较camparable ，camparator
 */
public class TestCompare {

    @Test
    public void testComparator() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(18, "zhang3"));
        list.add(new Student(16, "li4"));
        list.add(new Student(20, "wang5"));
        //使用Comparator进行排序
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println(student.getAge() + "," + student.getName());
        }
    }

    @Test
    public void testComparable() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("a"));
        list.add(new Person("g"));
        list.add(new Person("b"));
        Collections.sort(list);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Person person = (Person) iterator.next();
            System.out.println(person.getName());
        }
    }

    public class Person implements Comparable<Person>{
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return this.name.compareTo(o.getName());
        }
    }


    public class Student {
        Integer age;
        String name;

        public Student(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
