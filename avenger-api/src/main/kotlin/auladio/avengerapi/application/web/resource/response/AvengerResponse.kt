package auladio.avengerapi.application.web.resource.response

import auladio.avengerapi.doman.avenger.Avenger

data class AvengerResponse( // requisição de resposta
    //propriedades:1
    val id: Long?,
    val nick: String,
    val person: String,
    val description: String? = "",
    val history: String? = ""
) {
    companion object {
        fun from(avenger: Avenger) = AvengerResponse(
            id = avenger.id,
            nick = avenger.nick,
            person = avenger.person,
            description = avenger.description,
            history = avenger.history
        )
    }
}
