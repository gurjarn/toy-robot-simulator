package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandEnum;
import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandExecutor {

    /**
     * This method is used to parse the command to validate the command input.
     *
     * @param command: command to parse
     * @return CommandEnum: Enum for the given command
     * @throws CommandException: If no valid command is found. The message is a 'Invalid command'
     */
    private CommandEnum parseCommand(String command) throws CommandException {
        if(command != null && !command.isEmpty()){
            try {
                //valid command and making it case insensitive
                return CommandEnum.valueOf(command.trim().toUpperCase());
            }catch(IllegalArgumentException il){
                //invalid command
                throw new CommandException(String.format("Invalid command: %s",command));
            }

        }
        //by default invalid command
        throw new CommandException("Invalid command");
    }

    /**
     * This method will execute a command
     *
     * @param context: Context on which this set of commands will be applied
     * @param command: a command to execute
     * @return StatusContext: Updated status context after applying command
     * @throws CommandException : If no valid command is found. The message is 'Invalid command'
     */
    public StatusContext executeCommand(StatusContext context, String command, List<String> parameters) throws CommandException {

        CommandEnum commandToExecute = parseCommand(command);
        ICommand commandInstance = commandToExecute.getInstance();
        if (commandInstance instanceof IParameterized) {
            //parse parameters
            List<String> commandArguments = parameters.stream().filter(e -> e != null && !e.isEmpty()).map(String::trim).collect(Collectors.toList());
            ((IParameterized) commandInstance).accept(commandArguments);
        }
        context = commandInstance.apply(context);

        return context;
    }

    /**
     * This method is extension of single command execution where it will accept single string containing command and it's parameters
     *
     * @param context: Context on which this set of commands will be applied
     * @param commandLine: Command line with it's parameters. e.g. PLACE 1,2,NORTH
     * @return StatusContext: Updated status context after applying command
     * @throws CommandException: If the command is invalid
     */
    public StatusContext executeCommand(StatusContext context, String commandLine) throws CommandException{

        if(commandLine != null){
            //e.g. PLACE 1,2,NORTH
            String[] commandSplit = commandLine.trim().split("[ ]+");
            // first parameter is a command so copy range index starts from 1
            List<String> commandParameters = Arrays.asList(Arrays.copyOfRange(commandSplit,1,commandSplit.length));
            context = executeCommand(context,commandSplit[0],commandParameters);
        }
        return context;
    }
}
