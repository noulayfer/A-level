package com.fedorenko.action;

public class EndProgram implements Action {

    @Override
    public void execute() {
        System.out.println("Program is finished");
        System.exit(0);
    }
}
