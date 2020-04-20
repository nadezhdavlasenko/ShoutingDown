import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val coordinates = arrayListOf<Pair<Double, Double>>()
    val n = Integer.parseInt(br.readLine())
    repeat(n) {
        val arr = br.readLine().split(" ")
        coordinates.add(arr[0].toDouble() to arr[1].toDouble())
    }
    val graph = Array(coordinates.size) { DoubleArray(coordinates.size) }

    coordinates.forEachIndexed { i, point ->
        (i until coordinates.size).forEach { j ->
            graph[i][j] =
                sqrt(
                    (abs(point.first - coordinates[j].first)).pow(2)
                            + (abs(point.second - coordinates[j].second)).pow(2)
                )
            graph[j][i] = graph[i][j]
        }
    }
// TODO
//    graph.forEach {
//        it.forEach { print(" $it") }
//        println()
//    }

    println(dijkstra(graph, 0).max())


}

fun dijkstra(graph: Array<DoubleArray>, src: Int): DoubleArray {
    val V = graph.size
    val dist = DoubleArray(V)
    val processed = BooleanArray(V)
    for (i in 0 until V) {
        dist[i] = Double.MAX_VALUE
        processed[i] = false
    }
    dist[src] = 0.0
    for (count in 0 until V - 1) {
        val u: Int = minDistance(dist, processed)
        processed[u] = true
        for (v in 0 until V)
            if (!processed[v] && dist[u] != Double.MAX_VALUE && graph[u][v] < dist[v])
                dist[v] = graph[u][v]
    }
// TODO   printSolution(dist)
    return dist
}

fun minDistance(dist: DoubleArray, sptSet: BooleanArray): Int {
    var min = Double.MAX_VALUE
    var min_index = -1
    for (v in dist.indices) if (!sptSet[v] && dist[v] <= min) {
        min = dist[v]
        min_index = v
    }
    return min_index
}

fun printSolution(dist: DoubleArray) {
    println("Vertex \t\t Distance from Source")
    for (i in 0 until dist.size) println(i.toString() + " \t\t " + dist.get(i))
}
