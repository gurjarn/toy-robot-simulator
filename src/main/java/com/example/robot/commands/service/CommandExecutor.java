package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandEnum;
import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;

public class CommandExecutor {

    /**
     * This method is used to parse the command to validate the command input.
     *
     * @param command: command to parse
     * @return CommandEnum: Enum for the given command
     * @throws CommandException: If no valid command is found. The message is 'Invalid command'
     */
    private CommandEnum parseCommand(String command) throws CommandException {
        if(command != null && !command.isEmpty()){
            // split params from command
            for (String name : command.trim().split("[ ]+")) {
                try {
                    //valid command
                    return CommandEnum.valueOf(name);
                }catch(IllegalArgumentException il){
                    //invalid command
                    throw new CommandException(String.format("Invalid command: %s",command));
                }
            }
        }
        //by default invalid command
        throw new CommandException("Invalid command");
    }

    /**
     * This method will execute set of commands separated by new line character
     *
     * @param context: Context on which this set of commands will be applied
     * @param commandList: New line separated list of commands
     * @return StatusContext: Updated status context after applying commands
     * @throws CommandException : If no valid command is found. The message is 'Invalid command'
     */
    public StatusContext executeCommands(StatusContext context, String commandList) throws CommandException {

        for (String commandInput : commandList.split("[\\r\\n]+")) {
            CommandEnum commandToExecute = parseCommand(commandInput);
            ICommand command = commandToExecute.getInstance();
            if(command instanceof IParameterized){
                //parse parameters
                String[] params = commandInput.replace(commandToExecute.name(),"").trim().split(",");
                ((IParameterized)command).accept(params);
            }
            context = command.apply(context);
        }
        return context;
    }
}
