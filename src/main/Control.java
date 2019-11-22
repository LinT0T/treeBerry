package main;

import role.Enemy;
import role.Player;
import role.Role;

import java.util.Random;
import java.util.Scanner;

public class Control {

    public void key(Player you, Enemy computer) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int c = 0;
        you.setBlood(100);
        you.setAttack(50);
        you.setDefend(20);
        you.setSpeed(2);
        int i = 0;
        for (i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            System.out.println("第" + i + "关");
            Thread.sleep(1000);
            if (i == 3) {
                System.out.println("挖藕，为了让你打过小BOSS，你不得不发现了一个宝箱，生命值翻倍");
                you.addMaxBlood(you.getMaxBlood());
            }
            if (i == 5) {
                System.out.println("你碰到了小boss！");
                Thread.sleep(1000);
                printInfor(you);
                Thread.sleep(1000);
                computer.setName("小BOSS");
                littleBoss(you,computer);
                fight(you,computer);

            }
            else if (i == 10){
                System.out.println("最终boss！");
                Thread.sleep(1000);
                printInfor(you);
                Thread.sleep(1000);
                finalBoss(you,computer);
                computer.setName("最终BOSS");
                fight(you,computer);
            }
                else {
                System.out.println("你碰到了小怪");
                Thread.sleep(500);
                printInfor(you);
                Thread.sleep(500);
                String s = "小怪" + String.valueOf(i);
                computer.setName(s);
                monster(you,computer);
                fight(you,computer);
            }
            System.out.println("");
                if (you.getBlood() <= 0 ) {
                    System.out.println("你挂了");
                    break;
                }
        }
    }

    private void printInfor(Role role){
        System.out.println(role.getName() + "的属性：");
        System.out.print("血量：" + role.getBlood());
        System.out.println("\t攻击力：" + role.getAttack());
        System.out.print("防御力：" + role.getDefend());
        System.out.println("\t敏捷：" + role.getSpeed()+ '\n');
    }
    private void littleBoss(Player you,Enemy computer){
        int ro = 0;
        ro = (int) (Math.random() * 5);
        computer.setBlood((you.getMaxBlood() + ro));
        ro = (int) (Math.random() * 15);
        computer.setAttack((you.getAttack() + ro));
        ro = (int) (Math.random() * 5);
        computer.setDefend((you.getDefend() + ro));
        ro = (int) (Math.random() * 2);
        computer.setSpeed((you.getSpeed() + ro));
        printInfor(computer);
    }
    private void finalBoss(Player you,Enemy computer){
        int ro = 0;
        ro = (int) (Math.random() * 15);
        computer.setBlood((you.getMaxBlood() + ro));
        ro = (int) (Math.random() * 25);
        computer.setAttack((you.getAttack() + ro));
        ro = (int) (Math.random() * 10);
        computer.setDefend((you.getDefend() + ro));
        computer.setSpeed(7);
        printInfor(computer);
    }
    private void monster(Player you,Enemy computer){
        int ro = 0;
        ro = (int) (Math.random() * 70);
        computer.setBlood(((you.getMaxBlood() / 4) + ro));
        ro = (int) (Math.random() * 25);
        computer.setAttack((you.getAttack() - ro));
        ro = (int) (Math.random() * 10);
        computer.setDefend((you.getDefend() - ro));
        ro = (int) (Math.random() * 4);
        computer.setSpeed((you.getSpeed() - ro));
        if (computer.getSpeed() <= 0) computer.setSpeed(1);
        printInfor(computer);
    }
    private int choose(int inPut,Player you,Enemy computer) throws InterruptedException {
        int i = 0,r = 2;
        switch (inPut) {
            case 1: {
                i = you.attack(you,computer);
                computer.setBlood(i);
                break;
            }
            case 2: {
                r = you.escape();
                break;
            }
        }
        return r;
    }

    private void fight(Player you,Enemy computer) throws InterruptedException {
        Thread.sleep(500);
        Scanner in = new Scanner(System.in);
        int inPut,front,lastSpeed1,lastSpeed2,r = 2,c= 0;
        lastSpeed1 = you.getSpeed();
        lastSpeed2 = computer.getSpeed();
        while (you.getBlood() > 0 && computer.getBlood() > 0) {
            Thread.sleep(500);
            front = Role.speed(you,computer,lastSpeed1,lastSpeed2);
            switch (front) {
                case 1:
                    Thread.sleep(500);
                    System.out.println("要做什么？1.攻击 2.逃跑");
                    inPut = in.nextInt();
                    r = choose(inPut, you, computer);
                    if (r == 1) break;
                    break;
                case 2:
                    if (computer.getBlood() > 0){
                        System.out.println("到敌人了");
                        c = computer.attack(computer,you);
                        you.setBlood(c);
                        System.out.println("你的血量:" + you.getBlood() + "\t敌人血量：" + computer.getBlood());
                    }
                    break;
                case 3:
                    System.out.println("要做什么？1.攻击 2.逃跑");
                    inPut = in.nextInt();
                    r = choose(inPut, you, computer);
                    if (r == 1) break;
                    Thread.sleep(500);
                    if (computer.getBlood() > 0){
                        System.out.println("到敌人了");
                        c = computer.attack(computer,you);
                        you.setBlood(c);
                        System.out.println("你的血量:" + you.getBlood() + "\t敌人血量：" + computer.getBlood());}
                    break;
            }
            if (computer.getBlood() <= 0) {
                System.out.println("你打败敌人了！");
                you.setSpeed(lastSpeed1);
                computer.setSpeed(lastSpeed2);
                you.reward(you,computer);
            }
            if (r == 1) break;
        }
    }
}
