package jp.co.systena.tigerscave.springtest.model.display;

public class Warrior extends Job{
  // 戦士クラス
  private String name;

  // コンストラクタ
  public Warrior(String name) {
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
