package role;

public class Player extends Role {
    private int maxBlood = 100;

    public int getMaxBlood() {
        return maxBlood;
    }

    public void addMaxBlood(int maxBlood) {
        this.maxBlood += maxBlood;
        this.setBlood((this.getBlood() + maxBlood));
    }

    public Player() {
        System.out.println("勇者宁好，欢迎来到地牢！\n地牢一共有十关，每次打败敌人可以选择奖励，第五关和第十关的BOSS奖励更多嗷！\n那么你叫什么名字：");
    }

    public int escape() {
        int ro = 0,r = 2;
        ro = (int) ((Math.random() * 10) + 1);
        if (ro <= 3) {
            System.out.println("逃跑成功！");r = 1;
        }
        else {
            System.out.println("逃跑失败...");r = 0;
        }
        return r;
    }

}
