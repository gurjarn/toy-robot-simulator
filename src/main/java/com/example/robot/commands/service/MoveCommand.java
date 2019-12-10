package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;

import static com.example.robot.commands.data.Constants.*;

public class MoveCommand implements  ICommand{

    public MoveCommand(){
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
                int newPositionX = context.getPositionX();
                int newPositionY = context.getPositionY();

                switch(context.getDirection()){
                    case NORTH:
                        newPositionY += 1;
                        break;
                    case EAST:
                        newPositionX += 1;
                        break;
                    case SOUTH:
                        newPositionY -= 1;
                        break;
                    case WEST:
                        newPositionX -= 1;
                        break;
                }
                if(newPositionX >= MIN_X_MOVEMENT
                        && newPositionX <= MAX_X_MOVEMENT
                        && newPositionY >= MIN_Y_MOVEMENT
                        && newPositionY <= MAX_Y_MOVEMENT) {

                    //update current context of the robot
                    context.setPositionX(newPositionX);
                    context.setPositionY(newPositionY);
                }
                return context;
            } else{
               throw new CommandException("Please use PLACE command to begin");
            }
    }
}
