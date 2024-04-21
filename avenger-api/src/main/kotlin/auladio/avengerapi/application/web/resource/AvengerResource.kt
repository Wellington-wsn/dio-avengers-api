package auladio.avengerapi.application.web.resource

import auladio.avengerapi.application.web.resource.request.AvengerRequest
import auladio.avengerapi.application.web.resource.response.AvengerResponse
import auladio.avengerapi.doman.avenger.AvengerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

private const val API_PATH = "/v1/api/avenger"

@RestController
@RequestMapping(value = [API_PATH])
class AvengerResource(
    @Autowired private val repository: AvengerRepository
){

    @GetMapping // Mapeamento de uma requisição GET
    fun getAvengers() = repository.getAvengers()
        .map { AvengerResponse.from(it)}
        .let {
            ResponseEntity.ok().body(it)

        }

    @GetMapping("{id}/detail") // Mapeamento de uma requisição por 'id'
    fun getAvengersDetails(@PathVariable("id") id: Long) =
        repository.getDetail(id).let {
            ResponseEntity.ok().body(AvengerResponse.from(it))
        } ?: ResponseEntity.notFound().build<Void>()

    @PostMapping // Posta retorna uma URI para levar o cliente para detalhe (reuqest, tem que ser validado(@valid) de acordo com Avengerequest) parseado do Json para o objeto da classe
    fun createAvenger(@Valid @RequestBody request: AvengerRequest) =
        request.toAvenger().run {
            repository.create(this)
        }.let {
            ResponseEntity
                .created(URI("$API_PATH/${it.id}"))
                .body(AvengerResponse.from(it))
        }

    @PutMapping("{id}")
    fun updateAvenger(@Valid @RequestBody request: AvengerRequest, @PathVariable("id") id: Long) =
        repository.getDetail(id).let {
            AvengerRequest.to(it.id, request).apply {
                repository.update(this)
            }.let { avenger ->
                ResponseEntity.ok().body(AvengerResponse.from(avenger))
            }
        } ?: ResponseEntity.notFound().build<Void>()

    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        repository.delete(id).let {
            ResponseEntity.accepted().build<Void>()
        }
}