package com.example.robot;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.system.data.ApplicationInputException;
import com.example.robot.system.utils.ApplicationFileInputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    /**
     * User does valid selection
     */
    @Test
    public void testUserInputValidSelection()
    {
        Assertions.fail();
    }

    /**
     * User enters invalid text. e.g. PLACE x,x,NORTHEAST
     */
    @Test
    public void testUserInputInvalidText()
    {
        Assertions.fail();
    }

    /**
     * User does invalid selection
     */
    @Test
    public void testUserInputInvalidSelection()
    {
        Assertions.fail();
    }

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
}
