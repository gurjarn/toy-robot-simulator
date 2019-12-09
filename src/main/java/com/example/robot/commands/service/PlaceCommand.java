package com.example.robot.commands.service;

import static com.example.robot.commands.data.Constants.*;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.DirectionEnum;
import com.example.robot.commands.data.StatusContext;

public class PlaceCommand {

    private int placeX;
    private int placeY;
    private DirectionEnum placeDirection;

    public PlaceCommand(int placeX, int placeY, DirectionEnum placeDirection){
        this.placeX = placeX;
        this.placeY = placeY;
        this.placeDirection = placeDirection;
    }

    /**
     * This method will apply current command parameters on the given context
     * @param context: Context on which this command will be applied
     * @return StatusContext: Updated status context
     * @throws CommandException: If invalid parameters are found. The message is 'Invalid command'
     */
    public StatusContext apply(StatusContext context) throws CommandException {

        //Check if the parameters are valid
        if(this.placeX >= MIN_X_MOVEMENT
                && this.placeX <= MAX_X_MOVEMENT
                && this.placeY >= MIN_Y_MOVEMENT
                && this.placeY <= MAX_Y_MOVEMENT){

            //update current context of the robot
            context.setPositionX(this.placeX);
            context.setPositionY(this.placeY);
            context.setDirection(this.placeDirection);

            return context;
        }else{
            //invalid parameter
            throw new CommandException("Invalid command");
        }
    }
}
