package com.example.robot.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * This class is to test sequence of commands and assert the context
 * The test class is parameterized and takes its input from resource file src/test/resources/command_test.csv
 * It takes two values
 * "expected message", "new line separated list of commands"
 * Please cover list of commands in double quotes to treat it as a row in CSV parsing
 *
 */
public class CommandsTest {

    /**
     *
     * @param expected: expected message from the execution of commands. This is the first parameter
     * @param commands: list of commands to be executed. This is second parameter
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/command_test.csv")
    public void testOperation(String expected, String commands){
        Assertions.fail();
    }
}
