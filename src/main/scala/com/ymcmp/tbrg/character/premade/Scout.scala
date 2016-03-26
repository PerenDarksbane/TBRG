package com.ymcmp.tbrg.character.premade

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-21.
  */
object Scout {
  def originalAtk = () => Dice.d(2)
}

class Scout(name: String, gender: Gender.Value, stats: Stats) extends GenericSheet(name, gender,
  stats, Scout.originalAtk, 4, 10,
  Array("Man, you got hit by a Scout? You suck!"),
  Array("Man, you got killed by a Scout? You suck even more!"),
  "I spy with my little eye, you being ded") {
  addSpells(1, Spell("SNEAK PEAK", "You see stats of all enemies", (_, enemies) => enemies.foreach(println(_))))
  addSpells(2, Spell("SELF DAMAGE", "Hit yourself, but then all skill points increase by 2 (PERMANENT)",
    (self, _) => {
      self.hp -= self.atk()
      self.stats.map(_ + 2)
      self.atk = () => Scout.originalAtk() + 2
      self.ac += 2
    }))
}
