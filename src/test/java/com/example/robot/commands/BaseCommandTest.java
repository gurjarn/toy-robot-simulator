package com.example.robot.commands;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.commands.service.CommandExecutor;

/**
 * This is a base class for all command test classes. This class will execute multiple commands from test resources
 */
public class BaseCommandTest {

    protected CommandExecutor executor = new CommandExecutor();

    /**
     * This method will execute new line separated commands
     * @param context: Status context
     * @param commandList: new line separated command list
     * @return String: outcome of command execution
     */
    protected String executeCommand(StatusContext context, String commandList) {
        try {
            for (String commandInput : commandList.split("[\\r\\n]+")){
                context = executor.executeCommand(context,commandInput);
            }
            return context.toString();
        } catch (CommandException e) {
            return e.getMessage();
        }
    }
}
