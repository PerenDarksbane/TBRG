package com.ymcmp.tbrg.character

/**
  * Created by Plankp on 2016-03-20.
  */
object Dice {
  def d(x: Int): Int = (math.random * (x - 1) + 1).asInstanceOf[Int]
  def d(x: Int, times: Int): Int = {
    var accum: Int = 0
    for (_ <- 0 until times) {
      accum += d(x)
    }
    return accum
  }
  def d4: Int = d(4)
  def d6: Int = d(6)
  def d8: Int = d(8)
  def d10: Int = d(10)
  def d20: Int = d(20)
}
