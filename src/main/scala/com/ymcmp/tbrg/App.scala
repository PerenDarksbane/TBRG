package com.ymcmp.tbrg

import com.ymcmp.tbrg.SheetFactory.CharacterTypes
import com.ymcmp.tbrg.character.{Dice, Race}
import com.ymcmp.tbrg.event.Event

object App {
  def main(args: Array[String]) {
    println("Hello there. What race do you want to be?")
    Race.values foreach (r => println(s"${r.id} $r"))
    println("-1) quit")
    var res = io.StdIn.readByte().toInt
    if (res == -1)
      sys.exit()
    val urace = if (1 to Race.values.size contains res + 1) // `to` contains the numbers
      Race.values toList res
    else throw new RuntimeException("You entered an illegal number...")

    CharacterTypes.values foreach (r => println(s"${r.id} $r"))
    println("-1) quit")
    res = io.StdIn.readByte().toInt
    if (res == -1)
      sys.exit()
    val uclass = if (1 to CharacterTypes.values.size contains res + 1)
      SheetFactory(CharacterTypes.values toList res, urace)
    else throw new RuntimeException("You entered an illegal number... ")

    println(s"Your character: $uclass")

    val session = new Event()
    1 to Dice.d8 foreach (_ =>
      session conflict(uclass,
        SheetFactory(CharacterTypes.values toList (Dice d CharacterTypes.values.size) - 1,
          Race.values toList (Dice d Race.values.size) - 1)))
  }
}
