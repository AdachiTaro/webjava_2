package jp.co.systena.tigerscave.springtest.model.display;

public class Goblin extends Monster {
  private final int DEFAULT_HP = 100;

  public Goblin(String name) {
    setHp(DEFAULT_HP);
    setName(name);
    setKnockedDownMessage(name + "は倒れた！");
  }

  @Override
  public void takeDamage(int damageQuantity) {
    int remainingHP = getHp();

    remainingHP -= damageQuantity;

    if (remainingHP < 0) {
      remainingHP = 0;
    }

    setHp(remainingHP);
  }
}
