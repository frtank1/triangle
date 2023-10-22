fun main(args: Array<String>) {
    val triangle = Triangle.randomTriangle()

    triangle.print()
    triangle.rotate(30.0)
    triangle.print()
}