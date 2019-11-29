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
      id = mParty.getPartyList().size() +1;
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

  @RequestMapping(value = "/SelectCommand", method = RequestMethod.POST)
  public ModelAndView commandSelect(@ModelAttribute("selectcommand") String selectedCommand,@ModelAttribute("memberId") int selectCharaId, HttpSession session, ModelAndView mav) {
    mParty = (Party)session.getAttribute("partyList");
    List<Character> partyList = mParty.getPartyList();

    // 選ばれたコマンドの表示する文言をキャラクタにセット
    Character commandSelectChara = partyList.get(selectCharaId -1);
    switch (selectedCommand) {
      case "たたかう" :
        commandSelectChara.setCommand(commandSelectChara.getJob().fight());
        break;
      case "かいふく" :
        commandSelectChara.setCommand(commandSelectChara.getJob().heal());
        break;
    }

    // 全員のコマンド選択が終わっているか確認
    Party commandUnselectedCharacterList = new Party();
    for (Character chara : mParty.getPartyList()) {
      if (chara.getCommand() == "未選択") {
        commandUnselectedCharacterList.addPartyMember(chara);
      }
    }

    if (commandUnselectedCharacterList.getPartyList().size() != 0) {
      // コマンド選択が終わっていないキャラがいるため、未選択キャラだけ残したCommandViewを再表示
      mav.addObject("party", commandUnselectedCharacterList);
      mav.setViewName("CommandView");
    } else {
      // コマンド選択が終わっているようなので戦闘結果画面を表示
      mav.addObject("party", mParty);
      mav.setViewName("BattleView");
    }
    return mav;
  }

  @RequestMapping(value = "/Battle", method = RequestMethod.POST)
  public ModelAndView battleStart(@ModelAttribute("battle") String selectedCommand, HttpSession session, ModelAndView mav) {
    // TODO:ここのメソッドは戦闘画面ように直す予定
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
