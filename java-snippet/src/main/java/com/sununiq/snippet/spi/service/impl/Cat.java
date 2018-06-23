package com.sununiq.snippet.spi.service.impl;

import com.sununiq.snippet.spi.service.Animal;

/**
 * @program: java-snippet
 *
 * @description: cat
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 13:15
 **/
public class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("Cat eating...");
    }
}
