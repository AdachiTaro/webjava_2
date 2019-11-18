package jp.co.systena.tigerscave.springtest.model.display;

public class Character {
  private int characterId;
  private String jobName;
  private int hp;
  private Job job;
  private String name;

  public Character(int id, String jobname, int hp, String name) {
    switch(jobname) {
      case "戦士":
        setJob(new Warrior());
        break;
      case "魔法使い":
        setJob(new Wizard());
        break;
    }
    setCharacterId(id);
    setJobName(jobname);
    setHp(hp);
    setName(name);
  }

  public int getCharacterId() {
    return characterId;
  }

  public void setCharacterId(int characterId) {
    this.characterId = characterId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
