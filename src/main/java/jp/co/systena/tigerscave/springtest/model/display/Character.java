package jp.co.systena.tigerscave.springtest.model.display;

public class Character {
  private int characterId;
  private String jobName;
  private int hp;
  private Job job;
  private String name;
  private String command;

  public Character(int id, String jobname, int hp, String name) {
    switch(jobname) {
      case "戦士":
        setJob(new Warrior());
//        setCommand(getJob().fight());
        break;
      case "魔法使い":
        setJob(new Wizard());
//        setCommand(getJob().fight());
        break;
      case "武闘家":
        setJob(new Monk());
        break;
    }
    setCharacterId(id);
    setJobName(jobname);
    setHp(hp);
    setName(name);
    if (getCommand() == null) {
      setCommand("未選択");
    }
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

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

}
