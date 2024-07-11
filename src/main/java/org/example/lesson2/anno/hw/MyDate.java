package org.example.lesson2.anno.hw;

import org.example.lesson2.anno.lib.RandomDate;

import java.util.Date;

public class MyDate {
    @RandomDate
    Date rndDate;

    @Override
    public String toString() {
        return "MyDate{" +
                "rndDate=" + rndDate +
                '}';
    }
}
