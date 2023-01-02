package io.github.markgregg.expression.operations.relational

import io.github.markgregg.expression.Context
import io.github.markgregg.expression.exceptions.IncompatibleTypeException
import io.github.markgregg.expression.operations.BinaryOperation
import io.github.markgregg.expression.operations.Operation
import io.github.markgregg.expression.operations.Util.castToType
import java.time.LocalDate
import java.time.LocalDateTime

class NotEqualsOperation(
    leftOperation: Operation,
    rightOperation: Operation
) : BinaryOperation(leftOperation, rightOperation)  {
    override fun execute(context: Context): Any =
        areNotEquals(leftOperation.execute(context), rightOperation.execute(context))

    private fun areNotEquals(value1: Any, value2: Any): Boolean =
        when(value1) {
            is String -> {
                value1 != (castToType(value2, String::class.java) as String)
            }
            is Long -> {
                value1 != (castToType(value2, Long::class.java) as Long)
            }
            is Double -> {
                value1 != (castToType(value2, Double::class.java) as Double)
            }
            is Boolean -> {
                value1 != (castToType(value2, Boolean::class.java) as Boolean)
            }
            is LocalDate -> {
                value1 != (castToType(value2, LocalDate::class.java) as LocalDate)
            }
            is LocalDateTime -> {
                value1 != (castToType(value2, LocalDateTime::class.java) as LocalDateTime)
            }
            else -> {
                throw IncompatibleTypeException("${value1.javaClass.name} cannot be compared")
            }
        }
}