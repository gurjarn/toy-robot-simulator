package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.DirectionEnum;
import com.example.robot.commands.data.StatusContext;

public class RightCommand implements ICommand{

    public RightCommand(){
    }

    /**
     * This method will apply current command parameters on the given context
     * @param context: Context on which this command will be applied
     * @return StatusContext: Updated status context
     * @throws CommandException: If invalid parameters are found. The message is 'Invalid command'
     */
    @Override
    public StatusContext apply(StatusContext context) throws CommandException {

            if(context.isPlaced()){
                switch(context.getDirection()){
                    case NORTH:
                        context.setDirection(DirectionEnum.EAST);
                        break;
                    case EAST:
                        context.setDirection(DirectionEnum.SOUTH);
                        break;
                    case SOUTH:
                        context.setDirection(DirectionEnum.WEST);
                        break;
                    case WEST:
                        context.setDirection(DirectionEnum.NORTH);
                        break;
                }
                return context;
            } else{
               throw new CommandException("Please use PLACE command to begin");
            }
    }
}
