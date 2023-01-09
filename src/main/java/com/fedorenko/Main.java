package com.fedorenko;


import com.fedorenko.container.CarTree;
import com.fedorenko.model.*;
import com.fedorenko.service.CarService;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        CarService carService = CarService.getInstance();
        System.out.println(carService.xmlToObject());
        System.out.println(carService.jsonToObject());
    }
}

