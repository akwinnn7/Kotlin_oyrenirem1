package com.example.lib

class Products(val name: String, var price: Double, var stock: Int){
    //Amazing
    fun isAvailable(): Boolean{
        return stock > 0
    }

    //Well

    fun sell(amount: Int): Boolean{
        return when {
            (amount <= 0)-> {
                println("Invalid amount")
                false
            }
            (stock == 0)-> {
                println("$name is out of stock")
                false
            }
            (amount > stock)-> {
                println("Only $stock $name left")
                false
            }
            else-> {
                val total = amount * price
                stock -= amount
                println("Sold $amount × $name for $total AZN")
                true
            }
        }
    }
}
class Shop(var products: Array<Products>, var revenue: Double = 0.0){
    fun findProduct(name: String): Products?{
        for (product in products){
            if (product.name.equals(name, ignoreCase = true)) { // AI yazdi  Böyük/kiçik hərflərə həssas olmadan ad müqayisəsi //aferin
                return product
            }
        }
        return null
    }
    fun processOrder(name: String, amount: Int){
        var products = findProduct(name)
        if(products==null){
            println( "Product not found: $name")
        }
        else{
            if (products.sell(amount)){
                revenue= revenue + products.price*amount // gelir money
            }
        }
    }
    fun printInventory(){
        var count = 0
        for (product in products){
            if(product.stock>0){
                println("$count. ${product.name} - Price: ${product.price}, Stock: ${product.stock}")
                count ++
            }
            else{
                println()
                count ++
            }
        }
    }
    fun applyDiscount(percent: Int, condition: (Products) -> Boolean){
        if(percent>1 && percent<90){
            println("Error: Discount of $percent% is invalid")
            return
        }
        for (product in products){
            if (condition(product)){
                product.price= product.price - product.price *(percent/100)
                println(percent)
            }
        }
    }
    fun total(selector: (Products) -> Double): Double {
        var cem = 0.0
        for (product in products){
            cem = cem + selector(product)
        }
        return cem
    }
    fun restock(threshold: Int, amount: Int) {
        var restockedCount = 0
        for (product in products) {
            if (product.stock < threshold) {
                product.stock += amount
                println("Restocked ${product.name}: added $amount units. New stock: ${product.stock}")
                restockedCount++
            }
        }
        if (restockedCount == 0) {
            println("Nothing to restock")
        }
    }
}
fun main(){
    val products = arrayOf(
        Products("Diz ustu komp", 1200.0, 5),
        Products("Mouse", 25.0, 2),
        Products("Klavyatura", 75.0, 10),
        Products("Monitor", 300.0, 4),
        Products("Type c kabel", 50.0, 0),
        Products("Sistem bloku", 10.0, 15)
    )
    val shop = Shop(products)
    val orderNames = arrayOf(
        "Laptop",       // Normal valid order
        "Tablet",       // Unknown product
        "Mouse",        // Amount of 0
        "Monitor",      // Amount larger than stock (stock is 4, requests 5)
        "Mouse",        // Sells product out completely (stock is 2, requests 2)
        "Mouse",        // Order for that same product after it is sold out
        "Keyboard",     // Normal valid order
        "USB Cable"     // Normal valid order
    )
    val orderAmounts = intArrayOf(2, 1, 0, 5, 2, 1, 3, 5)
    for (i in orderNames.indices){
        println("Processing order for '${orderNames[i]}' (Amount: ${orderAmounts[i]}")
        shop.processOrder(orderNames[i], orderAmounts[i])
    }
    shop.printInventory()
    println()
    println("=== Applying Discounts ===")
    shop.applyDiscount(20) { it.stock < 5 }
    shop.applyDiscount(10) { it.price > 1000 }
    shop.applyDiscount(95) { true }
    println()
    shop.printInventory()
    println()
    println("=== Financial & Stock Summary ===")
    println("Total Revenue: ${shop.revenue} AZN")
    val totalInventoryValue = shop.total { it.price * it.stock }
    val totalUnitsInStock = shop.total { it.stock.toDouble() }
    println("Total Inventory Value: $totalInventoryValue AZN")
    println("Total Units in Stock: $totalUnitsInStock")
    println()
    println("=== Analytics ===")
    val outOfStockNames = shop.products.filter { !it.isAvailable() }.map { it.name }
    println("Out-of-stock products: $outOfStockNames")
    val mostExpensive = shop.products.maxByOrNull { it.price }
    println("Most expensive product: ${mostExpensive?.name} (${mostExpensive?.price} AZN)")
}