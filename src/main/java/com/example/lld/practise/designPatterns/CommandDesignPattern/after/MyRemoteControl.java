package com.example.lld.practise.designPatterns.CommandDesignPattern.after;

import java.util.Stack;

public class MyRemoteControl {
    Stack<ICommand> commandHistory = new Stack<>();
    ICommand command;

    public MyRemoteControl() {
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
        commandHistory.add(command);
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            ICommand lastExecutedCommand = commandHistory.pop();
            lastExecutedCommand.undo();

            // shouldn't we need to set command to lastExecutedCommand?
        }
    }
}
