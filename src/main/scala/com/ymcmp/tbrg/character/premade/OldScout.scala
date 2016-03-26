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

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-21.
  */
object OldScout {
  def originalAtk = () => Dice.d(2)
}

class OldScout(name: String, gender: Gender.Value, stats: Stats) extends GenericSheet(name, gender,
  stats, OldScout.originalAtk, 4, 10,
  Array("Man, you got hit by a Scout? You suck!"),
  Array("Man, you got killed by a Scout? You suck even more!"),
  "I spy with my little eye, you being ded") {
  addSpells(1, Spell("SNEAK PEAK", "You see stats of all enemies", (_, enemies) => enemies.foreach(println(_))))
  addSpells(2, Spell("SELF DAMAGE", "Hit yourself, but then all skill points increase by 2 (PERMANENT)",
    (self, _) => {
      self.hp -= self.atk()
      self.stats.map(_ + 2)
      self.atk = () => OldScout.originalAtk() + 2
      self.ac += 2
    }))
}
