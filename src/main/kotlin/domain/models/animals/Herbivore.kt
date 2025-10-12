package domain.models.animals

/**
 * Абстрактный класс травоядного
 */
abstract class Herbivore: Animal() {
    abstract val kindness: Int
}