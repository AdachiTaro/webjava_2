package jp.co.systena.tigerscave.springtest.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.springtest.model.display.Character;
import jp.co.systena.tigerscave.springtest.model.display.Party;
import jp.co.systena.tigerscave.springtest.model.form.CharacterCreateForm;

@Controller
public class RpgController {

  Party mParty = new Party();

  @Autowired
  HttpSession session;

  @RequestMapping("/CharacterCreate")
  public ModelAndView showCharacterCreate() {
    ModelAndView mav = new ModelAndView();
    mav.addObject("CharacterForm", new CharacterCreateForm());
    mav.setViewName("CharacterCreateView");
    return mav;
  }

  @RequestMapping(value = "/CreateCompleted", method = RequestMethod.POST)
  public ModelAndView characterCreate(HttpSession session, ModelAndView mav, CharacterCreateForm characterCreateForm) {
    int id = 1;
    if (session.getAttribute("partyList") != null) {
      mParty = (Party) session.getAttribute("partyList");
      id = mParty.getPartyList().size();
    }
    final int DEFAULT_HP = 100;
    Character createdCharacter = new Character(id,characterCreateForm.getJob(),DEFAULT_HP,characterCreateForm.getName());

    mParty.addPartyMember(createdCharacter);

    session.setAttribute("partyList", mParty);

    mav.addObject("party", mParty);

    // テンプレート名を設定
    mav.setViewName("CharacterCreatedView");

    return mav;
  }

  @RequestMapping(value = "/Command", method = RequestMethod.POST)
  public ModelAndView commandSelect(HttpSession session, ModelAndView mav) {
    mParty = (Party)session.getAttribute("partyList");



    mav.addObject("party", mParty);
    mav.setViewName("CommandView");
    return mav;
  }

  @RequestMapping(value = "/Battle", method = RequestMethod.POST)
  public ModelAndView battleStart(@ModelAttribute("battle") String selectedCommand, HttpSession session, ModelAndView mav) {
    mParty = (Party)session.getAttribute("partyList");
    List<Character> partyList = mParty.getPartyList();
    for (Character member:partyList) {
      String command = "";
      switch (selectedCommand) {
        case "たたかう" :
          command = member.getJob().fight();
          break;
        case "かいふく" :
          command = member.getJob().heal();
          break;
      }
      member.setCommand(command);
    }

    mav.addObject("party", mParty);
    mav.setViewName("BattleView");
    return mav;
  }
}
