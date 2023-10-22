import kotlin.math.pow

class Coordinates(var x: Double, var y: Double) {

    public fun pointToPoinLength(B:Coordinates): Double {
        return Math.sqrt((x - B.x).toDouble().pow(2)+(y-B.y).toDouble().pow(2))
    }
}