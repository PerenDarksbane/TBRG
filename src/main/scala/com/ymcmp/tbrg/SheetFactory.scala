package com.ymcmp.tbrg

import com.ymcmp.tbrg.character._
import com.ymcmp.tbrg.character.premade._

/**
  * Created by Plankp on 2016-03-21.
  */
object SheetFactory {

  object CharacterTypes extends Enumeration {
    val ELEMENTALIST = Value
    val NECROMANCER = Value
    val PRIEST = Value
    val WARLOCK = Value
    val PHASE_KNIGHT = Value
    val SCOUT = Value
    val BATTLEMAGE = Value
    val TEMPLAR = Value
  }

  def apply(t: CharacterTypes.Value, r: Race.Value): GenericSheet = t match {
    case CharacterTypes.ELEMENTALIST => new Elementalist(r)
    case CharacterTypes.NECROMANCER => new Necromancer(r)
    case CharacterTypes.PRIEST => new Priest(r)
    case CharacterTypes.WARLOCK => new Warlock(r)
    case CharacterTypes.PHASE_KNIGHT => new PhaseKnight(r)
    case CharacterTypes.SCOUT => new Scout(r)
    case CharacterTypes.BATTLEMAGE => new Battlemage(r)
    case CharacterTypes.TEMPLAR => new Templar(r)
  }
}
