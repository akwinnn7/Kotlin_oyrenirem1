package com.example.lib

import kotlin.random.Random
fun artan() {
    val array = arrayOf(45, 12, 3, 89, 23, 7)
        for(i in 0 until array.size-1){
            for(j in 0 until array.size-1){
                if(array[j]>array[j+1]){
                    val a = array[j]
                    array[j]=array[j+1]
                    array[j+1]=a
                }
            }
        }
        for (i in 0..array.size-1) {
        print("${array[i]}, ")
    }
    }
fun main() {
    artan()
}

//fun azalan() {
//    val array = arrayOf(45, 12, 3, 89, 23, 7)
//    for (i in 0 until array.size - 1) {
//        for (j in 0 until array.size - 1) {
//            if (array[j] < array[j + 1]) {
//                val a = array[j]
//                array[j] = array[j + 1]
//                array[j + 1] = a
//            }
//        }
//    }
//    for (i in 0..array.size-1) {
//        print("${array[i]}, ")
//    }
//}
//
//fun main() {
//    azalan()
//}

