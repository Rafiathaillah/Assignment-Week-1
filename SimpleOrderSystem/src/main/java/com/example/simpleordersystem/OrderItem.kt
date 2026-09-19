package com.example.simpleordersystem

class OrderItem (val menu: Menu, val quantity: Int){
    val subtotal: Double get() = menu.price * quantity
}