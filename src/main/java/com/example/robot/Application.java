package com.example.robot;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.system.data.ApplicationInputException;
import com.example.robot.system.utils.ApplicationFileInputHandler;

/**
 * Application launch
 */
public class Application {

    /**
     * User can interact with the program through command line inputs or by supplying a file with set of input commands
     *
     * e.g
     * 1. Supply a file with commands
     *  [program_name] --file /file/path
     * 2. Without any arguments. This will display different options to user to choose from
     *
     * @param args: User entered parameters
     */
    public static void main( String[] args ) {

        StatusContext context = new StatusContext();

        //try with file input handle
        ApplicationFileInputHandler fileInputHandler = new ApplicationFileInputHandler();
        try {
            context = fileInputHandler.handleInput(args,context);

            //if context is initialized and executed
            if(!context.isPlaced()){
                //fall back to interactive user input
            }
        } catch (ApplicationInputException | CommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
