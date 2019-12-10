package com.example.robot.commands.data;

import com.example.robot.commands.service.*;

/**
 * This enum defines the supported commands for this robot simulator
 */
public enum CommandEnum {
    PLACE(PlaceCommand.class),
    MOVE(MoveCommand.class),
    LEFT(LeftCommand.class),
    RIGHT(RightCommand.class),
    REPORT(ReportCommand.class);

    private ICommand INSTANCE;

    CommandEnum(Class<? extends ICommand> clazz){
        try {
            INSTANCE = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {

            //if it fails to instantiate command
            INSTANCE = null;
        }
    }

    public ICommand getInstance(){
        return this.INSTANCE;
    }
}
