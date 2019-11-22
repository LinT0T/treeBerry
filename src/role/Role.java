package role;

import java.util.Scanner;

public class Role {
    private String name;
    private int blood;
    private int attack;
    private int defend;
    private int speed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int attack(Role a,Role b) throws InterruptedException {
        Thread.sleep(500);
        if (a.attack <= b.defend) {        b.blood -= 1;
            System.out.println(a.getName() + "对" + b.getName() + "造成了" + 1 + "点伤害"+ '\n');
            Thread.sleep(500); }
       else{ b.blood -= a.attack - b.defend;
        System.out.println(a.getName() + "对" + b.getName() + "造成了" + (a.attack - b.defend) + "点伤害"+ '\n');
        Thread.sleep(500);}
        return b.blood;
    }

    public static int speed(Player player,Enemy enemy,int c,int d){
        int a = player.getSpeed(),b = enemy.getSpeed(),r = 0;
        if (a != b) {
            while (a <= 10 && b <= 10) {
                a += 1;
                b += 1;
            }
            if (a == 11) {enemy.setSpeed(b);player.setSpeed(c);r = 1;}
            else if (b == 11) {player.setSpeed(a);enemy.setSpeed(d);r = 2;}
        }
        else r = 3;
        return r;
    }


    public void reward(Player you,Enemy computer) {
       if ((you.getMaxBlood() - you.getBlood()) >= 50) you.setBlood((you.getBlood() + 50));
       else you.setBlood(you.getMaxBlood());
       int a,b,c=0;
       a = (computer.getAttack() / 5);
       b = (computer.getDefend() / 2);
       c = (computer.getSpeed());
        System.out.println("生命值回复了50\n选择你的奖励：\n" + "1.生命值+20\n" + "2.攻击力+" + a +
                "\n3.防御力+" + b + "\n4.敏捷+" + c);
        Scanner in = new Scanner(System.in);
        int inPut = in.nextInt();
        switch (inPut){
            case 1:
                you.addMaxBlood(20);
                break;
            case 2:
                you.setAttack((you.getAttack() + a));
                break;
            case 3:
                you.setDefend((you.getDefend() + b));
                break;
            case 4:
                System.out.println("敏捷" + you.getSpeed() + "+" + c);
                if ((you.getSpeed() + c) <= 10){
                you.setSpeed((you.getSpeed() + c));
                if (you.getSpeed() > 10){
                    you.setSpeed(10);
                    System.out.println("敏捷满了");}
                }
                break;
        }
    }
}
