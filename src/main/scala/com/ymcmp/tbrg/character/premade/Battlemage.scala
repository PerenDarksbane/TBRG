package com.ymcmp.tbrg.character.premade

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-26.
  */
class Battlemage(name: String, gender: Gender.Value, stats: Stats) extends GenericSheet(name, gender,
  stats, () => Dice.d(10), 15, 0,
  Array(
    "Your mace causes your enemy;s armour to crumble",
    "You spray your enemy with poison",
    "You blast your enemy with arcane energy",
    "Your burning hands melt your enemy's armour",
    "The air extends your reach allowing you to hit your enemy with a fist of air"
  ),
  Array(
    "You break your opponent's skull with your mace",
    "Flames engulf your opponent",
    "Lightning is channeled through your mace into your enemy, frying him",
    "You form a tornado around your enemy, ripping him to shreds",
    "An earthquake takes your enemy into the ground"
  ), "Your thrust your mace into the ground. It opens up swallowing the remains of your foes") {

  hp = 8 + stats.constitution + 5 * (lvl - 1)

  addSpells(1, Spell("THUNDERWAVE", "Create a large thunder sound causing enemies to miss their next turn"))
  addSpells(3, Spell("COUNTERSPELL", "Prevents spells from affecting you until your next turn"))
  addSpells(5, Spell("STONESKIN", "Damage taken is halved for the rest of the round (Does not attack)"))
  addSpells(7, Spell("FIRESTORM", "Damage an enemy like an attack and prevent all enemies from doing anything on their next turn"))
  addSpells(9, Spell("EARTHQUAKE", "Traps enemies in the earth, allowing your next attack to hit no matter what",
    (hero, enemies) => enemies.foreach(_.hp -= hero.atk()))) // Each enemy, roll atk and hit'em!
}
