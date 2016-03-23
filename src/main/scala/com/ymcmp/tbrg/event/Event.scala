package com.ymcmp.tbrg.event

import com.ymcmp.tbrg.character.{Dice, GenericSheet}

/**
  * Created by Plankp on 2016-03-21.
  */
class Event {

  private object ConflictState extends Enumeration {
    val RUN = Value
    val ATTACK = Value
    val SPELL = Value
    val SURRENDER = Value
  }

  object EndOfConflict extends Enumeration {
    val DONE = Value
    val SURRENDER = Value
    val CONTINUE = Value
  }


  def conflict(hero: GenericSheet, enemy: GenericSheet): EndOfConflict.Value = {
    var t = EndOfConflict.CONTINUE
    while (true) {
      println("You are in combat. Do you want to:")
      for (r <- ConflictState.values)
        println(s"${r.id} $r")
      println("-1) quit")
      val res = io.StdIn.readByte().toInt
      if (res == -1)
        sys.exit()
      val state = if (1 to ConflictState.values.size contains (res + 1)) // `to` contains the numbers
        ConflictState.values.toList(res)
      else {
        println("Setting action to run...")
        ConflictState.RUN
      }

      t = turnHero(hero, enemy)(state)
      if (t != EndOfConflict.CONTINUE)
        return t

      if (hero.hp <= 0) {
        println("You ded... GG BOY!!!")
        sys.exit()
      }
    }
    EndOfConflict.DONE // Just so IntelliJ's IntelliSense can STFU
  }

  private def turnHero(hero: GenericSheet, enemy: GenericSheet)
                      (userChoice: ConflictState.Value): EndOfConflict.Value = {
    userChoice match {
      case ConflictState.RUN =>
        if (hero.dcDex > enemy.stats.dexterity)
          return EndOfConflict.DONE
        else enemyTurn(hero, enemy)
      case ConflictState.ATTACK =>
        if (hero.dcPro > enemy.ac) {
          println(hero.msgOnHit)
          enemy.hp -= hero.atk()
          if (enemy.hp <= 0) {
            println(hero.msgOnKill)
            val gains: Int = Dice.d(enemy.lvl, 5) * 4
            println(s"Gained $gains EXP")
            hero.increaseExp(gains)
            return EndOfConflict.DONE
          }
        } else println(hero.msgOnMiss)

        // And then enemy's turn
        enemyTurn(hero, enemy)
      case ConflictState.SPELL =>
        if (hero.hasSpells) {
          val spells = hero.getUsableSpells
          for (i <- 0 until spells.size)
            println(s"$i ${spells(i)}")
          println("-1) quit")
          val res = io.StdIn.readByte().toInt
        } else println("I cannot use spells. You should have known.")
        enemyTurn(hero, enemy)
      case ConflictState.SURRENDER => return EndOfConflict.SURRENDER
    }
    return EndOfConflict.CONTINUE
  }

  private def enemyTurn(hero: GenericSheet, enemy: GenericSheet): Unit = {
    if (enemy.hasSpells) {
      println("ENEMY: How come the heros get to use spells and we don't?")
    } else {
      if (enemy.dcPro > hero.ac) {
        println(s"ENEMY: ${enemy.msgOnHit}")
        hero.hp -= enemy.atk()
        if (hero.hp <= 0) {
          println(s"ENEMY: ${enemy.msgOnKill}")
          val gains: Int = Dice.d(hero.lvl, 5) * 4
          enemy.increaseExp(gains)
        }
      } else {
        println(s"ENEMY: ${enemy.msgOnMiss}")
      }
    }
  }
}

