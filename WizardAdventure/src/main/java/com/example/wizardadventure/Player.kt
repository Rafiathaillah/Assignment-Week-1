package com.example.wizardadventure

class Player (var name: String, var hp: Int = 50, var currHp: Int = hp, var mp: Int = 30, var currMp: Int = mp, var currKill: Int = 0, var evolveKill: Int = 5, var mpHeld: Int = 5, var hpHeld: Int = 5, var status: String = "Weak"){
    fun viewPlayerStats(){
        println("\n=== $name's Stats ===\n" +
                "HP: $currHp/$hp\n" +
                "Mana: $currMp/$mp\n" +
                "Kills needed to evolve: ${currKill}/$evolveKill\n" +
                "Health potions held: $hpHeld\n" +
                "Mana potions held: $mpHeld\n" +
                "====================")
    }

    fun useHpPotion(){
        if (hpHeld > 0){
            if (currHp + 10 >= hp){
                currHp = hp
                hpHeld--
            } else{
                currHp += 10
                hpHeld--
            }
        } else{
            println("There's no health potion left!")
        }
    }

    fun useMpPotion(){
        if (mpHeld > 0){
            if (currMp + 10 >= mp){
                currMp = mp
                mpHeld--
            } else{
                currMp += 10
                mpHeld--
            }
        } else{
            println("There's no mana potion left!")
        }
    }
}