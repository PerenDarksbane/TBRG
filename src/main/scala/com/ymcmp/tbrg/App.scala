package com.ymcmp.tbrg

import com.ymcmp.tbrg.SheetFactory.{BasicData, CharacterTypes}
import com.ymcmp.tbrg.character.{Gender, Race, Stats}
import com.ymcmp.tbrg.event.Event

object App {
  def main(args: Array[String]) {
    if (args.length == 1) {
      args(0) match {
        case "-c" | "--console" => {} // Empty block
        case "-g" | "--gui" => AppGui.enterGui()
        case "-h" | "--help" => printHelp()
        case _ => {
          println(s"Argument not recognized! ${args(0)}")
          printHelp()
        }
      }
    } else AppGui.enterGui()
    new Event(SheetFactory(characterSetup)) play()
  }

  def printHelp(): Unit = {
    println("TBRG [OPTIONS]")
    println("[OPTIONS]:")
    println("    -c, --console  Launches game in console (fallback) mode")
    println("    -g, --gui      Launches game in gui (default) mode")
    println("    -h, --help     Prints this message")
    sys.exit()
  }

  def characterSetup: BasicData = {
    println("Hello. My name is the Creator. Today, I will help you create your adventurer so you can explore the country of Un. First of all your adventurer will need a name. Type your adventurer's name to continue.")
    print("name >>")
    val cname = io.StdIn.readLine().trim
    println("Excellent, now what gender is your adventurer? MALE or FEMALE?")
    print("gender(f) >>")
    val gend = io.StdIn.readLine().trim.toLowerCase match {
      case "male" | "m" => Gender.MALE
      case "female" | "f" => Gender.FEMALE
      case _ => {
        println("Using default: f")
        Gender.FEMALE
      }
    }
    println("Now, what race do you want your character to be? There are many, each with their own advantages. Click on one to learn about it, Don't worry, if you don't like your choice, you can come back and choose another. Here are the races: DWARF, ELF, GNOME, HALF-ELF, HALFLING, HALF-ORC, HUMAN, DARK ELF, DRAGONKIN, DEMONKIN.")
    val race = setupRace
    println("Adventurer, what is your profession? Pick your class! Again, you can always come back and remake your choice after learning about each class. The classes are: MAGE, CLERIC, PALADIN, WARRIOR, ARCHER, ROGUE, and MONK. Each class has multiple subclasses.")
    val ctype = setupClass
    println("Now all that is left is to acquire your ability scores. They will be chose randomly.")
    val stats = setupStats(race)
    new BasicData(ctype, cname, gend, stats)
  }

  def setupStats(r: Race.Value): Stats = {
    val s = new Stats(r)
    setupSelectionInfo(s, setupStats(r), s"Your scores are $s. Are you happy with this score?")
  }

  def setupClass: CharacterTypes.Value = {
    print("class(mage) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "cleric" | "c" =>
        setupSelectionInfo(setupClassCleric, setupClass, "Clerics gain their powers from religious devotion to the gods or all powerful beings. They use spells primarily to defend and heal. They value wisdom. They have many subclasses. They are: WARLOCKE, PRIEST, and NECROMANCER.")
      case "paladin" | "p" =>
        setupSelectionInfo(setupClassPaladin, setupClass, "Paladins use either religious power or magic to aid them in battle. They value strength as well as intelligence or wisdom. They have many subclasses. They are: BATTLEMAGE, TEMPLAR, and PHASE KNIGHT.")
      case "warrior" | "w" =>
        setupSelectionInfo(setupClassWarrior, setupClass, "Warriors specialize in melee engagement and weapons handling. They value strength and constitution. They have many subclasses. They are: KNIGHT, BRUTE, and WEAPONSMASTER.")
      case "archer" | "a" =>
        setupSelectionInfo(setupClassArcher, setupClass, "Archers use ranged weapons primarily. They value dexterity and intelligence. They have many subclasses. They are: BOWMAN, ARCANE ARCHER, RANGER and FLINGER.")
      case "rogue" | "r" =>
        setupSelectionInfo(setupClassRogue, setupClass, "Rogues are the sneakiest class. They value dexterity and charisma. They have many subclasses. They are: TRICKSTER, DECEIVER, and SCOUT.")
      case "monk" | "mo" =>
        setupSelectionInfo(setupClassMonk, setupClass, "Monks are masters at hand to hand combat. They know martial arts and value constitution and dexterity. They have many subclasses. They are: NINJA, DISCIPLE, and KI MASTER.")
      case _ =>
        setupSelectionInfo(setupClassMage, setupClass, "Mages primarily use arcanic energy, also known as magic. Mages have lower health than other classes and they prioritize intelligence. They have many subclasses. They are: DRUID, SUMMONER, ELEMENTALIST, and ILLUSIONIST.")
    }
  }

  def setupClassMonk: CharacterTypes.Value = {
    print("class-monk(ki master) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "ninja" | "n" =>
        setupSelectionInfo(CharacterTypes.NINJA, setupClassMonk, "Ninjas use both weapons and hand to hand combat.")
      case "disciple" | "d" =>
        setupSelectionInfo(CharacterTypes.DISCIPLE, setupClassMonk, "Disciples are trained in monasteries and use martial arts in battle.")
      case _ =>
        setupSelectionInfo(CharacterTypes.KI_MASTER, setupClassMonk, "Ki masters use their ki energy to make energy attacks, similar to those of a battlemage.")
    }
  }

  def setupClassRogue: CharacterTypes.Value = {
    print("class-rogue(trickster) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "scout" | "s" =>
        setupSelectionInfo(CharacterTypes.SCOUT, setupClassRogue, "Scouts use weapons and stealth to out think and defeat their opponents.")
      case "deceiver" | "d" =>
        setupSelectionInfo(CharacterTypes.DECEIVER, setupClassRogue, "Deceivers rely on negotiation and deception to avoid conflict and achieve their goals.")
      case _ =>
        setupSelectionInfo(CharacterTypes.TRICKSTER, setupClassRogue, "Tricksters are rogues that use illusionary magic to trick others and defend themselves.")
    }
  }

  def setupClassArcher: CharacterTypes.Value = {
    print("class-archer(arcane archer) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "ranger" | "r" =>
        setupSelectionInfo(CharacterTypes.RANGER, setupClassArcher, "Rangers use bows in short range combat.")
      case "flinger" | "f" =>
        setupSelectionInfo(CharacterTypes.FLINGER, setupClassArcher, "Flingers do not use bows but rather throw any object near them at their foes. Often they have darts, playing cards or throwing knives.")
      case "bowman" | "b" =>
        setupSelectionInfo(CharacterTypes.BOWMAN, setupClassArcher, "Bowmen use a bow and have amazing long range accuracy.")
      case _ =>
        setupSelectionInfo(CharacterTypes.ARCANE_ARCHER, setupClassArcher, "Arcane archers use magic to enhance their archery.")
    }
  }

  def setupClassWarrior: CharacterTypes.Value = {
    print("class-warrior(weaponmaster) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "knight" | "k" =>
        setupSelectionInfo(CharacterTypes.KNIGHT, setupClassWarrior, "Knights use swords and shields as well as heavy armour.")
      case "brute" | "b" =>
        setupSelectionInfo(CharacterTypes.BRUTE, setupClassWarrior, "Brutes use large melee weapons and are fueled by anger in battle.")
      case _ =>
        setupSelectionInfo(CharacterTypes.WEAPONMASTER, setupClassWarrior, "Weaponsmasters can use any weapon in battle as though they had trained with it their entire life.")
    }
  }

  def setupClassPaladin: CharacterTypes.Value = {
    print("class-paladin(templar) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "phase knight" | "p" =>
        setupSelectionInfo(CharacterTypes.PHASE_KNIGHT, setupClassPaladin, "Phase knights use illusionary magic as well as weaponry.")
      case "battlemage" | "b" =>
        setupSelectionInfo(CharacterTypes.BATTLEMAGE, setupClassPaladin, "Battlemages use elementalist magic and weaponry.")
      case _ =>
        setupSelectionInfo(CharacterTypes.TEMPLAR, setupClassPaladin, "Templars use religious power and weaponry.")
    }
  }

  def setupClassCleric: CharacterTypes.Value = {
    print("class-cleric(warlocke) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "priest" | "p" =>
        setupSelectionInfo(CharacterTypes.PRIEST, setupClassCleric, "Priests use religious power and divine favor to power their spells.")
      case "necromancer" | "n" =>
        setupSelectionInfo(CharacterTypes.NECROMANCER, setupClassCleric, "Necromancers gain their powers from Dark Gods or from other powerful beings. They use their magic to undo death, attempting to bring back lost souls.")
      case _ =>
        setupSelectionInfo(CharacterTypes.WARLOCKE, setupClassCleric, "Warlockes sold their souls to demons or devils to gain powers.")
    }
  }

  def setupClassMage: CharacterTypes.Value = {
    print("class-mage(elementalist) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "summoner" | "s" =>
        setupSelectionInfo(CharacterTypes.SUMMONER, setupClassMage, "Summoners summon minions to fight for them.")
      case "druid" | "d" =>
        setupSelectionInfo(CharacterTypes.DRUID, setupClassMage, "Druids base their magic mainly off nature. They devote their lives to protecting the source of their magic.")
      case "illusionist" | "i" =>
        setupSelectionInfo(CharacterTypes.ILLUSIONIST, setupClassMage, "Illusionists use magic to trick their foes.")
      case _ =>
        setupSelectionInfo(CharacterTypes.ELEMENTALIST, setupClassMage, "Elementalists use magic to manipulate the elements and weather.")
    }
  }

  def setupRace: Race.Value = {
    print("race(human) >>")
    io.StdIn.readLine().trim.toLowerCase match {
      case "dwarf" | "d" | "dw" =>
        setupSelectionInfo(Race.DWARF, setupRace, "Dwarfs are known for living in underground mining strongholds and searching for precious metals. They are shorter than humans ranging 4 to 5 feet tall. Dwarfs are very stout and strong, despite their size. They mature at the same rate as humans and live the same amount of time and are very resilient and tough. They often dislike being outside and are able to navigate caves with ease. They can speak and write common, as well as their native language of Dwarvish. Their hair is often white and they are prone to growing beards. Their skin colour can range from a silverish colour to more of a brass tone. Do you want to be a DWARF?")
      case "elf" | "e" =>
        setupSelectionInfo(Race.ELF, setupRace, "Elves are known for being in tune with nature. They live and mature ten times more slowly than humans and are about a foot taller. They have a very slender frame and are often agile and have good eyesight. Magic comes naturally to elves and they are often very intelligent. They live primarily in forest villages although some live in human cities. Their skin tone varies greatly, as do eye colour and hair colour. They have pointed ears and cannot grown facial hair. They can speak and write common, elvish and arcanic. The elves dislike cramped dark spaces and are able to navigate forests with ease. Do you want to be an ELF?")
      case "gnome" | "g" =>
        setupSelectionInfo(Race.GNOME, setupRace, "Gnomes are known for living in a great variety of places. Some enjoy the peacefulness of nature while others enjoy the organized life of a city. They are much shorter than humans, around 2-4 feet tall. Gnomes are often very curious and intelligent, yet can also be timid at times. There are very few gnome adventurers as many consider the job as \"uncivilized and dangerous\". Gnomes are often tinkerers, creating little gadgets and artifacts to help them in their journeys. Gnomes live half as long as humans and mature twice as fast. Their skin tone is often a pasty yellow or green and they usually have completely black eyes. Their hair colour is usually wild compared to the other races. They can write and speak gnomish and common. They are often very good at puzzles. Do you want to be a GNOME?")
      case "half-elf" | "he" =>
        setupSelectionInfo(Race.HALF_ELF, setupRace, "Half-elves are what happen when humans and elves have children. They have the pointed elvish ears but can grow facial hair. Their skin tone, eye colour and hair colour vary. They tend to be innately magical but often live in human cities more than their elvish counterparts. They range from human height to elf height. They can speak and write common and elvish. Often \nhalf-elves are intelligent, however they are not as agile as their elvish kin. Half-elves mature at a human rate, but once they reach adulthood their aging stops and they can live until 500 years old. Do you want to be a HALF-ELF?")
      case "halfling" | "hl" =>
        setupSelectionInfo(Race.HALFLING, setupRace, "Halflings live as long as humans and are half as tall. There are no other differences. Do you want to be a HALFLING?")
      case "half-orc" | "ho" =>
        setupSelectionInfo(Race.HALF_ORC, setupRace, "Half-orcs are what happen when humans and orcs have children. They usually have underbites and are taller than normal humans. Their skin is very leathery and they have the strength of an orc. They speak and write common and orcish. Often half-orcs aren't as bright as the average human and they often live in human settlements as they are often outcast by orcs. They mature slightly faster than humans and live till around 75 years of age. Often half-orcs have temper problems. Do you want to be a HALF-ORC?")
      case "dark elf" | "de" =>
        setupSelectionInfo(Race.DARK_ELF, setupRace, "Dark elves are a branch of the elvish people. Their lifespan is the same as an elf. Dark elves have elvish ears and a slender frame, but that is where the similarity ends. They live in underground cities, have bloodshot eyes, and semi-transparent pale skin. Their hair is dark black. Often these elves capture surface humanoids and use them as slaves. Most of them see the surfaces dwellers as inferior.  Any who reject this mindset are outcast from society. If you choose this race you would be one of these outcasts living on the surface. Do you want to be a DARK ELF?")
      case "dragonkin" | "dg" =>
        setupSelectionInfo(Race.DRAGONKIN, setupRace, "Dragonkin are a mysterious race. None know how they came to be, although there are many theories. They are taller than humans, have coloured scales and dragon-like facial features as well as a draconic tail. They are often stronger than the average human. They often live in isolation as they are often feared by society.  They mature slightly slower than humans, often reaching 125 years of age. They can speak and write draconic and common. Do you want to be a DRAGONKIN?")
      case "demonkin" | "dm" =>
        setupSelectionInfo(Race.DEMONKIN, setupRace, "Demonkin were once humanoid, but their bloodline was cursed by a demon or devil. This resulted in stunted growth, shorter life span and cosmetic effects. They are about a foot smaller than humans,and they live until around 60. They have a reddish purple skin tone and horns on their heads. They often live in isolation as they are often feared by society. They can speak and write common and demonic. Do you want to be a DEMONKIN?")
      case _ =>
        setupSelectionInfo(Race.HUMAN, setupRace, "Humans are the most generic yet diverse people. They live in settlements ranging from isolation to capital cities. Most humans however prefer to live with others. They live on average to 100 years of age, where they then die of natural causes. Humans are naturally well liked among the other races. Skin color, eye color and hair color vary greatly, as do personality traits. Do you want to be a HUMAN?")
    }
  }

  private def setupSelectionInfo[T](default: => T, elseClause: => T, info: String): T = {
    println(info)
    print("confirm(n) >>")
    if (io.StdIn.readBoolean())
      default
    else
      elseClause
  }
}
