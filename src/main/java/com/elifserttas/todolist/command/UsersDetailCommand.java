package com.elifserttas.todolist.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UsersDetailCommand {

    private Long id;


    private String description;
}