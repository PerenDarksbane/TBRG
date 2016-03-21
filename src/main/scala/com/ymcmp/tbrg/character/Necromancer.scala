package com.ymcmp.tbrg.character

/**
  * Created by Plankp on 2016-03-21.
  */
class Necromancer(r: Race.Value) extends GenericSheet(r, () => Dice.d4, 0, 0,
  Array(
    "You blast your enemy with a bolt of darkness.",
    "Using your sacrificial dagger, you carve a deep wound into your enemy's side.",
    "Your cold lifeless hands freeze the blood under your enemy's skin.",
    "You spray the enemy in boiling black tar.",
    "Your gaze burns into the enemy's soul."
  ),
  Array(
    "You summon the hands of minor demons to drag your enemy into the underworld.",
    "You drain the life out of your opponent, leaving him a hollowed corpse.",
    "You inflict the killing wound with your dagger.",
    "Your enemy's soul is taken away by your patron god.",
    "You store your enemy's soul in a bottle, for later."
  ),
  "Having defeated all your enemies, you bend down over each body, collecting the souls before moving on.",
  Array(
    new Spell("FALSE LIFE", "Your health is double for (your level) turns before returning to what it was before the doubling"),
    new Spell("DARKNESS", "All attacks miss you until your next turn"),
    new Spell("ANIMATE DEAD", "Any people you have killed in this combat come back to life but fight for you"),
    new Spell("DRAIN", "Do a normal attack but gain in hp the damage you inflict on the enemy"),
    new Spell("CALL ON PATRON GOD", "One of your enemies is killed by the gods"),
    new Spell("CREATE UNDEAD", "Create a zombie to fight for you"),
    new Spell("ETHEREALNESS", "All damage that would be done to you is done to the attacker. Lasts until your next turn"),
    new Spell("TRAP THE SOUL", "Cause enemy to be unable to attack you until below half health"),
    new Spell("DEATH FOG", "Damages all enemies equal to one attack. If this attack kills, any who are killed return as zombies to help the player")
  )) {
  hp = 8 + stats.constitution + 3 * (lvl - 1)
  ac = 11 + stats.dexterity
}
