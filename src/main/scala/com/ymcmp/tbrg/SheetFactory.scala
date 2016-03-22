package com.ymcmp.tbrg

import com.ymcmp.tbrg.character._
import com.ymcmp.tbrg.character.enemies.Weakling
import com.ymcmp.tbrg.character.heros._

/**
  * Created by Plankp on 2016-03-21.
  */
object SheetFactory {

  object HeroTypes extends Enumeration {
    val ELEMENTALIST = Value
    val NECROMANCER = Value
    val PRIEST = Value
    val WARLOCK = Value
    val PHASE_KNIGHT = Value
  }

  def makeHero(t: HeroTypes.Value, r: Race.Value): GenericSheet = {
    t match {
      case HeroTypes.ELEMENTALIST => new Elementalist(r)
      case HeroTypes.NECROMANCER => new Necromancer(r)
      case HeroTypes.PRIEST => new Priest(r)
      case HeroTypes.WARLOCK => new Warlock(r)
      case HeroTypes.PHASE_KNIGHT => new PhaseKnight(r)
    }
  }

  object EnemyTypes extends Enumeration {
    val WEAKLING = Value
  }

  def makeEnemy(t: EnemyTypes.Value, r: Race.Value): GenericSheet = {
    t match {
      case EnemyTypes.WEAKLING => new Weakling(r)
    }
  }
}
