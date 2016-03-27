package com.ymcmp.tbrg.event

import com.ymcmp.tbrg.character.{Dice, GenericSheet}

/**
  * Created by Plankp on 2016-03-21.
  */
object Event {
  // The conflicts will be re-written here
  // Multi-turn conflict use Dex DC as initiative
  // which determines when the player goes.
}

import Event._

class Event(phero: GenericSheet) {

  var hero = phero

  def play(): Unit = MainStoryBoard(this)

  private object ConflictState extends Enumeration {
    val RUN = Value
    val ATTACK = Value
    val SPELL = Value
    val SURRENDER = Value
  }

  object EndOfConflict extends Enumeration {
    val DONE = Value
    val RUN = Value
    val SURRENDER = Value
    val CONTINUE = Value
  }

  def conflict(enemy: GenericSheet): EndOfConflict.Value = {
    var t = EndOfConflict.CONTINUE
    println("You are in combat. Do you want to:")
    while (true) {
      ConflictState.values foreach (r => println(s"${r.id} $r"))
      println("-1) quit")
      val res = io.StdIn.readByte().toInt
      if (res == -1)
        sys.exit()
      val state = if (1 to ConflictState.values.size contains res + 1) // `to` contains the numbers
        ConflictState.values toList res
      else {
        println("Setting action to run...")
        ConflictState.RUN
      }

      t = turnHero(hero, enemy)(state)
      if (t != EndOfConflict.CONTINUE) {
        hero restoreSpells()
        return t
      }

      if (hero.hp <= 0) {
        println("You ded... GG BOY!!!")
        sys.exit()
      }
      println(s"End of turn report: $hero")
      println("You are still in combat. Do you want to:")
    }
    EndOfConflict.DONE // Just so IntelliJ's IntelliSense can STFU
  }

  private def turnHero(hero: GenericSheet, enemy: GenericSheet)
                      (userChoice: ConflictState.Value): EndOfConflict.Value = {
    userChoice match {
      case ConflictState.RUN =>
        if (hero.dcDex > enemy.stats.dexterity) return EndOfConflict.RUN
      case ConflictState.ATTACK =>
        if (hero.dcPro > enemy.ac) {
          println(hero.msgOnHit)
          enemy.hp -= hero.atk()
        } else println(hero.msgOnMiss)
      case ConflictState.SPELL =>
        if (hero.hasSpells) {
          val spells = hero.getUsableSpells
          if (spells.nonEmpty) {
            spells.indices foreach (i => println(s"$i ${spells(i)}"))
            var res = io.StdIn.readByte().toInt
            if (!(spells.indices contains res)) {
              println("Using spell number 0")
              res = 0
            }
            spells(res)(enemy)
          }
        } else println("I cannot use spells. You should have known.")
      case ConflictState.SURRENDER => return EndOfConflict.SURRENDER
    }
    if (enemy.hp <= 0) {
      println(hero.msgOnKill)
      val gains: Int = calculateExp(enemy)
      println(s"Gained $gains EXP")
      hero increaseExp gains
      return EndOfConflict.DONE
    }
    enemyTurn(hero, enemy)
    EndOfConflict.CONTINUE
  }

  private def enemyTurn(hero: GenericSheet, enemy: GenericSheet): Unit = {
    if (enemy.canUseSpells && Dice.d8 > 5) {
      val spells = enemy.getUsableSpells
      print("ENEMY: ")
      spells((Dice d spells.length) - 1)(hero)
    } else {
      if (enemy.dcPro > hero.ac) {
        println(s"ENEMY: ${enemy.msgOnHit}")
        hero.hp -= enemy.atk()
        if (hero.hp <= 0) {
          println(s"ENEMY: ${enemy.msgOnKill}")
          val gains: Int = calculateExp(hero)
          enemy increaseExp gains
        }
      } else {
        println(s"ENEMY: ${enemy.msgOnMiss}")
      }
    }
  }

  private def calculateExp(opponent: GenericSheet): Int = (Dice d(opponent.lvl, 5)) * 4
}
