package org.workPall;

import org.workPall.console.MainConsole;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainConsole mainConsole = new MainConsole();
        mainConsole.main(args);
    }
}
