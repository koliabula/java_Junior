package org.example.lesson2.anno.lib;

import org.example.lesson2.anno.lib.RandomDate;

import java.lang.reflect.Field;
import java.util.Date;

public class RandomAnnotationProcessor {

  public static void processAnnotation(Object obj) {
    // найти все поля класса, над которыми стоит аннотация @Random
    // вставить туда рандомное число в диапазоне [0, 100)

    java.util.Random random = new java.util.Random();
    Class<?> objClass = obj.getClass();
    for (Field field : objClass.getDeclaredFields()) {
      // obj.getClass().isAssignableFrom(AnnotationsMain.Person.class)

      if (field.isAnnotationPresent(Random.class) && field.getType().isAssignableFrom(int.class)) {
        Random annotation = field.getAnnotation(Random.class);
        int min = annotation.min();
        int max = annotation.max();


        try {
          field.setAccessible(true); // чтобы можно было изменять финальные поля
          field.set(obj, random.nextInt(min, max));
        } catch (IllegalAccessException e) {
          System.err.println("Не удалось вставить значение в поле: " + e.getMessage());
        }
      }

      if (field.isAnnotationPresent(RandomDate.class) && field.getType().isAssignableFrom(Date.class)) {
        RandomDate annotationRD = field.getAnnotation(RandomDate.class);
        long minDate = annotationRD.minDate();
        long maxDate = annotationRD.maxDate();
        if(minDate < maxDate) {
          try {
            field.setAccessible(true); // чтобы можно было изменять финальные поля
            Date date = new Date(random.nextLong(minDate, maxDate));
            field.set(obj, date);
          } catch (IllegalAccessException e) {
            System.err.println("Не удалось вставить значение в поле: " + e.getMessage());
          }
        }else
          System.out.println("min > max");
      }

    }

  }

}
