package com.example.robot.system.data;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;

public interface IApplicationUserInputHandler {

    /**
     * Implement this method for any User input handler for the program
     * @param args: Program arguments
     * @param context: Status context
     * @return StatusContext: Updated status context
     * @throws CommandException
     */
    StatusContext handleInput(String[] args, StatusContext context) throws ApplicationInputException,CommandException;
}
