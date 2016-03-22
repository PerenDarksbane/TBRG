package com.ymcmp.tbrg.character.enemies

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-21.
  */
class Weakling extends GenericSheet(Race.HALFLING, () => Dice.d(2), 4, 10,
  Array("Man, you got hit by a Weakling? You suck!"), Array("Man, you got killed by a Weakling? You suck even more!"),
  "I'm supposed to be dead. I guess not.") {

}
