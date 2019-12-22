package com.example.robot.commands.service;

import static com.example.robot.commands.data.Constants.*;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.DirectionEnum;
import com.example.robot.commands.data.StatusContext;

import java.util.Arrays;
import java.util.List;

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
                    String.format("Invalid parameters for PLACE command: [%d,%d,%s]",
                    this.placeX,
                    this.placeY,
                    this.placeDirection));

        }
    }

    @Override
    public void accept(List<String> parameters) throws CommandException {

        String errorMessage = String.format("Invalid parameters for PLACE command: %s",
                parameters == null ? "" : parameters);

        if(parameters != null && parameters.size() == 1){
            try {
                //e.g. 1,2,NORTH
                String[] arguments = parameters.get(0).trim().split(",");
                if(arguments.length == 3) {
                    this.placeX = Integer.parseInt(arguments[0]);
                    this.placeY = Integer.parseInt(arguments[1]);
                    this.placeDirection = DirectionEnum.valueOf(arguments[2]);
                }else{
                    throw new CommandException(errorMessage);
                }
            }catch (IllegalArgumentException ne){
                throw new CommandException(errorMessage);
            }
        } else {
            throw new CommandException(errorMessage);
        }
    }

    @Override
    public List<String> askFor(){
        return Arrays.asList("Parameters for PLACE command e.g. 1,2,NORTH:");
    }
}
