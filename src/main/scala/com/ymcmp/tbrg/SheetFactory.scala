package com.ymcmp.tbrg

import com.ymcmp.tbrg.character._
import com.ymcmp.tbrg.character.premade._

/**
  * Created by Plankp on 2016-03-21.
  */
object SheetFactory {

  object CharacterTypes extends Enumeration {
    val KI_MASTER = Value
    val DISCIPLE = Value
    val NINJA = Value
    val ARCANE_ARCHER = Value
    val BOWMAN = Value
    val FLINGER = Value
    val RANGER = Value
    val TRICKSTER = Value
    val DECEIVER = Value
    val BRUTE = Value
    val KNIGHT = Value
    val WEAPONMASTER = Value
    val ILLUSIONIST = Value
    val SUMMONER = Value
    val DRUID = Value
    val ELEMENTALIST = Value
    val NECROMANCER = Value
    val PRIEST = Value
    val WARLOCKE = Value
    val PHASE_KNIGHT = Value
    val SCOUT = Value
    val BATTLEMAGE = Value
    val TEMPLAR = Value
  }

  case class BasicData(ctype: CharacterTypes.Value, name: String, gender: Gender.Value, stats: Stats)

  def apply(basicData: BasicData): GenericSheet =
    apply(basicData.ctype, basicData.name, basicData.gender, basicData.stats)

  def apply(t: CharacterTypes.Value, name: String, gender: Gender.Value, stats: Stats): GenericSheet = t match {
    case CharacterTypes.ELEMENTALIST => new Elementalist(name, gender, stats)
    case CharacterTypes.NECROMANCER => new Necromancer(name, gender, stats)
    case CharacterTypes.PRIEST => new Priest(name, gender, stats)
    case CharacterTypes.WARLOCKE => new Warlocke(name, gender, stats)
    case CharacterTypes.PHASE_KNIGHT => new PhaseKnight(name, gender, stats)
    case CharacterTypes.SCOUT => new Scout(name, gender, stats)
    case CharacterTypes.BATTLEMAGE => new Battlemage(name, gender, stats)
    case CharacterTypes.TEMPLAR => new Templar(name, gender, stats)
    case CharacterTypes.ARCANE_ARCHER => new ArcaneArcher(name, gender, stats)
    case _ => ???
  }
}
