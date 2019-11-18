package jp.co.systena.tigerscave.springtest.model.display;

import java.util.ArrayList;
import java.util.List;

public class Party {
  private List<Character> partyList = new ArrayList<Character>();

  public void addPartyMember(Character createdCharacter) {
    partyList.add(createdCharacter);
  }

  public List<Character> getPartyList() {
    return partyList;
  }
}
