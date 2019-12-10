package com.example.robot.system;

import com.example.robot.Application;
import com.example.robot.commands.data.CommandEnum;
import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;
import com.example.robot.system.data.ApplicationInputException;
import com.example.robot.system.utils.ApplicationConsoleInputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationConsoleInputHandlerTest {


    @Spy
    private ApplicationConsoleInputHandler consoleInputHandler;


    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    /**
     * User does valid selection
     */
    @Test
    public void testUserInputValidSelection() throws CommandException, ApplicationInputException {

        //mocking user selection. Don't forget to keep last exit option
        Mockito.doReturn(CommandEnum.PLACE.ordinal(),
                        CommandEnum.MOVE.ordinal(),
                        CommandEnum.MOVE.ordinal(),
                        CommandEnum.LEFT.ordinal(),
                        CommandEnum.MOVE.ordinal(),
                        CommandEnum.REPORT.ordinal(),CommandEnum.values().length).when(consoleInputHandler).getUserSelection(Mockito.any());

        //Mocking user parameter input
        Mockito.doReturn("1,2,EAST").when(consoleInputHandler).getCommandParameters(Mockito.any());

        StatusContext context = new StatusContext();
        context = consoleInputHandler.handleInput(new String[]{},context);
        Assertions.assertEquals("3,3,NORTH",context.toString());
    }

    /**
     * User enters invalid text. e.g. PLACE x,x,NORTHEAST
     */
    @Test
    public void testUserInputInvalidText() throws ApplicationInputException
    {
        //mocking user selection. Don't forget to keep last exit option
        Mockito.doReturn(CommandEnum.PLACE.ordinal(),CommandEnum.values().length).when(consoleInputHandler).getUserSelection(Mockito.any());

        //Mocking parameters for the PLACE command
        Mockito.doReturn("1,2,WRONGPARAM").when(consoleInputHandler).getCommandParameters(Mockito.any());

        StatusContext context = new StatusContext();
        try {
            context = consoleInputHandler.handleInput(new String[]{},context);
            Assertions.fail();
        } catch (CommandException  e) {

        }

    }

    /**
     * User does invalid selection
     */
    @Test
    public void testUserInputInvalidSelection() throws CommandException, ApplicationInputException {

        //mocking user selection. Don't forget to keep last exit option
        Mockito.doReturn(7,CommandEnum.values().length).when(consoleInputHandler).getUserSelection(Mockito.any());

        StatusContext context = new StatusContext();
        context = consoleInputHandler.handleInput(new String[]{},context);
        Assertions.assertFalse(context.isPlaced());
    }



}
