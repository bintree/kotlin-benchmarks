package org.jetbrains

import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations.Benchmark
import java.util.ArrayList
import org.openjdk.jmh.infra.Blackhole

BenchmarkMode(Mode.AverageTime)
OutputTimeUnit(TimeUnit.NANOSECONDS)
open class ClassBaselineBenchmark : SizedBenchmark() {

    Benchmark fun consume(bh: Blackhole) {
        for (item in 1..size) {
            bh.consume(Value(item))
        }
    }

    Benchmark fun allocateList(): List<Value> {
        val list = ArrayList<Value>(size)
        return list
    }

    Benchmark fun allocateArray(): Array<Value?> {
        val list = Array<Value?>(size) { null }
        return list
    }

    Benchmark fun allocateListAndFill(): List<Value> {
        val list = ArrayList<Value>(size)
        for (item in 1..size) {
            list.add(Value(item))
        }
        return list
    }

    Benchmark fun allocateListAndWrite(): List<Value> {
        val value = Value(0)
        val list = ArrayList<Value>(size)
        for (item in 1..size) {
            list.add(value)
        }
        return list
    }

    Benchmark fun allocateArrayAndFill(): Array<Value> {
        val list = Array(size) { Value(it) }
        return list
    }
}