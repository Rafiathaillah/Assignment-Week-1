package com.example.wizardadventure

import java.util.Scanner

class Controller {
    private val scanner = Scanner(System.`in`)
    private var stillRun: Boolean = true
    private lateinit var player: Player
    fun start(){
        println("What's your name?")
        val name = scanner.nextLine()
        player = Player(name)

        do {
            print("\nWhat're you going to do?\n" +
                    "1. View Stats\n" +
                    "2. Enter Battle\n" +
                    "Choice: ")
            val choice = scanner.nextLine().toInt()
            when (choice){
                1 -> ViewStats()
                2 -> EnterBattle()
                else -> println("Invalid input. Please try again!")
            }
        }while (stillRun)
    }

    fun ViewStats(){
        player.viewPlayerStats()
        print("a. Drink Mana Potion\n" +
                "b. Drink Health Potion\n" +
                "c. Rename self\n" +
                "d. Back\n" +
                "Choice: ")
        val choice = scanner.nextLine()
        when (choice){
            "a" -> player.useMpPotion()
            "b" -> player.useHpPotion()
            "c" -> {
                print("Enter new name: ")
                player.name = scanner.nextLine()
            }
            "d" -> return
            else -> println("Invalid input. Please try again!")
        }
    }

    fun EnterBattle(){
        println("Enter Battle")
    }
}