package com.example.robot.commands.data;

/**
 * This exception will be thrown by each command to notify of any error
 */
public class CommandException extends Exception{

    public CommandException(String message) {
        super(message);
    }
}
