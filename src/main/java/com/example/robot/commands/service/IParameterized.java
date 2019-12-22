package com.example.robot.commands.service;

import com.example.robot.commands.data.CommandException;

import java.util.List;

public interface IParameterized {
    void accept(List<String> parameters) throws CommandException;
    List<String> askFor();
}
