package com.ymcmp.tbrg

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-21.
  */
object SheetFactory {

  object Classes extends Enumeration {
    val ELEMENTALIST = Value
    val NECROMANCER = Value
    val PRIEST = Value
    val WARLOCK = Value
  }

  def makeSheet(t: Classes.Value, r: Race.Value): GenericSheet = {
    t match {
      case Classes.ELEMENTALIST => new Elementalist(r)
      case Classes.NECROMANCER => new Necromancer(r)
      case Classes.PRIEST => new Priest(r)
      case Classes.WARLOCK => new Warlock(r)
    }
  }
}
