package org.example.lesson1.hw;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Homework {

  private static class Department {
      private String name;
      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public Department(String name) {
          this.name = name;
      }

      @Override
      public String toString() {
          return "Department{" +
                  "name='" + name + '\'' +
                  '}';
      }
      // TODO: геттеры, сеттеры
  }

  private static class Person {
    private String name;
    private int age;
    private int salary;
    private Department depart;

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public int getAge() {
          return age;
      }

      public void setAge(int age) {
          this.age = age;
      }

      public int getSalary() {
          return salary;
      }

      public void setSalary(int salary) {
          this.salary = salary;
      }

      public Department getDepart() {
          return depart;
      }

      public void setDepart(Department depart) {
          this.depart = depart;
      }

      @Override
      public String toString() {
          return "Person{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  ", salary=" + salary +
                  ", depart=" + depart.getName() +
                  '}' + "\n";
      }
      // TODO: геттеры, сеттеры
  }

  /**
   * Найти самого молодого сотрудника
   */
  static Optional<Person> findMostYoungestPerson(List<Person> people) {
    // FIXME: ваша реализация здесь
      return people.stream()
              .min((o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()));

  }

  /**
   * Найти департамент, в котором работает сотрудник с самой большой зарплатой
   */
  static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
    // FIXME: ваша реализация здесь
      return people.stream()
              .max((o1, o2) -> Integer.compare(o1.getSalary(), o2.getSalary()))
              .map(s -> s.getDepart());


  }

  /**
   * Сгруппировать сотрудников по департаментам
   */
  static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
    // FIXME: ваша реализация здесь
      return people.stream()
              .collect(Collectors.groupingBy(Person::getDepart));
  }

  /**
   * Сгруппировать сотрудников по названиям департаментов
   */
  static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
    // FIXME: ваша реализация здесь
      return people.stream()
              .collect(Collectors.groupingBy((Person person) -> person.getDepart().getName()));
  }

  /**
   * В каждом департаменте найти самого старшего сотрудника
   */
  static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
    // FIXME: ваша реализация здесь
      return people.stream()
              .collect(Collectors.toMap((person) -> person.getDepart().getName(),
                      Function.identity(), (a, b) ->{
                  if (a.getAge() > b.getAge()) {
                      return a;
                  } else {
                      return b;
                  }
                      } ));
  }


  /**
   * *Найти сотрудников с минимальными зарплатами в своем отделе
   * (прим. можно реализовать в два запроса)
   */
  static List<Person> cheapPersonsInDepartment(List<Person> people) {
    // FIXME: ваша реализация здесь
        Map<String, Person> map = people.stream()
              .collect(Collectors.toMap((person) -> person.getDepart().getName(),
                      Function.identity(), (a, b) -> {
                          if (a.getSalary() < b.getSalary()) {
                              return a;
                          } else {
                              return b;
                          }
                      }
                  ));
      return new ArrayList<>(map.values());
  }

    private static <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }

    public static void main(String[] args) {
        List<String> names = List.of("Nikita", "Nikolai", "Ekaterina", "Polina", "Mark", "Andrei", "Natali");
        List<Department> dep = List.of(new Department("Dep 1"),
                new Department("Dep 2"),
                new Department("Dep 3"),
                new Department("Dep 4"));

        List<Person> people = new ArrayList<Person>();
        for (int i = 0; i < 15; i++) {
            Person person = new Person();
            person.setName(getRandom(names));
            person.setAge(ThreadLocalRandom.current().nextInt(22, 66));
            person.setSalary(ThreadLocalRandom.current().nextInt(10_000, 100_000));
            person.setDepart(getRandom(dep));

            people.add(person);
        }
        System.out.println(people + "\n");

        System.out.println(findMostYoungestPerson(people).get() + "\n");

        System.out.println(findMostExpensiveDepartment(people).get() + "\n");

        System.out.println(groupByDepartment(people) + "\n");

        System.out.println(groupByDepartmentName(people) + "\n");

        System.out.println(getDepartmentOldestPerson(people) + "\n");

        System.out.println(cheapPersonsInDepartment(people) + "\n");

        System.out.println(cheapPersonsInDepartment(people) + "\n");
    }

}
