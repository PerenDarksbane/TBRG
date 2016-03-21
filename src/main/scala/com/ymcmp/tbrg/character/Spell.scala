package com.ymcmp.tbrg.character

/**
  * Created by Plankp on 2016-03-21.
  */
@SerialVersionUID(1426L)
class Spell(sname: String, sdesc: String = "<no desc>") extends Serializable {
  val name = sname
  val desc = sdesc

  override def toString(): String = "[" + name + ":" + desc + "]"
}
