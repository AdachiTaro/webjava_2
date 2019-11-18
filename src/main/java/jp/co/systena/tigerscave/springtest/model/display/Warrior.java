package jp.co.systena.tigerscave.springtest.model.display;

public class Warrior extends Job{
  // 戦士クラス
  @Override
  public String fight() {
    return "剣で攻撃した！";
  }

}
