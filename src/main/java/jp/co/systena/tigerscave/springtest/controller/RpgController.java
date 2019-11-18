package jp.co.systena.tigerscave.springtest.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/CharacterCreate")
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
    mav.setViewName("CommandView");

    return mav;
  }
}
