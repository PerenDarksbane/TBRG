package com.ymcmp.tbrg.character.premade

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-21.
  */
class Scout(name: String, gender: Gender.Value, stats: Stats) extends GenericSheet(name, gender,
  stats, () => Dice.d4 + 2, 14, 0,
  Array(
    "You stab your enemy in the back",
    "You blade slashes across your enemy's chest",
    "You smash the handle of your dagger on the enemy's head",
    "Your knee smashes into your opponent's chin",
    "You slice your opponent's side"
  ),
  Array(
    "Your enemy's body slides off your blade onto the floor",
    "You shatter your enemy's skull with your elbow",
    "You snap your enemy's neck",
    "Your blade impales your opponent, killing him",
    "You throw your opponent over your shoulder breaking his neck on the floor"
  ),
  "You finish off your last enemy then fade into the shadows") {

  hp = 5 + stats.constitution + 3 * (lvl - 1)
}
