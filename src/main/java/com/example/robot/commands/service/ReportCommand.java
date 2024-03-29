package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;

public class ReportCommand implements ICommand{


    public ReportCommand(){

    }

    /**
     * This method will apply current command parameters on the given context
     * @param context: Context on which this command will be applied
     * @return StatusContext: Updated status context
     * @throws CommandException: If invalid parameters are found. The message is 'Invalid command'
     */
    @Override
    public StatusContext apply(StatusContext context) throws CommandException {

        if(context.isPlaced()) {
            //The report command is not required to change status context
            System.out.println(context.toString());
            return context;
        }else{
            throw new CommandException("Please use PLACE command to begin");
        }
    }
}
