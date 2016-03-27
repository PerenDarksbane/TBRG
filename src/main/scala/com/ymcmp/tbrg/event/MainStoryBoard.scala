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

package com.ymcmp.tbrg.event

import com.ymcmp.tbrg.SheetFactory
import com.ymcmp.tbrg.SheetFactory.CharacterTypes
import com.ymcmp.tbrg.character.{Gender, Race, Stats}

/**
  * Created by Plankp on 2016-03-26.
  */
object MainStoryBoard {

  // Note: This file (believe it or not) is supposed to look super easy to
  // understand... Hope the most confusing part of this file is this comment
  // Oh and also, when MainStoryBoard() is called, apply() is called (Scala
  // style) and Unit means nothing is returned

  def apply(event: Event): Unit = {
    println("You wake up in dirty clothing in a damp cell underground. You look around and see you are in a small cell in a large room with no windows. You have no idea how you got here. The last thing you remeber was going to sleep in your cottage. There are other CELLS as well as other PEOPLE in your cell. The GUARDS stand outside your cell.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "cell" | "cells" | "c" => cells(event)
      case "guard" | "guards" | "g" => guard(event)
      case "people" | "p" => people(event)
    }
  }

  def cells(event: Event): Unit = {
    println("There are around 100 other cells in the large dark room. GUARDS stalk the spaces between each cell. The cells themselves are made of iron BARS and have 4-10 PEOPLE in them.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "bar" | "bars" | "b" => ironBars(event)
      case "guard" | "guards" | "g" => guard(event)
      case "people" | "p" => people(event)
    }
  }

  def guard(event: Event): Unit = {
    println("The guards are dark elves. They look similar to elves, with the same pointed ears and slender features, but they have pale, translucent skin and live in large underground cities. Often these elves capture surface humanoids and use them as slaves. Most of them see the surfaces dwellers as inferior.  Any who reject this mindset are outcast from society. The guards are fully armored in iron battle gear and they are equipped with short-swords. other PEOPLE. CELLS.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "cell" | "cells" | "c" => cells(event)
      case "people" | "p" => people(event)
    }
  }

  def people(event: Event): Unit = {
    println("There are 6 other people in your cell. A DWARF, two HUMANS, a GNOME, and two ELVES.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "dwarf" | "d" => peopleDwarf(event)
      case "human" | "humans" | "h" => peopleHumans(event)
      case "gnome" | "g" => peopleGnome(event)
      case "elves" | "elf" | "e" => peopleElves(event)
    }
  }

  def peopleHumans(event: Event): Unit = {
    println("The first human is a female with long blond hair and green eyes. She looks to be around 25 years old and is chatting with the other human. He has dark black hair and green eyes as well. He seems to be around the same age as the female and has fair skin. Perhaps you could join their CONVERSATION? BACK.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "back" | "b" => people(event)
      case "conversation" | "c" => notImplementedYet()
    }
  }

  def peopleGnome(event: Event): Unit = {
    println("The gnome has a deck of cards in his hands and he is shuffling them nervously. He has pasty yellow skin, dirty blond hair, and soft black eyes typical of a gnome. He looks to be around 50 for a human, so he is probably around 25 as gnome mature twice as fast as humans. He looks anxious, perhaps you could TALK to him? BACK.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "back" | "b" => people(event)
      case "conversation" | "c" => notImplementedYet()
    }
  }

  def peopleDwarf(event: Event): Unit = {
    println("The dwarf has a long bushy orange beard and brown eyes. He is stout and tall for a dwarf. He looks to be around 45 years old and because humans and dwarves mature at the same rate, he probably is. His skin is tanned, copper-like in appearance. He is sitting down near one of the ELVES. Although neither has made the effort to talk to you, you could start a CONVERSATION with them. BACK.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "back" | "b" => people(event)
      case "conversation" | "c" => notImplementedYet()
      case "elves" | "elf" | "e" => peopleElves(event)
    }
  }

  def peopleElves(event: Event): Unit = {
    println("The first elf is male and looks like he is a 15 year old by human standards. He has messy black hair, pale skin, and brown eyes. Elves age ten times slower than humans so he is most likely around 150 years old. He is wearing the same rags you are and is hanging out near the DWARF. The second elf is female and is an adult. She is around 150 years older than the other elf. She has long brown hair, fair skin, and dark brown eyes. She is sitting in the corner of the cell alone. Although neither has made the effort to talk to you, you could start a CONVERSATION with them. BACK.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "back" | "b" => people(event)
      case "conversation" | "c" => notImplementedYet()
      case "dwarf" | "d" => peopleDwarf(event)
    }
  }

  def ironBars(event: Event): Unit = {
    println("The iron bars are very strong. It would be very hard to BREAK. BACK.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "back" | "ba" => cells(event)
      case "break" | "br" => ironBarsBreak(event)
    }
  }

  def ironBarsBreak(event: Event): Unit = {
    if (event.hero.dcStr > 20) {
      println("You break the bars. Immediately you are confronted by the two guards out side your cell. You could ATTACK, RUN, or SURRENDER.")
      print("action >>")
      io.StdIn.readLine.trim.toLowerCase match {
        case "attack" | "a" =>
          event.conflict(SheetFactory(CharacterTypes.PHASE_KNIGHT, "", Gender.MALE, new Stats(Race.HALF_ORC))) match {
            case event.EndOfConflict.DONE => println("Done!")
            case event.EndOfConflict.SURRENDER => ironBarsBreakSurrender(event)
            case event.EndOfConflict.RUN => ironBarsBreakRun(event)
          }
        case "run" | "r" => ironBarsBreak(event)
      }
    } else {
      println("You do not break the bars. The other PEOPLE in your cell take note of your actions.")
      people(event)
    }
  }

  def ironBarsBreakRun(event: Event): Unit = {
    println("You run as fast as you can dodging in between guards until you are eventually trapped by an locked iron door. You have two options FIGHT or SURRENDER.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "fight" | "f" => notImplementedYet()
      case "surrender" | "s" => ironBarsBreakSurrender(event)
    }
  }

  def ironBarsBreakSurrender(event: Event): Unit = {
    println("You surrender and the GUARDS take you and the other PRISONERS to a different cell.")
    print("action >>")
    io.StdIn.readLine.trim.toLowerCase match {
      case "guard" | "guards" | "g" => guard(event)
      case "prisoners" | "prisoner" | "p" => people(event)
    }
  }

  def notImplementedYet(): Unit = {
    println("Sorry... we are still working on it...")
    sys.exit()
  }
}
