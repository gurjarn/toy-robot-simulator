package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandException;
import com.example.robot.commands.data.StatusContext;

public interface IParameterized {
    void accept(String... parameters) throws CommandException;
}
