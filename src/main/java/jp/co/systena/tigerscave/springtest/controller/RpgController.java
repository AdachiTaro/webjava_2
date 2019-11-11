package jp.co.systena.tigerscave.springtest.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.springtest.model.display.Party;
import jp.co.systena.tigerscave.springtest.model.display.Warrior;
import jp.co.systena.tigerscave.springtest.model.display.Wizard;
import jp.co.systena.tigerscave.springtest.model.form.CharacterCreateForm;

@Controller
public class RpgController {

  Party mParty = new Party();

  @Autowired
  HttpSession session;

  @GetMapping("/CharacterCreate")
  public ModelAndView showCharacterCreate() {
    ModelAndView mav = new ModelAndView();
    mav.addObject("CharacterForm", new CharacterCreateForm());
    mav.setViewName("CharacterCreateView");
    return mav;
  }

  @RequestMapping(value = "/CreateCompleted", method = RequestMethod.POST)
  public ModelAndView characterCreate(HttpSession session, ModelAndView mav, CharacterCreateForm characterCreateForm) {
    if (characterCreateForm.getJob().equals("戦士")) {
      Warrior warrior = new Warrior(characterCreateForm.getName());
      mParty.addPartyMember(warrior);
    } else if (characterCreateForm.getJob().equals("魔法使い")) {
      Wizard wizard = new Wizard(characterCreateForm.getName());
      mParty.addPartyMember(wizard);
    }

    session.setAttribute("partyList", mParty);

    Party party = (Party) session.getAttribute("partyList");

    mav.addObject("party", party);

    // テンプレート名を設定
    mav.setViewName("cartView");

    return mav;
  }
}
