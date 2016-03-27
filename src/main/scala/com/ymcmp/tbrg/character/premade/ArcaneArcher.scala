/*
 * The MIT License (MIT)
 * Copyright (c) 2016 $user
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.ymcmp.tbrg.character.premade

import com.ymcmp.tbrg.character.{Dice, Gender, GenericSheet, Stats}

/**
  * Created by Plankp on 2016-03-27.
  */
class ArcaneArcher(name: String, gender: Gender.Value, stats: Stats) extends GenericSheet(name, gender,
  stats, () => Dice.d6 + 2, 12, 0,
  Array(
    "Your arrow finds its way into your opponent's shoulder.",
    "You stab your opponent with your arrow.",
    "You magically teleport your arrow into your enemy.",
    "You smash your enemy with your bow.",
    "You shoot your enemy in the side."
  ),
  Array(
    "You shoot an arrow through your enemy's head.",
    "You choke your enemy with your bow.",
    "You teleport your arrow into the enemy's heart.",
    "You stab your opponent through their chest with a arrow.",
    "You shoot five arrows through you opponent's body."
  ),
  "Your arrows teleport from the dead bodies into your quiver."
) {

  hp = 6 + stats.constitution + 4 * (lvl - 1)

  addSpells(1, Spell("TELEPORT", "Make your attack hit no matter what",
    (hero, enemies) => enemies.foreach(_.hp -= hero.atk())))
  addSpells(3, Spell("CONFUSE", "If you hit your enemy they can't attack their next turn"))
  addSpells(5, Spell("VANISH", "Make the first attack by an enemy next turn that is targeting you miss"))
  addSpells(7, Spell("DEFLECTION", "Enemy attacks that would hit bounce of the magical shield and hit themselves"))
  addSpells(9, Spell("MULTISHOT", "Shoot five arrows instead of one this gives you 4 more chances to hit and everyone that hit does normal damage"))
}
