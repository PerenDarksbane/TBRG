package com.ymcmp.tbrg.character

/**
  * Created by Plankp on 2016-03-20.
  */
class Stats(r: Race.Value) {
  // Pre-initialize
  val race = r
  var dexterity: Int = Dice.d(6, 3)
  var strength: Int = Dice.d(6, 3)
  var constitution: Int = Dice.d(6, 3)
  var intelligence: Int = Dice.d(6, 3)
  var charisma: Int = Dice.d(6, 3)
  var wisdom: Int = Dice.d(6, 3)

  def convToPts(x: Int): Int = (x - 10) / 2

  // Ctor logic
  race match {
    case Race.DWARF =>
      strength += 2
      constitution += 2
    case Race.ELF =>
      dexterity += 2
      intelligence += 1
    case Race.DARK_ELF | Race.HALFLING =>
      dexterity += 2
      charisma += 1
    case Race.HUMAN =>
      charisma += 1
      wisdom += 1
      constitution += 1
    case Race.DRAGONKIN | Race.HALF_ORC =>
      strength += 2
      constitution += 1
    case Race.GNOME =>
      dexterity += 1
      intelligence += 2
    case Race.HALF_ELF =>
      intelligence += 1
      wisdom += 1
    case Race.DEMONKIN =>
      intelligence += 1
      charisma += 2
  }

  dexterity = convToPts(dexterity)
  strength = convToPts(strength)
  constitution = convToPts(constitution)
  intelligence = convToPts(intelligence)
  charisma = convToPts(charisma)
  wisdom = convToPts(wisdom)

  def map(act: (Int) => Int): Unit =
    Array(dexterity, strength, constitution, intelligence, charisma, wisdom) map act
}
