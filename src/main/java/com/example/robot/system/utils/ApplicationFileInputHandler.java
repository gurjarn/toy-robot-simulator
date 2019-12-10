package com.example.robot.system.utils;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.commands.service.CommandExecutor;
import com.example.robot.system.data.ApplicationInputException;
import com.example.robot.system.data.IApplicationUserInputHandler;
import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class allows to pass commands through file option
 */
public class ApplicationFileInputHandler implements IApplicationUserInputHandler {


    /**
     * This mehtod will implement file processing
     * @param args: Program arguments
     * @param context: Status context
     * @return StatusContext: Updated status context
     * @throws ApplicationInputException
     * @throws CommandException
     */
    @Override
    public StatusContext handleInput(String[] args, StatusContext context) throws ApplicationInputException, CommandException {

        Options options = new Options();

        // setting up file option
        Option input = new Option("f", "file", true, "input file path");
        input.setRequired(false);
        options.addOption(input);

        //setting up help option
        Option help = new Option("h", "help", false, "help on using application");
        help.setRequired(false);
        options.addOption(help);

        //no option input
        Option none = new Option("", "No parameters to run with interactive input");
        none.setRequired(false);
        options.addOption(none);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;

        try {

            cmd = parser.parse(options, args);

            //if user has provided file input option
            if(cmd.hasOption("file")) {
                String inputFilePath = cmd.getOptionValue("file");

                //read through the file and process each command
                try(BufferedReader br = Files.newBufferedReader (Paths.get(inputFilePath))) {
                    String line;
                    CommandExecutor executor = new CommandExecutor();
                    while ((line = br.readLine()) != null){
                        if(!(line = line.trim()).isEmpty()
                                && !line.startsWith("#")){
                            context = executor.executeCommands(context,line);
                        }
                    }
                } catch (IOException e) {
                    //if it fails to read the file
                    throw new ApplicationInputException(String.format("Failed to process input file %s. Message: %s",inputFilePath,e.getMessage()));
                }
            }else if(cmd.getOptions().length > 0){
                //if some other option is provided. Notify correct usage
                formatter.printHelp("toy-robot-simulator", options);
            }
        } catch (ParseException e) {
            //if it fails to parse the option
            formatter.printHelp("toy-robot-simulator", options);
        }
        //returning original context
        return context;
    }
}
