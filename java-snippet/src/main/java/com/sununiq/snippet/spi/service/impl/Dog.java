package com.sununiq.snippet.spi.service.impl;

import com.sununiq.snippet.spi.service.Animal;

/**
 * @program: java-snippet
 *
 * @description: dog
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 13:15
 **/
public class Dog implements Animal{
    @Override
    public void eat() {
        System.out.println("Dog eating...");
    }
}
