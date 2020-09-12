package com.hae.juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestAtomic {
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private AtomicReference<Person> atomicReference = new AtomicReference<Person>();
    private AtomicStampedReference<Person> atomicStampedReference;

    @Test
    public void testAtomic() {
        atomicInteger.compareAndSet(1, 2);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testAtomicReference() {
        Person person = new Person(1);
        Person person1 = new Person(2);
        Person person2 = new Person(3);
        atomicReference.set(person);
        atomicReference.compareAndSet(person, person1);
        atomicReference.compareAndSet(person1, person);
        System.out.println(atomicReference.compareAndSet(person, person2));
        System.out.println(atomicReference.get().getAge());
    }

    @Test
    public void testAtomicStampedReference() {
        int i = 0;
        Person person = new Person(1);
        Person person1 = new Person(2);
        Person person2 = new Person(3);
        atomicStampedReference = new AtomicStampedReference<Person>(person, i);
        boolean b = atomicStampedReference.compareAndSet(person, person1, 0, ++i);
        System.out.println(b);
        atomicStampedReference.compareAndSet(person1, person ,1, ++i);
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(atomicStampedReference.compareAndSet(person, person2 ,1, ++i));
        System.out.println(atomicStampedReference.getStamp());

    }

    public class Person {
        private Integer age;

        public Person(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
 }

