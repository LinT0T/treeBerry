package main;

import role.Enemy;
import role.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        Player you = new Player();
        you.setName(in.nextLine());
        Enemy computer = new Enemy();
        Control ctrl = new Control();
        ctrl.key(you,computer);
    }
}
