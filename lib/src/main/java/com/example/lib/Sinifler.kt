class Student(val name: String, val scores: IntArray) {

    fun average(): Double {
        var say = 0
        var cem = 0.0
        for (qiymetler in scores) {
            cem += qiymetler
            say++
        }
        return if (say > 0) cem / say else 0.0
    }

    fun highestScore(): Int {
        var max = scores[0]
        for (enYuksek in scores) {
            if (enYuksek > max) {
                max = enYuksek
            }
        }
        return max
    }

    fun letterGrade(): String {
        val avg = average()
        val netice: String
        if (avg >= 90) {
            netice = "A"
        } else if (avg >= 80) {
            netice = "B"
        } else if (avg >= 70) {
            netice = "C"
        } else if (avg >= 60) {
            netice = "D"
        } else {
            netice = "E"
        }
        return netice
    }

    fun isPassed(): Boolean {
        val avg = average()
        if (avg < 60) return false
        for (score in scores) {
            if (score < 40) return false
        }
        return true
    } // deyisiklik
    //fsdufisbdhdf
}
fun main(){
    var students = arrayOf(
        Student("Men", intArrayOf(79,54,88,83,90)),
        Student("Sen", intArrayOf(90,85,80,75,100)),
        Student("O", intArrayOf(41,34,77,65,66)),
        Student("Biz", intArrayOf(100,49,99,95,99)),
        Student("Siz", intArrayOf(66,54,100,93,90))
    )
    printReport(students)
    var kecenlerinSayi = countStudents(students) {
        it.isPassed()
    }
    var  aSaylari = countStudents(students) {
        it.letterGrade() == "A"
    }
    var tamAlanlar = countStudents(students) { student ->
        var found = false
        for (score in student.scores){
            if(score==100){
                found = true
                break
            }
        }
        found
    }
    var enYaxsiTelebe = findBest(students)
    if (enYaxsiTelebe != null) {
        println("En yaxsi telebe: ${enYaxsiTelebe.name}")
    } else {
        println("Haminiz fehle olacaqsiz >_<")
    }
    val KesilenTelebeler = students.filter { !it.isPassed() }.map { it.name }
    println("Kesilen telebeler: $KesilenTelebeler")

    println("\nTelebelerin ortlamaya gore siralamasi:")
    val telebeSiralamasi = students.sortedByDescending { it.average() }
    for (student in telebeSiralamasi) {
        val avgFormatted = String.format("%.1f", student.average())
        println("${student.name} - Orta bal: $avgFormatted")
    }
}
    fun printReport(students : Array<Student>){
        for (student in students){
            val status = if (student.isPassed())"Passed" else "Failed"
            val formattedAvg = String.format("%.1f", student.average())
            println("Ad: ${student.name} Orta: $formattedAvg En yuksek: ${student.highestScore()} Qiymet: ${student.letterGrade()} Status: $status")
        }
        }
    fun countStudents(students: Array<Student>, condition: (Student) -> Boolean): Int {
        var count = 0
        for (student in students){
            if (condition(student)){
                count++
            }
        }
        return count
    }
    fun findBest(students: Array<Student>): Student?{
        if (students.isEmpty()) return null
        var enYaxsiTelebe = students[0]
        for (student in students){
            if (student.average()>enYaxsiTelebe.average()) {
                enYaxsiTelebe=student
            }
        }
        return enYaxsiTelebe
    }
