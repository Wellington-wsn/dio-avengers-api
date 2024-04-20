package auladio.avengerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AvengerApiApplication

fun main(args: Array<String>) {
	runApplication<AvengerApiApplication>(*args)
}
