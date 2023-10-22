import kotlin.random.Random

class Triangle {
    private var A: Coordinates
    private var B: Coordinates
    private var C: Coordinates
    private var lenghtAtoB: Double = 0.0
    private var lenghtBtoC: Double = 0.0
    private var lenghtCtoA: Double = 0.0

    constructor(A: Coordinates, B: Coordinates, C: Coordinates) {
        if (arePointsCollinear(A, B, C)) {
            throw IllegalArgumentException("Vertices must not be collinear")
        }
        this.A = A
        this.B = B
        this.C = C
        updateLenght()
    }

    companion object {
        fun randomTriangle(): Triangle {
            val randomX = { Random.nextDouble(0.0, 100.0) }
            val randomY = { Random.nextDouble(0.0, 100.0) }

            val vertexA = Coordinates(randomX(), randomY())
            val vertexB = Coordinates(randomX(), randomY())
            val vertexC = Coordinates(randomX(), randomY())

            return Triangle(vertexA, vertexB, vertexC)
        }
    }


    fun areaOfTheTriangle(): Double {
        val s = (lenghtAtoB + lenghtBtoC + lenghtCtoA) / 2.0
        return Math.sqrt(s * (s - lenghtAtoB) * (s - lenghtBtoC) * (s - lenghtCtoA))
    }

    fun print() {

        if (lenghtAtoB == lenghtBtoC || lenghtBtoC == lenghtCtoA || lenghtCtoA == lenghtAtoB) {
            print("Трейгольник равнобедренный ")
        } else if (lenghtAtoB == lenghtBtoC && lenghtBtoC == lenghtCtoA && lenghtCtoA == lenghtAtoB) {
            print("Трейгольник равносторонний")
        } else {
            print("Трейгольник разностороний")
        }

        println(" периметр  = ${perimeterOfTheTriangle()}см , площадь ${areaOfTheTriangle()} см^2")


    }

    fun perimeterOfTheTriangle(): Double {
        // Суммируем длины сторон, чтобы найти периметр
        val perimeter = lenghtAtoB + lenghtBtoC + lenghtCtoA
        return perimeter
    }


    private fun arePointsCollinear(A: Coordinates, B: Coordinates, C: Coordinates): Boolean {
        val AB = Coordinates(B.x - A.x, B.y - A.y)
        val AC = Coordinates(C.x - A.x, C.y - A.y)

        val crossProduct = AB.x * AC.y - AB.y * AC.x


        return crossProduct.toDouble() == 0.0
    }

    private fun updateLenght() {
        lenghtAtoB = A.pointToPoinLength(B)
        lenghtBtoC = B.pointToPoinLength(C)
        lenghtCtoA = C.pointToPoinLength(A)
    }

    fun rotate(degrees: Double) {
        val centerX = (A.x + B.x + C.x) / 3.0
        val centerY = (A.y + B.y + C.y) / 3.0

        val radians = Math.toRadians(degrees)
        val cosTheta = Math.cos(radians)
        val sinTheta = Math.sin(radians)

        val tempAX = (A.x - centerX) * cosTheta - (A.y - centerY) * sinTheta + centerX
        val tempAY = (A.x - centerX) * sinTheta + (A.y - centerY) * cosTheta + centerY
        val tempBX = (B.x - centerX) * cosTheta - (B.y - centerY) * sinTheta + centerX
        val tempBY = (B.x - centerX) * sinTheta + (B.y - centerY) * cosTheta + centerY
        val tempCX = (C.x - centerX) * cosTheta - (C.y - centerY) * sinTheta + centerX
        val tempCY = (C.x - centerX) * sinTheta + (C.y - centerY) * cosTheta + centerY

        A = Coordinates(tempAX, tempAY)
        B = Coordinates(tempBX, tempBY)
        C = Coordinates(tempCX, tempCY)
    }
}