package com.example.lib
import kotlin.random.Random
fun main() {
    val cedvel = arrayOf(
        charArrayOf('1', '2', '3'),
        charArrayOf('4', '5', '6'),
        charArrayOf('7', '8', '9')
    )

    println("Sisteme qarsi oynamaq isteyirsizse 1 duymesini sixin, Dostunuzla oynamaq isteyirsizse 2 duymesini sixin:")
    val secimDuymesi = readlnOrNull()?.toIntOrNull()

    if (secimDuymesi != 1 && secimDuymesi != 2) {
        println("Siz duzgun secim etmediniz.")
        return
    }
    var Oyuncu1 = 'X'
    var hereketlerinSayi = 0
    var oyunBitdi = false
    while (!oyunBitdi) {
        printBoard(cedvel)
        if (secimDuymesi == 1 && Oyuncu1 == 'O') {
            println("Kompüterin gedişi...")
            makeComputerMove(cedvel)
        } else {
            println("Oyuncu $Oyuncu1, gedis etmek istediyiniz xana nomresini daxil edin (1-9):")
            val input = readlnOrNull()?.toIntOrNull()
            if (input == null || input !in 1..9 || !makeMove(cedvel, input, Oyuncu1)) {
                println("Yanlis gedis! Xana ya tutulub, ya da duzgun reqem daxil edilmeyib. Yeniden cehd edin.")
                continue
            }
        }
        hereketlerinSayi++
        if (checkWin(cedvel, Oyuncu1)) {
            printBoard(cedvel)
            if (secimDuymesi == 1 && Oyuncu1 == 'O') {
                println("Komputer qazandi!")
            } else {
                println("Tebrikler! Oyuncu $Oyuncu1 qazandi!")
            }
            oyunBitdi = true
        } else if (hereketlerinSayi == 9) { // limit doldu ve oyun bitdi
            printBoard(cedvel)
            println("Oyun berabere bitdi!")
            oyunBitdi = true
        } else {
            Oyuncu1 = if (Oyuncu1 == 'X') 'O' else 'X' // növbələri dəyisir
        }
    }
}
fun printBoard(board: Array<CharArray>) { // cedvelin qurulusu
    println("\n-------------")
    for (row in board) { // setir aralarina | qoyur
        print("| ")
        for (cell in row) { //
            print("$cell | ")
        }
        println("\n-------------")
    }
}
fun makeMove(board: Array<CharArray>, position: Int, symbol: Char): Boolean {
    val row = (position - 1) / 3 //setir
    val col = (position - 1) % 3 //sutun

    if (board[row][col] != 'X' && board[row][col] != 'O') {
        board[row][col] = symbol // xananin bos olub olmamasin yoxlayir
        return true
    }
    return false
}
fun makeComputerMove(board: Array<CharArray>) {
    val emptyPositions = mutableListOf<Int>()
    var pos = 1
    for (r in 0..2) {
        for (c in 0..2) {
            if (board[r][c] != 'X' && board[r][c] != 'O') {
                emptyPositions.add(pos)
            }
            pos++
        }
    }
    if (emptyPositions.isNotEmpty()) {
        val tesadufiHereket = emptyPositions[Random.nextInt(emptyPositions.size)]
        makeMove(board, tesadufiHereket, 'O')
    }
}
fun checkWin(cedvel: Array<CharArray>, oyuncu: Char): Boolean {
    for (i in 0..2) {
        if ((cedvel[i][0] == oyuncu && cedvel[i][1] == oyuncu && cedvel[i][2] == oyuncu) ||
            (cedvel[0][i] == oyuncu && cedvel[1][i] == oyuncu && cedvel[2][i] == oyuncu)
        ) // setirler ve sutunlari yoxlayir
        {
            return true
        }
    }
    if ((cedvel[0][0] == oyuncu && cedvel[1][1] == oyuncu && cedvel[2][2] == oyuncu) ||
        (cedvel[0][2] == oyuncu && cedvel[1][1] == oyuncu && cedvel[2][0] == oyuncu)
    )  // diaqonallari yoxlayir
    {
        return true
    }
    return false
}