package jp.co.systena.tigerscave.springtest.model.display;

public class Wizard extends Job {
  // 魔法使いクラス
  private String name;

  public Wizard(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  void fight() {
    // TODO 自動生成されたメソッド・スタブ

  }

}
