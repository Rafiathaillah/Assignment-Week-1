package com.example.simpleordersystem

import java.util.Scanner

class Restaurant {
    private val menuList = ArrayList<Menu>()
    private val orderList = ArrayList<Order>()
    private val scanner = Scanner(System.`in`)

    init {
        menuList.add(Menu("Nasi Goreng Padang", "Nasi Goreng Dengan Bumbu Khas Padang", 2.99))
        menuList.add(Menu("Nasi Bakar Ayam", "Nasi Bakar Dengan Potongan Ayam", 2.59))
        menuList.add(Menu("Mie Bangladesh", "Mie Nyemek Khas Bangladesh", 1.59))
        menuList.add(Menu("Es Teh Manis", "Teh Keraton Solo Dengan Es Batu", 0.59))
        menuList.add(Menu("Es Manado", "Campuran Susu Dengan Aneka Buah", 1.09))
    }

    fun start() {
        var choice: Int
        do {
            println("========== RAFI'S RESTAURANT ORDER SYSTEM ===========")
            println("1. Make Order")
            println("2. View Order")
            println("3. View Menu")
            println("4. Add Menu")
            println("5. Edit Menu")
            println("6. Delete Menu")
            println("7. Exit")
            print("Choice: ")

            choice = scanner.nextLine().toInt()

            when (choice) {
                1 -> makeOrder()
                2 -> viewOrder()
                3 -> viewMenu()
                4 -> addMenu()
                5 -> editMenu()
                6 -> deleteMenu()
                7 -> println("Thank you for using RAFI'S RESTAURANT ORDER SYSTEM")
                else -> println("Invalid choice. Please try again.")
            }

        } while (choice != 7)
    }

    private fun makeOrder() {
        if (menuList.isEmpty()) {
            println("No menu available. Please add menu first.")
            return
        }

        print("Enter Customer Name: ")
        val name = scanner.nextLine()
        val newOrder = Order(name)

        var addLoop = true
        var hasOrderedSomething = false

        while (addLoop) {
            viewMenu()
            print("Select Food Number to Order (or 0 to Finish): ")
            val foodNum = try { scanner.nextLine().toInt() - 1 } catch (e: Exception) { -2 }

            if (foodNum == -1) {
                addLoop = false
                continue
            }

            if (foodNum < 0 || foodNum >= menuList.size) {
                println("Invalid food number. Please try again.")
                continue
            }

            print("Enter Quantity: ")
            val quantity = try { scanner.nextLine().toInt() } catch (e: Exception) { 0 }

            if (quantity <= 0) {
                println("Invalid quantity. Please try again.")
                continue
            }

            newOrder.tambahItem(menuList[foodNum], quantity)
            println("Added ${menuList[foodNum].name} to cart")
            hasOrderedSomething = true
        }

        if (hasOrderedSomething) {
            orderList.add(newOrder)
            newOrder.printReceipt()
        } else {
            println("Order cancelled. No items selected.\n")
        }
    }

    private fun viewOrder() {
        if (orderList.isEmpty()) {
            println("\nNo orders have been made yet.\n")
            return
        }
        println("\n=== ALL ORDERS ===")
        orderList.forEach { it.printReceipt() }
    }

    private fun viewMenu() {
        println("\n=== RESTAURANT MENU ===")
        if (menuList.isEmpty()) {
            println("(Menu is empty)")
        } else {
            menuList.forEachIndexed { index, food ->
                println("${index + 1}. ${food.name} - ${food.desc} ($${food.price})")
            }
        }
        println()
    }

    private fun addMenu() {
        print("Enter food name: ")
        val name = scanner.nextLine()
        print("Enter description: ")
        val desc = scanner.nextLine()
        print("Enter price: ")
        val price = scanner.nextDouble()

        menuList.add(Menu(name, desc, price))
        println("✅ Menu '$name' added successfully!\n")
    }

    private fun editMenu() {
        viewMenu()
        if (menuList.isEmpty()) return

        print("Select menu number to edit: ")
        val index = scanner.nextInt() - 1
        scanner.nextLine() // Clear buffer

        if (index in menuList.indices) {
            val food = menuList[index]
            print("Enter new name (leave blank to keep '\${food.name}'): ")
            val newName = scanner.nextLine()
            if (newName.isNotBlank()) food.name = newName

            print("Enter new description (leave blank to keep '\${food.description}'): ")
            val newDesc = scanner.nextLine()
            if (newDesc.isNotBlank()) food.desc = newDesc

            print("Enter new price (or -1 to keep \$(${food.price.toInt()}): ")
            val newPrice = scanner.nextDouble()
            if (newPrice != -1.0) food.price = newPrice

            println("Menu updated successfully!\n")
        } else {
            println("Invalid menu number!\n")
        }
    }

    private fun deleteMenu() {
        viewMenu()
        if (menuList.isEmpty()) return

        print("Select menu number to delete: ")
        val index = try { scanner.nextLine().toInt() - 1 } catch (e: Exception) { -1 }

        if (index in menuList.indices) {
            val removed = menuList.removeAt(index)
            println("Menu '${removed.name}' deleted successfully!\n")
        } else {
            println("Invalid menu number!\n")
        }
    }
}