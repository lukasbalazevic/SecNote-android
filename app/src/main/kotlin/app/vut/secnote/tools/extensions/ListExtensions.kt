package app.vut.secnote.tools.extensions

fun <T> List<T>.second(): T {
    if (isEmpty() || size < 2)
        throw NoSuchElementException("List is empty or don`t have 2 elements.")
    return this[1]
}
