package com.ymcmp.tbrg

import com.ymcmp.tbrg.SheetFactory.Classes
import com.ymcmp.tbrg.character.Race
import com.ymcmp.tbrg.character.enemies.Weakling
import com.ymcmp.tbrg.event.Event

object App {
  def main(args: Array[String]) {
    println("Hello there. What race do you want to be?")
    for (r <- Race.values)
      printf("%d) %s\n", r.id, r)
    println("-1) quit")
    var res = io.StdIn.readByte().toInt
    if (res == -1)
      sys.exit()
    val urace = if (1 to Race.values.size contains (res + 1)) // `to` contains the numbers
      Race.values.toList(res)
    else throw new RuntimeException("You entered an illegal number...")

    for (r <- Classes.values)
      printf("%d) %s\n", r.id, r)
    println("-1) quit")
    res = io.StdIn.readByte().toInt
    if (res == -1)
      sys.exit()
    val uclass = if (1 to Classes.values.size contains (res + 1))
      SheetFactory.makeSheet(Classes.values.toList(res), urace)
    else throw new RuntimeException("You entered an illegal number... ")

    println("Your character: " + uclass)

    val session = new Event()
    session.conflict(uclass, new Weakling)
  }
}
