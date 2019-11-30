package jp.co.systena.tigerscave.springtest.model.display;

public abstract class Monster {
  // 敵クラス

  private int hp;
  private String name;
  private String knockedDownMessage;

  public abstract void takeDamage(int damageQuantity);

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKnockedDownMessage() {
    return knockedDownMessage;
  }

  public void setKnockedDownMessage(String knockedDownMessage) {
    this.knockedDownMessage = knockedDownMessage;
  }

}
