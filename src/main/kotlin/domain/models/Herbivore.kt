package domain.models

/**
 * Абстрактный класс травоядного
 */
abstract class Herbivore: Animal() {
    abstract val kindness: Int
}