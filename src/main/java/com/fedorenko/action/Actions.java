package com.fedorenko.action;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Actions {
    CREATE("Create new cars", new CreateAction()),
    SHOW("Show all cars", new ShowAllAction()),
    FIND("Find the car", new FindAction()),
    EXIT("End the program", new EndProgram())
    ;
    private final String name;
    private final Action action;
    Actions(String name, Action action) {
        this.name = name;
        this.action = action;
    }
    public void execute() {
        action.execute();
    }
    public static String[] mapToNames() {
        String[] names = Arrays.stream(Actions.values())
                .map(x -> x.getName())
                .toArray(String[]::new);
        return names;
    }
}
