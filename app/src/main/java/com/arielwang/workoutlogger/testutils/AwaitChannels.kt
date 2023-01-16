package com.arielwang.workoutlogger.testutils

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.typeOf

/**
 * A collection of named [AwaitChannel] instances.
 *
 * Built to be used with [AwaitChannelsRule].
 */
class AwaitChannels {
    val channels: MutableMap<String, AwaitChannel<*>> = LinkedHashMap()

    inline fun <reified T> create(): AwaitChannel<T> = create(description<T>())

    fun <T> create(name: String): AwaitChannel<T> {
        if (name in channels) {
            error("Channel named $name already created")
        }
        val channel = AwaitChannel<T>(name = name)

        channels[name] = channel

        return channel
    }

    fun assertEmpty(ruleName: String) {
        channels.forEach { (channelName, channel) ->
            check(channel.isEmpty) {
                "Unconsumed events found in $ruleName -> $channelName: ${channel.takeCurrentContents()}"
            }
        }
    }
}

inline fun <reified T> description(): String {
    val type = typeOf<T>()
    return description(T::class.qualifiedName!!, type)
}

fun description(type: KType?) = description(baseDescription(type), type)

fun description(baseDescription: String, type: KType?): String {
    if (type == null) {
        return baseDescription
    }
    val typeArguments = type.arguments
    val typeParamsList = typeArguments.map { description(it.type) }.joinToString(",")
    return baseDescription + if (typeArguments.isEmpty()) "" else "<$typeParamsList>"
}

private fun baseDescription(type: KType?) = when (val classifier = type?.classifier) {
    is KClass<*> -> classifier.qualifiedName!!
    is KTypeParameter -> classifier.name
    null -> "*"
    else -> classifier.toString()
}
