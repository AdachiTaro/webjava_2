package jp.co.systena.tigerscave.springtest.model.display;

import java.util.ArrayList;
import java.util.List;

public class Party {
  private List<Job> partyList = new ArrayList<Job>();

  public void addPartyMember(Job j) {
    partyList.add(j);
  }

  public List<Job> getPartyList() {
    return partyList;
  }
}
