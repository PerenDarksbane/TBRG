package com.ymcmp.tbrg.character.enemies

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-21.
  */
class Weakling(r: Race.Value) extends GenericSheet(r, () => Dice.d(2), 4, 10,
  Array("Man, you got hit by a Weakling? You suck!"),
  Array("Man, you got killed by a Weakling? You suck even more!"),
  "I'm supposed to be dead. I guess not.") {
  addSpells(1, Spell("SELF DAMAGE", "Hit yourself, but then all skill points increase by 2",
    (self, _) => {
      self.hp -= self.atk()
      self.stats.map(_ + 2)
    }))
}
