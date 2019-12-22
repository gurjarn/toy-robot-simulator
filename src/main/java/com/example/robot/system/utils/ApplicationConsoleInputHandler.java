package com.example.robot.system.utils;

import com.example.robot.commands.data.CommandEnum;
import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.commands.service.CommandExecutor;
import com.example.robot.commands.service.IParameterized;
import com.example.robot.system.data.ApplicationInputException;
import com.example.robot.system.data.IApplicationUserInputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is a console input handler and allows user to use application interactively
 */
public class ApplicationConsoleInputHandler implements IApplicationUserInputHandler {

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
     * @throws CommandException: Command execution exception
     * @throws ApplicationInputException: User input exception
     */
    @Override
    public StatusContext handleInput(String[] args, StatusContext context) throws ApplicationInputException,CommandException {

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
                List<String> userEnteredParams = new ArrayList<>();
                if(currentCommand.getInstance() instanceof IParameterized){
                    //Get the list of params to ask
                    List<String> commandAskParams = ((IParameterized) (currentCommand.getInstance())).askFor();
                    for (String askParam : commandAskParams) {
                        String parameter = getCommandParameters(sn);
                        userEnteredParams.add(parameter);
                    }
                }
                context = executor.executeCommand(context, command, userEnteredParams);
            }else {
                //inform user in case of invalid choice.
                System.out.println("Invalid choice. Please choose valid option");
            }
        }
        return context;
    }
}
