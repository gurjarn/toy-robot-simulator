package com.example.robot.commands.data;

import lombok.Data;

import java.io.Serializable;

/**
 * This is context for the current status of the robot
 */
@Data
public class StatusContext implements Serializable {
    private int positionX;
    private int positionY;
    private DirectionEnum direction;

    @Override
    public String toString(){
        return String.format("%d,%d,%s",getPositionX(),getPositionY(),getDirection());
    }
}
