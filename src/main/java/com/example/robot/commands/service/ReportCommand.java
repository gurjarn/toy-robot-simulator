package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.DirectionEnum;
import com.example.robot.commands.data.StatusContext;

import static com.example.robot.commands.data.Constants.*;

public class ReportCommand{


    public ReportCommand(){

    }

    /**
     * This method will apply current command parameters on the given context
     * @param context: Context on which this command will be applied
     * @return StatusContext: Updated status context
     * @throws CommandException: If invalid parameters are found. The message is 'Invalid command'
     */
    public StatusContext apply(StatusContext context) throws CommandException {

        //The report command is not required to change status context
        return context;
    }
}
