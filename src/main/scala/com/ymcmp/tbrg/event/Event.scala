package com.ymcmp.tbrg.event

import com.ymcmp.tbrg.character.GenericSheet

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
  }

  def conflict(hero: GenericSheet, enemy: GenericSheet): EndOfConflict.Value = {
    while (true) {
      println("You are in combat. Do you want to:")
      for (r <- ConflictState.values)
        printf("%d) %s\n", r.id, r)
      println("-1) quit")
      val res = io.StdIn.readByte().toInt;
      if (res == -1)
        sys.exit()
      val state = if (1 to ConflictState.values.size contains (res + 1)) // `to` contains the numbers
        ConflictState.values.toList(res)
      else {
        println("Setting action to run...")
        ConflictState.RUN
      }
      state match {
        case ConflictState.RUN => {
          if (hero.dcDex > enemy.stats.dexterity) {
            println("ENEMY: I'm supposed to be using spells right now... The devs are too lazy...")
            return EndOfConflict.DONE
          }
          else enemyTurn(hero, enemy)
        }
        case ConflictState.ATTACK => {
          if (hero.dcPro > enemy.ac) {
            println(hero.msgOnHit)
            enemy.hp -= hero.atk()
            if (enemy.hp <= 0) {
              println(hero.msgOnKill)
              return EndOfConflict.DONE
            }
          } else println(hero.msgOnMiss)

          // And then enemy's turn
          enemyTurn(hero, enemy)
        }
        case ConflictState.SPELL => {
          if (hero.hasSpells()) {
            println("I'm supposed to be using spells right now... The devs are too lazy...")
          } else {
            println("I cannot use spells. You should have known.")
          }
          enemyTurn(hero, enemy)
        }
        case ConflictState.SURRENDER => {
          return EndOfConflict.SURRENDER
        }
      }

      if (hero.hp <= 0) {
        println("You ded... GG BOY!!!")
        sys.exit()
      }
    }
    return EndOfConflict.DONE // Just so IntelliJ's IntelliSense can STFU
  }

  private def enemyTurn(hero: GenericSheet, enemy: GenericSheet): Unit = {
    if (enemy.hasSpells()) {
      println("ENEMY: I'm supposed to be using spells right now... The devs are too lazy...")
    } else {
      if (enemy.dcPro > hero.ac) {
        println("ENEMY: " + enemy.msgOnHit)
        hero.hp -= enemy.atk()
        if (hero.hp <= 0)
          println("ENEMY: " + enemy.msgOnKill)
      } else {
        println("ENEMY: " + enemy.msgOnMiss)
      }
    }
  }
}

