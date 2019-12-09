package com.example.robot.commands.utils;

import com.example.robot.commands.data.CommandEnum;
import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.DirectionEnum;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.commands.service.LeftCommand;
import com.example.robot.commands.service.PlaceCommand;
import com.example.robot.commands.service.ReportCommand;
import com.example.robot.commands.service.RightCommand;

public class CommandUtils {

    /**
     * This method is used to parse the command to validate the command input.
     *
     * @param command: command to parse
     * @return CommandEnum: Enum for the given command
     * @throws CommandException: If no valid command is found. The message is 'Invalid command'
     */
    public static CommandEnum parseCommand(String command) throws CommandException {
        if(command != null && !command.isEmpty()){
            // split params from command
            for (String name : command.trim().split("[ ]+")) {
                try {
                    //valid command
                    return CommandEnum.valueOf(name);
                }catch(IllegalArgumentException il){
                    //invalid command
                    throw new CommandException("Invalid command");
                }
            }
        }
        //by default invalid command
        throw new CommandException("Invalid command");
    }

    /**
     * This method will return a PLACE command from the given string. e.g. "PLACE 1,3,EAST"
     * @param command: PLACE command in a String format
     * @return PlaceCommand: Place command to apply
     * @throws CommandException: If no valid command is found. The message is 'Invalid command'
     */
    public static PlaceCommand parsePlaceCommand(String command) throws  CommandException{
        if(parseCommand(command) == CommandEnum.PLACE){
            //parse parameters
            String[] params = command.replace(CommandEnum.PLACE.name(),"").trim().split(",");

            if(params.length == 3){
                try {
                    return new PlaceCommand(Integer.parseInt(params[0]), Integer.parseInt(params[1]), DirectionEnum.valueOf(params[2]));
                }catch(IllegalArgumentException in){
                    throw new CommandException("Invalid command");
                }
            }
        }
        throw new CommandException("Invalid command");
    }

    /**
     * This method will return a REPORT command from the given string. e.g. "REPORT"
     * @param command: REPORT command in a String format
     * @return ReportCommand: Report command to apply
     * @throws CommandException: If no valid command is found. The message is 'Invalid command'
     */
    public static ReportCommand parseReportCommand(String command) throws  CommandException{
        if(parseCommand(command) == CommandEnum.REPORT){
            return new ReportCommand();
        }
        throw new CommandException("Invalid command");
    }

    /**
     * This method will return a RIGHT command from the given string. e.g. "RIGHT"
     * @param command: RIGHT command in a String format
     * @return ReportCommand: Report command to apply
     * @throws CommandException: If no valid command is found. The message is 'Invalid command'
     */
    public static RightCommand parseRightCommand(String command) throws  CommandException{
        if(parseCommand(command) == CommandEnum.RIGHT){
            return new RightCommand();
        }
        throw new CommandException("Invalid command");
    }

    /**
     * This method will return a LEFT command from the given string. e.g. "LEFT"
     * @param command: LEFT command in a String format
     * @return ReportCommand: Report command to apply
     * @throws CommandException: If no valid command is found. The message is 'Invalid command'
     */
    public static LeftCommand parseLeftCommand(String command) throws  CommandException{
        if(parseCommand(command) == CommandEnum.LEFT){
            return new LeftCommand();
        }
        throw new CommandException("Invalid command");
    }

    /**
     * This method will execute set of commands separated by new line character
     *
     * @param context: Context on which this set of commands will be applied
     * @param commandList: New line separated list of commands
     * @return StatusContext: Updated status context after applying commands
     * @throws CommandException: If no valid command is found. The message is 'Invalid command'
     */
    public static StatusContext executeCommands(StatusContext context, String commandList) throws CommandException {

        for (String commandInput : commandList.split("[\\r\\n]+")) {

            switch (CommandUtils.parseCommand(commandInput)) {
                case PLACE:
                    context = CommandUtils.parsePlaceCommand(commandInput).apply(context);
                    break;
                case REPORT:
                    context = CommandUtils.parseReportCommand(commandInput).apply(context);
                    break;
                case RIGHT:
                    context = CommandUtils.parseRightCommand(commandInput).apply(context);
                    break;
                case LEFT:
                    context = CommandUtils.parseLeftCommand(commandInput).apply(context);
                    break;
            }

        }
        return context;
    }
}
