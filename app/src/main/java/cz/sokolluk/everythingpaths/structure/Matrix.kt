package cz.sokolluk.everythingpaths.structure


class Matrix<T>(val width: Int, val height: Int, defaultValue: T) {
    private var data: Array<Array<MatrixData>>? = null

    inner class MatrixData(var data: T)

    init {
        data = Array(height) { Array(width) { MatrixData(defaultValue) } }
    }

    fun get(i: Int): Array<MatrixData> {
        return data?.get(i) ?: throw DataOverflowException()
    }

    fun get(i: Int, j: Int): MatrixData {
        return get(i)[j]
    }

    fun set(i: Int, j: Int, newVal: T) {
        data?.let {
            it[i][j].data = newVal
        }
    }

    fun forEach(f: (T) -> Unit) {
        data?.forEach { row ->
            row.forEach { matrixData ->
                f(matrixData.data)
            }
        }
    }

    fun forEachIndexed(f: (Int, Int, T) -> Unit) {
        data?.forEachIndexed { i, row ->
            row.forEachIndexed { j, matrixData ->
                f(i, j, matrixData.data)
            }
        }
    }

    class DataOverflowException: Exception()
}