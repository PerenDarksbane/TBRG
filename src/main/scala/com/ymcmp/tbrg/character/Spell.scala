package com.ymcmp.tbrg.character

/**
  * Created by Plankp on 2016-03-21.
  */
@SerialVersionUID(1426L)
class Spell(charac: GenericSheet, sname: String, sdesc: Option[String] = None,
            act: (GenericSheet, Array[GenericSheet]) => Unit) extends Serializable {
  val name = sname
  val desc = sdesc

  final def apply(enemies: GenericSheet*): Unit = {
    println(s"used spell $this")
    act(charac, enemies.toArray)
  }

  override def toString: String = desc match {
    case Some(s) => s"[$name:$s]"
    case None => s"[$name]"
  }
}
