package com.example.robot.system;

import com.example.robot.Application;
import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.system.data.ApplicationInputException;
import com.example.robot.system.utils.ApplicationFileInputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationFileInputHandlerTest {


    /**
     * user chooses file input option
     */
    @Test
    public void testUserInputFile() throws ApplicationInputException, CommandException {

        ApplicationFileInputHandler fileInputHandler = new ApplicationFileInputHandler();
        StatusContext context = new StatusContext();
        context = fileInputHandler.handleInput(new String[]{"--file", "src/test/resources/file_input_test1.txt"},context);

        Assertions.assertEquals("3,3,NORTH",context.toString());

    }

    /**
     * user chooses file input option. User has passed correct option but invalid file path
     */
    @Test
    public void testUserInputFileInvalid() throws CommandException {

        try {
            ApplicationFileInputHandler fileInputHandler = new ApplicationFileInputHandler();
            fileInputHandler.handleInput(new String[]{"--file", "invalid/file/path"}, new StatusContext());
            Assertions.fail();
        }catch(ApplicationInputException ae){

        }
    }

    /**
     * user chooses file input option. User has passed incorrect option
     */
    @Test
    public void testUserInputFileInvalidOption() throws ApplicationInputException, CommandException {

        ApplicationFileInputHandler fileInputHandler = new ApplicationFileInputHandler();
        StatusContext context = new StatusContext();
        context = fileInputHandler.handleInput(new String[]{"--xyz", "invalid/file/path"}, context);
        Assertions.assertFalse(context.isPlaced());

    }

    @Test
    public void testApplicationLaunch(){
        Application.main(new String[]{"-f","src/test/resources/file_input_test1.txt"});
    }
}
