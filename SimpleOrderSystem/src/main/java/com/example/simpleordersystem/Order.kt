package com.example.simpleordersystem

class Order (val namaPembeli: String){
    private val daftarPesanan = ArrayList<OrderItem>()

    fun tambahItem(menu: Menu, quantity: Int){
        val orderItem = OrderItem(menu, quantity)
        daftarPesanan.add(orderItem)
    }

    fun hitungTotal(): Double{
        return daftarPesanan.sumOf { it.subtotal }
    }

    fun printReceipt() {
        println("\n--- ${namaPembeli}'s ORDER ---")
        daftarPesanan.forEachIndexed { index, orderItem ->
            println("${index + 1}. ${orderItem.menu.name} x${orderItem.quantity} $${orderItem.subtotal}")
        }
        println("--------------------------------")
        println("TOTAL $${hitungTotal()}\n")
    }
}