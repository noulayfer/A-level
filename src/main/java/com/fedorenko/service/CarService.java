package com.fedorenko.service;

import com.fedorenko.model.*;
import com.fedorenko.repository.CarArrayRepository;
import com.fedorenko.util.RandomGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.fedorenko.model.CarType.*;


public class CarService {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final Random random = new Random();
    private final CarArrayRepository carArrayRepository;
    private static CarService carService;

    private CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public static CarService getInstance() {
        if (carService == null) {
            return new CarService(CarArrayRepository.getInstance());
        }
        return carService;
    }

    public static CarService getInstance(final CarArrayRepository repository) {
        if (carService == null) {
            carService = new CarService(repository);
        }
        return carService;
    }

    public Car create() {
        final Color color = getRandomColor();
        final Car car = new PassengerCar(color);
        carArrayRepository.save(car);
        return car;
    }

    public int create(final int count) {
        if (count <= 0) {
            return -1;
        }
        for (int i = 0; i < count; i++) {
            create();
        }
        return count;
    }

    public int create(final RandomGenerator randomGenerator) {
        if (randomGenerator == null) {
            return -1;
        }
        int count = randomGenerator.getRandomNumber();
        if (count <= 0) {
            return -1;
        }
        create(count);
        printAll();
        return count;
    }

    public Car createCar(CarType carType) {
        final Color color = getRandomColor();
        final Car car;
        if (carType.equals(CAR)) {
            car = new PassengerCar(color);
        } else if (carType.equals(TRUCK)) {
            car = new Truck(color);
        } else {
            car = null;
        }
        return car;
    }

    public Car[] getAll() {
        if (carArrayRepository.getAll() == null) {
            return new Car[0];
        }
        return carArrayRepository.getAll();
    }

    public Car find(final String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }

    public void delete(final String id) {
        if (id == null || id.isBlank()) {
            return;
        }
        carArrayRepository.delete(id);
    }

    public void insertCar(final int index, final Car car) {
        if (car == null || index < 0) {
            return;
        }
        carArrayRepository.insert(index, car);
    }

    public boolean carEquals(Car car1, Car car2) {
        if (car1 == null || car2 == null) {
            return false;
        }
        if (car1.getType() != car2.getType()) {
            return false;
        }
        if (car1.hashCode() != car2.hashCode()) {
            return false;
        }
        return car1.equals(car2);
    }

    public void printManufacturerAndCount(final Car car) {
        Optional.ofNullable(car)
                .ifPresent(x -> System.out.println(x.getCount() + " " + x.getType()));
    }

    public void printColor(final Car car) {
        final Optional<Car> optionalCar = Optional.ofNullable(car);
        System.out.println(optionalCar.orElse(new PassengerCar(getRandomColor())).getColor());
    }

    public void checkCount(final Car car) {
        Car car1 = Optional.ofNullable(car).filter(x -> x.getCount() > 10)
                .orElseThrow(() -> new UserInputException("count - 10 or less"));
        System.out.println(car1.getCount() + " " + car1.getType());
    }

    public void printEngineInfo(final Car car) {
        final Optional<Car> optionalCar = Optional.ofNullable(car);
        System.out.println(optionalCar.map(x -> x.getEngine()).
                orElseGet(() -> getRandomTypeCar().getEngine()).getPower());
    }

    public void printInfo(final Car car) {
        final Optional<Car> optionalCar = Optional.ofNullable(car);
        optionalCar.ifPresentOrElse(x -> System.out.println(x),
                () -> System.out.println(getRandomTypeCar()));
    }

    public Map<String, Integer> mappingManufacturerAndCount(final List<? extends Car> cars) {
        final Map<String, Integer> map = cars.stream()
                .collect(Collectors.toMap(Car::getManufacturer, Car::getCount,
                        (x, someElement) -> x));
        return map;
    }

    public Map<Integer, List<Car>> mappingPowerToCarList(final List<? extends Car> cars) {
        final List<Engine> list = cars.stream().map(Car::getEngine).collect(Collectors.toList());
        Map<Integer, List<Car>> map = new HashMap<>();
        for (Engine engine : list) {
            List<Car> carWithSameEngine = Arrays.stream(getAll())
                    .filter(x -> x.getEngine().equals(engine)).collect(Collectors.toList());
            map.put(engine.getPower(), carWithSameEngine);
        }
        return map;
    }

    private Car getRandomTypeCar() {
        final Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 0) {
            return new PassengerCar(getRandomColor(), new Engine());
        } else {
            return new Truck(getRandomColor(), new Engine());
        }
    }

    public String randomString() {
        String str = "";
        final Random random = new Random();
        int[] randomArr = random.ints(random.ints(1, 3, 10)
                .findAny().getAsInt(), 0, 25).toArray();
        for (int i : randomArr) {
            int j = random.nextInt();
            if (j % 2 == 0) {
                str += String.valueOf(alphabet.charAt(i)).toUpperCase();
            } else {
                str += alphabet.charAt(i);
            }
        }
        return str;
    }

    public static void check(final Car car) {
        if (car == null) {
            return;
        }
        if (car.getEngine() == null) {
            System.out.println("Engine is null");
            return;
        }
        if (car.getCount() > 0 && car.getEngine().getPower() > 200) {
            System.out.println("Ready to sell");
        } else if (car.getCount() > 0) {
            System.out.println("Power of engine is not enough");
        } else if (car.getEngine().getPower() > 200) {
            System.out.println("The amount of cars is 0");
        } else {
            System.out.println("Power of engine is not enough and amount of cars is 0");
        }
    }

    public void printAll() {
        final Car[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
        System.out.println();
    }

    public void findManufacturerByPrice(final List<? extends Car> cars, int price) {
        List<String> strings = cars.stream().filter(x -> x.getPrice() > price)
                .map(Car::getManufacturer).collect(Collectors.toList());
    }

    public int countSum(final List<? extends Car> cars) {
        int sum = cars.stream().map(Car::getPrice)
                .reduce(0, Integer::sum);
        return sum;
    }

    public Map<String, CarType> mapToMap(final List<? extends Car> cars) {
        LinkedHashMap<String, CarType> map = cars.stream()
                .sorted(Comparator.comparing(Car::getManufacturer))
                .distinct()
                .collect(Collectors.toMap(Car::getId, Car::getType, (x, y) -> y, LinkedHashMap::new));
        return map;
    }

    public DoubleSummaryStatistics statistic(final List<? extends Car> cars) {
        DoubleSummaryStatistics doubleSummaryStatistics = cars.stream()
                .collect(Collectors.summarizingDouble(Car::getPrice));
        return doubleSummaryStatistics;
    }

    public boolean priceCheck(final List<? extends Car> cars, int price) {
        boolean isAllMatch = cars.stream().allMatch(x -> x.getPrice() > price);
        return isAllMatch;
    }

    public Function<Map<String, Object>, Car> mapToObject = map -> {
        CarType type = (CarType) map.getOrDefault("type", CAR);
        if (type == CAR) {
            return createPassengerCar(map);
        } else {
            return createTruck(map);
        }
    };

    public Car xmlToObject() throws IOException {
        Map<String, Object> hashMap;
        String data = readFromFile("CarObject.xml");
        hashMap = dataToMapXML(data);
        return mapToObject.apply(hashMap);
    }

    public Car jsonToObject() throws IOException {
        Map<String, Object> hashMap;
        String data = readFromFile("CarObject.json");
        hashMap = dataToMapJson(data);
        return mapToObject.apply(hashMap);
    }

    private String readFromFile(final String fileName) throws IOException {
        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String str = "";
        try (final InputStream resourceAsStream = contextClassLoader.getResourceAsStream(fileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(resourceAsStream));) {
            String strAppend;
            while ((strAppend = bf.readLine()) != null) {
                str += strAppend + "\n";
            }
        }
        return str;
    }

    private Map<String, Object> dataToMapJson(final String data) {
        Map<String, Object> map = new HashMap<>();
        Pattern pattern = Pattern.compile("(.*?): (.*)[^ {][,\\n]");
        Matcher matcher = pattern.matcher(data);
        String result = "";
        while (matcher.find()) {
            result += matcher.group();
        }
        result = result.replaceAll("[, \"]", "").trim();
        String[] strings = result.split("\n");
        for (String s : strings) {
            List<String> tempList = Arrays.stream(s.split(":")).toList();
            if (tempList.size() == 1) {
                map.put(tempList.get(0).toLowerCase(), null);
            } else {
                map.put(tempList.get(0).toLowerCase(), tempList.get(1));
            }
        }
        return map;
    }

    private Map<String, Object> dataToMapXML(final String data) {
        Map<String, Object> map = new HashMap<>();
        Pattern pattern = Pattern.compile("<(.*?)>(.*)</?");
        Matcher matcher = pattern.matcher(data);
        String result = "";
        while (matcher.find()) {
            result += matcher.group();
        }
        result = result.replaceAll("[</]", " ").trim();
        for (String s : result.split(" ")) {
            if (s.length() == 0) {
                continue;
            }
            final List<String> list = Arrays.stream(s.split(">")).toList();
            if (list.size() == 1) {
                map.put(list.get(0).toLowerCase(), null);
            } else {
                map.put(list.get(0).toLowerCase(), list.get(1));
            }
        }
        return map;
    }

    private PassengerCar createPassengerCar(final Map<String, Object> map) {
        final PassengerCar passengerCar = (PassengerCar) createAbstractCar(CAR, map);
        final int passengerCount = (int) map.getOrDefault("passengerCount", 1);
        passengerCar.setPassengerCount(passengerCount);
        return passengerCar;
    }

    private Truck createTruck(final Map<String, Object> map) {
        final Truck truck = (Truck) createAbstractCar(TRUCK, map);
        final int loadCapacity = (int) map.getOrDefault("loadCapacity", 10);
        truck.setLoadCapacity(loadCapacity);
        return truck;
    }

    private Car createAbstractCar(final CarType type, final Map<String, Object> map) {
        final Car car;
        if (type == CAR) {
            car = new PassengerCar();
        } else {
            car = new Truck();
        }
        setFields(car, map);
        return car;
    }

    private Car setFields(final Car car, final Map<String, Object> map) {
        final String count = String.valueOf(map.getOrDefault("count", 10));
        car.setCount(Integer.parseInt(count));
        final String color = String.valueOf(map.getOrDefault("color", Color.BLACK));
        car.setColor(Color.valueOf(color));
        final String price = String.valueOf(map.getOrDefault("price", 2000));
        car.setPrice(Integer.parseInt(price));
        final Engine engine = (Engine) map.getOrDefault("engine", new Engine());
        car.setEngine(engine);
        final String manufacturer = (String) map.getOrDefault("manufacturer", "unknown");
        car.setManufacturer(manufacturer);
        final String id = (String) map.getOrDefault("id", "unknown");
        car.setId(id);
        return car;
    }


    public Map<Color, Integer> innerList(final List<List<Car>> cars) {
        Map<Color, Integer> map = cars.stream().flatMap(List::stream)
                .sorted(Comparator.comparing(Car::getColor))
                .peek(System.out::println)
                .filter(x -> x.getPrice() > 2500)
                .collect(Collectors.toMap(Car::getColor, Car::getCount));
        return map;
    }

    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }
}