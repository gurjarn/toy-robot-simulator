package com.example.robot.system.utils;

import com.example.robot.commands.data.CommandEnum;
import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.commands.service.CommandExecutor;

import java.util.Scanner;

/**
 * This class is a console input handler and allows user to use application interactively
 */
public class ApplicationConsoleInputHandler {

    public int getUserSelection(Scanner inputFrom){
        return inputFrom.nextInt();
    }

    public String getCommandParameters(Scanner inputFrom){
        return inputFrom.next();
    }

    /**
     * This method will allow user to use program interactively
     * @param args: Program arguments
     * @param context: Status context
     * @return StatusContext: Updated status context
     * @throws CommandException
     */
    public StatusContext handleInput(String[] args, StatusContext context) throws CommandException {

        Scanner sn = new Scanner(System.in);

        //loop the utility in loop until the user makes the choice to exit
        boolean circuitBreaker = true;

        CommandExecutor executor = new CommandExecutor();

        while (circuitBreaker) {
            //Print the options for the user to choose from
            System.out.println("*****Available Options*****");
            int exitOption = 0; //start of options
            for (CommandEnum value : CommandEnum.values()) {

                System.out.println(String.format("*. Press %d for %s command",value.ordinal(),value.name()));
                exitOption = value.ordinal() + 1;
            }

            System.out.println(String.format("*. Press %d to exit",exitOption));
            // Prompt the use to make a choice
            System.out.println("Enter your choice:");

            //Capture the user input in scanner object and store it in a variable
            int userInput = getUserSelection(sn);

            if(userInput == exitOption){
                circuitBreaker = false;
            }else if(userInput >= 0 && userInput < CommandEnum.values().length){
                CommandEnum currentCommand = CommandEnum.values()[userInput];
                String command = currentCommand.name();
                if(currentCommand == CommandEnum.PLACE){
                    System.out.println("Parameters for PLACE command e.g. 1,2,NORTH:" );
                    String parameters = getCommandParameters(sn);
                    command = String.format("%s %s",command,parameters);
                }
                context = executor.executeCommands(context, command);
            }else {
                //inform user in case of invalid choice.
                System.out.println("Invalid choice. Please choose valid option");
            }
        }
        return context;
    }
}