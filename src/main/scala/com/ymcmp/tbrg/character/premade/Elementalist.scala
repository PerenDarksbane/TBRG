package com.ymcmp.tbrg.character.premade

import com.ymcmp.tbrg.character._

/**
  * Created by Plankp on 2016-03-21.
  */
class Elementalist(r: Race.Value) extends GenericSheet(r, () => Dice.d6, 0, 0,
  Array(
    "You use your staff and thrust the end into the enemy's stomach.",
    "You form a bubble of acid which you hurl at the enemy, covering him in bubbling, burning, green slime.",
    "You catch your hand ablaze, burning your enemy when you punch him.",
    "You blast a gust of icy wind from your staff.",
    "You shoot a bolt of lightning into the enemy, causing him to shake violently."
  ),
  Array(
    "You form a ball of wind, which you blast through the enemy leaving a hole in the chest.",
    "You call lightning down from the sky, turning your enemy into a pile of ash.",
    "The ground opens up beneath your enemy and you use magic to drag him down into the earth.",
    "You touch your enemy and he freezes into an ice statue, which you proceed to shatter with your staff.",
    "You create a flaming tornado around an enemy, destroying him."
  ),
  "Satisfied with your work, you turn and walk away from your enemies as thunder rolls in the background.") {
  hp = 6 + stats.constitution + 3 * (lvl - 1)
  ac = 13 + stats.dexterity

  addSpells(1, Spell("THUNDERWAVE", "Create a large thunder sound causing enemies to miss their next turn"))
  addSpells(2, Spell("MISTY STEP", "If you run next turn you are guaranteed to succeed"))
  addSpells(3, Spell("COUNTERSPELL", "Prevents spells from affecting you until your next turn"))
  addSpells(4, Spell("STONESKIN", "Damage taken is halved for the rest of the round (Does not attack)"))
  addSpells(5, Spell("CLOUDKILL", "Half your health to instantly defeat a random enemy (Use lightning ON KILL)",
    (hero, enemies) => {
      hero.hp /= 2 // Take half health
      enemies(Dice.d(enemies.length) - 1).hp = 0 // INSTA-KILL!!!
    }))
  addSpells(6, Spell("CHAIN LIGHTNING", "Attack as normal except on hit do damage to all enemies(Use lightning ON HIT)",
    (hero, enemies) =>
      enemies
        .filter(hero.dcPro > _.ac) // Figure out if enemy is hit or not
        .foreach(_.hp -= hero.atk()))) // and then roll atk and hit'em!
  addSpells(7, Spell("FIRESTORM", "Damage an enemy like an attack and prevent all enemies from doing anything on their next turn"))
  addSpells(8, Spell("EARTHQUAKE", "Traps enemies in the earth, allowing your next attack to hit no matter what",
    (hero, enemies) => enemies.foreach(_.hp -= hero.atk()))) // Each enemy, roll atk and hit'em!
  addSpells(9, Spell("METEORSWARM", "Do a double damage attack to all enemies and prevent them from using their next turn"))
}
