package com.ymcmp.tbrg.character

/**
  * Created by Plankp on 2016-03-21.
  */

@SerialVersionUID(1425L)
abstract class GenericSheet(race: Race.Value, paramAtk: () => Int, paramAc: Int, paramHp: Int,
                            hitMsgs: Array[String], killMsgs: Array[String], eocMsgs: String,
                            spells: Array[Spell]) extends Serializable {
  val stats = new Stats(race)
  var exp = 0
  var atk = paramAtk
  var ac = paramAc
  var hp = paramHp

  private val hitMsg = hitMsgs
  private val killMsg = killMsgs
  private val eocMsg = eocMsgs
  private val lvlSpells = spells
  private val atkMissMsg = Array(
    "Your attack missed.",
    "Your enemy was able to avoid your attack.",
    "Your enemy's armour protected him from your attack."
  )

  def hasSpells(): Boolean = lvlSpells.size > 0

  var proficiency = 0
  var lvl = 0

  updateProficiency
  updateLvl

  def updateProficiency: Int = {
    proficiency = lvl match {
      case 1 | 2 => 2
      case 3 | 4 => 3
      case 5 | 6 => 4
      case 7 | 8 => 5
      case _ => 6
    }
    return proficiency
  }

  def updateLvl: Int = {
    lvl = exp match {
      case it if 0 until 900 contains it => 1 // `until` does not contain the higher number
      case it if 900 until 6500 contains it => 2
      case it if 6500 until 23000 contains it => 3
      case it if 23000 until 48000 contains it => 4
      case it if 48000 until 85000 contains it => 5
      case it if 85000 until 120000 contains it => 6
      case it if 120000 until 165000 contains it => 7
      case it if 165000 until 225000 contains it => 8
      case _ => 9
    }
    return lvl
  }

  def msgOnHit(): String = hitMsg(Dice.d(hitMsg.size) - 1)

  def msgOnKill: String = killMsg(Dice.d(killMsg.size) - 1)

  def msgOnMiss: String = atkMissMsg(Dice.d(atkMissMsg.size) - 1)

  def msgPostConflict: String = eocMsg

  override def toString: String = "[" + stats.race + " " + this.getClass.getSimpleName + " = HP: " + hp +
    " AC: " + ac + "]"

  final def dcPro(): Int = Dice.d20 + proficiency

  final def dcDex(): Int = Dice.d20 + stats.dexterity

  final def dcStr(): Int = Dice.d20 + stats.strength

  final def dcCon(): Int = Dice.d20 + stats.constitution

  final def dcInt(): Int = Dice.d20 + stats.intelligence

  final def dcWis(): Int = Dice.d20 + stats.wisdom

  final def dcCha(): Int = Dice.d20 + stats.charisma
}
