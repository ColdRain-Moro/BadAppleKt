package kim.bifrost.rain.badapple

import kotlinx.coroutines.*
import java.io.File
import java.net.URLDecoder
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * kim.bifrost.rain.badapple.BadApple
 * BadAppleKt
 *
 * @author 寒雨
 * @since 2022/1/12 9:59
 **/
fun main() {
    runBlocking {
        println("输入任意键以开始")
        val scan = Scanner(System.`in`)
        scan.nextLine()
        scan.close()
        repeat(48) { println() }
        val source = File("source.txt")
        val jobs = arrayListOf<Job>()
        if (!source.exists()) {
            println("未在当前路径下找到资源文件")
        } else {
            val currentFrame = arrayListOf<String>()
            source.readLines().forEach { s ->
                if (s.isEmpty()) {
                    return@forEach
                }
                currentFrame += s
                if (currentFrame.size == 48) {
                    val frame = currentFrame.clone() as List<*>
                    val loc = jobs.size
                    currentFrame.clear()
                    jobs += launch(start = CoroutineStart.LAZY) {
                        delay(TimeUnit.SECONDS.toMillis(1) / 24 * loc)
                        frame.forEach {
                            println(it)
                        }
                    }
                }
            }
            jobs.forEach { it.start() }
        }
    }
}

fun String.urlDecode(): String = URLDecoder.decode(this, "utf-8")
