package com.example.robot.commands.service;

import static com.example.robot.commands.data.Constants.*;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.DirectionEnum;
import com.example.robot.commands.data.StatusContext;

import java.util.Arrays;

public class PlaceCommand implements ICommand, IParameterized{

    private int placeX;
    private int placeY;
    private DirectionEnum placeDirection;

    public PlaceCommand(){

    }


    /**
     * This method will apply current command parameters on the given context
     * @param context: Context on which this command will be applied
     * @return StatusContext: Updated status context
     * @throws CommandException: If invalid parameters are found. The message is 'Invalid command'
     */
    @Override
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
            context.setPlaced(true);

            return context;
        }else{
            //invalid parameter
            throw new CommandException(
                    String.format("Invalid parameters for PLACE command: [%d, %d, %s]",
                    this.placeX,
                    this.placeY,
                    this.placeDirection));

        }
    }

    @Override
    public void accept(String... parameters) throws CommandException {

        if(parameters != null && parameters.length == 3){
            try {
                this.placeX = Integer.parseInt(parameters[0]);
                this.placeY = Integer.parseInt(parameters[1]);
                this.placeDirection = DirectionEnum.valueOf(parameters[2]);
            }catch (IllegalArgumentException ne){
                throw new CommandException(String.format("Invalid parameters for PLACE command: %s", Arrays.asList(parameters)));
            }
        } else {
            throw new CommandException(String.format("Invalid parameters for PLACE command: %s",
                    parameters == null ? "" : Arrays.asList(parameters)));
        }
    }
}
