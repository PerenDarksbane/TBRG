package com.ymcmp.tbrg.character.premade

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-26.
  */
class Templar(name: String, gender: Gender.Value, stats: Stats) extends GenericSheet(name, gender,
  stats, () => Dice.d8 + 4, 18, 0,
  Array(
    "You smash your enemy's face with your shield",
    "Your longsword cuts deep",
    "You blast the enemy with divine power",
    "You thrust your blade, stabbing your enemy",
    "You channel energy through your blade searing your enemy's flesh"
  ),
  Array(
    "Your sword impales your enemy, killing him",
    "Using your shield, you break your enemy's neck",
    "You disintegrate your enemy with a sun beam",
    "You inflict a killing wound with your blade",
    "You turn your blade into a divine energy cannon and blast a hold straight through your foe"
  ),
  "This is justice. Divine justice"
) {

  hp = 9 + stats.constitution + 6 * (lvl - 1)

  addSpells(1, Spell("HEAL", "Gain 1-4 health", (hero, _) => hero.hp += Dice.d4))
  addSpells(3, Spell("SHIELD OF FAITH", "Your next turn is skipped but you are protected against normal attacks until your next turn (not counting the skipped one)"))
  addSpells(5, Spell("SPIRITUAL WEAPON", "Your normal attacks do double the damage for the next 1-4 turns"))
  addSpells(7, Spell("CALL ON PATRON GOD", "One of your enemies is killed by the gods",
    (_, enemies) => enemies(Dice.d(enemies.length) - 1).hp = 0)) // INSTA-KILL!!!
  addSpells(9, Spell("REVIVAL", "If you die after using this spell you are brought back to full health. Works once after use"))
}
