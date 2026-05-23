package com.crio.xlido.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(String commandName, ICommand command){
        commands.putIfAbsent(commandName, command);
    }
    public ICommand getCommand(String commandName){
        return commands.get(commandName);
    }
    private List<String> parse(String tokens){
        return Arrays.asList(tokens.split(","));
    }
    public void invoke(String input){
        List<String> tokens = parse(input);
        ICommand command = getCommand(tokens.get(0));
        if(command == null){
            throw new RuntimeException("Invalid Command");
        }
        command.invoke(tokens);
        
    }
    
}
